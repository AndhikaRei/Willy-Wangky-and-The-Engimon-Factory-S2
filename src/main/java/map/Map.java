package main.java.map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import main.java.exception.*;
import main.java.engimon.*;
import main.java.engimon.species.*;
import main.java.skill.*;

public class Map {
    private int length; // Map Length
    private int width; // Map Width
    private ArrayList<ArrayList<Mapelem>> mapelem ; // Array of MapElem
    private ArrayList<Integer> player_pos; // Array of Integer with size 2 for Player Position
    private ArrayList<Integer> active_engimon_pos; // Array of Integer with size 2 for Active Engimon Position
    private String active_engimon_species;  // Current Active Engimon Species
    private int total_engimon; // Total Wild Engimon that exist on Map
    private final int max_engimon = 20; // Maximum Engimon Exist on Map
    // Panggil modul srand untuk mereset angka yang dirandom
    private Random rand;

    public Map(){
        this.length = 0;
        this.width = 0;
        this.total_engimon = 0;
    };
    public ArrayList<ArrayList<Mapelem>> getMapElem(){
        return this.mapelem;
    }

    // ctor
    public Map(int m, int n, String txt) throws Exception{
        // I.S. m dan n terdefinisi untuk length dan width peta
        int i, j;
        this.length = m;
        this.width = n;
        // Inisialisasi Array of MapElem
        this.mapelem =  new ArrayList<ArrayList<Mapelem>>(this.width);
        for(i = 0; i<width; i++){
            mapelem.add(new ArrayList<Mapelem>(this.length));
        }
        Engidex.initEngidex();
        Skidex.initSkill();
        // Read file txt per character
        // File txt only contains basic map symbol 'o' for sea and '-' for grassland
        // ifstream infile(txt);
        i = 0;
        j = 0;
        // Set x, y, symbol, and type for each MapElem
        String filename = "src\\main\\resources\\".concat(txt);
        File file=new File(filename);
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()){
            String currLine = sc.nextLine();
            for(j=0; j<currLine.length(); j++){
                if(currLine.charAt(j) != '\n'){
                    if(currLine.charAt(j) == '^'){
                        mapelem.get(i).add(new Mapelem(i, j, '^', false, new Pyro("Default", true), "mountains"));
                    }else if(currLine.charAt(j) == 'o'){
                        mapelem.get(i).add(new Mapelem(i, j, 'o', false, new Pyro("Default", true), "sea"));
                    }else if(currLine.charAt(j) == '*'){
                        mapelem.get(i).add(new Mapelem(i, j, '*', false, new Pyro("Default", true), "tundra"));
                    }else{
                        mapelem.get(i).add(new Mapelem(i, j, '-', false, new Pyro("Default", true), "grassland"));
                    }
                }
            }
            i++;
        }
        // Initialize Player Position
        this.player_pos = new ArrayList<Integer>(2);
        this.player_pos.add(0);
        this.player_pos.add(1);
        // Initialize Active Engimon Position
        this.active_engimon_pos = new ArrayList<Integer>(2);
        this.active_engimon_pos.add(0);
        this.active_engimon_pos.add(0);
        // Initialize default active engimon species
        this.active_engimon_species = "Pyro";
        this.mapelem.get(player_pos.get(0)).get(player_pos.get(1)).set_symbol('P');
        this.mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).set_symbol('X');
        // set total wild engimon on map = 0 at the beginning
        this.total_engimon = 0;
        this.rand = new Random();
    };

    // Checking if a position is valid for a pokemon based on the world boundaries and current symbol on the map
    public Boolean isValidPosition(int x, int y, Boolean isActive){
        // I.S. Koordinat x dan y terdefinisi, isActive terdefinisi (false untuk wild engimon dan true untuk active engimon)
        // F.S. Mengembalikan true jika posisi valid, false jika tidak
        if(isActive){
            return ((x<this.width && x>=0 && y >=0 && y<this.length));
        } else{
            return ((x<this.width && x>=0 && y >=0 && y<this.length) && (this.mapelem.get(x).get(y).get_symbol()=='-' || this.mapelem.get(x).get(y).get_symbol()=='o' || this.mapelem.get(x).get(y).get_symbol()=='^' || this.mapelem.get(x).get(y).get_symbol()=='*'));
        }
        
    };

    public Boolean isValidEngimonPosition(int x, int y, String species, Boolean isActive){
        // I.S. Koordinat x dan y terdefinisi, jenis species engimon, isActive terdefinisi (false untuk wild engimon dan true untuk active engimon)
        // F.S. Mengembalikan true jika posisi valid, false jika tidak
        
        // Cek apakah ada engimon di tiles tersebut dan apakah x,y adalah posisi yang valid
        if(!this.mapelem.get(x).get(y).isEngimonExist() && isValidPosition(x, y, isActive)){
            // Cek kesesuaian species engimon dengan tipe tiles x,y
            if(this.mapelem.get(x).get(y).get_type() == "mountains"){
                return(species == "Pyro" || species == "Vaporize" || species == "Overload" || species == "PyroCrystallize" || species == "Melt");
            } else if(this.mapelem.get(x).get(y).get_type() == "sea"){
                return(species == "Hydro" || species == "Vaporize" || species == "ElectroCharged" || species == "Hydrocrystallize" || species == "Frozen");
            } else if(this.mapelem.get(x).get(y).get_type() == "grassland"){
                return(species == "Electro" || species == "Geo" || species == "Overload" || species == "PyroCrystallize" || species == "ElectroCharged" 
                || species == "HydroCrystallize" || species == "ElectroCrystallize" || species == "Superconductor" || species == "CryoCrystallize");
            } else {
                // if(this.mapelem.get(x).get(y).get_type() == "tundra")
                return(species == "Cryo" || species == "Melt" || species == "Frozen" || species == "Superconductor" || species == "CryoCrystallize");
            } 
        }
        return false;
    };

    // Check if there is any active engimon for player
    public Boolean isAnyActiveEngimon() {
        // I.S. Map terdefinisi
        // F.S. Mengembalikan true jika species selain undefined
        return this.active_engimon_species != "undefined";
    };

    // Check if x, y is position of player
    public Boolean isPlayerPosition(int x, int y) {
        // I.S. Map, posisi player, dan x, y terdefinisi
        // F.S. Mengembalikan true jika
        return (this.player_pos.get(0)==x) && (this.player_pos.get(1)==y);
    };

    public int get_total_engimon(){
        // I.S. Map terdefinisi
        // F.S. Mengembalikan jumlah wild engimon yang ada di Map
        return this.total_engimon;
    };

    public int get_max_engimon() {
        // I.S. Map terdefinisi
        // F.S. Mengembalikan jumlah maksimal wild engimon yang ada di Map
        return this.max_engimon;
    };

    // Map Manipulation Functions
    public void printMap(){
        // I.S. Map dengan setiap MapElem terdefinisi
        // F.S. Map ditampilkan ke layar dengan symbol yang merepresentasikan Tipe Tiles/ Engimon/ Active Engimon/ Player

        // Update Map untuk mendapatkan symbol terbaru untuk setiap tiles
        this.updateMap();
        for(int i = 0; i<width; i++){
            // Print blank spaces
            System.out.print("                                ");
            for(int j= 0; j<length ; j++){
                // Print symbol per tiles
                System.out.print(this.mapelem.get(i).get(j).get_symbol());
            }
            // Print newline
            System.out.println();
        }
    };

    public void updateMap(){
        // I.S. Map terdefinisi
        // F.S. Symbol untuk setiap tiles telah diubah ke kondisi terbaru
        
        // Cek posisi engimon liar
        for(int i = 0; i<width; i++){
            for(int j= 0; j<length ; j++){
                if(this.mapelem.get(i).get(j).isEngimonExist() && !(this.player_pos.get(0) == i && this.player_pos.get(1) ==j)){
                    // this.mapelem.get(i).get(j).set_symbol(this.mapelem.get(i).get(j).get_engimon().getEngimonSymbol());
                    this.mapelem.get(i).get(j).set_symbol(this.mapelem.get(i).get(j).get_engimon().getEngimonSymbol());
                }
            }
        }
        // Cek posisi active engimon
        if(this.isAnyActiveEngimon()){
            this.mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).set_symbol('X');
        }else{
            if(mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).get_type().equals("grassland") && !mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).isEngimonExist()){
                mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).set_symbol('-');
            } else if(mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).get_type().equals("mountains")&& !mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).isEngimonExist()){
                mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).set_symbol('^');
            } else if(mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).get_type().equals("sea")&& !mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).isEngimonExist()){
                mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).set_symbol('o');
            } else if(mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).get_type().equals("tundra")&& !mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).isEngimonExist()){
                mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).set_symbol('*');
            }
        }
        // Cek posisi player
        this.mapelem.get(player_pos.get(0)).get(player_pos.get(1)).set_symbol('P');
    };

    public void saveMap(){
        // I.S. Map terdefinisi
        // F.S. File akan disimpan dalam txt
        this.updateMap();
        try {
            FileWriter myWriter = new FileWriter("src\\main\\resources\\saveMap.txt");
            String textToSave;
            StringBuilder sb = new StringBuilder();
            // Save map symbol
            for(int i = 0; i<width; i++){
                for(int j= 0; j<length ; j++){
                    sb.append(this.mapelem.get(i).get(j).get_symbol());
                }
                // Print newline
                sb.append('\n');
            }
            // Save length width
            sb.append(length).append(" ").append(width).append('\n');
            // Save player position
            sb.append("Player ").append(player_pos.get(0)).append(" ").append(player_pos.get(1)).append('\n');
            // Save active engimon position
            sb.append("\"").append(active_engimon_species).append("\"").append(" ").append(active_engimon_pos.get(0)).append(" ").append(active_engimon_pos.get(1)).append('\n');
            // Save total engimon
            sb.append("Total Engimon: ").append(total_engimon).append('\n');
            // Save mapelem that contain engimon
            HashMap<Integer, ArrayList<Integer>> wildEngimon = getWildEngimonPosition();
            for(Integer id : wildEngimon.keySet()){
                int i = wildEngimon.get(id).get(0);
                int j = wildEngimon.get(id).get(1);
                Engimon currEngimon = this.mapelem.get(i).get(j).get_engimon();
                sb.append(i).append(',').append(j).append(',')
                    .append("\"").append(currEngimon.getSpecies()).append("\"").append(",{");
                for(Skill skill : currEngimon.getSkill()){
                    sb.append(skill.getName()).append(",");
                }
                sb.append("},")
                    .append(currEngimon.getLevel()).append(',')
                    .append(currEngimon.getCumulExp()).append('\n');
            }
            textToSave = sb.toString();
            myWriter.write(textToSave);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // ctor
    public void loadMap(String txt) throws Exception{
        // I.S. m dan n terdefinisi untuk length dan width peta
        int i, j;
        // Read file txt per character
        // File txt only contains basic map symbol 'o' for sea and '-' for grassland
        // ifstream infile(txt);
        i = 0;
        j = 0;
        // Set x, y, symbol, and type for each MapElem
        String filename = "src\\main\\resources\\".concat(txt);
        File file=new File(filename);
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()){
            String currLine = sc.nextLine();
            if(i<10){
                for(j=0; j<currLine.length(); j++){
                    if(currLine.charAt(j) != '\n'){
                        mapelem.get(i).get(j).set_symbol(currLine.charAt(j));
                        if(currLine.charAt(j)!= 'P' && currLine.charAt(j)!= 'X' && currLine.charAt(j)!= '^'&& currLine.charAt(j)!= '-'&& currLine.charAt(j)!= 'o'&& currLine.charAt(j)!= '*'){
                            if(mapelem.get(i).get(j).get_type().equals("grassland")){
                                mapelem.get(i).get(j).set_symbol('-');
                            } else if(mapelem.get(i).get(j).get_type().equals("mountains")){
                                mapelem.get(i).get(j).set_symbol('^');
                            } else if(mapelem.get(i).get(j).get_type().equals("sea")){
                                mapelem.get(i).get(j).set_symbol('o');
                            } else{
                                mapelem.get(i).get(j).set_symbol('*');
                            }
                        }
                        mapelem.get(i).get(j).set_engimon_exist(false);
                    }
                }
            }else if(i==10){
                Scanner scanner = new Scanner(currLine);
                List<Integer> list = new ArrayList<Integer>();
                while (scanner.hasNextInt()) {
                    list.add(scanner.nextInt());
                }
                this.length = list.get(0);
                this.width = list.get(1);
            }else if(i==11){
                currLine = currLine.substring(7);
                Scanner scanner = new Scanner(currLine);
                this.player_pos = new ArrayList<Integer>(2);
                while (scanner.hasNextInt()) {
                    this.player_pos.add(scanner.nextInt());
                }
            }else if(i==12){
                this.active_engimon_species = currLine.substring(1, currLine.indexOf(" ")-1);
                this.active_engimon_pos = new ArrayList<Integer>(2);
                currLine = currLine.substring(currLine.indexOf(" ")+1);
                Scanner scanner = new Scanner(currLine);
                while (scanner.hasNextInt()) {
                    this.active_engimon_pos.add(scanner.nextInt());
                }
            }else if(i==13){
                currLine = currLine.substring(15);
                this.total_engimon = Integer.parseInt(currLine);
            }else{
                int x2= Integer.parseInt(currLine.substring(0, currLine.indexOf(",")));
                currLine = currLine.substring(currLine.indexOf(",")+1);
                int y2 = Integer.parseInt(currLine.substring(0, currLine.indexOf(",")));
                currLine = currLine.substring(currLine.indexOf(",")+2);
                String engiSpecies = currLine.substring(0, currLine.indexOf("\""));
                Engimon loadEngimon = Engidex.getEngimonBySpecies(engiSpecies).cloneEngimon();
                currLine = currLine.substring(currLine.indexOf(",")+1);
                currLine = currLine.substring(currLine.indexOf(",")+1);
                String extraSkillName = currLine.substring(0,currLine.indexOf(","));
                if(extraSkillName.equals("}")){
                    currLine = currLine.substring(currLine.indexOf(",")+1);
                }else{
                    Skill newSkill = Skidex.getSkillByName(extraSkillName);
                    loadEngimon.addSkill(newSkill);
                    currLine = currLine.substring(currLine.indexOf(",")+3);
                }
                int level = Integer.parseInt(currLine.substring(0, currLine.indexOf(",")));
                loadEngimon.setLevel(level);
                currLine = currLine.substring(currLine.indexOf(",")+1);
                int cumul_exp = Integer.parseInt(currLine);
                loadEngimon.setCumulExp(cumul_exp);
                try{
                    addEngimon(x2, y2, loadEngimon);
                } catch (Exception e){
                    System.out.println(e);
                }
            }
            i++;
        }
        System.out.println("Loaded map from saved.txt");
        printMap();
    };

    // // Engimon Handlers
    public HashMap<Integer, ArrayList<Integer>> getWildEngimonPosition(){
        // I.S. Map terdefinisi dan keberadaan engimon liar di Map juga terdefinisi
        // F.S. Mengembalikan sebuah tipe data map dengan key species dan value nya adalah 
        // array of interger yang merepresentasikan posisi engimon liar pada map
        
        // Inisialisasi map untuk hasil
        HashMap<Integer, ArrayList<Integer>> result = new HashMap<Integer, ArrayList<Integer>>() ;
        // Untuk setiap tiles cek keberadaan engimon
        int id = 0;
        for(int i = 0; i<width; i++){
            for(int j= 0; j<length ; j++){    
                // Jika terdapat engimon liar pada tiles x,y
                if(this.mapelem.get(i).get(j).isEngimonExist()){
                    // Tambahkan species engimon beserta posisinya ke map hasil
                    ArrayList<Integer> position = new ArrayList<Integer>();
                    position.add(i);
                    position.add(j);
                    result.put(id, position);
                    id++;
                }
            }
        }
        return result;
    };

   public void addEngimon(int x, int y, Engimon engi) throws EngimonExist{
        // I.S. x, y, dan species engimon terdefinisi
        // F.S. Jika total engimon < max engimon dan di tiles x, y belum ada engimon maka engimon species di tambahkan ke tiles
        // Jumlah total engimon di map ditambah jika penambahan engimon berhasil
        // Jika salah satu kondisi tidak terpenuhi, throw exception sesuai dengan kondisinya

        // Cek apakah total engimon < max engimon
        if(total_engimon < max_engimon){
            // Cek apakah terdapat engimon liar pada tiles x, y
            if(this.mapelem.get(x).get(y).isEngimonExist()){
                throw new EngimonExist();
            } 
            // Cek apakah  species yang ingin ditambahkan cocok dengan type tiles x, y 
            else if(!isValidEngimonPosition(x, y, engi.getSpecies(), false)){
                // do nothing
                System.out.println(engi.getSpecies());
            } 
            // Jika melewati kedua cek diatas maka tambahkan engimon ke tiles x, y
            else { 
                try{
                    Engimon newEngimon = engi.cloneEngimon();
                    this.mapelem.get(x).get(y).set_engimon_exist(true);
                    this.mapelem.get(x).get(y).set_engimon(newEngimon);
                    this.mapelem.get(x).get(y).set_symbol(newEngimon.getEngimonSymbol());
                    this.total_engimon++; 
                } catch (Exception e){
                    System.out.println(e);
                }
            }
        }

    };

    public void removeEngimon(int x, int y){
        // I.S. x, y terdefinisi
        // F.S. Jika pada tiles x, y terdapat engimon, maka hilangkan engimon dari tiles itu 
        // dan ubah symbol dari tiles itu sesuai dengan tipe tiles serta kurangi jumlah engimon liar pada map
        // Cek apakah ada engimon liar pada tiles x, y
        if(this.mapelem.get(x).get(y).isEngimonExist()){
            this.mapelem.get(x).get(y).set_engimon_exist(false);
            if(this.mapelem.get(x).get(y).get_type()=="grassland"){
                this.mapelem.get(x).get(y).set_symbol('-');
            }else if(this.mapelem.get(x).get(y).get_type()=="mountains"){
                this.mapelem.get(x).get(y).set_symbol('^');
            }else if(this.mapelem.get(x).get(y).get_type()=="sea"){
                this.mapelem.get(x).get(y).set_symbol('o');
            } else{
                this.mapelem.get(x).get(y).set_symbol('*');
            }
            this.total_engimon--; 
        }else{
        }
    };

    public void moveEngimon(int x1, int y1, int x2, int y2, Engimon engi){
        // I.S. x1, y1 merupakan posisi awal engimon liar. x2, y2 merupakan posisi akhir engimon liar. 
        // F.S. Jika x2, y2 merupakan tiles yang valid untuk engimon species maka pindahkan engimon tersebut ke tiles itu
        // Cek kevalidan posisi x2, y2 untuk engimon
        if(this.isValidEngimonPosition(x2, y2, engi.getSpecies(), false)){
                removeEngimon(x1, y1);
                try{
                    addEngimon(x2, y2, engi);
                } catch (Exception e){
                    System.out.println(e);
                }
        }
    };

    public void randomMoveAllEngimon() throws InvalidEngimonMoveToPlayer{
        // I.S. Engimon liar terdefinisi di map
        // F.S. Engimon bergerak random dengan jarak maksimal 1 dari posisi awal (ke atas, ke bawah, ke kanan, ke kiri, atau diam)
        // Jika tiles yang dituju valid maka engimon bergerak ke tiles itu, jika engimon bergerak ke tiles player maka throw exception InvalidEngimonMoveToPlayer
        
        // Ambil semua posisi engimon beserta speciesnya dari map
        HashMap<Integer, ArrayList<Integer>> wildEngimon = new HashMap<Integer, ArrayList<Integer>>();
        wildEngimon = this.getWildEngimonPosition();


        // Move setiap engimon di map
        for(Integer id : wildEngimon.keySet()){
            int number = rand.nextInt(5);
            int i = wildEngimon.get(id).get(0);
            int j = wildEngimon.get(id).get(1);
            if(number==1){
                // Engimon bergerak ke atas
                // Cek apakah tiles valid atau tiles yang dituju adalah tiles player
                if(isValidPosition(i-1, j, false)){
                    this.moveEngimon(i, j, i-1, j, this.mapelem.get(i).get(j).get_engimon());
                } else if(this.isPlayerPosition(i-1, j)){
                    throw new InvalidEngimonMoveToPlayer();
                }
            }else if(number==2){
                // Engimon bergerak ke kiri
                // Cek apakah tiles valid atau tiles yang dituju adalah tiles player
                if(isValidPosition(i, j-1, false)){
                    this.moveEngimon(i, j, i, j-1, this.mapelem.get(i).get(j).get_engimon());
                }else if(this.isPlayerPosition(i, j-1)){
                    throw new InvalidEngimonMoveToPlayer();
                }
            }else if(number==3){
                // Engimon bergerak ke bawah
                // Cek apakah tiles valid atau tiles yang dituju adalah tiles player
                if(isValidPosition(i+1, j, false)){
                    this.moveEngimon(i, j, i+1, j, this.mapelem.get(i).get(j).get_engimon());
                }else if(this.isPlayerPosition(i+1, j)){
                    throw new InvalidEngimonMoveToPlayer();
                }
            } else if(number==4){
                // Engimon bergerak ke kanan
                // Cek apakah tiles valid atau tiles yang dituju adalah tiles player
                if(isValidPosition(i, j+1, false)){
                    this.moveEngimon(i, j, i, j+1, this.mapelem.get(i).get(j).get_engimon());
                }else if(this.isPlayerPosition(i, j+1)){
                    throw new InvalidEngimonMoveToPlayer();
                }
            } else{
                // Engimon tidak bergerak
            }
        }      

    };

    public void evolveAllEngimon(){
        // I.S. Engimon liar terdefinisi di map
        // F.S. Engimon bergerak random dengan jarak maksimal 1 dari posisi awal (ke atas, ke bawah, ke kanan, ke kiri, atau diam)
        // Jika tiles yang dituju valid maka engimon bergerak ke tiles itu, jika engimon bergerak ke tiles player maka throw exception InvalidEngimonMoveToPlayer
        
        // Ambil semua posisi engimon beserta speciesnya dari map
        HashMap<Integer, ArrayList<Integer>> wildEngimon = new HashMap<Integer, ArrayList<Integer>>();
        wildEngimon = this.getWildEngimonPosition();
        // Level up semua wild engimon
        for(Integer id : wildEngimon.keySet()){
            int i = wildEngimon.get(id).get(0);
            int j = wildEngimon.get(id).get(1);
            this.mapelem.get(i).get(j).get_engimon().addExp(100);
            this.mapelem.get(i).get(j).set_symbol(this.mapelem.get(i).get(j).get_engimon().getEngimonSymbol());
        }
    };


    public void spawnRandomEngimon(int maxLevel){
        // Fungsi ini digunakan untuk menambahkan pokemon liar ke permainan
        // Bekerja dengan memanfaatkan posisi random yang digenerate menggunakan fungsi rand() dari library standard C 
        // Fungsi ini bekerja secara pseudo random, sehingga apabila terlalu banyak engimon yang sudah ada di map ( > 100 )
        // Fungsi akan memaksa keluar dan menghasilkan exception "Engimon Exist"
        // I.S. Map dengan setiap MapElem terdefinisi
        // F.S. Memunculkan Engimon liar baru di map. 


        // Bekerja untuk menambahkan engimon liar baru dengan maksimal 120 pokemon 
        // Tidak ada level handling. Seluruh Engimon baru akan di spawn pada level 0. 
        // Berikut himpunan untuk meningkatkan probabilitas munculnya engimon dengan satu elemen. 
        String[] Pokemons = {
            "Pyro", 
            "Pyro", 
            "Pyro", 
            "Hydro",
            "Hydro",
            "Hydro", 
            "Vaporize", 
            "Overload", 
            "PyroCrystallize", 
            "Melt", 
            "Electro",
            "Electro", 
            "Electro", 
            "ElectroCharged", 
            "HydroCrystallize",
            "Frozen", 
            "Geo",
            "Geo",
            "Geo",
            "ElectroCrystallize", 
            "Superconductor",
            "Cryo",
            "Cryo",
            "Cryo", 
            "CryoCrystallize"
        };


        // Random Number Generator membutuhkan seed dari program global 
        int randomNumber = rand.nextInt(Pokemons.length);
        int randomLevel = rand.nextInt(5) + maxLevel;
        String species = Pokemons[randomNumber];


        int x = 0;
        int y = 0;
        
        // Generate posisi engimon liar baru 
        if(species=="Pyro"){
            x = rand.nextInt(10);
            y = rand.nextInt(5);
        }else if(species=="Hydro"){
            x = rand.nextInt(10)+10;
            y = rand.nextInt(5);
        }else if(species=="Electro" || species=="Geo" || species=="ElectroCrystallize"){
            x = rand.nextInt(10);
            y = rand.nextInt(5)+5;
        }else if(species=="Cryo"){
            x = rand.nextInt(10)+10;
            y = rand.nextInt(5)+5;
        }else if(species=="Vaporize"){
            int loc = rand.nextInt(2);
            if(loc==0){
                x = rand.nextInt(10);
                y = rand.nextInt(5);
            }else{
                x = rand.nextInt(10)+10;
                y = rand.nextInt(5);
            }
        }else if(species=="Overload" || species=="PyroCrystallize"){
            int loc = rand.nextInt(2);
            if(loc==0){
                x = rand.nextInt(10);
                y = rand.nextInt(5);
            }else{
                x = rand.nextInt(10);
                y = rand.nextInt(5)+5;
            }
        }else if(species=="Melt"){
            int loc = rand.nextInt(2);
            if(loc==0){
                x = rand.nextInt(10);
                y = rand.nextInt(5);
            }else{
                x = rand.nextInt(10)+10;
                y = rand.nextInt(5)+5;
            }
        }else if(species=="ElectroCharged" || species=="HydroCrystallize"){
            int loc = rand.nextInt(2);
            if(loc==0){
                x = rand.nextInt(10)+10;
                y = rand.nextInt(5);
            }else{
                x = rand.nextInt(10);
                y = rand.nextInt(5)+5;
            }
        }else if(species=="Frozen"){
            int loc = rand.nextInt(2);
            if(loc==0){
                x = rand.nextInt(10)+10;
                y = rand.nextInt(5);
            }else{
                x = rand.nextInt(10)+10;
                y = rand.nextInt(5)+5;
            }
        }else if(species=="Superconductor" || species=="CryoCrystallize"){
            int loc = rand.nextInt(2);
            if(loc==0){
                x = rand.nextInt(10);
                y = rand.nextInt(5)+5;
            }else{
                x = rand.nextInt(10)+10;
                y = rand.nextInt(5)+5;
            }
        }

        // Mencoba posisi lain apabila terbentur dengan sebuah exeception. 
        while ((this.mapelem.get(y).get(x).get_symbol()!='-') && (this.mapelem.get(y).get(x).get_symbol()!='o') && (this.mapelem.get(y).get(x).get_symbol()!='*') && (this.mapelem.get(y).get(x).get_symbol()!='^')) {
            if(species=="Pyro"){
                x = rand.nextInt(10);
                y = rand.nextInt(5);
            }else if(species=="Hydro"){
                x = rand.nextInt(10)+10;
                y = rand.nextInt(5);
            }else if(species=="Electro" || species=="Geo" || species=="ElectroCrystallize"){
                x = rand.nextInt(10);
                y = rand.nextInt(5)+5;
            }else if(species=="Cryo"){
                x = rand.nextInt(10)+10;
                y = rand.nextInt(5)+5;
            }else if(species=="Vaporize"){
                int loc = rand.nextInt(2);
                if(loc==0){
                    x = rand.nextInt(10);
                    y = rand.nextInt(5);
                }else{
                    x = rand.nextInt(10)+10;
                    y = rand.nextInt(5);
                }
            }else if(species=="Overload" || species=="PyroCrystallize"){
                int loc = rand.nextInt(2);
                if(loc==0){
                    x = rand.nextInt(10);
                    y = rand.nextInt(5);
                }else{
                    x = rand.nextInt(10);
                    y = rand.nextInt(5)+5;
                }
            }else if(species=="Melt"){
                int loc = rand.nextInt(2);
                if(loc==0){
                    x = rand.nextInt(10);
                    y = rand.nextInt(5);
                }else{
                    x = rand.nextInt(10)+10;
                    y = rand.nextInt(5)+5;
                }
            }else if(species=="ElectroCharged" || species=="HydroCrystallize"){
                int loc = rand.nextInt(2);
                if(loc==0){
                    x = rand.nextInt(10)+10;
                    y = rand.nextInt(5);
                }else{
                    x = rand.nextInt(10);
                    y = rand.nextInt(5)+5;
                }
            }else if(species=="Frozen"){
                int loc = rand.nextInt(2);
                if(loc==0){
                    x = rand.nextInt(10)+10;
                    y = rand.nextInt(5);
                }else{
                    x = rand.nextInt(10)+10;
                    y = rand.nextInt(5)+5;
                }
            }else if(species=="Superconductor" || species=="CryoCrystallize"){
                int loc = rand.nextInt(2);
                if(loc==0){
                    x = rand.nextInt(10);
                    y = rand.nextInt(5)+5;
                }else{
                    x = rand.nextInt(10)+10;
                    y = rand.nextInt(5)+5;
                }
            } 
        }

        // Menambahkan engimon baru 
        // Exception handling sudah ditangani oleh fungsi addEngimon
        try{
            Engimon newEngimon = Engidex.getEngimonBySpecies(species).cloneEngimon();
            newEngimon.setLevel(randomLevel);
            newEngimon.setCumulExp((randomLevel-1)*100);
            List<Skill> list = Skidex.getCompatibleSkill(newEngimon.getElement());
            int getExtraSkill = rand.nextInt(2);
            if(getExtraSkill==1){
                int randomSkill = rand.nextInt(list.size());
                Skill newSkill = list.get(randomSkill);
                if(!newEngimon.getSkill().contains(newSkill)){
                    newEngimon.addSkill(newSkill);
                }
            }
            this.addEngimon(y, x, newEngimon);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public Engimon getNearbyEnemyEngimon(AtomicInteger X, AtomicInteger Y) throws InvalidBattleException{
        // Fungsi ini digunakan untuk battle dengan engimon liar
        // I.S. Posisi player terdeifinisi di map
        // F.S. Jika terdapat engimon disekitar player (diatas/dibawah/dikanan/dikiri)
        // maka X dan Y adalah posisi diketemukannya engimon pertama kali di sekitar player
        // Jika tidak ada engimon liar disekitar player maka throw exception
        if(isValidPosition(this.player_pos.get(0)-1, this.player_pos.get(1), true)){
            if (this.mapelem.get(this.player_pos.get(0)-1).get(this.player_pos.get(1)).isEngimonExist()){
                 X.set(this.player_pos.get(0)-1);
                 Y.set(this.player_pos.get(1));
                 return this.mapelem.get(this.player_pos.get(0)-1).get(this.player_pos.get(1)).get_engimon();
            }
        }
        if(isValidPosition(this.player_pos.get(0), this.player_pos.get(1)-1, true)){
            if (this.mapelem.get(this.player_pos.get(0)).get(this.player_pos.get(1)-1).isEngimonExist()){
                X.set(this.player_pos.get(0));
                Y.set(this.player_pos.get(1)-1);
                return this.mapelem.get(this.player_pos.get(0)).get(this.player_pos.get(1)-1).get_engimon();
            }
        }
        if(isValidPosition(this.player_pos.get(0)+1, this.player_pos.get(1), true)){
            if (this.mapelem.get(this.player_pos.get(0)+1).get(this.player_pos.get(1)).isEngimonExist()){
                X.set(this.player_pos.get(0)+1);
                Y.set(this.player_pos.get(1));
                return this.mapelem.get(this.player_pos.get(0)+1).get(this.player_pos.get(1)).get_engimon();
            }
        }
        if(isValidPosition(this.player_pos.get(0), this.player_pos.get(1)+1, true)){
            if (this.mapelem.get(this.player_pos.get(0)).get(this.player_pos.get(1)+1).isEngimonExist()){
                X.set(this.player_pos.get(0));
                Y.set(this.player_pos.get(1)+1);
                return this.mapelem.get(this.player_pos.get(0)).get(this.player_pos.get(1)+1).get_engimon();
            }
        }
        throw new InvalidBattleException();
    }

    // Player Handlers 
    public void move(char c) throws InvalidMoveException, InvalidPlayerMove{
        // I.S. Command c terdefinisi antara w, a, s, d
        // F.S. Jika tiles yang dituju valid untuk engimon dan active engimon, 
        // pindahkah engimon dan active engimonnya sesuai command
        // Jika tidak throw exception sesuai kondisi

        // Cek apakah player memiliki active engimon dan apakah posisi player yang sekarang adalah posisi yang valid untuk ditempati active engimon jika player bergerak
            if(c == 'w'){
                // Player ingin bergerak ke atas
                if(isValidPosition(player_pos.get(0)-1, player_pos.get(1), true) && mapelem.get(player_pos.get(0)-1).get(player_pos.get(1)).isEngimonExist()){
                    // Cek apakah player bergerak ke tiles yang diisi oleh engimon liar
                    throw new InvalidPlayerMove();
                }else if(!isValidPosition(player_pos.get(0)-1, player_pos.get(1), true)){
                    // Cek apakah player bergerak ke posisi yang valid (tidak keluar map)
                    throw new InvalidMoveException();
                }else{
                    // Pindahkan player dan active engimon ke atas
                    set_player_pos(player_pos.get(0)-1, player_pos.get(1));
                    if(this.isAnyActiveEngimon()){
                        set_active_engimon_pos(player_pos.get(0)+1, player_pos.get(1));
                    }
                }
            }else if(c == 'a'){
                // Player ingin bergerak ke kiri
                if(isValidPosition(player_pos.get(0), player_pos.get(1)-1, true) && mapelem.get(player_pos.get(0)).get(player_pos.get(1)-1).isEngimonExist()){
                    // Cek apakah player bergerak ke tiles yang diisi oleh engimon liar
                    throw new InvalidPlayerMove();
                }else if(!isValidPosition(player_pos.get(0), player_pos.get(1)-1, true)){
                    // Cek apakah player bergerak ke posisi yang valid (tidak keluar map)
                    throw new InvalidMoveException();
                }else{
                    // Pindahkan player dan active engimon ke kiri
                    set_player_pos(player_pos.get(0), player_pos.get(1)-1);
                    if(this.isAnyActiveEngimon()){
                        set_active_engimon_pos(player_pos.get(0), player_pos.get(1)+1);
                    }
                }
            }else if(c == 's'){
                // Player ingin bergerak ke bawah
                if(isValidPosition(player_pos.get(0)+1, player_pos.get(1), true) && mapelem.get(player_pos.get(0)+1).get(player_pos.get(1)).isEngimonExist()){
                    // Cek apakah player bergerak ke tiles yang diisi oleh engimon liar
                    throw new InvalidPlayerMove();
                }else if(!isValidPosition(player_pos.get(0)+1, player_pos.get(1), true)){
                    // Cek apakah player bergerak ke posisi yang valid (tidak keluar map)
                    throw new InvalidMoveException();
                }else{
                    // Pindahkan player dan active engimon ke bawah
                    set_player_pos(player_pos.get(0)+1, player_pos.get(1));
                    if(this.isAnyActiveEngimon()){
                        set_active_engimon_pos(player_pos.get(0)-1, player_pos.get(1));
                    }
                }
            }else if(c == 'd'){
                // Player ingin bergerak ke kanan
                if(isValidPosition(player_pos.get(0), player_pos.get(1)+1, true) && mapelem.get(player_pos.get(0)).get(player_pos.get(1)+1).isEngimonExist()){
                    // Cek apakah player bergerak ke tiles yang diisi oleh engimon liar
                    throw new InvalidPlayerMove();
                }else if(!isValidPosition(player_pos.get(0), player_pos.get(1)+1, true)){
                    // Cek apakah player bergerak ke posisi yang valid (tidak keluar map)
                    throw new InvalidMoveException();
                }else{
                    // Pindahkan player dan active engimon ke kanan
                    set_player_pos(player_pos.get(0), player_pos.get(1)+1);
                    if(this.isAnyActiveEngimon()){
                        set_active_engimon_pos(player_pos.get(0), player_pos.get(1)-1);
                    }
                }
            }
        // Setelah player memberikan command w,a,s,d maka turn akan bertambah
        // Lakukan randomisasi gerakan untuk setiap engimon liar yang ada di map
        try{
            this.randomMoveAllEngimon();
        } catch(Exception e){
            System.out.println(e);
        }
        // Print kondisi map terbaru
        this.printMap();
    };

    public void set_player_pos(int x, int y) throws InvalidMoveException {
        // I.S. x dan y adalah posisi baru dari player
        // F.S. pindahkan player jika x, y adalah tiles yang valid dan ubah symbol tiles posisi player sebelumnya sesuai type tiles
        if(x>=0 && x<10 && y>=0 && y<20){
            if(this.mapelem.get(player_pos.get(0)).get(player_pos.get(1)).get_type() == "grassland"){
                this.mapelem.get(player_pos.get(0)).get(player_pos.get(1)).set_symbol('-');
            } else if(this.mapelem.get(player_pos.get(0)).get(player_pos.get(1)).get_type() == "mountains"){
                this.mapelem.get(player_pos.get(0)).get(player_pos.get(1)).set_symbol('^');
            } else if(this.mapelem.get(player_pos.get(0)).get(player_pos.get(1)).get_type() == "sea"){
                this.mapelem.get(player_pos.get(0)).get(player_pos.get(1)).set_symbol('o');
            } else{
                this.mapelem.get(player_pos.get(0)).get(player_pos.get(1)).set_symbol('*');
            }
            this.player_pos.set(0,x);
            this.player_pos.set(1,y);
            this.mapelem.get(x).get(y).set_symbol('P');
        } else{
            // Jika tiles x, y tidak valid throw exception InvalidMoveException
            throw new InvalidMoveException();
        }
    };

    public ArrayList<Integer>get_player_pos(){
        // I.S. Map dan posisi player pada map terdefinisi
        // F.S. Mengembalikan posisi player pada map dalam array of integer dengan size 2
        return this.player_pos;
    };

    public void set_active_engimon_pos(int x, int y){
        // I.S. x dan y adalah posisi baru dari active engimon
        // F.S. pindahkan active engimon jika x, y adalah tiles yang valid
        // dan ubah symbol tiles posisi active engimon sebelumnya sesuai type tiles

        // String species = this.active_engimon_species;
        // Cek apakah tiles x, y valid untuk active engimon
        if(isValidPosition(x, y, true)){
            if(this.mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).get_type() == "grassland"){
                this.mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).set_symbol('-');
            } else if(this.mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).get_type() == "mountains"){
                this.mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).set_symbol('^');
            } else if(this.mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).get_type() == "sea"){
                this.mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).set_symbol('o');
            } else{
                this.mapelem.get(active_engimon_pos.get(0)).get(active_engimon_pos.get(1)).set_symbol('*');
            }
            this.active_engimon_pos.set(0,x);
            this.active_engimon_pos.set(1,y);
            this.mapelem.get(x).get(y).set_symbol('X');
        }
    };

    public ArrayList<Integer> get_active_engimon_pos(){
        // I.S. Map dan posisi active engimon pada map terdefinisi
        // F.S. Mengembalikan posisi active engimon pada map dalam array of integer dengan size 2
        return this.active_engimon_pos;
    };

    public void set_active_engimon_species(String species){
        // I.S. map terdefinisi dan spesies active engimon terdefinisi atau "undefined"
        // F.S. mengubah nilai atribut active engimon species pada map menjadi species
        this.active_engimon_species = species;
    };

    public String get_active_engimon_species(){
        // I.S. map terdefinisi dan spesies active engimon terdefinisi atau "undefined"
        // F.S. mengembalikan nilai dari atribut active engimon species
        return this.active_engimon_species;
    };
}


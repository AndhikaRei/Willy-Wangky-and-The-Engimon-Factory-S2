package sample.Game;
import java.util.List;

import main.java.engimon.Breeding_Fountain;
import sample.AlertBox;
import sample.BreedConfirm.BreedConfirm;
import sample.DetailEngimon.DetailEngimon;
import sample.DetailSkillItem.DetailSkillItem;
import sample.ReplaceSkill.ReplaceSkill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.java.battle.Battle;
import main.java.element.Element;
import main.java.engimon.Engidex;
import main.java.engimon.Engimon;
import main.java.exception.SkillFullException;
import main.java.inventory.Skill_Item;
import main.java.map.*;
import sample.BattleConfirm.BattleConfirm;
import main.java.player.Player;
import main.java.skill.Skidex;

import java.util.concurrent.atomic.AtomicInteger;

public class GameController {
    // Gripane tempat peta dibentuk
    @FXML private GridPane MapGridPane;

    // Tombol movement
    @FXML private Button btn_w;
    @FXML private Button btn_a;
    @FXML private Button btn_s;
    @FXML private Button btn_d;

    // Tombol lain
    @FXML private Button btn_exit;

    // Tabel (Inventory)
    @FXML private TableView table_Engimon;
    @FXML private TableView table_Item;

    // Data active engimon
    @FXML private AnchorPane active_engimonPane;
    @FXML private ImageView active_el1;
    @FXML private ImageView active_el2;
    @FXML private ImageView active_sprite;
    @FXML private Label active_speciesName;
    @FXML private Label active_name;
    @FXML private Label active_level;
    @FXML private Label active_skillPower;
    @FXML private Label turnGUI;

    // Background tiap petak peta
    private  Image grass;
    private  Image sea;
    private  Image tundra;
    private  Image mountain;

    // Giliran
    private Integer turn;

    // Data peta
    private Map map;

    // Data Player
    private Player player;

    // Inisialisasi komponen peta
    @FXML private void initialize(){
        // Inisialisasi resources yang diperlukan sekaligus refresh GUI
        Engidex.initEngidex();
        Skidex.initSkill();
        try{
            this.turn =0;
            this.map = new Map(20, 10, "map.txt");
            this.player = new Player();
            this.loadImage();
            this.refreshMapGUI();
            this.setupInventory();
            this.refreshInventory();
            this.refreshActiveEngimonGUI();
        } catch ( Exception e){
            AlertBox.displayWarning(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    public void loadImage(){
        // Melakukan load image
        this.grass = new Image("main/resources/grass.png",35,35,false,false);
        this.sea = new Image("main/resources/sea.png",35,35,false,false);
        this.tundra = new Image("main/resources/tundra.png",35,35,false,false);
        this.mountain = new Image("main/resources/mountain.png",35,35,false,false);
    }

    // Merefresh GUI bagian active engimon dengan data active engimon player sekarang
    public void refreshActiveEngimonGUI(){
        if(player.getActiveEngimon() == null){
            this.active_engimonPane.setVisible(false);
        } else {
            this.active_engimonPane.setVisible(true);
            this.active_sprite.setImage(this.player.getActiveEngimon().getSprite(130,130));
            this.active_speciesName.setText(this.player.getActiveEngimon().getSpecies());
            this.active_name.setText(this.player.getActiveEngimon().getName());
            this.active_level.setText(Integer.toString(this.player.getActiveEngimon().getLevel()));
            this.active_skillPower.setText(Integer.toString(Battle.sumOfSkillPower(this.player.getActiveEngimon())));
            this.active_el1.setImage(Element.getSpriteEl(this.player.getActiveEngimon().getElement().get(0),30.0,30.0));
            if (this.player.getActiveEngimon().isOneElement()){
                this.active_el2.setImage(null);
            } else {
                this.active_el2.setImage(Element.getSpriteEl(this.player.getActiveEngimon().getElement().get(1),30.0,30.0));
            }
        }
    }

    // Melakukan load ulang peta berdasarkan kondisi peta yang ada di array
    public void refreshMapGUI(){
        // Cek Active engimon
        if (this.player.getActiveEngimon() == null){
            this.map.set_active_engimon_species("undefined");
        } else {
            this.map.set_active_engimon_species(this.player.getActiveEngimon().getSpecies());
        }

        // Set ulang turn
        this.turnGUI.setText(Integer.toString(this.turn));

        // Membersihkan isi gridpane peta
        this.MapGridPane.getChildren().clear();

        // Loop untuk tiap tile yang ada di peta
        for (int i=0; i< 10; i++){
            for (int j= 0; j < 20; j++){
                // Instansiasi pane baru
                StackPane Npane = new StackPane();

                // Mendapatkan info tile sekarang
                Mapelem tile = this.map.getMapElem().get(i).get(j);

                // Menggambar pane dengan background sesuai dengan type peta
                switch (tile.get_type()){
                    case "grassland":
                        Npane.getChildren().add(new ImageView(this.grass));
                        break;
                    case "sea":
                        Npane.getChildren().add(new ImageView(this.sea));
                        break;
                    case("tundra"):
                        Npane.getChildren().add(new ImageView(this.tundra));
                        break;
                    case("mountains"):
                        Npane.getChildren().add(new ImageView(this.mountain));
                        break;
                    default:
                        Npane.setStyle("-fx-background-color: black");
                }
                // Memasukkan pane kedalam gridpane
                this.MapGridPane.add(Npane,j,i);

                // Menambahkan gambar pemain atau engimon active di pane apabila tile sekarang ada player/engimon active
                if (tile.get_symbol() != '-' && tile.get_symbol() != '*' && tile.get_symbol() !='^' &&tile.get_symbol() !='o'){
                    switch (tile.get_symbol()){
                        case 'P':
                            Image player = new Image("main/resources/player.png",35,35,false,false);
                            ImageView pl = new ImageView(player);
                            Npane.getChildren().add(pl);
                            Npane.setAlignment(pl, Pos.CENTER);
                            break;
                        case 'X':
                            Image activeEngimon = new Image("main/resources/activeEngimon.png",30,30,false,false);
                            ImageView ae = new ImageView(activeEngimon);
                            Npane.getChildren().add(ae);
                            Npane.setAlignment(ae, Pos.CENTER);
                            break;
                    }
                }
                // Menambahkan gambar engimon liar di pane apabila tile sekarang ada engimon liar
                if(tile.get_engimonExist() == Boolean.TRUE){
                    // Mendapatkan foto engimon yang sesuai dengan engimon yang ada di tile sekarang
                    ImageView engimon = new ImageView(tile.get_engimon().getSprite(35,35));

                    // Mendapatkan aura engimon yang sesuai dengan engimon yang ada di tile sekarang
                    DropShadow ds = tile.get_engimon().getAura();
                    ds.setBlurType(BlurType.GAUSSIAN); ds.setRadius(1.5); ds.setSpread(1.5);
                    engimon.setEffect(ds);

                    // Menambahkan engimon dan aura ke pane
                    Npane.getChildren().add(engimon);
                    Npane.setAlignment(engimon, Pos.CENTER);
                }
            }
        }
    }
    // Melakukan setup inventory engimon dan skill item
    public void setupInventory(){
        // Setup kolom spesies pada tabel engimon
        TableColumn<Engimon, String> speciesColumn = new TableColumn<>("Species");
        speciesColumn.setPrefWidth(70);
        speciesColumn.setSortable(false);
        speciesColumn.setCellValueFactory(new PropertyValueFactory<>("species"));

        // Setup kolom name pada tabel engimon
        TableColumn<Engimon, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setPrefWidth(110);
        nameColumn.setSortable(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Setup kolom elemen pada tabel engimon
        TableColumn<Engimon, List<String>> elementColumn = new TableColumn<>("Element");
        elementColumn.setPrefWidth(110);
        elementColumn.setSortable(false);
        elementColumn.setCellValueFactory(new PropertyValueFactory<>("element"));

        // Setup kolom level pada tabel engimon
        TableColumn<Engimon, Integer> levelColumn = new TableColumn<>("Level");
        levelColumn.setPrefWidth(45);
        levelColumn.setSortable(false);
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));

        // Menambahkan semua kolom ke tabel engimon
        this.table_Engimon.getColumns().addAll(speciesColumn,nameColumn,elementColumn,levelColumn);
        this.table_Engimon.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

        // Setup kolom quantity pada tabel Skill_Item
        TableColumn<Skill_Item, Integer> quantityColumn = new TableColumn<>("Qty");
        quantityColumn.setPrefWidth(45);
        quantityColumn.setSortable(false);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        // Setup kolom name pada tabel Skill_Item
        TableColumn<Skill_Item, String> skillNameColumn = new TableColumn<>("Name");
        skillNameColumn.setPrefWidth(110);
        skillNameColumn.setSortable(false);
        skillNameColumn.setCellValueFactory(new PropertyValueFactory<>("skillName"));

        // Setup kolom elemen pada tabel Skill_Item
        TableColumn<Skill_Item, List<String>> skillElementColumn = new TableColumn<>("Element");
        skillElementColumn.setPrefWidth(110);
        skillElementColumn.setSortable(false);
        skillElementColumn.setCellValueFactory(new PropertyValueFactory<>("element"));

        // Setup kolom base power pada tabel Skill_Item
        TableColumn<Skill_Item, Integer> basePowerColumn = new TableColumn<>("BP");
        basePowerColumn.setPrefWidth(45);
        basePowerColumn.setSortable(false);
        basePowerColumn.setCellValueFactory(new PropertyValueFactory<>("basePower"));

        // Menambahkan semua kolom ke tabel skill_item
        this.table_Item.getColumns().addAll(quantityColumn,skillNameColumn,skillElementColumn,basePowerColumn);
        this.table_Item.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

    }

    // Merefresh ulang inventory berdasarkan kondisi inventory engimon dan skill item sekarang
    public void refreshInventory(){
        // Sort inventory untuk pengaman
        this.player.getInventory().sortItems();
        this.player.getInventory().sortEngimons();

        // Instansiasi ObservableList untuk ditampung di tabel
        // Inventory Engimon
        ObservableList<Engimon> engimonInventory =  FXCollections.observableArrayList(this.player.getInventory().getEngimons());
        // Inventory Skill Item
        ObservableList<Skill_Item> skillItemsInventory = FXCollections.observableArrayList(this.player.getInventory().getItems());

        // Mengisi tabel dengan observable list inventory engimon dan inventory skill_item, mengcolapse title pane supaya kerefresh
        this.table_Engimon.getItems().clear();
        this.table_Item.getItems().clear();
        this.table_Engimon.setItems(engimonInventory);
        this.table_Item.setItems(skillItemsInventory);
    }

    // Inventory Command
    // Breed Engimon Command
    public void breedEngimon(){
        try{
            if ((this.table_Engimon.getSelectionModel().getSelectedIndices()).size() != 2) {
                throw new Exception("Pilihlah tepat dua engimon untuk melakukan breeding");
            } else {
                Integer indexEngimon1 = (Integer) this.table_Engimon.getSelectionModel().getSelectedIndices().get(0);
                Integer indexEngimon2 = (Integer) this.table_Engimon.getSelectionModel().getSelectedIndices().get(1);
                Engimon engi1 = this.player.getInventory().getEngimon(indexEngimon1);
                Engimon engi2 = this.player.getInventory().getEngimon(indexEngimon2);
//                Boolean isBreed = BreedConfirm.display(this.e.get(indexEngimon1),this.e.get(indexEngimon2));
                Boolean isBreed = BreedConfirm.display(engi1,engi2);
                if (isBreed){
                    // Prosedur breeding
                    Engimon engi3 = Breeding_Fountain.startBreeding(engi1,engi2);
                    String newName = AlertBox.displayAskNewName();
                    if(newName != null){
                        engi3.setName(newName);
                    }
                    this.player.getInventory().addEngimon(engi3);
                    this.refreshInventory();
                    this.refreshActiveEngimonGUI();
                }
            }
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }

    // Rename engimon command
    public void renameEngimon(){
        try{
            if ((this.table_Engimon.getSelectionModel().getSelectedIndices()).size() != 1) {
                throw new Exception("Pilihlah tepat satu engimon untuk di rename");
            } else {
                String newName = AlertBox.displayAskNewName();
                if(newName != null){
                    // Prosedur rename engimon
                    this.player.renameEngimon(this.table_Engimon.getSelectionModel().getSelectedIndex(),newName);
                    this.refreshInventory();
                    this.refreshActiveEngimonGUI();
                }
            }
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }

    // Interact with active engimon command
    public void interactActiveEngimon(){
        try{
            if (this.player.getActiveEngimon() == null) {
                throw new Exception("Anda tidak mempunyai active engimon");
            } else {
                AlertBox.displayInteract(this.player.getActiveEngimon());
            }
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }

    // Change Active Engimon Command
    public void changeActiveEngimon(){
        try{
            if ((this.table_Engimon.getSelectionModel().getSelectedIndices()).size() != 1) {
                throw new Exception("Pilihlah tepat satu engimon untuk menjadi active engimon");
            } else {
                this.player.changeActiveEngimon(this.table_Engimon.getSelectionModel().getSelectedIndex());
                refreshActiveEngimonGUI();
            }
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }

    // Release Engimon command
    public void releaseEngimon(){
        try{
            if ((this.table_Engimon.getSelectionModel().getSelectedIndices()).size() != 1) {
                throw new Exception("Pilihlah tepat satu engimon untuk di examine");
            } else {
                // Prosedur release engimon dengan index tertentu (detilnya di player, disini tinggal panggil aja)
                this.player.ReleaseEngimon(this.table_Engimon.getSelectionModel().getSelectedIndex());
                this.refreshInventory();
                this.refreshActiveEngimonGUI();
            }
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }
    // Examine Engimon command
    public void examineEngimon(){
        try{
            if ((this.table_Engimon.getSelectionModel().getSelectedIndices()).size() != 1) {
                throw new Exception("Pilihlah tepat satu engimon untuk di examine");
            } else {
                DetailEngimon.display(this.player.getInventory().getEngimon(this.table_Engimon.getSelectionModel().getSelectedIndex()));
            }
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }

    public void inspectItem(){
        try{
            if ((this.table_Item.getSelectionModel().getSelectedIndices()).size() != 1) {
                throw new Exception("Pilihlah tepat satu item untuk di inspect");
            } else {
                DetailSkillItem.display(this.player.getInventory().getItem(this.table_Item.getSelectionModel().getSelectedIndex()));
            }
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }

    // Throw item Command
    public void throwItem(){
        try{
            if ((this.table_Item.getSelectionModel().getSelectedIndices()).size() != 1) {
                throw new Exception("Pilihlah tepat satu item untuk dibuang");
            } else {
                Integer numOfItem = AlertBox.displayAskNumDropItems();
                if(numOfItem != null){
                    this.player.getInventory().throwItem(this.table_Item.getSelectionModel().getSelectedIndex(),numOfItem);
                    this.refreshInventory();
                }
            }
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }

    // Use item Command
    public void useItem(){
        try{
            if (((this.table_Item.getSelectionModel().getSelectedIndices()).size() != 1) ||((this.table_Item.getSelectionModel().getSelectedIndices()).size() != 1)  ) {
                throw new Exception("Pilihlah tepat satu item dan satu engimon untuk learn skill");
            } else {
                Engimon goingToLearn = this.player.getInventory().getEngimon(this.table_Engimon.getSelectionModel().getSelectedIndex());
                Skill_Item skilToLearn = this.player.getInventory().getItem(this.table_Item.getSelectionModel().getSelectedIndex());
                goingToLearn.addSkill(skilToLearn);
                if(skilToLearn.getAmount() == 0){
                    this.player.getInventory().getItems().remove(this.table_Item.getSelectionModel().getSelectedIndex());
                }
                this.refreshInventory();
                this.refreshActiveEngimonGUI();
            }
        }
        catch (SkillFullException e){
            Engimon goingToLearn = this.player.getInventory().getEngimon(this.table_Engimon.getSelectionModel().getSelectedIndex());
            Skill_Item skilToLearn = this.player.getInventory().getItem(this.table_Item.getSelectionModel().getSelectedIndex());
            Integer indexReplace = ReplaceSkill.display(goingToLearn);
            if (indexReplace != null){
                // Algo replace skill
                try{
                    goingToLearn.replaceSkill(indexReplace,skilToLearn);
                } catch (Exception e2){
                    AlertBox.displayWarning(e2.getMessage());
                }
                if(skilToLearn.getAmount() == 0){
                    this.player.getInventory().getItems().remove(this.table_Item.getSelectionModel().getSelectedIndex());
                }
                this.refreshInventory();
                this.refreshActiveEngimonGUI();
            }
        }
        catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }

    }

    // Basic Command
    // Move command
    public void move(ActionEvent event){
        // Apabila tombol W A S D ditekan
        try{
            if(event.getSource().equals(this.btn_w)){
                this.map.move('w');
            } else if (event.getSource().equals(this.btn_a)){
                this.map.move('a');
            }else if (event.getSource().equals(this.btn_s)){
                this.map.move('s');
            }else {
                this.map.move('d');
            }
            this.turn++;
            if(this.turn%5==0){
                this.map.spawnRandomEngimon(this.player.getInventory().getHighestLevel().getLevel());
            }
            if(this.turn%20==0){
                this.map.evolveAllEngimon();
            }
            this.refreshMapGUI();
            this.map.printMap();

        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }

    // Battle Command
    public void Battle(){
        // Apabila tombol battle ditekan
        try{
            if (this.player.getActiveEngimon() == null){
                throw new Exception("Anda tidak memiliki active engimon");
            }
            AtomicInteger x = new AtomicInteger(0);
            AtomicInteger y = new AtomicInteger(0);
            Engimon enemy = this.map.getNearbyEnemyEngimon(x,y);
            Boolean isBattle = BattleConfirm.display(this.player.getActiveEngimon(),enemy);
            if (isBattle){
                if (Battle.playerEngimonWin(this.player.getActiveEngimon(), enemy)){
                    AlertBox.displayInfo("Anda berhasil menang", "Pesan Kemenangan");
                    this.player.getActiveEngimon().addExp(1000);
                    String newName = AlertBox.displayAskNewName();
                    if(newName != null){
                        enemy.setName(newName);
                    }
                    enemy.setLives(3);
                    this.player.getInventory().addEngimon(enemy);
                    this.player.getInventory().addItem(Battle.getEnemySkillItem(enemy));
                    this.refreshInventory();
                    this.map.removeEngimon(x.get(),y.get());
                    if (this.player.getActiveEngimon().getLevel() > 100){
                        this.player.getInventory().getEngimons().remove(this.player.getActiveEngimon());
                        this.player.setActiveEngimon(null);
                        AlertBox.displayInfo("Engimon anda meninggal karena level sudah melebihi 100", "Pesan Kematian");
                    }
                } else {
                    AlertBox.displayInfo("Anda kalah", "Pesan Kekalahan");
                    this.player.KillActiveEngimon();
                    this.refreshMapGUI();
                }
                this.refreshMapGUI();
                this.refreshActiveEngimonGUI();
                this.refreshInventory();
            }
            if (this.player.getInventory().getEngimons().isEmpty()){
                AlertBox.displayWarning("Anda kalah karena anda tidak mempunyai engimon tersisa");
                this.exit();
            }
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }

    }
    // Save Command
    public void save(){
        try {
            this.map.saveMap();
            this.player.savePlayer();
            AlertBox.displayInfo("Anda sukses mengesave", "Save");
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }
    // Load Command
    public void load(){
        try {
            this.map.loadMap("saveMap.txt");
            Player pal = new Player();
            pal.loadPlayer("savePlayer.txt");
            this.player = pal;
            AlertBox.displayInfo("Anda berhasil meload data", "Load");
            this.refreshMapGUI();
            this.refreshInventory();
            this.refreshActiveEngimonGUI();
        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }
    // Exit command
    public void exit(){
        Stage stage = (Stage) this.btn_exit.getScene().getWindow();
        stage.close();
    }

}

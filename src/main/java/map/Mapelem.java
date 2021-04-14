package main.java.map;

public class Mapelem {
    private int x; /* Tiles Abscissa Coordinate */
    private int y; /* Tiles Ordinate Coordinate */
    private char symbol; /* Tiles Symbol on Map that represents Tiles Type / Engimon / Active Engimon/ Player */
    private Boolean engimonExist; /* Boolean Status For The Existance of a Wild Engimon in this Tiles */
    private String engimon; /* Pointer to Engimon in this Tiles*/
    private String type; /* Tiles type either grassland or sea*/

    // Default ctor
    public Mapelem(){
        this.x = 0;
        this.y = 0;
        this.symbol = 'x';
        this.engimonExist = false;
        this.engimon = "undefined";
        this.type = "xxx";
    }
    // Ctor
    public Mapelem(int x1, int y1, char symbol1, Boolean exist1, String engi1, String type1){
        this.x = x1;
        this.y = y1;
        this.symbol = symbol1;
        this.engimonExist = exist1;
        this.engimon = engi1;
        this.type = type1;
    };
    
    // Getters and Setters 
    public void set_x(int x1){
        // I.S. atribut x pada Mapelem terdefinisi
        // F.S. atribut x pada Mapelem telah diubah menjadi x
        this.x = x1;
    };

    public int get_x(){
        // I.S. atribut x pada Mapelem terdefinisi
        // F.S. mengembalikan nilai atribut x pada Mapelem
        return this.x;
    }

    public void set_y(int y1){
        // I.S. atribut y pada Mapelem terdefinisi
        // F.S. atribut y pada Mapelem telah diubah menjadi y
        this.y = y1;
    };

    public int get_y() {
        // I.S. atribut y pada Mapelem terdefinisi
        // F.S. mengembalikan nilai atribut y pada Mapelem
        return this.y;
    };

    public void set_symbol(char sym){
        // I.S. atribut symbol pada Mapelem terdefinisi
        // F.S. atribut symbol pada Mapelem telah diubah menjadi sym
        this.symbol = sym;
    };

    public char get_symbol(){
        // I.S. atribut symbol pada Mapelem terdefinisi
        // F.S. mengembalikan nilai atribut symbol pada Mapelem
        return this.symbol;
    };

    public void set_type(String type){
        // I.S. atribut type pada Mapelem terdefinisi
        // F.S. atribut type pada Mapelem telah diubah menjadi type
        this.type = type;
    };

    public String get_type() {
        // I.S. atribut type pada Mapelem terdefinisi
        // F.S. mengembalikan nilai atribut type pada Mapelem
        return this.type;
    };

    // Engimon Managers 
    public void set_engimon(String engimon){
        // I.S. atribut engimon pada Mapelem terdefinisi
        // F.S. atribut engimon pada Mapelem merujuk ke engimon
        this.engimon = engimon;
    };

    public String get_engimon() {
        // I.S. atribut engimon pada Mapelem terdefinisi
        // F.S. mengembalikan nilai pointer to engimon dari atribut engimon pada Mapelem
        return this.engimon;
    };

    public void set_engimon_exist(Boolean status){
        // I.S. atribut engimonExist pada Mapelem terdefinisi
        // F.S. atribut engimonExist pada Mapelem telah diubah menjadi status
        this.engimonExist = status;
    };

    public Boolean isEngimonExist() {
        // I.S. atribut engimonExist pada Mapelem terdefinisi
        // F.S. mengembalikan nilai atribut engimonExist pada Mapelem
        return this.engimonExist;
    };

}

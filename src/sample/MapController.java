package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.java.engimon.Engimon;
import main.java.map.*;

import java.util.concurrent.atomic.AtomicInteger;

public class MapController {
    // Gripane tempat peta dibentuk
    @FXML private GridPane MapGridPane;

    // Tombol movement
    @FXML private Button btn_w;
    @FXML private Button btn_a;
    @FXML private Button btn_s;
    @FXML private Button btn_d;

    // Background tiap petak peta
    private  Image grass;
    private  Image sea;
    private  Image tundra;
    private  Image mountain;

    // Giliran
    private Integer turn;

    // Data peta
    private Map map;

    // Inisialisasi komponen peta
    @FXML private void initialize(){
        // Load resources yang diperlukan sekaligus refresh GUI
        try{
            this.map = new Map(20, 10, "map.txt");
            this.loadImage();
            this.refreshMapGUI();
            this.turn =0 ;
        } catch ( Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }
    public void loadImage(){
        // Melakukan load image
        this.grass = new Image("./main/resources/grass.png",35,35,false,false);
        this.sea = new Image("./main/resources/sea.png",35,35,false,false);
        this.tundra = new Image("./main/resources/tundra.png",35,35,false,false);
        this.mountain = new Image("./main/resources/mountain.png",35,35,false,false);
    }
    public void refreshMapGUI(){
        // Melakukan load ulang peta berdasarkan kondisi peta yang ada di array
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
                            Image player = new Image("./main/resources/player.png",35,35,false,false);
                            ImageView pl = new ImageView(player);
                            Npane.getChildren().add(pl);
                            Npane.setAlignment(pl, Pos.CENTER);
                            break;
                        case 'X':
                            Image activeEngimon = new Image("./main/resources/activeEngimon.png",30,30,false,false);
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
                this.map.spawnRandomEngimon(8);
            }
            this.refreshMapGUI();
            this.map.printMap();

        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }
    }
    public void Battle(){
        // Apabila tombol battle ditekan
        try{
            AtomicInteger x = new AtomicInteger(0);
            AtomicInteger y = new AtomicInteger(0);
            Engimon enemy = this.map.getNearbyEnemyEngimon(x,y);
            Boolean isBattle = BattleConfirm.display(enemy,enemy);
            if (isBattle){
                this.map.removeEngimon(x.get(),y.get());
                AlertBox.displayWarning("Menang");
                this.refreshMapGUI();
            } else {
                AlertBox.displayWarning("Kontol kok kabur");
            }

        } catch (Exception e){
            AlertBox.displayWarning(e.getMessage());
        }

    }


}

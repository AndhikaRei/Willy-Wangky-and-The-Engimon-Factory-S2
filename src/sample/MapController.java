package sample;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import main.java.map.*;

public class MapController {
    @FXML private GridPane MapGridPane;
    @FXML private Button btn_w;
    @FXML private Button btn_a;
    @FXML private Button btn_s;
    @FXML private Button btn_d;

    private  Image grass;
    private  Image sea;
    private  Image tundra;
    private  Image mountain;

    private Integer turn;


    private Map map;

    @FXML private void initialize(){
        try{
            this.map = new Map(20, 10, "map.txt");
            this.loadImage();
            this.refreshMapGUI();
            this.turn =0 ;
        } catch ( Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void loadImage(){
        this.grass = new Image("./main/resources/grass.png",35,35,false,false);
        this.sea = new Image("./main/resources/sea.png",35,35,false,false);
        this.tundra = new Image("./main/resources/tundra.png",35,35,false,false);
        this.mountain = new Image("./main/resources/mountain.png",35,35,false,false);
    }
    public void refreshMapGUI(){
        this.MapGridPane.getChildren().clear();
        for (int i=0; i< 10; i++){
            for (int j= 0; j < 20; j++){
                StackPane Npane = new StackPane();Mapelem tile = this.map.getMapElem().get(i).get(j);
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
                this.MapGridPane.add(Npane,j,i);
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
                if(tile.get_engimonExist() == Boolean.TRUE){
                    DropShadow ds = tile.get_engimon().getAura();
                    ds.setBlurType(BlurType.GAUSSIAN);
                    ds.setRadius(1.5);
                    ds.setSpread(1.5);
                    ImageView engimon = new ImageView(tile.get_engimon().getSprite());
                    engimon.setEffect(ds);
                    Npane.getChildren().add(engimon);
                    Npane.setAlignment(engimon, Pos.CENTER);
                }
            }
        }
    }

    public void move(ActionEvent event){
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
            System.out.println(e.getMessage());
        }

    }
}

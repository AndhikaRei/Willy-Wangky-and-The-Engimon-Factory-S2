package sample;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.java.map.*;

public class MapController {
    @FXML private GridPane MapGridPane;
    @FXML private Button btn_w;
    @FXML private Button btn_a;
    @FXML private Button btn_s;
    @FXML private Button btn_d;

    private Map map;

    @FXML private void initialize(){
        try{
            this.map = new Map(20, 10, "map.txt");
            this.refreshMapGUI();
        } catch ( Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void refreshMapGUI(){
        this.MapGridPane.getChildren().clear();
        for (int i=0; i< 10; i++){
            for (int j= 0; j < 20; j++){
                StackPane Npane = new StackPane();Mapelem tile = this.map.getMapElem().get(i).get(j);
                switch (tile.get_type()){
                    case "grassland":
                        Npane.setStyle("-fx-background-color: springgreen");
                        break;
                    case "sea":
                        Npane.setStyle("-fx-background-color: aqua");
                        break;
                    case("tundra"):
                        Npane.setStyle("-fx-background-color: skyblue");
                        break;
                    case("mountains"):
                        Npane.setStyle("-fx-background-color: saddlebrown");
                        break;
                    default:
                        Npane.setStyle("-fx-background-color: black");
                }
                this.MapGridPane.add(Npane,j,i);
                if (tile.get_symbol() != '-' && tile.get_symbol() != '*' && tile.get_symbol() !='^' &&tile.get_symbol() !='o'){
                    Circle tileSymbol = new Circle(10);
                    switch (tile.get_symbol()){
                        case 'P':
                            tileSymbol.setFill(Color.GOLD);
                            break;
                        case 'X':
                            tileSymbol.setFill(Color.SILVER);
                            break;
                    }
                    Npane.getChildren().add(tileSymbol);
                    Npane.setAlignment(tileSymbol, Pos.CENTER);
                }

            }
        }

    }

    public void move(ActionEvent event){
        if(event.getSource().equals(this.btn_w)){
            this.map.move('w');
        } else if (event.getSource().equals(this.btn_a)){
            this.map.move('a');
        }else if (event.getSource().equals(this.btn_s)){
            this.map.move('s');
        }else {
            this.map.move('d');
        }
        this.refreshMapGUI();
    }
}

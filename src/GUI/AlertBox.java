package GUI;

import javafx.stage.*;
import javafx.scene.control.*;
import main.java.engimon.Engimon;

public class AlertBox {
    // Menampilkan alert box dengan pesan tertentu
    public static void displayWarning(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exception detected");
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

    public static void displayInteract(Engimon activeEngimon){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Interact with active engimon");
        alert.setHeaderText(activeEngimon.getSpecies().concat(" : ").concat(activeEngimon.getName()));
        alert.setContentText(activeEngimon.getSlogan());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

}

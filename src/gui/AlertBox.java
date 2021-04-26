package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.*;
import javafx.scene.control.*;
import main.java.engimon.Engimon;

import java.util.Optional;

public class AlertBox {
    // Menampilkan alert box yang berisikan pesan konfirmasi
    // Alertbox yang ditampilkan berjenis CONFIRMATION
    public static Boolean displayConfirmation(String message, String title){
        // Inisialisasi dan mengisi alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);

        // Set supaya alert memblock tindakan pada window lain
        alert.initModality(Modality.APPLICATION_MODAL);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return Boolean.TRUE;
        } else {
            return  Boolean.FALSE;
        }
    }

    // Menampilkan alert box yang berisikan pesan kesalahan
    // Alertbox yang ditampilkan berjenis WARNING
    public static void displayWarning(String message){
        // Inisialisasi dan mengisi alert
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exception detected");
        alert.setContentText(message);
        alert.setHeaderText(null);

        // Set supaya alert memblock tindakan pada window lain
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
    // Menampilkan alert box yang berisikan infomarsi
    // Alertbox yang ditampilkan berjenis Information
    public static void displayInfo(String message, String title){
        // Inisialisasi dan mengisi alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);

        // Set supaya alert memblock tindakan pada window lain
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

    // Menampilkan alert box yang berisikan interaksi dengan active engimon
    // Alertbox yang ditampilkan berjenis INFORMATION
    public static void displayInteract(Engimon activeEngimon){
        // Inisialisasi dan mengisi alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Interact with active engimon");
        alert.setHeaderText(activeEngimon.getSpecies().concat(" : ").concat(activeEngimon.getName()));
        alert.setContentText(activeEngimon.getSlogan());

        // Set supaya alert memblock tindakan pada window lain
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

    // Menampilkan alert box yang meminta input String nama engimon yang baru
    // Alertbox yang ditampilkan berjenis TextInputDialog
    // return String hasil input atau null jika user tidak mengiput apa apa
    public static String displayAskNewName(){
        // Inisialisasi dan mengisi alert
        TextInputDialog dialog = new TextInputDialog(null);
        dialog.setTitle("Membuang item");
        dialog.setHeaderText("Masukkan nama baru untuk engimon");
        dialog.setContentText(null);

        // Set supaya alert memblock tindakan pada window lain
        Optional<String> result = dialog.showAndWait();

        // Dapatkan input yang dimasukkan oleh pengguna
        if (result.isPresent()){
            return result.get();
        } else {
            return null;
        }
    }

    // Menampilkan alert box yang meminta input String jumlah item yang ingin dibuang
    // Alertbox yang ditampilkan berjenis TextInputDialog
    // return representasi Integer hasil input atau null jika user tidak mengiput apa apa
    public static Integer displayAskNumDropItems(){
        // Inisialisasi dan mengisi alert
        TextInputDialog dialog = new TextInputDialog(null);
        dialog.setTitle("Rename Engimon");
        dialog.setHeaderText("Masukkan jumlah item yang ingin dibuang");
        dialog.setContentText(null);

        // Membuat input hanya bisa diisi oleh angka
        dialog.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    dialog.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        // Set supaya alert memblock tindakan pada window lain
        Optional<String> result = dialog.showAndWait();

        // Dapatkan representasi Integer dari input yang dimasukkan user
        if (result.isPresent()){
            return Integer.parseInt(result.get());
        } else {
            return null;
        }
    }
}

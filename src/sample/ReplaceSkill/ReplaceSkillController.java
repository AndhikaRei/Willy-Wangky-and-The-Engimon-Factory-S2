package sample.ReplaceSkill;

import sample.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.engimon.Engimon;
import main.java.skill.Skill;

import java.util.List;

public class ReplaceSkillController {
    // Tombol
    @FXML private Button btn_replace;
    @FXML private Button btn_cancel;

    // Tabel skill yang mau direplace
    @FXML private TableView tabel_skill;

    // Boolean jadi replace atau tidak
    private Boolean isReplace;
    // Index skill yang mau direplace
    private Integer idxReplace;

    public Boolean getIsReplace(){
        return this.isReplace;
    }
    public  Integer getIdxReplace(){return this.idxReplace; }

    // Apabila tombol replace di tekan
    // Menutup window dan set boolean isBattle menjadi true
    public void Replace(){
        if (this.tabel_skill.getSelectionModel().getSelectedIndices().isEmpty()){
            AlertBox.displayWarning("Anda harus memilih satu skill untuk di replace");
        } else {
            this.isReplace = Boolean.TRUE;
            this.idxReplace = this.tabel_skill.getSelectionModel().getSelectedIndex();
            Stage stage = (Stage) this.btn_replace.getScene().getWindow();
            stage.close();
        }
    }

    // Apabila tombol cancel di tekan
    // Menutup window dan set boolean isBattle menjadi true
    public void Cancel() {
        this.isReplace = Boolean.FALSE;
        this.idxReplace = null;
        Stage stage = (Stage) this.btn_cancel.getScene().getWindow();
        stage.close();
    }

    public void setupTable(){
        TableColumn<Skill, Integer> masteryColumn = new TableColumn<>("Mastery");
        masteryColumn.setMinWidth(30);
        masteryColumn.setSortable(false);
        masteryColumn.setCellValueFactory(new PropertyValueFactory<>("masteryLevel"));

        TableColumn<Skill, Integer> basePowerColumn = new TableColumn<>("Power");
        basePowerColumn.setMinWidth(30);
        basePowerColumn.setSortable(false);
        basePowerColumn.setCellValueFactory(new PropertyValueFactory<>("basePower"));

        TableColumn<Skill, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setSortable(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Skill, List<String>> elementColumn = new TableColumn<>("Element");
        elementColumn.setMinWidth(100);
        elementColumn.setSortable(false);
        elementColumn.setCellValueFactory(new PropertyValueFactory<>("element"));

        TableColumn<Skill, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setMinWidth(260);
        descriptionColumn.setSortable(false);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));

        this.tabel_skill.getColumns().addAll(masteryColumn,basePowerColumn,nameColumn,elementColumn,descriptionColumn);
        this.tabel_skill.getSelectionModel().setSelectionMode(
                SelectionMode.SINGLE
        );
    }

    public void loadData(Engimon ally){
        this.setupTable();
        ObservableList<Skill> skills = FXCollections.observableArrayList();
        skills.addAll(ally.getSkill());
        this.tabel_skill.setItems(skills);
    }
}

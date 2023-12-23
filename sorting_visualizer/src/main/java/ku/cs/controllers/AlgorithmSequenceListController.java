package ku.cs.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import ku.cs.models.SortingAlgorithm;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AlgorithmSequenceListController {
    public VBox vBox;
    private List<SortingAlgorithm> sortingAlgorithmList;


    public void initialize() {
        sortingAlgorithmList = new LinkedList<>();
    }

    public void addAlgorithm(SortingAlgorithm sortingAlgorithm) {
        sortingAlgorithmList.add(sortingAlgorithm);
        updateList();
    }

    public void removeAlgorithm(int index) {
        sortingAlgorithmList.remove(index);
    }

    public List<SortingAlgorithm> getSortingAlgorithmList() {
        return sortingAlgorithmList;
    }

    public void updateList() {
        vBox.getChildren().clear();
        for (SortingAlgorithm sortingAlgorithm : sortingAlgorithmList) {
            this.addItem(sortingAlgorithm);
        }
    }

    private void addItem(SortingAlgorithm sortingAlgorithm) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ku/cs/views/algorithm_sequence_item.fxml"));
        Node node;
        try {
            node = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        vBox.getChildren().add(node);
        AlgorithmSequenceItemController controller = fxmlLoader.getController();
        controller.setParentController(this);
        controller.setAlgorithm(sortingAlgorithm);
        controller.setIndex(vBox.getChildren().size() - 1);
    }
}

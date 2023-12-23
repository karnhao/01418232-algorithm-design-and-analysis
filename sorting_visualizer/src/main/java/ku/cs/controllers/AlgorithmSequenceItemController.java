package ku.cs.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ku.cs.models.SortingAlgorithm;

public class AlgorithmSequenceItemController {
    public Label nameLabel;
    public Button removeButton;
    private int index;

    private AlgorithmSequenceListController parentController;

    public void initialize() {

    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setParentController(AlgorithmSequenceListController algorithmSequenceListController) {
        this.parentController = algorithmSequenceListController;
    }

    public void setAlgorithm(SortingAlgorithm algorithm) {
        this.nameLabel.setText(algorithm.getClass().getSimpleName());
    }

    public void onRemoveButton() {
        parentController.removeAlgorithm(index);
        parentController.updateList();
    }
}

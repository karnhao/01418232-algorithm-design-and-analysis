package ku.cs.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import ku.cs.models.SortingAlgorithm;

public class AlgorithmSequenceItemController {
    public Label nameLabel;
    public Button removeButton;
    public VBox vBox;
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

    public void onDragDetected(MouseEvent mouseEvent) {
        /* drag was detected, start a drag-and-drop gesture*/
        /* allow any transfer mode */
        Dragboard db = vBox.startDragAndDrop(TransferMode.MOVE);

        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(index));
        db.setContent(content);

        mouseEvent.consume();
    }

    public void onDragOver(DragEvent dragEvent) {
        /* data is dragged over the target */
        /* accept it only if it is not dragged from the same node
         * and if it has a string data */
        if (dragEvent.getGestureSource() != vBox &&
                dragEvent.getDragboard().hasString()) {
            dragEvent.acceptTransferModes(TransferMode.MOVE);
        }

        dragEvent.consume();
    }

    public void onDragDone(DragEvent dragEvent) {
        /* the drag and drop gesture ended */
        /* if the data was successfully moved, clear it */
        if (dragEvent.getTransferMode() == TransferMode.MOVE) {
            parentController.updateList();
        }
        dragEvent.consume();
    }

    public void onDragDropped(DragEvent dragEvent) {
        /* data dropped */
        /* if there is a string data on dragboard, read it and use it */
        Dragboard db = dragEvent.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            SortingAlgorithm algorithm1 = parentController.getSortingAlgorithmList().get(Integer.parseInt(db.getString()));
            parentController.addAlgorithm(this.index, algorithm1);
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        dragEvent.setDropCompleted(success);

        dragEvent.consume();
    }

    public void onDragEntered(DragEvent dragEvent) {
    }

    public void onDragExited(DragEvent dragEvent) {
    }
}

package ku.cs.controllers;

import java.io.IOException;
import java.util.Arrays;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ku.cs.App;
import ku.cs.services.utils.Beep;
import ku.cs.services.utils.SortingSound;

public class SecondaryController {

    public HBox hBox;
    private Thread sequence1;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    public void test1() {
        if (sequence1 != null) {
            System.out.println("Interrupting...");
            sequence1.interrupt();
            sequence1 = null;
            return;
        }
        sequence1 = new Thread(() -> {
            final int delay = 20;
            // create sorted array
            int[] array = new int[32];
            for (int i = 0; i < array.length; i++) array[i] = i;

            SortingSound sortingSound = new SortingSound(array.length - 1);

            // shuffle array
            for (int i = array.length - 1; i > 0; i--) {
                if (Thread.interrupted()) return;
                // random index to swap
                int random_index = (int)Math.floor(Math.random() * array.length);
                int temp = array[random_index];
                array[random_index] = array[i];
                array[i] = temp;
                Platform.runLater(()-> updateHBox(array));
                Beep.tone(sortingSound.getHz(i), 50, 0.075);
                Beep.tone(sortingSound.getHz(temp), 50, 0.075);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    return;
                }
            }


            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (int i : array) System.out.print(i + ", ");
            System.out.println();

            // selection sort
            for (int i = 0; i < array.length - 1; i++) {
                int index = i;
                for (int j = i + 1; j < array.length; j++) {
                    int finalIndex = index;
                    int finalJ = j;
                    Platform.runLater(()-> updateHBox(array, finalIndex, finalJ));
                    Beep.tone(sortingSound.getHz(array[j]), 50, 0.125);
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        return;
                    }
                    if (array[j] < array[index]) index = j;
                }
                int smaller = array[index];
                array[index] = array[i];
                array[i] = smaller;
            }

            int[] selected = new int[array.length];
            Arrays.fill(selected, -1);

            // finish
            for (int i : array) {
                selected[i] = array[i];
                System.out.print(i + ", ");
                Platform.runLater(()-> updateHBox(array, selected));
                Beep.tone(sortingSound.getHz(array[i]), 50, 0.125);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    return;
                }
            }
            Platform.runLater(()-> updateHBox(array));
            System.out.println();
            System.out.flush();
            sequence1 = null;
        });
        sequence1.start();
    }

    private void updateHBox(int[] array, int... selected_index) {

        for (int i : selected_index)
            if (i > 0) array[i] = -array[i];


        hBox.getChildren().clear();
        for (int i = 0; i < array.length; i++) {
            Pane pane = new Pane();
            if (array[i] > 0) pane.setStyle("-fx-background-color: #006664");
            else {
                pane.setStyle("-fx-background-color: #b2bb1c");
                array[i] = -array[i];
            }


            pane.setPrefHeight(1 + array[i] * 10);
            pane.setMaxHeight(1 + array[i] * 10);
            pane.setPrefWidth(7);
            hBox.getChildren().add(pane);
        }
    }
}
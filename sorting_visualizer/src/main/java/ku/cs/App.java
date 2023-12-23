package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    private static String currentStyle;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1080, 720);
        stage.setScene(scene);
        App.stage = stage;

        setTheme("default.css");
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return App.stage;
    }

    public static void setTheme(String styleFileName) {
        URL stylesheetURL = App.class.getResource("/ku/cs/styles/" + styleFileName);
        String styleSheet = "";

        if (stylesheetURL != null) styleSheet = stylesheetURL.toExternalForm(); // theme
        if (currentStyle != null) App.stage.getScene().getStylesheets().remove(currentStyle);
        App.stage.getScene().getStylesheets().add(styleSheet);
        App.currentStyle = styleSheet;
    }
}
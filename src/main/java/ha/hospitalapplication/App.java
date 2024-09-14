package ha.hospitalapplication;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Scene previousScene;
    private static Stage stage;
    private static Stage newStage;

    /**
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        App.scene = new Scene(loadFXML("SignInMenu"), 840, 700);
        App.stage = stage;
        stage.setTitle("Hospital Manager");
        stage.setResizable(false);
        stage.setScene(App.scene);
        stage.show();
    }

    static void alterScene(String sceneToChangeTo, int width, int height) throws IOException {
        App.previousScene = App.scene;
        Scene newScene = new Scene(loadFXML(sceneToChangeTo), width, height);
        App.scene = newScene;
        stage.hide();
        stage.setScene(App.scene);
        stage.show();
    }

    static void createHelp() throws IOException {
        newStage = new Stage();
        Scene newScene = new Scene(loadFXML("HelpMenu"), 840, 700);
        stage.hide();
        newStage.setScene(newScene);
        newStage.show();
    }

    static void back() {
        stage.hide();
        stage.setScene(App.previousScene);
        stage.show();
        App.scene = App.previousScene;
    }

    static void backHelp() {
        newStage.close();
        stage.show();
    }

    /**
     *
     * @param fxml
     * @return
     * @throws IOException
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}

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

    /**
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("SignInMenu"), 840, 700);
        App.stage = stage;
        stage.setTitle("Hospital Manager");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        /*
         * App.stage = stage;
         * scene = new Scene(loadFXML("MainMenu"), 1500, 750);
         * stage.setTitle("Hospital Manager");
         * stage.setResizable(false);
         * stage.setScene(scene);
         * stage.show();
         */
    }

    static void alterScene(String scene, int width, int height) throws IOException {
        stage.hide();
        previousScene = App.scene;
        stage.setTitle("Hospital Manager");
        stage.setResizable(false);
        stage.setScene(new Scene(loadFXML(scene), width, height));
        stage.show();
    }

    static void back() {
        stage.hide();
        stage.setTitle("Hospital Manager");
        stage.setResizable(false);
        stage.setScene(previousScene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
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

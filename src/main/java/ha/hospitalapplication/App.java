package ha.hospitalapplication;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    private static Scene previousScene;
    private static Stage stage;
    private static Stage newStage;

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Changes the current scene to a new scene.
     * 
     * This method hides the current stage, sets the new scene, and then shows the
     * stage again. Part of the method sets the previous scene to the current scene
     * and this is done as it is more efficient/reliable to store it than create a
     * new one.
     * 
     * @param sceneToChangeTo
     * @param width
     * @param height
     * @throws IOException
     */
    static void alterScene(String sceneToChangeTo, int width, int height) throws IOException {
        App.previousScene = App.scene;
        Scene newScene = new Scene(loadFXML(sceneToChangeTo), width, height);
        App.scene = newScene;
        stage.hide();
        stage.setScene(App.scene);
        stage.show();
    }

    /**
     * Creates a new help window.
     * 
     * This method creates a new stage and scene for the help menu, hides the main
     * stage, and then shows the new help stage. I couldn't figure out an efficient
     * way to store the previous scene when opening the help menu so my solution was
     * just to pop up a new stage and hide the previous one so that the user is sent
     * back to the screen they were previously at.
     * 
     * @throws IOException
     */
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

    @Override
    public void start(Stage stage) throws IOException {
        App.scene = new Scene(loadFXML("SignInMenu"), 840, 700);
        App.stage = stage;
        stage.setTitle("Hospital Manager");
        stage.setResizable(false);
        stage.setScene(App.scene);
        stage.show();
    }

}

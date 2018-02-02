package com.mycompany.projectv2;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    private String[][] puzzle;
    private String[][] c;
    private static Scene scene;
    private static FXMLLoader loader, loaderM;
    private static Stage stage;
            
    @Override 
    public void start(final Stage stage) throws Exception {
        MainApp.stage = stage;
        loaderM = new FXMLLoader(getClass().getResource("/fxml/OpeningScene.fxml"));
        loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        showSelection();
    }
    
    public static void showSelection() throws IOException{
        scene = new Scene((Parent) loaderM.load());
        scene.getStylesheets().add("/style/Styles.css");     
        stage.setTitle("New York Times Mini Puzzle");
        stage.setScene(scene);
        stage.show();
    }//Scene to choose today or previous
    
    public static void showToday() throws IOException{
        loader.setController(new TodayController());
        scene = new Scene((Parent) loader.load());
        scene.getStylesheets().add("/style/Styles.css");     
        stage.setTitle("New York Times Mini Puzzle");
        stage.setScene(scene);
        stage.show();
    }//Scene to show today's puzzle
    public static void showPrev() throws IOException{
        loader.setController(new PrevController());
        scene = new Scene((Parent) loader.load());
        scene.getStylesheets().add("/style/Styles.css");
        stage.setTitle("New York Times Mini Puzzle");
        stage.setScene(scene);
        stage.show();
    }//Scene to show previous puzzle
    public static void main(String[] args) {
        launch(args);    
    }
    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectv2;
import com.mycompany.projectv2.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 *
 * @author Güneş
 */
public class OpeningController implements Initializable{
    @FXML
    private Button TodayButton;
    @FXML
    private Button PreviousButton;    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TodayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                try {
                    MainApp.showToday();
                } catch (IOException ex) {
                    Logger.getLogger(OpeningController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        PreviousButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                try {
                    MainApp.showPrev();
                } catch (IOException ex) {
                    Logger.getLogger(OpeningController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    
    
    
}

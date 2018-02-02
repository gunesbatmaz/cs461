

package com.mycompany.projectv2;

import Parser.HTMLParser;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class TodayController implements Initializable{
    private final boolean isToday = true;
    @FXML
    private Button CheckButton;
    @FXML
    private Button RevealButton;
    @FXML
    private Button PrevButton;
    @FXML
    private ListView<String> Clues;
    @FXML
    private Label date;
    @FXML
    private Label p00;
    @FXML
    private Label p01;
    @FXML
    private Label p02;
    @FXML
    private Label p03;
    @FXML
    private Label p04;
    @FXML
    private Label p10;
    @FXML
    private Label p11;
    @FXML
    private Label p12;
    @FXML
    private Label p13;
    @FXML
    private Label p14;
    @FXML
    private Label p20;
    @FXML
    private Label p21;
    @FXML
    private Label p22;
    @FXML
    private Label p23;
    @FXML
    private Label p24;
    @FXML
    private Label p30;
    @FXML
    private Label p31;
    @FXML
    private Label p32;
    @FXML
    private Label p33;
    @FXML
    private Label p34;
    @FXML
    private Label p40;
    @FXML
    private Label p41;
    @FXML
    private Label p42;
    @FXML
    private Label p43;
    @FXML
    private Label p44;
    
    @FXML
    private TextArea t00;
    @FXML
    private TextArea tn00;
    @FXML
    private TextArea t01; 
    @FXML
    private TextArea t02; 
    @FXML
    private TextArea t03;
    @FXML
    private TextArea t04; 
    @FXML
    private TextArea t10;
    @FXML
    private TextArea t11; 
    @FXML
    private TextArea t12; 
    @FXML
    private TextArea t13;
    @FXML
    private TextArea t14; 
    @FXML
    private TextArea t20;
    @FXML
    private TextArea t21; 
    @FXML
    private TextArea t22; 
    @FXML
    private TextArea t23;
    @FXML
    private TextArea t24; 
    @FXML
    private TextArea t30;
    @FXML
    private TextArea t31; 
    @FXML
    private TextArea t32; 
    @FXML
    private TextArea t33;
    @FXML
    private TextArea t34; 
    @FXML
    private TextArea t40;
    @FXML
    private TextArea t41; 
    @FXML
    private TextArea t42; 
    @FXML
    private TextArea t43;
    @FXML
    private TextArea t44; 
    
    
    @FXML
    public ListView<String> listView;
    
    ObservableList<String> list = FXCollections.observableArrayList();
    public static String[][] puzzle;
    public static String[][] nums;
    public static String[][] c;
    public static String d;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CheckButton.setVisible(true);
        PrevButton.setVisible(false);
        final LinkedList<TextArea> areaList = new LinkedList<>();
        areaList.add(t00);
        areaList.add(t01);
        areaList.add(t02);
        areaList.add(t03);
        areaList.add(t04);
        areaList.add(t10);
        areaList.add(t11);
        areaList.add(t12);
        areaList.add(t13);
        areaList.add(t14);
        areaList.add(t20);
        areaList.add(t21);
        areaList.add(t22);
        areaList.add(t23);
        areaList.add(t24);
        areaList.add(t30);
        areaList.add(t31);
        areaList.add(t32);
        areaList.add(t33);
        areaList.add(t34);
        areaList.add(t40);
        areaList.add(t41);
        areaList.add(t42);
        areaList.add(t43);
        areaList.add(t44);
        final LinkedList<Label> labelList = new LinkedList<>();
        labelList.add(p00);
        labelList.add(p01);
        labelList.add(p02);
        labelList.add(p03);
        labelList.add(p04);
        labelList.add(p10);
        labelList.add(p11);
        labelList.add(p12);
        labelList.add(p13);
        labelList.add(p14);
        labelList.add(p20);
        labelList.add(p21);
        labelList.add(p22);
        labelList.add(p23);
        labelList.add(p24);
        labelList.add(p30);
        labelList.add(p31);
        labelList.add(p32);
        labelList.add(p33);
        labelList.add(p34);
        labelList.add(p40);
        labelList.add(p41);
        labelList.add(p42);
        labelList.add(p43);
        labelList.add(p44);

                
        HTMLParser p = new HTMLParser(isToday);
        
        puzzle = p.getPuzzle();
        nums = p.getNums();
        for(int i = 0; i < 5; i++)
            for(int j = 0; j <5; j++)
                labelList.get(i*5+j).setText(nums[i][j].equals("none")?"":nums[i][j]);
        d = p.getDate();

        //System.out.println(nums[0][0]);
        date.setText("Mini Puzzle: " + d);
        c = p.getClues();
        list.add("Across");
        for(int i = 0; i< c.length+1; i++) {
            if(i < 5)
                list.add(i+1, c[i][0] + ")" + c[i][1]);
            else if(i == 5)
                list.add("Down");
            else
                list.add(i+1, c[i-1][0] + ")" + c[i-1][1]);
        }
        //list.add("Across");
        //list.add("Down");
        listView.setItems(list);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        /*int qNum = 0;
        for(int i = 0; i < 25 && qNum < c.length; i++){
            if(labelList.get(i).equals("")){
                if(!(puzzle[i/5][i%5].equals("black"))&&((i%5) == 0 || (i/5) ==0))
                    labelList.get(i).setText((qNum++)+"");
                else if((i%5) == 0 && (puzzle[i/5][i%5].equals("black")))
                    labelList.get(++i).setText((qNum++)+"");
                else if((((int)(i/5))== 0) && (puzzle[i/5][i%5].equals("black")))
                    labelList.get(i+5).setText((qNum++)+"");
            }
        }*/


        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if((puzzle[i][j].equals("black"))){
                    areaList.get(5*i+j).setEditable(false);
                    labelList.get(5*i+j).setMouseTransparent(false);
                //    System.out.println(labelList.size());
                //    System.out.println(labelList.get(5*i+j));
                    labelList.get(5*i+j).setStyle("-fx-background-color: rgba(0,0,0,0.7);");//setBackground(new Background(new BackgroundFill(Color.black, new CornerRadii(0), null)));
                }
        /*areaList.get(0).addEventFilter(MouseEvent.MOUSE_PRESSED, e ->
                areaList.get(0).setStyle("-fx-background-color: green;"));*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene_1.fxml"));
        loader.setController(new TodayRevealedController());
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TodayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        RevealButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                boolean flag = true;
                boolean checkflag = true;
                do{
                    flag = true;
                    boolean xxflag = false;
                    int []blackcount = new int[areaList.size()];
                    for(int i = 0; i < 5; i++){
                        for(int j = 0; j < 5; j++){
                            if((puzzle[i][j].equals("black"))){
                                blackcount[5*i+j] = 1;
                            }
                        }
                    }
                    try{
                        ArrayList<String> tryanswers = PathToFollow.decidePath(areaList,isToday,puzzle,nums,c,checkflag);
                        for(int i = 0; i < c.length; i++){
                            if(i<5){
                                String loc =c[i][0];//question number
                                int finalloc = -1;
                                for(int j = 0; j < 5; j++){
                                    for(int k = 0; k < 5; k++){
                                        if(nums[j][k].equals(loc))
                                            finalloc = j;

                                    }
                                }
                                for(int j = 0; j < tryanswers.size();j++){
                                    String str = "";
                                    int count = 0;
                                    for(int k = 0; k < 5; k++){
                                        if(!(puzzle[finalloc][k].equals("black")) && finalloc != -1 && count < tryanswers.get(j).length()){
                                            if(puzzle[finalloc][k].equals(tryanswers.get(j).substring(count,count+1))){
                                                if(areaList.get(5*finalloc+k).getText().equals("")) {
                                                    areaList.get(5 * finalloc + k).setText(puzzle[finalloc][k]);
                                                    xxflag = true;
                                                    System.out.println(puzzle[k][finalloc] + (5*k+finalloc));
                                                }

                                            }
                                            count++;
                                        }

                                    }

                                }
                            }
                            else{
                                String loc =c[i][0];//question number
                                int finalloc = -1;
                                for(int j = 0; j < 5; j++){
                                    for(int k = 0; k < 5; k++){
                                        if(nums[j][k].equals(loc))
                                            finalloc = k;

                                    }
                                }
                                for(int j = 0; j < tryanswers.size();j++){
                                    String str = "";
                                    int count = 0;
                                    for(int k = 0; k < 5; k++){
                                        //int num = i%5;
                                        if(!(puzzle[k][finalloc].equals("black")) && finalloc != -1 && count < tryanswers.get(j).length()){
                                            if(puzzle[k][finalloc].equals(tryanswers.get(j).substring(count,count+1))){
                                                if(areaList.get(5*k+finalloc).getText().equals("")) {
                                                    areaList.get(5 * k + finalloc).setText(puzzle[k][finalloc]);
                                                    xxflag = true;
                                                    System.out.println(puzzle[k][finalloc] +" "+ (5*k+finalloc));
                                                }

                                            }
                                            count++;
                                        }
                                        //str = str + puzzle[i][k];
                                    }
                                }
                            }
                        }
                        if(!xxflag)
                            checkflag = false;
                        else
                            checkflag = true;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    for(int i = 0; i < areaList.size();i++){
                        if(areaList.get(i).getText().equals("") && blackcount[i] != 1){
                            flag = false;
                         //   System.out.println("Database Coulc not find " + i);
                        }
                    }
                }while(!flag && PathToFollow.count < 40);
                /*int t = 0;



                for(int i = 0; i < 5; i++)
                    for(int j = 0; j < 5; j++){
                        if(!(puzzle[i][j].equals("black"))){
                            labelList.get(5*i+j).setStyle("-fx-background-color: rgba(255,0,0,0.2);");
                            areaList.get(t).setEditable(false);
                            areaList.get(t).setText(puzzle[i][j]);

                        }
                        t++;
                    }*/
            }
        });
        CheckButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                int t = 0;
                for(int i = 0; i < 5; i++)
                    for(int j = 0; j < 5; j++){
                        if(!(puzzle[i][j].equals("black"))){
                            String data=areaList.get(t).getText().trim();//read contents of text area into 'data'
                            System.out.println(data);
                            if(!data.equals("")){
                                if(!data.equals(puzzle[i][j])){
                                    areaList.get(t).setWrapText(true);
                                    areaList.get(t).setEditable(true);
                                    labelList.get(5*i+j).setStyle("-fx-background-color: rgba(60,60,60,0.2);");
                                }
                                else{
                                    areaList.get(t).setWrapText(true);
                                    areaList.get(t).setEditable(false);
                                    labelList.get(5*i+j).setStyle("-fx-background-color: rgba(204, 255, 102,0.2);");
                                }
                            }
                        }
                        t++;
                    }
            }
        });
        
        PrevButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    MainApp.showSelection();
                } catch (IOException ex) {
                    Logger.getLogger(TodayController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}    

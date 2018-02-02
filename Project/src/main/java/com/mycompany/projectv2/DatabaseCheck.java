package com.mycompany.projectv2;

import Parser.HTMLParser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCheck {
    //public static ArrayList<String> questions = new ArrayList<String>();
    public static ArrayList<String> answers = new ArrayList<String>();
    public static ArrayList<String> questions = new ArrayList<String>();
    public static void databaseCheck(boolean today, String [][] clues)throws IOException{
        File file = new File("C:\\Users\\Alper\\Desktop\\ProjectV2\\ProjectV2\\src\\main\\java\\Parser\\clues.csv");
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);


        //HTMLParser p = new HTMLParser(today);
        //String [][] data1 = p.getClues();
        //String [][] dataw = p.getPuzzle();
        //System.out.println(dataw[0][1]);
        for (String line : lines) {
            String[] array = line.split(",");
            for(int i = 0; i < clues.length;i++){
                if(line.contains(clues[i][1])){
                        //System.out.println(questions.size());
                        //questions.add(data1[i][1]);
                        answers.add(array[array.length-1].substring(1,array[array.length-1].length()-1));
                }
            }
        }
        for(int i = 0; i < answers.size(); i++){
        //    System.out.println(questions.get(i));
            System.out.println(answers.get(i));
        }
        //System.out.println(questions.size());

    }
    /*public static void checkAnswers(String [][] c,String [][] nums){

    }
    public static void main(String[] args) {
        try{
            databaseCheck(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/
}

package com.mycompany.projectv2;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.LinkedList;

public class PathToFollow {
    public static boolean isDatabaseUsed = false;
    public static boolean isDictionaryUsed = false;
    public static boolean isMovieUsed = false;
    public static boolean isGoogleUsed = false;
    public static boolean isAbbUsed = false;
    public static ArrayList<String> questionorder = new ArrayList<String>();
    public static int count = 0;
    public static int count1 = 0;
    public static ArrayList<String> decidePath(LinkedList<TextArea> areaList, boolean today, String[][] puzzle, String[][] nums, String[][] clues, boolean flag) throws Exception{
        ArrayList<String> answers = new ArrayList<String>();
        /*if(!isDatabaseUsed){
            DatabaseCheck.databaseCheck(today,clues);
            answers = DatabaseCheck.answers;
            isDatabaseUsed = true;
            count++;
        }*/
        questionorder = Heuristic.getQuestion(areaList, puzzle, nums, clues);
        String question = "";
        if(flag){
            count1 = 0;
            question = questionorder.get(0);
        }
        else{
            count1++;
            if(count1 > 9){
                count1 = 9;
                count = 40;
            }

            question = questionorder.get(count1);
        }
        System.out.println(question);
        if(!isDictionaryUsed){
            System.out.println("entered reverse");
            ReverseDictionary rd = new ReverseDictionary(question);
            rd.executePost();
            answers = rd.reverseDictionary;
            isDictionaryUsed = true;
            count++;
        }
        else if(!isGoogleUsed && !question.equals("")){
            System.out.println("entered google");
            //Google Methodu
            int pos = -1;
            String beginningnumber = "0";
            question.replace("\"", "\'");
            for(int i = 0; i < clues.length; i++){
                if(clues[i][1].equals(question)){
                    pos = i;
                    beginningnumber = clues[i][0];
                }
            }
            int loc = getPos(pos,beginningnumber,nums);//getting the location of the row or column in the arealist of the specific question.
            int length = 0;
            String [][] arr = new String[5][2];
            if(pos < 5){
                for(int i = 0; i < 5; i++){
                    if(!(puzzle[loc][i].equals("black"))){
                        length++;
                    }
                }
            }
            else{
                for(int i = 0; i < 5; i++){
                    if(!(puzzle[i][loc].equals("black"))){
                        length++;
                    }
                }
            }//We got the array and the length of the answer
            answers = Deneme.getGoogleSearch(question,length);
            isGoogleUsed = true;
            count++;
        }
        else if(!isMovieUsed && !question.equals("")){
            System.out.println("entered movie");
            //Movie Methodu
            int pos = -1;
            String beginningnumber = "0";
            question.replace("\"", "\'");
            //Abbreviation Methodu
            for(int i = 0; i < clues.length; i++){
                if(clues[i][1].equals(question)){
                    pos = i;
                    beginningnumber = clues[i][0];
                }
            }
            int loc = getPos(pos,beginningnumber,nums);//getting the location of the row or column in the arealist of the specific question.
            int length = 0;
            String [][] arr = new String[5][2];
            if(pos < 5){
                for(int i = 0; i < 5; i++){
                    if(!(puzzle[loc][i].equals("black"))){
                        length++;
                    }
                }
            }
            else{
                for(int i = 0; i < 5; i++){
                    if(!(puzzle[i][loc].equals("black"))){
                        length++;
                    }
                }
            }//We got the array and the length of the answer
            if(filmSearch.searchMovie(question,length) != null)
                answers = filmSearch.searchMovie(question,length);

            isMovieUsed = true;
            count++;
        }
        else if(!isAbbUsed && !question.equals("")){
            System.out.println("entered abb");
            int pos = -1;
            String beginningnumber = "0";
            question.replace("\"", "\'");
            //Abbreviation Methodu
            for(int i = 0; i < clues.length; i++){
                if(clues[i][1].equals(question)){
                    pos = i;
                    beginningnumber = clues[i][0];
                }
            }
            int loc = getPos(pos,beginningnumber,nums);//getting the location of the row or column in the arealist of the specific question.
            int length = 0;
            int count1 = 0;
            String [][] arr = new String[5][2];
            if(pos < 5){
                for(int i = 0; i < 5; i++){
                    if(!(puzzle[loc][i].equals("black"))){
                        if(!areaList.get(5*loc+i).getText().equals("")){
                            String [] temp = new String[2];
                            temp[0] = areaList.get(5*loc+i).getText();
                            temp[1] = ""+i;
                            arr[count1] = temp;
                            count1++;
                        }
                        length++;
                    }
                }
            }
            else{
                for(int i = 0; i < 5; i++){
                    if(!(puzzle[i][loc].equals("black"))){
                        if(!areaList.get(5*i+loc).getText().equals("")){
                            String [] temp = new String[2];
                            temp[0] = areaList.get(5*loc+i).getText();
                            temp[1] = ""+i;
                            arr[count1] = temp;
                            count1++;
                        }
                        length++;
                    }
                }
            }//We got the array and the length of the answer
            answers = GetAbbrClass1.getAbbr(question,length,arr);

            isAbbUsed = true;
            count++;
        }
        else{
            isMovieUsed = false;
            isGoogleUsed = false;
            isDictionaryUsed = false;
            isAbbUsed = false;
        }
        //}

        return answers;
    }
    public static int getPos(int locquestion, String numberquestion, String[][] nums){
        int finalloc = 0;
        if(locquestion < 5){
            for(int j = 0; j < 5; j++){
                for(int k = 0; k < 5; k++){
                    if(nums[j][k].equals(numberquestion))
                        finalloc = j;

                }
            }
        }
        else{
            for(int j = 0; j < 5; j++){
                for(int k = 0; k < 5; k++){
                    if(nums[j][k].equals(numberquestion))
                        finalloc = k;

                }
            }
        }
        return finalloc;
    }

}

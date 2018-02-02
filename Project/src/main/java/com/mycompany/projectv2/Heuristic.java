package com.mycompany.projectv2;

import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.LinkedList;

public class Heuristic {
    public static boolean [] posarray = new boolean[10];
    public static int count = 0;
    public static ArrayList<String> temp1 = new ArrayList<String>();
    public static ArrayList<String> getQuestion(LinkedList<TextArea> areaList,String [][] puzzle, String[][] nums,String [][] clues){
        for(int i = 0; i < 10; i++){
            posarray[i] = true;
        }


        String temp = "";
        double [] totalratio = new double[10];
        int [] totalwords = new int[10];
        for(int i = 0; i < 5; i++){
            double count = 0;
            double count1 = 0;
            for(int j = 0; j < 5; j++){
                if(!(puzzle[i][j].equals("black"))){
                    if(areaList.get(5*i+j).getText().equals("")){
                        count1++;
                    }
                    else{
                        count++;
                        count1++;
                    }
                }
            }
            totalratio[i] = count/count1;
            totalwords[i] = (int)count1;
        }
        for(int i = 5; i < 10; i++){
            double count = 0;
            double count1 = 0;
            for(int j = 0; j < 5; j++){
                if(!(puzzle[j][i-5].equals("black"))){
                    if(areaList.get(5*j+i-5).getText().equals("")){
                        count1++;
                    }
                    else{
                        count++;
                        count1++;
                    }
                }
            }
            totalratio[i] = count/count1;
            totalwords[i] = (int)count1;
        }
        while(count < 10){
            //We found the ratios. Now we need to find the highest one. It will give us the column or the row
            int pos= -1;//position is important as well
            double largest = 0;
            for(int i = 0; i < 10; i++){

                if(totalratio[i] > largest && totalratio[i] != 1 && posarray[i]){
                    largest = totalratio[i];
                    pos = i;
                }
                else if(totalratio[i] == largest && totalratio[i] != 1 && posarray[i]){
                    if(pos == -1){
                        largest = totalratio[i];
                        pos = i;
                    }
                    else{
                        if(totalwords[i] <= totalwords[pos]){
                            largest = totalratio[i];
                            pos = i;
                        }
                    }
                }
            }
            posarray[pos] = false;
            //found the column or the row. Now we need to find the number at the beginning of the column or row
            //so we can get the question and return.
            boolean flag = true;
            String number = "";
            if(pos < 5){
                for(int i = 0; i < 5 && flag; i++){
                    if(!(nums[pos][i].equals("none"))){
                        number = nums[pos][i];
                        flag = false;
                    }
                }
            }
            else{
                for(int i = 0; i < 5 && flag; i++){
                    if(!(nums[i][pos-5].equals("none"))){
                        number = nums[i][pos-5];
                        flag = false;
                    }
                }
            }
            //We have found the number. Now we have to basically search in the clues and find the clue and return it.
            //int pos1 = -1;
            //int smallest = 5;
            for(int i = 0; i < 10;i++){
                if(clues[i][0].equals(number)){
                    if(i < 5 && pos < 5){
                        temp = clues[i][1];
                        //smallest = totalwords[i];
                    }
                    if(i > 4 && pos > 4){
                        temp = clues[i][1];
                    }
                }
            }
            temp1.add(temp);
            count++;
            //return temp;
        }
        for(int i = 0; i < temp1.size();i++){
            System.out.println(temp1.get(i) + "is number: " + i);
        }
        return temp1;

    }
}

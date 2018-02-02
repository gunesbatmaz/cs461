package com.mycompany.projectv2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.*;


public class GetAbbrClass1 {

    /*This method can be used for questions such as "The "M" in W.M.D"*/
    public static ArrayList<String> getAbbr(String clue, int answerSize, String[][] constraints) throws IOException
    {
        ArrayList<String> A = new ArrayList<String>();

    	/*Manipulate the clue here
    	 * "The "M" in W.M.D" -> WMD*/
        String abbr = "([A-Z]\\.)*[A-Z]$";
        Pattern p = Pattern.compile(abbr);
        Matcher m = p.matcher(clue);
        String str = ""; //Abbreviation to search for
        if(m.find())
        {
            str = m.group(0);
            str = str.replaceAll("\\.", "");
            str = str.toLowerCase();
        }
        else
            return A;

        String url = "https://acronyms.thefreedictionary.com/"+str;

        Document doc = null;
        doc = Jsoup.connect(url).get();//use the parameter here, in place of /wmd

        /*For "The "M" in W.M.D" example: Finding the words start with 'M' and has 4 letters*/
        int index = clue.indexOf('\'');
        char startingChar = clue.charAt(index+1);
        String rowText;
        Element table = doc.getElementById("AcrFinder");
        for(Element row : table.select("tr"))
        {
            rowText = (row.text());
            rowText= rowText.replaceAll("(", "");
            rowText= rowText.replaceAll(")", "");
            rowText= rowText.replaceAll("?", "");
            String[] answers = rowText.split("\\s+");
            for(int i = 0; i<answers.length ; i++){
                if (answers[i].length()==answerSize && answers[i].charAt(0)== startingChar){
                    int flag =1;
                    for(int k=0 ; k<constraints.length ; k++){
                        if((answers[i].charAt(Integer.parseInt(constraints[k][0]))+"").equalsIgnoreCase(constraints[k][1]))
                            flag = flag * 1;
                        else
                            flag = flag * 0;
                    }
                    if(flag==1)
                        A.add(answers[i]);
                }
            }
        }
        return A;
    }

    /*TODO:
     * http://abbreviations.yourdictionary.com/articles/list-of-commonly-used-abbreviations.html burdan da*/

    /*public static void main(String[] args) throws IOException {

        String constr[][]={{"0","F"}};
        ArrayList<String> B= getAbbr("The 'F' in W.T.F", 5, constr);
        for(int i=1; i<B.size(); i++){
            System.out.println(B.get(i).toString());
        }

    }*/
}



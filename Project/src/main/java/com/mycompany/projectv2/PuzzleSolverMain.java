package com.mycompany.projectv2;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class PuzzleSolverMain
{

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, InterruptedException
    {
        String clue = "Southern stew made with okra";
        String x = "gumbo";
        OneLookDictionary dictionary = new OneLookDictionary(clue);
        dictionary.executePost();

        for(int i  = 0; i<dictionary.oneLookDictionary.size();i++)
        {

            if(x.equalsIgnoreCase(dictionary.oneLookDictionary.get(i)))
            {
                System.out.println(true);
            }

        }

        ReverseDictionary dictionary2 = new ReverseDictionary(clue);
        dictionary2.executePost();

        for(int i  = 0; i<dictionary2.reverseDictionary.size();i++)
        {

            if(x.equalsIgnoreCase(dictionary2.reverseDictionary.get(i)))
            {
                System.out.println(true);
            }

        }


    }


}

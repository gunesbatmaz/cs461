package com.mycompany.projectv2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;



import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DictionarySolver
{


    private static String word;


    public  ArrayList<String> syn = new ArrayList<String>();
    public  ArrayList<String> rel = new ArrayList<String>();
    public  ArrayList<String> near= new ArrayList<String>();
    public  ArrayList<String> ant= new ArrayList<String>();

    public DictionarySolver(String word) throws ParserConfigurationException, SAXException, IOException
    {
        this.word = word;

        findWord();

    }

    public ArrayList<String> sendSynonyms()
    {
        String temp ="";
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i<syn.size();i++)
        {
            for(int x = 0; x<syn.get(i).length();x++)
            {

                if(syn.get(i).charAt(x) ==',' ||syn.get(i).charAt(x)==';')
                {
                    result.add(temp);
                    temp = "";
                }
                else if(syn.get(i).charAt(x) != ' ')
                {
                    temp +=  syn.get(i).charAt(x);
                }


            }

        }

        return result;
    }
    public ArrayList<String> sendRel()
    {
        String temp ="";
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i<rel.size();i++)
        {
            for(int x = 0; x<rel.get(i).length();x++)
            {

                if(rel.get(i).charAt(x) ==',' ||rel.get(i).charAt(x)==';')
                {
                    result.add(temp);
                    temp = "";
                }
                else if(rel.get(i).charAt(x) != ' ')
                {
                    temp +=  rel.get(i).charAt(x);
                }


            }

        }

        return result;
    }

    public ArrayList<String> sendNear()
    {
        String temp ="";
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i<near.size();i++)
        {
            for(int x = 0; x<near.get(i).length();x++)
            {

                if(near.get(i).charAt(x) ==',' ||near.get(i).charAt(x)==';')
                {
                    result.add(temp);
                    temp = "";
                }
                else if(near.get(i).charAt(x) != ' ')
                {
                    temp +=  near.get(i).charAt(x);
                }


            }

        }

        return result;
    }

    public ArrayList<String> sendAnt()
    {
        String temp ="";
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i<ant.size();i++)
        {
            for(int x = 0; x<ant.get(i).length();x++)
            {

                if(ant.get(i).charAt(x) ==',' ||ant.get(i).charAt(x)==';')
                {
                    result.add(temp);
                    temp = "";
                }
                else if(ant.get(i).charAt(x) != ' ')
                {
                    temp +=  ant.get(i).charAt(x);
                }


            }

        }

        return result;
    }
    private void findWord() throws ParserConfigurationException, SAXException, IOException
    {
        executePost();
        File xmlFile = new File("Dictionary.xml");


        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new FileInputStream(xmlFile));
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("entry");
        System.out.println("----------------------------");

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);


            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                int i = 0;
                while(eElement.getElementsByTagName("syn").item(i)!=null)
                {
                    syn.add(eElement.getElementsByTagName("syn").item(i).getTextContent());
                    i++;

                }
                i = 0;
                while(eElement.getElementsByTagName("rel").item(i)!=null)
                {
                    rel.add(eElement.getElementsByTagName("rel").item(i).getTextContent());
                    i++;
                }

                i = 0;
                while(eElement.getElementsByTagName("near").item(i)!=null)
                {
                    near.add(eElement.getElementsByTagName("near").item(i).getTextContent());
                    i++;
                }

                i = 0;
                while(eElement.getElementsByTagName("ant").item(i)!=null)
                {
                    ant.add(eElement.getElementsByTagName("ant").item(i).getTextContent());
                    i++;
                }




            }
        }
    }

    private  void makeXML(String xml)
    {
        try {
            File file = new File("Dictionary.xml");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(xml);

            fileWriter.flush();
            fileWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private  void  executePost() throws IOException
    {
        String a = "https://www.dictionaryapi.com/api/v1/references/thesaurus/xml/"+word+"?key=9e91fd90-ab82-49b6-bc30-19df3698ab82";
        URLConnection connection = new URL(a).openConnection();
        connection
                .setRequestProperty("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();

        BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                Charset.forName("UTF-8")));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            sb.append(line);
        }



        makeXML(sb.toString());
    }


}

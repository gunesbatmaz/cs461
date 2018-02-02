package com.mycompany.projectv2;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptJobManager;

public class OneLookDictionary
{

    public ArrayList<String> oneLookDictionary = null;
    private String key;
    public OneLookDictionary(String key)
    {
        this.key = key;
        oneLookDictionary = new ArrayList<String>();
    }

    public  void  executePost() throws IOException, InterruptedException
    {

        key = key.replaceAll(" ", "%20");
        String url = "https://www.onelook.com/thesaurus/beta/?s="+key;
        WebClient webClient =  new WebClient();
        HtmlPage page = null;

        try
        {
            page = webClient.getPage(url);
        }
        catch (Exception e)
        {
            System.out.println("Get page error");
        }

        JavaScriptJobManager manager = page.getEnclosingWindow().getJobManager();
        while (manager.getJobCount() > 0)
        {
            webClient.waitForBackgroundJavaScript(1);
        }
        List<HtmlElement> divs = (List<HtmlElement>)page.getByXPath("//li/div[@resid]");




        for(int i = 0; i<divs.size() && i<80;i++)
        {
            oneLookDictionary.add(divs.get(i).getFirstChild().asText());
        }

    }



}


package com.mycompany.projectv2;



import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptJobManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReverseDictionary {
    private String key;
    public ArrayList<String> reverseDictionary = null;

    public ReverseDictionary(String key)
    {
        this.key = key;
        reverseDictionary = new ArrayList<String>();
    }





    public  void  executePost() throws IOException, InterruptedException
    {

        key = key.replaceAll(" ", "%20");
        String url = "http://reversedictionary.org/wordsfor/"+key;
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
        List<?> divs = page.getByXPath("//div//a[@class='item']");


        for(int i = 0; i<divs.size();i++)
        {
            reverseDictionary.add(((List<HtmlElement>)(divs)).get(i).asText().toUpperCase());
        }


        //String text = page.asText();

        //parseString(text);

    }

	/*private void parseString(String text)
	{
		int begin = text.indexOf("definitions") + 11;
		int end = text.indexOf("Popular");
		String temp = text.substring(begin, end);
		String trnsfr = "";
		for(int i = 0; i<temp.length();i++)
		{
			if(temp.charAt(i)==' ')
			{
				reverseDictionary.add(trnsfr);
				trnsfr = "";
			}
			else
			{
				trnsfr += temp.charAt(i);
			}
			if(i == temp.length()-1)
			{
				reverseDictionary.add(trnsfr);
			}
		}


		for(int i = 0; i<reverseDictionary.size();i++)
		{

			System.out.println(reverseDictionary.get(i));
		}


	}*/
}


package Parser;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.net.URL;


public class HTMLParser {
    private static String[][] puzzle = {{"","","","",""},
                                        {"","","","",""},
                                        {"","","","",""},
                                        {"","","","",""},
                                        {"","","","",""}};
    private static String[][] nums = {{"","","","",""},
                                        {"","","","",""},
                                        {"","","","",""},
                                        {"","","","",""},
                                        {"","","","",""}};
    private static String[][] clues;
    private String date;
    
    public HTMLParser(boolean today){
        
        if(today){
            getClue();
            getBoxes();
            getAnswers();
            date();
        }
        else{
            getCluesPrevious();
            getBoxesPrevious();
            getAnswersPrevious();
            getDatePrevious();
        }
    }
    
    public static void getCluesPrevious(){
        try{
            File in = new File("C:\\Users\\Alper\\Desktop\\ProjectV2\\ProjectV2\\src\\main\\java\\Parser\\20171115.html");
            Document doc = Jsoup.parse(in, null);
            Elements cNum = doc.getElementsByClass("Clue-label--2IdMY");
            Elements cBody = doc.getElementsByClass("Clue-text--3lZl7");
            clues = new String[cNum.size()][2];
            for(int i = 0; i < cBody.size(); i++){
                clues[i][0] = cNum.get(i).text();
                clues[i][1] = cBody.get(i).text();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public static void getBoxesPrevious(){
        try{
            File in = new File("C:\\Users\\Alper\\Desktop\\ProjectV2\\ProjectV2\\src\\main\\java\\Parser\\20171115.html");
            Document doc = Jsoup.parse(in, null);
            Elements newelm = doc.getElementsByAttribute("fill");
            int a,b;
            for(int i = 0; i < newelm.size(); i++){
                if((""+newelm.get(i).attr("fill")).equals("black")){
                    a = (int)(i/5);
                    b = (int)(i%5);
                    puzzle[a][b] = "black";
                }


            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void getAnswersPrevious(){
        try{
            File in = new File("C:\\Users\\Alper\\Desktop\\ProjectV2\\ProjectV2\\src\\main\\java\\Parser\\20171115.html");
            Document doc = Jsoup.parse(in, null);
            Elements newelm = doc.getElementsByAttributeValue("text-anchor","middle");
            //System.out.println(newelm.size());
            int aCount = 0;
            for(int i = 0; i < 5; i++)
                for(int j = 0; j < 5; j++)
                    if(!(puzzle[i][j].equalsIgnoreCase("black"))){
                        puzzle[i][j] = newelm.get(aCount).text();
                        aCount++;
                    }
            Elements b = doc.getElementsByTag("g");
            Elements a = b.get(3).getElementsByTag("g");
            int acount = 1;
            int c = 0;
            for(int i = 0; i < 5 && acount < a.size(); i++)
                for(int j = 0; j < 5 &&acount < a.size(); j++){
                    if((a.get(acount).getElementsByAttributeValue("text-anchor", "start").size() > 0) && !(puzzle[i][j].equalsIgnoreCase("black"))){
                        nums[i][j] = a.get(acount++).getElementsByAttributeValue("text-anchor", "start").text();
                    }
                    else if(puzzle[i][j].equalsIgnoreCase("black")){
                        nums[i][j] = "none";
                        acount++;
                    }
                    else{
                        nums[i][j] = "none";
                        acount++;
                    }
                }
            

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void getDatePrevious(){
        File in = new File("C:\\Users\\Alper\\Desktop\\ProjectV2\\ProjectV2\\src\\main\\java\\Parser\\20171115.html");

        try {
            Document doc = Jsoup.parse(in, null);
            date = doc.getElementsByClass("PuzzleDetails-date--1HNzj").text();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public String[][] getPuzzle(){
        return puzzle;
    }
    public String[][] getClues(){
        return clues;
    }
    public String getDate(){
        return date;
    }
    public String[][] getNums(){
        return nums;
    }
    
    private void date(){
        String url = "https://www.nytimes.com/crosswords/game/mini";

        try {
            Document doc = Jsoup.parse(new URL(url), 10000);
            date = doc.getElementsByClass("PuzzleDetails-date--1HNzj").text();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
            
    private static void getClue() {
        String url = "https://www.nytimes.com/crosswords/game/mini";

        try {
            Document doc = Jsoup.parse(new URL(url), 10000);
            Elements cNum = doc.getElementsByClass("Clue-label--2IdMY");
            Elements cBody = doc.getElementsByClass("Clue-text--3lZl7");
            clues = new String[cNum.size()][2];
            for(int i = 0; i < cBody.size(); i++){
                clues[i][0] = cNum.get(i).text();
                clues[i][1] = cBody.get(i).text();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void getBoxes(){
        String url = "https://www.nytimes.com/crosswords/game/mini";

        try {
            Document doc = Jsoup.parse(new URL(url), 10000);
            Elements newelm = doc.getElementsByAttribute("fill");
            int a,b;
            for(int i = 0; i < newelm.size(); i++){
                if((""+newelm.get(i).attr("fill")).equals("black")){
                    a = (int)(i/5);
                    b = (int)(i%5);
                    puzzle[a][b] = "black";
                }
                
                    
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void getAnswers(){
        String exePath = "chromedriver.exe";
	System.setProperty("webdriver.chrome.driver", exePath);
	WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.nytimes.com/crosswords/game/mini");
        driver.findElement(By.xpath(".//button[contains(.,'OK')]")).click();
        driver.findElement(By.xpath(".//button[contains(.,'reveal')]")).click();
        driver.findElement(By.linkText("Puzzle")).click(); 
        driver.findElement(By.xpath(".//button[contains(.,'Reveal')]")).click();
        WebElement main = driver.findElement(By.tagName("div"));
        main.sendKeys(Keys.ESCAPE);
       
        List<WebElement> ans = driver.findElements(By.cssSelector("text[text-anchor='middle']"));
        int aCount = 0;
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(!(puzzle[i][j].equalsIgnoreCase("black"))){
                    puzzle[i][j] = ans.get(aCount).getText();
                    aCount++;
                }
        List<WebElement> b = driver.findElements(By.tagName("g"));
        List<WebElement> a = b.get(3).findElements(By.tagName("g"));
        int acount = 0;
        for(int i = 0; i < 5 && acount < a.size(); i++)
            for(int j = 0; j < 5 &&acount < a.size(); j++){
                if((a.get(acount).findElements(By.cssSelector("[text-anchor='start']")).size() > 0) && !(puzzle[i][j].equalsIgnoreCase("black"))){
                    nums[i][j] = a.get(acount++).findElement(By.cssSelector("[text-anchor='start']")).getText();
                }
                else if(puzzle[i][j].equalsIgnoreCase("black")){
                    nums[i][j] = "none";
                    acount++;
                }
                else{
                    nums[i][j] = "none";
                    acount++;
                }
            }
        driver.quit();
    }
}

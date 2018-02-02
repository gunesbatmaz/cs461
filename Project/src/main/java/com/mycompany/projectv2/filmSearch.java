package com.mycompany.projectv2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.util.ArrayList;


/**
 * This class searches IMDB movies by title
 *
 * @author Mert SARGON
 * MERT's API KEY: 310a8993
 */
public class filmSearch {

    // API KEY: 310a8993

    //This method searches Movie from OMDB with its title and personal API key
    public static ArrayList<String> searchMovie(String clue, int answerSize) throws IOException
    {
        ArrayList<String> A = new ArrayList<String>();
        String clue1 = clue.toLowerCase();
        clue1 = clue1.replace(")", " ");
        clue1 = clue1.replace("(", " ");
        clue1 = clue1.replace("\'", " ");
        String[] movie_title = clue1.split("\\s+");

        boolean is_movie = false;

        for(int j= 0; j<movie_title.length ; j++)
        {
            if(movie_title[j].contains("movie")||movie_title[j].contains("movies")||movie_title[j].contains("film")||movie_title[j].contains("film")||movie_title[j].contains("imdb")){
                is_movie = true;
            }
        }

        if (is_movie){


            for(int j= 0; j<movie_title.length ; j++)
            {
                movie_title[j] = URLEncoder.encode(movie_title[j], "UTF-8"); //in the suitable form for titles with more than 1 words

                String TARGET_URL = "http://www.omdbapi.com/?t=MOVIE_TITLE&plot=full&apikey=310a8993";
                //Modify the previously created frame with the input parameters
                String requested_URL = TARGET_URL
                        .replaceAll("MOVIE_TITLE", movie_title[j]);

                //request is sent
                StringBuffer response = new StringBuffer();

                URL url = new URL(requested_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "/");
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

                InputStream stream = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader buffer = new BufferedReader(reader);

                String line; //read line by line
                while ((line = buffer.readLine()) != null) {
                    response.append(line); //form string from buffer
                }
                buffer.close();
                connection.disconnect();
                String answer= response.toString();

                //---------------------------------------------------------------------------------------------------


                String TARGET_URL_2 = "http://www.omdbapi.com/?s=MOVIE_TITLE&plot=full&apikey=310a8993";
                //Modify the previously created frame with the input parameters
                String requested_URL_2 = TARGET_URL_2
                        .replaceAll("MOVIE_TITLE", movie_title[j]);

                //request is sent
                StringBuffer response_2 = new StringBuffer();

                URL url_2 = new URL(requested_URL_2);
                HttpURLConnection connection_2 = (HttpURLConnection) url_2.openConnection();

                connection_2.setRequestMethod("GET");
                connection_2.setRequestProperty("Accept", "/");
                connection_2.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

                InputStream stream_2 = connection_2.getInputStream();
                InputStreamReader reader_2 = new InputStreamReader(stream_2);
                BufferedReader buffer_2 = new BufferedReader(reader_2);

                String line_2; //read line by line
                while ((line_2 = buffer_2.readLine()) != null) {
                    response_2.append(line_2); //form string from buffer
                }
                buffer_2.close();
                connection_2.disconnect();
                String answer_2= response_2.toString();

                //---------------------------------------------------------------------------------------------------

                answer = answer + answer_2;

                answer= answer.replace("(", " ");
                answer= answer.replace(")", " ");
                answer= answer.replace("?", " ");
                answer= answer.replace("\"", " ");
                answer= answer.replace("}", " ");
                answer= answer.replace("{", " ");
                answer= answer.replace(":", " ");

                String[] answers = answer.split("\\s+");

                for(int i = 0; i<answers.length ; i++){
                    if (answers[i].length()==answerSize){

                        A.add(answers[i]);
                    }
                }
            }
            return  A;//Return the data in String

        }
        return null;
    }



   /* public static void main(String[] args) throws IOException {

        ArrayList<String> B= searchMovie("''_____ Cane'' (1963 movie)", 5);

        for(int i=1; i<B.size(); i++){
            if(B.get(i).toString().equals("Mondo"))
                System.out.println("YEAH!");
        }
    }*/

}

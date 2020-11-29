import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;

public class FirstRatings
{
    public ArrayList<Movie> loadMovies(String filename)
    {
        ArrayList<Movie> movieData = new ArrayList<Movie>(); //...read movies into this arraylist

        FileResource fr = new FileResource("data/" + filename + ".csv");
        CSVParser csvParser = fr.getCSVParser();

        for(CSVRecord csvRecord: csvParser)
        {
            String ID = csvRecord.get(0);
            String TITLE = csvRecord.get(1);
            String YEAR = csvRecord.get(2);
            String COUNTRY = csvRecord.get(3);
            String GENRE = csvRecord.get(4);
            String DIRECTOR = csvRecord.get(5);
            int LENGTH = Integer.parseInt(csvRecord.get(6));
            String POSTER = csvRecord.get(7);

            Movie thisMovie = new Movie(ID, TITLE, YEAR, GENRE, DIRECTOR, COUNTRY, POSTER, LENGTH);

            movieData.add(thisMovie);
        }
        return movieData;
    }

    public ArrayList<Rater> loadRaters (String filename)
    {
        ArrayList<Rater> raterArrayList = new ArrayList<Rater>();
        ArrayList<String> IDArrayList = new ArrayList<String>();

        FileResource fr = new FileResource("data/" + filename + ".csv");
        CSVParser csvParser = fr.getCSVParser();

        for(CSVRecord csvRecord : csvParser)
        {
            String raterID = csvRecord.get(0);
            String movieID = csvRecord.get(1);
            double movieRating  = Double.parseDouble(csvRecord.get(2));

            if(IDArrayList.contains(raterID))
            {
                for(int i = 0; i<raterArrayList.size(); i++)
                {
                    if(raterArrayList.get(i).getID().equals(raterID))
                    {
                        raterArrayList.get(i).addRating(movieID, movieRating);
                    }
                }
            }
            else
            {
                Rater newRater = new EfficientRater(raterID);
                raterArrayList.add(newRater);
                newRater.addRating(movieID, movieRating);
            }
            IDArrayList.add(raterID);
        }
        return raterArrayList;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void testLoadMovies()
    {
        ArrayList<Movie> movieArrayList = loadMovies("ratedmoviesfull");

        System.out.println("Total number of Movies: " + movieArrayList.size());
//////////////////////////////////////////////////////////////////////////////////////////////
        String genreQuery = "Comedy";
        int countGenre = 0;

        int movieLength = 150;
        int countLength = 0;

        for (Movie movie : movieArrayList)
        {
            //... determine how many movies include the Comedy genre...//
            if (movie.getGenres().contains(genreQuery))
            {
                countGenre++;
            }
            //...determine how many movies are greater than minutes in length...//
            if (movie.getMinutes() > movieLength)
            {
                countLength++;
            }
        }

        System.out.println("Total Comedy movies: " + countGenre);
        System.out.println("Total movies > 150mins: " + countLength);
///////////////////////////////////////////////////////////////////////////////////////////////

        //...Counting directors/movies directed by max director...//
        HashMap<String, Integer> directorsMap = new HashMap<String, Integer>();
        for(Movie movie: movieArrayList)
        {
            String[] directors = movie.getDirector().split(","); //...more than one directors are separated by commas...//

            for(String director : directors)
            {
                director = director.trim();
                if(directorsMap.containsKey(director))
                {
                    directorsMap.put(director, directorsMap.get(director)+1);
                }
                else
                {
                    directorsMap.put(director, 1);
                }
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int maxMoviesByMaxDirector = 0;
        for(String director : directorsMap.keySet())
        {
            if(directorsMap.get(director) > maxMoviesByMaxDirector)
            {
                maxMoviesByMaxDirector = directorsMap.get(director);
            }
        }
////..........................................................................................................////
        ArrayList<String> directorArrayList =  new ArrayList<String>();
        for(String director : directorsMap.keySet())
        {
            if(directorsMap.get(director) == maxMoviesByMaxDirector)
            {
                directorArrayList.add(director);
            }
        }
        System.out.println("Max number of movies directed by one Director: " + maxMoviesByMaxDirector);
        System.out.println("Directors who directed max movies: " + directorArrayList);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }


    public void testLoadRaters()
    {
        ArrayList<Rater> raterArrayList = loadRaters("ratings");
        System.out.println("Total raters: " + raterArrayList.size());

        HashMap<String, HashMap<String, Double>> hashmap = new HashMap<String, HashMap<String, Double>> ();
        for (Rater rater : raterArrayList)
        {
            HashMap<String, Double> ratings = new HashMap<String, Double> ();
            ArrayList<String> itemsRated = rater.getItemsRated();

            for (int i=0; i < itemsRated.size(); i++)
            {
                String movieID = itemsRated.get(i);
                double movieRating = rater.getRating(movieID);
                ratings.put(movieID, movieRating);
            }
            hashmap.put(rater.getID(), ratings);
        }

        //............................................................................................//
        String raterID = "193";
        int ratingSize = hashmap.get(raterID).size();
        System.out.println("Number of ratings by this user [ID:" + raterID + "]: " + ratingSize );
        //............................................................................................//
        int maxRating = 0;
        for(String key : hashmap.keySet())
        {
            if(hashmap.get(key).size() > maxRating)
            {
                maxRating = hashmap.get(key).size();
            }
        }
        System.out.println("Max number of of ratings done by any user: " + maxRating);
        //............................................................................................//
        ArrayList<String> raterWithMaxNumOfRatings = new ArrayList<String> ();
        for (String key : hashmap.keySet())
        {
            int currAmountOfRatings = hashmap.get(key).size();

            if (maxRating == currAmountOfRatings)
            {
                raterWithMaxNumOfRatings.add(key);
            }
        }
        System.out.println("Rater(s) with the most number of movies rated : " + raterWithMaxNumOfRatings);
        //............................................................................................//
        String movieID = "1798709";
        int numOfRatings = 0;
        for (String key : hashmap.keySet())
        {
            if(hashmap.get(key).containsKey(movieID))
            {
                numOfRatings +=1;
            }
        }
        System.out.println("Number of ratings movie " + movieID + " has : " + numOfRatings);
        //............................................................................................//
        ArrayList<String> uniqueMovies = new ArrayList<String> ();
        for (String key : hashmap.keySet())
        {
            for (String currMovieID : hashmap.get(key).keySet())
            {
                if (! uniqueMovies.contains(currMovieID))
                {
                    uniqueMovies.add(currMovieID);
                }
            }
        }
        System.out.println("Total number of movies that were rated : " + uniqueMovies.size());
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main (String []args)
    {
        FirstRatings FRATINGS = new FirstRatings();
        //FRATINGS.testLoadMovies();
        FRATINGS.testLoadRaters();
    }
}
/** SecondRatings class contains various methods to extract information from the corresponding ArrayLists.**/

import java.util.*;

public class SecondRatings
{
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

///////////////////////////////////////////...Constructors...////////////////////////////////////////////////////
    public SecondRatings()
    { //...default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile)
    { //... read in movie and ratings data and store them in ArrayList (myMovies and myRaters).
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getMovieSize()
    {
        return myMovies.size();
    }

    public int getRaterSize()
    {
        return myRaters.size();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private double getAverageByID (String id, int minimalRaters)
    {
        //...This method returns a double representing
        // the average movie rating for this ID
        // if there are at least minimalRaters ratings.
        // If there are not minimalRaters ratings, then it returns 0.0...//
        double sum = 0.0;
        int count = 0;

        for(Rater rater: myRaters)
        {
            if(rater.hasRating(id))
            {
                sum+=rater.getRating(id);
                count++;
            }
        }
        if (count >= minimalRaters)
        {
            return sum / count;///...average rating...///
        }
        else
        {
            return 0.0;
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Rating> getAverageRatings (int minimalRaters)
    {
        // This method finds the average rating for every movie
        // that has been rated by at least minimalRaters raters.
        // Return an ArrayList of all the Rating objects for movies
        // that have at least the minimal number of raters supplying a rating.
        ArrayList<Rating> averageRatings = new ArrayList<Rating> ();

        for (Movie movie : myMovies)
        {
            String movieID = movie.getID();
            //double average = Math.round(getAverageByID(movieID, minimalRaters) * 100.0) / 100.0;
            double average = getAverageByID(movieID, minimalRaters);
            if (average != 0.0)
            {
                Rating rating = new Rating (movieID, average);
                averageRatings.add(rating);
            }
        }

        return averageRatings;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getTitle (String id)
    {
        String title = null;

        for (Movie movie : myMovies)
        {
            if (movie.getID().equals(id))
            {
                title = movie.getTitle();
            }
        }

        if (title != null)
        {
            return title;
        }
        else
        {
            return "No movie with such ID was found.";
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getID (String title)
    {
        String movieID = null;

        for (Movie movie : myMovies)
        {
            if (movie.getTitle().equals(title))
            {
                movieID = movie.getID();
            }
        }

        if (movieID != null)
        {
            return movieID;
        }
        else
        {
            return "NO SUCH TITLE.";
        }
    }
}

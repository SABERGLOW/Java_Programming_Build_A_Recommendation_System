/**ThirdRatings class contains 2 methods that can be used to get average ratings
 * (with/without filters) as well as additional helper methods.
 *
 * * getAverageRatings and getAverageRatingsByFilter are used to find movies that have required
 * * number of ratings, and in case with getAverageRatingsByFilter can be filtered using a range
 * * of filters provided in this program.
 */
import java.util.*;

public class ThirdRatings
{
    private ArrayList<Rater> myRaters;

    public ThirdRatings ()
    {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings (String ratingsfile)
    {
        FirstRatings firstRatings = new FirstRatings ();
        myRaters = firstRatings.loadRaters(ratingsfile);
    }

    public int getRaterSize()
    {
        return myRaters.size();
    }

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

    public ArrayList<Rating> getAverageRatings (int minimalRaters)
    {
        // This method finds the average rating for every movie
        // that has been rated by at least minimalRaters raters.
        // Return an ArrayList of all the Rating objects for movies
        // that have at least the minimal number of raters supplying a rating.
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());

        for (String movieID : myMovies) {
            double average = Math.round(getAverageByID(movieID, minimalRaters) * 100.0) / 100.0;
            if (average != 0.0) {
                Rating rating = new Rating(movieID, average);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria)
    {
        ArrayList<Rating> averageRatings = new ArrayList<Rating> ();
        ArrayList<String> filteredMovies = MovieDatabase.filterBy(filterCriteria);

        for (String movieID : filteredMovies)
        {
            double average = Math.round(getAverageByID(movieID, minimalRaters) * 100.0) / 100.0;
            if (average != 0.0)
            {
                Rating rating = new Rating (movieID, average);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
    }
}

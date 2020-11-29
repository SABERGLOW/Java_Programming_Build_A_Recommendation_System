/***MovieRunnerAverage class contains tests for the getAverageRatings method from the secondRatings class.**/

import java.util.*;

public class MovieRunnerAverage
{
    public void printAverageRatings()
    {
        SecondRatings secondRatings = new SecondRatings ("ratedmoviesfull", "ratings");

        System.out.println("Total Number of Movies: " + secondRatings.getMovieSize());
        System.out.println("Total number of raters : " + secondRatings.getRaterSize());

        int minRating = 20;
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(minRating);
        Collections.sort(averageRatings);

        for(Rating rating : averageRatings)
        {
            System.out.println(rating.getValue() + " " + secondRatings.getTitle(rating.getItem()));
        }

        System.out.println("There are " + averageRatings.size() + " movies with " + minRating + " or more ratings.");
    }

    public void getAverageRatingOneMovie ()
    {
        SecondRatings secondRatings = new SecondRatings ("ratedmoviesfull", "ratings");

        String title = "Vacation"; // variable
        int minRating = 1; // variable

        String movieID = secondRatings.getID(title);
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(minRating);

        for (Rating rating : averageRatings)
        {
            if (rating.getItem().equals(movieID))
            {
                System.out.println("For movie \"" + title + "\" the average rating is: " + rating.getValue());
            }
        }
    }

}

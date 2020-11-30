import java.util.ArrayList;
import java.util.Arrays;

////////////////////////////////////////////////////////////////////////////////////////////////////
//..The class Movie is a Plain Old Java Object (POJO) class for storing the data about one movie..//
///////////////////////////////////////////////////////////////////////////////////////////////////


// An immutable passive data object (PDO) to represent item data
public class Movie
{
    private String id; //...represents IMDB ID of the movie..//
    private String title; //...represents title of the movie..//
    private int year; //...represents release year of the movie..//
    private String genres; //...represents genres of the movie..//
    private String director; //...represents directors of the movie..//
    private String country; //...represents countries that the movie was made in..//
    private int minutes; //...represents length (minutes) of the movie..//
    private String poster; //...represents link to image poster of the movie..//


    public Movie (String anID, String aTitle, String aYear, String theGenres)
    {
        //..just in case our data file contains extra whitespace, we trim those spaces..//
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
    }

    public Movie (String anID, String aTitle, String aYear, String theGenres, String aDirector, String aCountry, String aPoster, int theMinutes)
    {
        // just in case data file contains extra whitespace
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
        director = aDirector;
        country = aCountry;
        poster = aPoster;
        minutes = theMinutes;
    }

    // Returns ID associated with this item
    public String getID ()
    {
        return id;
    }

    // Returns title of this item
    public String getTitle ()
    {
        return title;
    }

    // Returns year in which this item was published
    public int getYear ()
    {
        return year;
    }

    // Returns genres associated with this item
    public String getGenres ()
    {
        return genres;
    }

    public String getCountry()
    {
        return country;
    }

    public String getDirector()
    {
        return director;
    }

    public String getPoster()
    {
        return poster;
    }

    public int getMinutes()
    {
        return minutes;
    }


    // Returns a string of the item's information
    public String toString ()
    {
        String result = "Movie [id=" + id + ", title=" + title + ", year=" + year;
        result += ", genres= " + genres + "]";
        return result;
    }
}

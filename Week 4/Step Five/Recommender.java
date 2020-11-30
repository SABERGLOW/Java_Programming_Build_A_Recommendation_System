import java.util.ArrayList;

public interface Recommender
{
    public ArrayList<String> getItemsToRate();

    public void printRecommendationsFor(String webRaterID);
}
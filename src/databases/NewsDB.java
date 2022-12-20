package databases;

import models.News;

import java.util.Vector;

public class NewsDB {
    public static Vector<News> newsList;
    static {
        newsList = new Vector<>();
        newsList.add(new News("123","Sport","Sport etc.."));
    }
}

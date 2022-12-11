package databases;

import models.News;

import java.util.ArrayList;
import java.util.List;

public class NewsDB {
    public static List<News> newsList;
    static {
        newsList = new ArrayList<>();
        newsList.add(new News("123","Sport","Sport etc.."));
    }
}

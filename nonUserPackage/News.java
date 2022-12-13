package nonUserPackage;

import java.time.LocalDate;


public class News {
    private String newsId, title, description;
    private LocalDate postDate;
    private static int newsNum = 0;
    {
    	newsNum++;
    }
    public News( String title, String description, LocalDate postDate) {
        this.title = title;
        this.description = description;
        this.postDate = postDate;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = "NEWS" + this.postDate + "0" + newsNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDate getPostDate() {
        return postDate;
    }


    @Override
    public String toString() {
        return "newsId = " + newsId + " | " +
                "Title:  " + title + " | " +
                "Description: " + description + " | " +
                "Posted Date: " + postDate;
                
    }
}

package nonUserPackage;

import java.util.Date;

public class News {
    private String newsId, title, description;
    private Date postDate;

    public News(String newsId, String title, String description, Date postDate) {
        this.newsId = newsId;
        this.title = title;
        this.description = description;
        this.postDate = postDate;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
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
    
    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    @Override
    public String toString() {
        return "newsId = " + newsId + " | " +
                "Title:  " + title + " | " +
                "Description: " + description + " | " +
                "Posted Date: " + postDate;
                
    }
}

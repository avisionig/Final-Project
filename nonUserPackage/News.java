package nonUserPackage;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * News, can be created by Manager and see by all Users.
 *
 */
public class News implements Serializable{
	private static final long serialVersionUID = -4521726234280720616L;
	private String newsID, title, description;
    private LocalDate postDate;
    public News( String title, String description, LocalDate postDate) {
        this.title = title;
        this.description = description;
        this.postDate = postDate;
        this.setNewsId();
    }

    public String getNewsID() {
        return newsID;
    }

    private void setNewsId() {
        this.newsID = "NEWS" + this.postDate + "0" + String.valueOf((int)(Math.random()*100+69));
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
        return "newsID: " + newsID + " | " +
                "Title:  " + title + " | " +
                "Description: " + description + " | " +
                "Posted Date: " + postDate;
                
    }
}

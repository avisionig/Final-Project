package models;

public class News {
    private String newsId;
    private String title;
    private String description;

    public News(String newsId, String title, String description) {
        this.newsId = newsId;
        this.title = title;
        this.description = description;
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

    @Override
    public String toString() {
        return "News: " +
                "newsId='" + newsId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description;
    }
}

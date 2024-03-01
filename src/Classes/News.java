package Classes;

import java.sql.Timestamp;
import java.util.Date;

public class News {
    private long id;
    private Timestamp postDate;
    private Categories category;
    private String title;
    private String content;

    public News() {
    }

    public News(long id, Timestamp postDate, Categories category, String title, String content) {
        this.id = id;
        this.postDate = postDate;
        this.category = category;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

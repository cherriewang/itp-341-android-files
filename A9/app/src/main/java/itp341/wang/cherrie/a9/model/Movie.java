package itp341.wang.cherrie.a9.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cherrie on 11/8/16.
 */

public class Movie implements Serializable {
    String title;
    String description;
    String url;
    int genre;
    int displayImage;
    ArrayList<Comment> comments;

    // Constructor
    public Movie(){
        super();
        ArrayList<String> comments = new ArrayList<String>();
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getGenre() {
        return genre;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public String getUrl() {
        return url;
    }


    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment newComment){
        this.comments.add(newComment);
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

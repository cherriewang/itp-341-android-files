package itp341.wang.cherrie.a9.model;

import java.util.ArrayList;

/**
 * Created by Cherrie on 11/9/16.
 */

public class Comment {
    String name;
    String comment;
    String timestamp;

    // Constructor
    public Comment(){
        super();
        name = "";
        comment = "";
        timestamp = "";
    }

    // Getters
    public String getComment() {
        return comment;
    }

    public String getName() {
        return name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}


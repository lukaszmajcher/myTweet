package pl.majcher.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Tweet  implements Serializable, Comparable<Tweet> {

    private int id;

    @Size(max = 140)
    private String message;

    @NotNull
    private User author;

    public Tweet() {
    }

    public Tweet(String message, User author) {
        this.message = message;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        return id == tweet.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(Tweet o) {
        return Integer.compare(this.id, o.getId());
    }
}

package sample.com.buddyclouddroidtest.model;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by Destri on 5/19/15.
 */
public class PostThread {
    private String id;
    private DateTime updated;
    private List<Post> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateTime getUpdated() {
        return updated;
    }

    public void setUpdated(DateTime updated) {
        this.updated = updated;
    }

    public List<Post> getItems() {
        return items;
    }

    public void setItems(List<Post> items) {
        this.items = items;
    }
}


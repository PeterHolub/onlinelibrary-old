package onlinelibrary.models;

import java.io.Serializable;
import java.util.Objects;

public class Author implements Serializable {

    private String name;
    private long id;
    
    public Author() {
    }

    public Author(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, id);
    }
}

package onlinelibrary.models;

import java.io.Serializable;
import java.util.Objects;

public class Author implements Serializable {

    private String authorname;
    private long id;

    public Author() {
        }

    public Author(String authorname, long id) {
        this.authorname = authorname;
        this.id = id;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                Objects.equals(authorname, author.authorname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(authorname, id);
    }
}

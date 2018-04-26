package onlinelibrary.models;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Book implements Serializable {

    private long id;
    private String name;
    private byte[] content;// pdf файла загружаем в это поле только в нужный момент (для просмотра)
    private String description;
    private String genre;
    private String author;
    private byte[] image;

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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                Objects.equals(name, book.name) &&
                Arrays.equals(content, book.content) &&
                Objects.equals(description, book.description) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(author, book.author) &&
                Arrays.equals(image, book.image);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, name, description, genre, author);
        result = 31 * result + Arrays.hashCode(content);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content=" + Arrays.toString(content) +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}

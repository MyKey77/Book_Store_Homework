package org.progmatic.model;
import jakarta.persistence.*;
import java.util.List;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Title;
    private String ISBN;

    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)
    private List <Store> stores;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", author=" + author +
                ", stores=" + stores +
                '}';
    }
}

package library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Name is empty")
    @Size(min = 3, max = 250, message = "Too small or long")
    //@Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Incorrect form")
    @Column(name = "fullname")
    private String fullName;

    @Column(name = "birthdayear")
    private int birthdayear;
    @OneToMany(mappedBy = "owner",fetch=FetchType.EAGER)
    private List<Book> books;


    public Person(int id, String fullName, int birthdayear) {
        this.id = id;
        this.fullName = fullName;
        this.birthdayear = birthdayear;
    }
    public Person(){}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getFullName() {return fullName;}
    public void setFullName(String fullName) {this.fullName = fullName;}
    public int getBirthdayear() {return birthdayear;}
    public void setBirthdayear(@Min(value = 14, message = "Age less than 14 years") int birthdayear) {this.birthdayear = birthdayear;}

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

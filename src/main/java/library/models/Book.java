package library.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min=10,max=250,message="Name need be between 10 and 250 characters")
    private String name;
    @NotEmpty(message = "Author shouldn't be empty")
    @Size(min=10,max=250,message="Author need be between 10 and 250 characters")
    private String author;
    public Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }
    public Book(){}
    public int getId(){return id;}
    public String getName(){return name;}
    public String getAuthor(){return author;}
    public void setId(int id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setAuthor(String author){this.author = author;}
}

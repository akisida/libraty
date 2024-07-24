package library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name shouldn't be empty")
    //@Size(min=10,max=250,message="Name need be between 10 and 250 characters")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "Author shouldn't be empty")
    //@Size(min=10,max=250,message="Author need be between 10 and 250 characters")
    private String author;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;
    @Column(name = "year")
    private int year;

    @Column(name = "taken_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenTime;

    @Transient
    private boolean period;

    public Book(String name, String author, Person owner, int year, Date takenTime) {
        this.name = name;
        this.author = author;
        this.owner = owner;
        this.year = year;
        this.takenTime = takenTime;
    }

    public Book() {

    }

    public int getId(){return id;}
    public String getName(){return name;}
    public String getAuthor(){return author;}
    public void setId(int id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setAuthor(String author){this.author = author;}
    public Person getOwner(){return owner;}
    public void setOwner(Person owner){this.owner = owner;}

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTakenTime(Date takenTime) {
        this.takenTime = takenTime;
    }
    public Date getTakenTime(){return takenTime;}
    public void setPeriod() {
        Instant now = Instant.now();
        Instant takenInstant = takenTime.toInstant();
        Duration duration = Duration.between(takenInstant, now);

        if (duration.toDays() > 10) {
            this.period = true;
        } else {
            this.period = false;
        }
    }
    public boolean isPeriod() {
        return period;
    }
}

package library.DAO;

import library.mapper.BookMapper;
import library.models.Book;
import library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
    }

    public Optional<Book> showBookById(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public Optional<Book> getBooksByName(String name) {
        return jdbcTemplate.query("SELECT * FROM book WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public Optional<Book> getBooksByAuthor(String author) {
        return jdbcTemplate.query("SELECT * FROM book WHERE author=?", new Object[]{author}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author) VALUES (?, ?)", book.getName(), book.getAuthor());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET author=?, name=? WHERE id=?", updatedBook.getAuthor(),updatedBook.getName(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }
    public Optional<Person> getBookOwner(int bookId) {
        String sql = "SELECT p.* FROM person p INNER JOIN personbook pb ON p.id = pb.person_id WHERE pb.book_id = ?";
        return jdbcTemplate.query(sql, new Object[]{bookId}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public void assignBookToPerson(int personId, int bookId) {
        deletePersonFromBook(bookId);
        jdbcTemplate.update("INSERT INTO personbook (person_id, book_id) VALUES (?, ?)", personId, bookId);
    }
    public void deletePersonFromBook(int bookId){
        jdbcTemplate.update("DELETE FROM personbook WHERE book_id=?",bookId);
    }
}

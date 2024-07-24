package library.DAO;

import library.mapper.PersonMapper;
import library.models.Book;
import library.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
  /*  private final JdbcTemplate jdbcTemplate;
    private final SessionFactory sessionFactory;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate, SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.jdbcTemplate = jdbcTemplate;
    }
    @Transactional
    public List<Person> index() {
       // return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
        Session session = sessionFactory.getCurrentSession();

        List<Person> people = session.createQuery("select p from Person p", Person.class)
                .getResultList();

        return people;
    }

    public Optional<Person> showByName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM person WHERE fullName=?", new Object[]{fullName}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public Optional<Person> showPersonById(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fullname, birthdayear) VALUES (?, ?)", person.getFullName(), person.getAge());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET fullName=?, birthdayear=? WHERE id=?", updatedPerson.getFullName(), updatedPerson.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
    public List<Book> getBooksTakes(int personId) {
        String sql = "SELECT b.* FROM book b INNER JOIN personbook pb ON b.id = pb.book_id WHERE pb.person_id = ?";
        return jdbcTemplate.query(sql, new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class));
    }*/
}

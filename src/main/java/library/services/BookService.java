package library.services;

import library.models.Book;
import library.models.Person;
import library.repositories.BookRepository;
import library.repositories.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService  {
    private final PersonRepository personRepository;
    private BookRepository bookRepository;
    public BookService(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }
    public Book findById(int id) {
    Optional<Book>book =  bookRepository.findById(id);
        return book.orElse(null);
    }
    public Page<Book> findAllPagenable(int page,int itemsPerPage){
        return bookRepository.findAll(PageRequest.of(page, itemsPerPage));
    }
    public List<Book> findAllSorted(String columnName){
        return bookRepository.findAll(Sort.by(columnName));
    }
    public List<Book> findAllSortedPagenable(String columnName, int page, int itemsPerPage){
        return bookRepository.findAll(PageRequest.of(page,itemsPerPage,Sort.by(columnName))).getContent();
    }
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    public List<Book> findBookInSearch(String search) {
        return bookRepository.findByNameStartingWith(search);
    }
    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }
    @Transactional
    public Book update(int id ,Book book) {
        book.setId(id);
        return bookRepository.save(book);
    }

}

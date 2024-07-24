package library.controllers;

import jakarta.validation.Valid;
import library.models.Book;
import library.models.Person;
import library.services.BookService;
import library.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/library")
public class BookController {
    public final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
/*        model.addAttribute("books", bookDAO.getAllBooks());*/
        model.addAttribute("books", bookService.findAll());
        return "/library/books";
    }
    @GetMapping("/books/pagenable")
    public String showBooksPagenable(Model model,@RequestParam("page") int page,@RequestParam("books_per_page") int booksPerPage){
        model.addAttribute("books",bookService.findAllPagenable(page,booksPerPage));
        return "/library/books";
    }
    @GetMapping("/books/sorted")
    public String showBooksSorted(Model model,@RequestParam("column") String column){
        model.addAttribute("books",bookService.findAllSorted(column));
        return "/library/books";
        }

    @GetMapping("/books/sorted_pagenable")
    public String showBooksSortedPagenable(Model model,@RequestParam("page") int page,@RequestParam("books_per_page") int booksPerPage,
                                           @RequestParam("column") String column){
        model.addAttribute("books",bookService.findAllSortedPagenable(column,page,booksPerPage));
        return "/library/books";
    }
    @GetMapping("/books/search")
    public String showBooksSearch(Model model,@RequestParam(value = "query",required = false) String query){
        List<Book> books;
        books = bookService.findBookInSearch(query);
        model.addAttribute("books", books);

        // Debugging output
        System.out.println("Search query: " + query);
        books.forEach(book -> System.out.println(book.getName() + " by " + book.getAuthor()));

        return "/library/search";
    }
    @GetMapping("/books/{id}")
    public String showBookById(@PathVariable("id") int id, Model model) {

        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("people",personService.findAll());
        model.addAttribute("owner",bookService.findById(id).getOwner());
        return "library/show";

    }
    @GetMapping("/new_book")
    public String newBook(@ModelAttribute("book") Book book) {
        return "library/new_book";
    }

    @PostMapping("/books")
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {

        bookService.save(book);
        return "redirect:/library/books";
    }

    @GetMapping("/books/{id}/edit")
    public String BookEdit(@PathVariable("id") int id, Model model) {
        System.out.println("Edit method called with id: " + id);
        Book book = bookService.findById(id);
            model.addAttribute("book", book);
            return "library/edit";

    }
    @PatchMapping("/books/{id}/assign")
    @Transactional
    public String assignBookToPerson(@PathVariable("id") int bookId, @RequestParam("personId") int personId) {
        bookService.findById(bookId).setOwner(personService.findOne(personId));
        bookService.findById(bookId).setTakenTime(new Date());
        bookService.update(bookId, bookService.findById(bookId));
        return "redirect:/library/books/" + bookId;
    }
    @DeleteMapping("/books/{id}/free")
    @Transactional
    public String freeBook(@PathVariable("id") int bookId) {
        bookService.findById(bookId).setOwner(null);
        bookService.findById(bookId).setTakenTime(null);
        bookService.update(bookId,bookService.findById(bookId));
        return "redirect:/library/books/" + bookId;
    }
    @PatchMapping("/books/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,BindingResult bindingResult, @PathVariable("id")int id)
    {
        bookService.update(id,book);
        return "redirect:/library/books";
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/library/books";
    }
}

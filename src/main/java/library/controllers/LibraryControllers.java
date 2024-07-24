package library.controllers;

import jakarta.validation.Valid;
import library.DAO.BookDAO;
import library.DAO.PersonDAO;
import library.models.Book;
import library.models.Person;
import library.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Patch;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/library")
public class LibraryControllers {

   /* private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final PersonValidator personValidator;

    @Autowired
    public LibraryControllers(PersonDAO personDAO, PersonValidator personValidator, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
        this.bookDAO = bookDAO;
        System.out.println("Start");
    }

    // Лендинг начальный
    @GetMapping
    public String library() {
        System.out.println("Library index called");
        return "library/index";
    }

    // Обработка пользователей
    @GetMapping("/people")
    public String showPeople(Model model) {
        model.addAttribute("people", personDAO.index());
        return "library/people";
    }

    @GetMapping("/people/{id}")
    public String showPeopleById(@PathVariable("id") int id, Model model) {
        System.out.println("Show people by id called with id: " + id);
        Optional<Person> person = personDAO.showPersonById(id);
        List<Book> books = personDAO.getBooksTakes(id);

        if (person.isPresent()) {
            System.out.println("Person found: " + person.get());
            model.addAttribute("person", person.get());
            model.addAttribute("books", books);
            return "library/show";
        } else {
            System.out.println("Person not found with id: " + id);
            return "library/not_found";
        }
    }
    @GetMapping("/new_person")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "library/new_person";
    }

    @PostMapping("/people")
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        *//*personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "library/new_person";*//*

        personDAO.save(person);

        return "redirect:/library/people";
    }

    @GetMapping("/people/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        System.out.println("Edit method called with id: " + id);
        Optional<Person> person = personDAO.showPersonById(id);
        if (person.isPresent()) {
            System.out.println("Person found: " + person.get());
            model.addAttribute("person", person.get());

            return "library/edit";
        } else {
            System.out.println("Person not found with id: " + id);
            return "library/not_found";
        }
    }
    @PatchMapping("/people/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult, @PathVariable("id")int id)
    {
       *//* personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "library/edit";
        }*//*
        personDAO.update(id,person);
        return "redirect:/library/people";
    }
    @DeleteMapping("/people/{id}")
    public String deletePeople(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/library/people";
    }


    // Обработка книг
    @GetMapping("/books")
    public String showBooks(Model model) {
        model.addAttribute("books", bookDAO.getAllBooks());
        return "/library/books";
    }

    @GetMapping("/books/{id}")
    public String showBookById(@PathVariable("id") int id, Model model) {
        System.out.println("Show book by id called with id: " + id);
        model.addAttribute("people", personDAO.index());
        Optional<Book> book = bookDAO.showBookById(id);
        Optional<Person> owner = bookDAO.getBookOwner(id);
        if (book.isPresent()) {
            System.out.println("Book found: " + book.get());
            model.addAttribute("book", book.get());
            owner.ifPresent(person -> model.addAttribute("owner", person));
            return "library/show";
        } else {
            System.out.println("Book not found with id: " + id);
            return "library/not_found";
        }
    }
    @GetMapping("/new_book")
    public String newBook(@ModelAttribute("book") Book book) {
        return "library/new_book";
    }

    @PostMapping("/books")
    public String createBook(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        *//*personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "library/new_person";*//*

        bookDAO.save(book);

        return "redirect:/library/books";
    }

    @GetMapping("/books/{id}/edit")
    public String BookEdit(@PathVariable("id") int id, Model model) {
        System.out.println("Edit method called with id: " + id);
        Optional<Book> book = bookDAO.showBookById(id);
        if (book.isPresent()) {
            System.out.println("Book found: " + book.get());
            model.addAttribute("book", book.get());
            return "library/edit";
        } else {
            System.out.println("Book not found with id: " + id);
            return "library/not_found";
        }
    }
    @PatchMapping("/books/{id}/assign")
    public String assignBookToPerson(@PathVariable("id") int bookId, @RequestParam("personId") int personId) {
        bookDAO.assignBookToPerson(personId, bookId);
        return "redirect:/library/books/" + bookId;
    }
    @DeleteMapping("/books/{id}/free")
    public String freeBook(@PathVariable("id") int bookId) {
        bookDAO.deletePersonFromBook(bookId);
        return "redirect:/library/books/" + bookId;
    }
    @PatchMapping("/books/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,BindingResult bindingResult, @PathVariable("id")int id)
    {


        bookDAO.update(id,book);

        Optional<Person> currentOwner = bookDAO.getBookOwner(id);


        return "redirect:/library/books";
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/library/books";
    }*/
}

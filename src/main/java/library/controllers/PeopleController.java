package library.controllers;

import jakarta.validation.Valid;
import library.models.Book;

import library.models.Person;
import library.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/library")
public class PeopleController {
    private final PersonService personService;
    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }
    // Обработка пользователей
    @GetMapping("/people")
    public String showPeople(Model model) {
        model.addAttribute("people", personService.findAll());
        return "library/people";
    }

    @GetMapping("/people/{id}")
    public String showPeopleById(@PathVariable("id") int id, Model model) {

        Person person = personService.findOne(id);
        List<Book> books = person.getBooks();
        for(Book b:books)
        {b.setPeriod();}
        System.out.println(books);
            model.addAttribute("person", person);
            model.addAttribute("books", books);
            return "library/show";

    }
    @GetMapping("/new_person")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "library/new_person";
    }

    @PostMapping("/people")
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {

        personService.save(person);

        return "redirect:/library/people";
    }

    @GetMapping("/people/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        System.out.println("Edit method called with id: " + id);
        Person person = personService.findOne(id);
            model.addAttribute("person", person);
            return "library/edit";

    }
    @PatchMapping("/people/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult, @PathVariable("id")int id)
    {

        personService.update(id,person);
        return "redirect:/library/people";
    }
    @DeleteMapping("/people/{id}")
    public String deletePeople(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/library/people";
    }


}

package library.controllers;

import library.DAO.PersonDAO;
import library.util.PersonValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library")
public class LibraryControllers {

    private PersonDAO personDAO;
    private final PersonValidator personValidator;
    LibraryControllers(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people",personDAO.index());
        return "library/index";
    }
}

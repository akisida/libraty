package library.util;

import library.DAO.PersonDAO;
import library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

    }
}

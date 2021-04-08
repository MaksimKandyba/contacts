package contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    @GetMapping("/hello/contacts")
    public List<Contact> get(@RequestParam(value = "nameFilter") String nameFilter) throws SQLException {
        return contactsService.get(nameFilter);
    }
}

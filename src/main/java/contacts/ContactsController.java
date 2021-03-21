package contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ContactsController {

    @Autowired
    private DataSource dataSource;

    @Value("${fetchsize}")
    private int fetchSize;

    @Value("${limit}")
    private int limit;

    @GetMapping("/hello/contacts")
    public Object[] get(@RequestParam(value = "nameFilter") String nameFilter) throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.setFetchSize(fetchSize);
            try (ResultSet resultSet = statement.executeQuery("select * from public.contacts")) {
                boolean limited = limit != 0;
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    if (!name.matches(nameFilter)) {
                        contacts.add(new Contact(resultSet.getInt("id"), name));
                        if (limited && contacts.size() == limit) {
                            break;
                        }
                    }
                }
            }
        }
        return contacts.toArray();
    }
}

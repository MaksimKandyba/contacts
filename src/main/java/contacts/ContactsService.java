package contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactsService {

    @Autowired
    private DataSource dataSource;

    @Value("${fetchsize:1000}")
    private int fetchSize;

    @Value("${limit:1000}")
    private int limit;

    public List<Contact> get(String nameFilter) throws SQLException {
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
        return contacts;
    }
}

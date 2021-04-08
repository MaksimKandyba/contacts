package contacts;

import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

public class TestUtils {

    public static List<LinkedHashMap<String, String>> execute(TestRestTemplate template, int port, String nameFilter) {
        return template
                .getForEntity("http://localhost:" + port + "/hello/contacts?nameFilter=" + nameFilter, List.class)
                .getBody();
    }
}

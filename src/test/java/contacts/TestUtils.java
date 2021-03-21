package contacts;

import org.springframework.boot.test.web.client.TestRestTemplate;

public class TestUtils {

    public static Contact[] execute(TestRestTemplate template, int port, String nameFilter) {
        return template
                .getForEntity("http://localhost:" + port + "/hello/contacts?nameFilter=" + nameFilter, Contact[].class)
                .getBody();
    }
}

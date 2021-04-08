package contacts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static contacts.TestUtils.execute;
import static java.util.stream.Collectors.toList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unlimited-test")
public class ContactsControllerUnlimitedTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void doesNotStartsWith_A() {
        assertThat(execute(restTemplate, port, "^A.*$").size()).isEqualTo(9);
    }

    @Test
    public void doesNotContains_aei() {
        assertThat(execute(restTemplate, port, "^.*[aei].*$").size()).isEqualTo(0);
    }

    @Test
    public void isNotMisterOrDoctor() {
        assertThat(
                execute(restTemplate, port, "^(M|D)r\\..*$")
                .stream()
                .map(el -> el.get("name"))
                .collect(toList())
        ).containsOnly("Gabriel John Utterson", "Richard Enfield", "Inspector Newcomen", "Sir Danvers Carew, MP", "Maid");
    }
}

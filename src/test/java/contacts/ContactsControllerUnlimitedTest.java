package contacts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static contacts.Contact.valueOf;
import static contacts.TestUtils.execute;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unlimited-test")
public class ContactsControllerUnlimitedTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void doesNotStartsWith_A() {
        assertThat(execute(restTemplate, port, "^A.*$").length).isEqualTo(9);
    }

    @Test
    public void doesNotContains_aei() {
        assertThat(execute(restTemplate, port, "^.*[aei].*$").length).isEqualTo(0);
    }

    @Test
    public void isNotMisterOrDoctor() {
        assertThat(execute(restTemplate, port, "^(M|D)r\\..*$")).containsOnly(
                valueOf("Gabriel John Utterson"),
                valueOf("Richard Enfield"),
                valueOf("Inspector Newcomen"),
                valueOf("Sir Danvers Carew, MP"),
                valueOf("Maid")
        );
    }
}

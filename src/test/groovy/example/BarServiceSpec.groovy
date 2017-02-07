package example

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

/**
 * An example of component test using a mock.
 *
 * @author Hidetake Iwata
 */
@SpringBootTest(webEnvironment = NONE)
class BarServiceSpec extends Specification {
    @Subject BarService service

    ExternalApiClient client = Mock()

    def setup() {
        service = new BarService(client)
    }

    def 'hello() should return a named bean'() {
        given:
        1 * client.findByName('happy') >> new Hello('happy')

        when:
        def hello = service.hello('happy')

        then:
        hello.name == 'happy'
    }
}

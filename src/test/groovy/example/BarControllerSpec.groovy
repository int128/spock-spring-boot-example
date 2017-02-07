package example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

/**
 * An example of API integration test.
 *
 * @author Hidetake Iwata
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(IntegrationTestConfiguration)
class BarControllerSpec extends Specification {
    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    ExternalApiClient client

    def '/bar/you should return world'() {
        given:
        1 * client.findByName('happy') >> new Hello('happy')

        when:
        def entity = restTemplate.getForEntity('/bar/happy', Hello)

        then:
        entity.statusCode == HttpStatus.OK
        entity.body.name == 'happy'
    }
}

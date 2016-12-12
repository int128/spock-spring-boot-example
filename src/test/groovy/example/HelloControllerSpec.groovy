package example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

/**
 * An example of API integration test.
 *
 * @author Hidetake Iwata
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
class HelloControllerSpec extends Specification {
    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    ExternalApiClient client

    def '/hello should return world'() {
        given:
        1 * client.getDefault() >> new Hello('world')

        when:
        def entity = restTemplate.getForEntity('/hello', Hello)

        then:
        entity.statusCode == HttpStatus.OK
        entity.body.name == 'world'
    }

    @TestConfiguration
    static class MockConfig {
        final detachedMockFactory = new DetachedMockFactory()

        @Bean
        ExternalApiClient externalApiClient() {
            detachedMockFactory.Mock(ExternalApiClient)
        }
    }
}

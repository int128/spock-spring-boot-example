package example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

/**
 * An example of component test using a mock.
 *
 * @author Hidetake Iwata
 */
@SpringBootTest(webEnvironment = NONE)
class HelloServiceSpec extends Specification {
    @Autowired
    HelloService service

    @Autowired
    ExternalApiClient client

    def 'hello() should return world'() {
        given:
        1 * client.getDefault() >> new Hello('world')

        when:
        def hello = service.hello()

        then:
        hello.name == 'world'
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

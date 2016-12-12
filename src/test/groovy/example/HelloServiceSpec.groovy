package example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

@SpringBootTest(webEnvironment = NONE)
class HelloServiceSpec extends Specification {
    @Autowired
    HelloService service

    def 'hello() should return world'() {
        when:
        def hello = service.hello()

        then:
        hello.name == 'world'
    }
}

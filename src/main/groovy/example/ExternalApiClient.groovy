package example

import org.springframework.stereotype.Component

@Component
class ExternalApiClient {

    static final String DEFAULT_HELLO_VALUE = 'world'

    Hello findByName(String name) {
        assert name
        new Hello(name)
    }

    Hello getDefault() {
        new Hello(DEFAULT_HELLO_VALUE)
    }
}

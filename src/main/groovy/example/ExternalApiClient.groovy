package example

import org.springframework.stereotype.Component

@Component
class ExternalApiClient {
    Hello findByName(String name) {
        assert name
        new Hello(name)
    }

    Hello getDefault() {
        new Hello('world')
    }
}

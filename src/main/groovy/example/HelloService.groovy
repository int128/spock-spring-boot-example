package example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HelloService {
    @Autowired
    ExternalApiClient client

    Hello hello(String name = null) {
        if (name) {
            client.findByName(name)
        } else {
            client.default
        }
    }
}

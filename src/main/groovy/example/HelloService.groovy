package example

import org.springframework.stereotype.Service

@Service
class HelloService {
    final ExternalApiClient client

    HelloService(ExternalApiClient client) {
        this.client = client
        assert client
    }

    Hello hello(String name = null) {
        if (name) {
            client.findByName(name)
        } else {
            client.default
        }
    }
}

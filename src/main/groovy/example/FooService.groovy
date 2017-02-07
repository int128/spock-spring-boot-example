package example

import org.springframework.stereotype.Service

@Service
class FooService {
    final ExternalApiClient client

    FooService(ExternalApiClient client) {
        this.client = client
        assert client
    }

    Hello hello() {
        client.default
    }
}

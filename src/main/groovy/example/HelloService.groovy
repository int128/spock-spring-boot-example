package example

import org.springframework.stereotype.Service

@Service
class HelloService {
    Hello hello(String name = null) {
        new Hello(name ?: 'world')
    }
}

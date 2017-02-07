package example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(produces = 'application/json')
class FooController {
    @Autowired
    private FooService service

    @GetMapping('/foo')
    Hello hello() {
        service.hello()
    }
}

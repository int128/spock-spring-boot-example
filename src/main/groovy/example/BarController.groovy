package example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(produces = 'application/json')
class BarController {
    @Autowired
    private BarService service

    @GetMapping('/bar/{name}')
    Hello helloByName(@PathVariable String name) {
        service.hello(name)
    }
}

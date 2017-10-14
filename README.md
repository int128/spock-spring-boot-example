# Spock and Spring Boot example [![Build Status](https://travis-ci.org/int128/spock-spring-boot-example.svg?branch=master)](https://travis-ci.org/int128/spock-spring-boot-example) [![Gradle Status](https://gradleupdate.appspot.com/int128/spock-spring-boot-example/status.svg)](https://gradleupdate.appspot.com/int128/spock-spring-boot-example/status)

This is an example of Spring Boot app tested with Spock framework. 

- Spring Boot 1.5
- Spock 1.1


## Component Test

- [`FooServiceSpec`](/src/test/groovy/example/FooServiceSpec.groovy)
- [`BarServiceSpec`](/src/test/groovy/example/BarServiceSpec.groovy)

These are component tests using the constructor mock injection.

Mark as a Spring Boot test without the web environment:

```groovy
@SpringBootTest(webEnvironment = NONE)
```

Instantiate a service class with a mock dependency.

```groovy
    @Subject BarService service

    ExternalApiClient client = Mock()

    def setup() {
        service = new BarService(client)
    }
```

Declare the mock interaction by Spock style:

```groovy
        given:
        1 * client.getDefault() >> new Hello('world')
```


## Integration Test

- [`FooControllerSpec`](/src/test/groovy/example/FooControllerSpec.groovy)
- [`BarControllerSpec`](/src/test/groovy/example/BarControllerSpec.groovy)

These are end-to-end tests using the test rest template.
See also [Testing improvements in Spring Boot 1.4](https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4).

Mark as a Spring Boot test with the real web environment:

```groovy
@SpringBootTest(webEnvironment = RANDOM_PORT)
```

Import the mock configuration.

```groovy
@Import(IntegrationTestConfiguration)
```

Use the `TestRestTemplate` to make a request.

```groovy
    @Autowired
    TestRestTemplate restTemplate
```

Make a request to the target application:

```groovy
        when:
        def entity = restTemplate.getForEntity('/foo', Hello)
```

Verify the response:

```groovy
        then:
        entity.statusCode == HttpStatus.OK
        entity.body.name == 'world'
```

# Spock and Spring Boot example [![Build Status](https://travis-ci.org/int128/spock-spring-example.svg?branch=master)](https://travis-ci.org/int128/spock-spring-example)

This is an example of Spring Boot app tested with Spock framework. 

- Spring Boot 1.5
- Spock 1.1-rc-3
- Groovy 2.4


## [`example.HelloServiceSpec`](/src/test/groovy/example/HelloServiceSpec.groovy)

This verifies behavior of the component using mock injection.
See also [Spock Spring Module](http://spockframework.org/spock/docs/1.1-rc-3/modules.html).

Mark as a Spring Boot test without the web environment:

```groovy
@SpringBootTest(webEnvironment = NONE)
```

Declare the mock interaction by Spock style:

```groovy
        given:
        1 * client.getDefault() >> new Hello('world')
```

Configure the mock injection:

```groovy
    @TestConfiguration
    static class MockConfig {
        final detachedMockFactory = new DetachedMockFactory()

        @Bean
        ExternalApiClient externalApiClient() {
            detachedMockFactory.Mock(ExternalApiClient)
        }
    }
```


## [`example.HelloControllerSpec`](/src/test/groovy/example/HelloControllerSpec.groovy)

This is an end-to-end test to verify behavior of the REST API.
See also [Testing improvements in Spring Boot 1.4](https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4).

Mark as a Spring Boot test with the real web environment:

```groovy
@SpringBootTest(webEnvironment = RANDOM_PORT)
```

Use the `TestRestTemplate` to make a request.

```groovy
    @Autowired
    TestRestTemplate restTemplate
```

Make a request to the target application:

```groovy
        when:
        def entity = restTemplate.getForEntity('/hello', Hello)
```

Verify the response:

```groovy
        then:
        entity.statusCode == HttpStatus.OK
        entity.body.name == 'world'
```

Mock injection can be used in the end-to-end test.

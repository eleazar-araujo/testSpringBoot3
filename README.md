# Spring Boot 4 Migration Fixture

This is a deliberately broad Spring Boot 3.5.x Maven project for validating an AI migration skill that upgrades applications to Spring Boot 4.x.

It is intentionally small at runtime but wide in dependency and API surface. The app models a customer-support service with MVC, WebFlux client usage, GraphQL, HATEOAS, JPA, JDBC, security, actuator, Jackson, templates, cache, batch, messaging dependencies, Flyway, Liquibase, session dependencies, and native-image hints.

## What A Migration Skill Should Notice

- `spring-boot-starter-parent` starts at `3.5.14` and should move to the chosen Boot 4 maintenance version.
- Old starter names are present: `spring-boot-starter-web`, `spring-boot-starter-web-services`, `spring-boot-starter-aop`, and the old OAuth2 starter names.
- `spring-boot-starter-undertow` is present even though Boot 4 removes Undertow support while Servlet 6.1 is required.
- Flyway and Liquibase are direct third-party dependencies; Boot 4 expects technology starters for those paths.
- Test dependencies use the Boot 3 style: `spring-boot-starter-test`, `spring-security-test`, `spring-graphql-test`, and REST Docs directly.
- `spring-pulsar-reactive-spring-boot-starter` is included to exercise removed reactive Pulsar support.
- The Maven plugin uses removed executable launch-script/classic loader settings.
- `hibernate-jpamodelgen` is configured as an annotation processor; Boot 4 dependency management replaces it with `hibernate-processor`.
- `spring-retry` relies on Boot 3 dependency management; Boot 4 removes that dependency management.
- `@EntityScan` imports the Boot 3 package that moves under Boot 4 persistence auto-configuration.
- Jackson code uses `com.fasterxml.jackson.databind`, `@JsonComponent`, and `Jackson2ObjectMapperBuilderCustomizer`.
- Test code uses `@MockBean`, old `TestRestTemplate`, and MockMvc patterns that Boot 4 changes.
- `application.yml` includes moved or removed properties for Jackson, MongoDB, Spring Session Redis, DAO exception translation, Kafka retry, management Mongo health, Logback charset, and forwarded headers.
- `spring.factories` registers an `EnvironmentPostProcessor` from the Boot 3 package.
- Runtime hints use resource patterns and member categories that changed with the Spring Framework 7/GraalVM metadata baseline.

## Useful Commands

```bash
mvn test
mvn spring-boot:run
```

After your migration skill runs, a good first validation is `mvn test`. A stronger validation is to inspect the POM and source imports to confirm the migration did more than only bump the parent version.
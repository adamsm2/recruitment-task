## Table of contents
- [Technologies](#technologies)
- [How to use](#how-to-use)
- [Features](#features)
- [How to have a higher rate limit](#how-to-have-a-higher-rate-limit)
- [Test cases](#test-cases)
- [Examples](#examples)

## Technologies
- Java 21
- SpringBoot 3
- WebFlux
- WireMock

## How to use
### Access Swagger UI: http://localhost:8080/swagger-ui/index.html
### Build
```
docker compose build
```
### Run
```
docker compose up
```
### Run backend in detach mode
```
docker compose up backend -d
```
### Stop
```
docker compose down
```

## Features
- List user's github repositories, which are not forks

## How to have a higher rate limit
- Make authenticated request. Include your Github token in the Authorization header in your requests to the Github REST API. You can store your token in an environment variable for example.

Enter your github access token in [compose.yml](./compose.yml)
```
environment:
  - GITHUB_ACCESS_TOKEN=enter-your-github-access-token-here
```
Uncomment default header in [ClientConfig.java](./backend/src/main/java/com/adamsm2/backend/shared/config/ClientConfig.java)
```
    @Bean
    WebClient webClient() {
        return WebClient.builder()
/*                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + githubAccessToken)*/
                .filter(errorHandler())
                .build();
    }
```

## Test cases
|                                      Case                                   |    Then response http status   |
| --------------------------------------------------------------------------- | ------------------------------ |
| Existing username                                                           | OK                             |
| Non existing username                                                       | NOT_FOUND                      |
| Existing username and while fetching branches a repository no longer exists | NOT_FOUND                      |
| Github service unavailable                                                  | SERVICE_UNAVAILABLE            |
| Github rate limit exceeded                                                  | TOO_MANY_REQUESTS              |
| Invalid Github access token                                                 | UNAUTHORIZED                   |

## Examples
![getUserReposSuccess](https://github.com/user-attachments/assets/4d532b89-f128-4728-83d8-6a32f592ff8b)
![getUserReposFailure](https://github.com/user-attachments/assets/136c20c6-b159-456f-a60c-b26ae04c4c74)



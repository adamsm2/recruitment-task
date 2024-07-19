## Table of contents
- [Technologies](#technologies)
- [How to use](#how-to-use)
- [Features](#features)
- [How to have a higher rate limit](#how-to-have-a-higher-rate-limit)
- [Examples](#examples)

## Technologies
- Java 21
- SpringBoot 3

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

- Use a GitHub App 

## Examples
![getUserReposSuccess](https://github.com/user-attachments/assets/4d532b89-f128-4728-83d8-6a32f592ff8b)
![getUserReposFailure](https://github.com/user-attachments/assets/136c20c6-b159-456f-a60c-b26ae04c4c74)



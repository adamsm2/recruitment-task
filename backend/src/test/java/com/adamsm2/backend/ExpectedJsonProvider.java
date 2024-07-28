package com.adamsm2.backend;

class ExpectedJsonProvider {
    static final String GetNotForkedUserRepositories_okStatusExpectedJson = """
            [
              {
                "name": "optimal-heart-rate-calculator",
                "ownerLogin": "adamsm2",
                "branches": [
                  {
                    "name": "master",
                    "lastCommitSha": "c24264244e3eda1d4b2cf22823e6ca6300849c82"
                  }
                ]
              },
              {
                "name": "recruitment-task2",
                "ownerLogin": "adamsm2",
                "branches": [
                  {
                    "name": "deployment",
                    "lastCommitSha": "65fd227c0f04dcbe64b494333b59bf8bd8baf53b"
                  },
                  {
                    "name": "gh-pages",
                    "lastCommitSha": "9e6059308bf3b292833380b0780c51b7f8539e72"
                  },
                  {
                    "name": "main",
                    "lastCommitSha": "a712a832ccce42165883473d4f798dd3d97d8440"
                  }
                ]
              }
            ]
            """;

    static final String GetNotForkedUserRepositories_notFoundStatusExpectedJson = """
            {
              "status": 404,
              "message": "User with given username doesn't exist"
            }
            """;

    static final String GetNotForkedUserRepositories_andWhileFetchingEachRepositoryBranchesRepositoryNoLongerExists_notFoundStatusExpectedJson = """
            {
                "status": 404,
                "message": "Try again, cannot access a repository with given name"
            }
            """;

    static final String GetNotForkedUserRepositories_githubServiceUnavailableStatusExpectedJson = """
            {
                "status": 503,
                "message": "Github service is currently unavailable"
            }
            """;

    static final String GetNotForkedUserRepositories_tooManyRequestStatusExpectedJson = """
            {
                "status": 429,
                "message": "Github rate limit exceeded"
            }
            """;

    static final String GetNotForkedUserRepositories_unauthorizedStatusExpectedJson = """
            {
                "status": 401,
                "message": "Provided Github access token is invalid"
            }
            """;
}

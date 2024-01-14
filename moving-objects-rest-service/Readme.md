# international-aggregator-reactive-rest-service

## Build

---

1.  With OAuth2.0:

-   Make the build

```shell
gradle -Pprod build
```

-   Create environment variables with your own okta config:

```shell
. ./exportVars.sh
```

-   Run the application on the same shell:

```shell
java -jar build/libs/moving-objects-rest-service.jar
```

---

2.  Without OAuth2.0:

```shell
gradle build
```

---

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)

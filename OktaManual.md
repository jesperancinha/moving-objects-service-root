# Moving Objects Okta Configuration

In order to be able to run the secure Okta version of this project you need to have these values in your system variables:

```yaml
CLIENT_ID: "AAAAAAAAAAAAAAAAAAAA",
ISSUER: "https://dev-DEVID.okta.com/",
REDIRECT_URL: "http://localhost:8080/login/callback",
CLIENT_SECRET: "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
```

To get these values, please visit [Okta Dev](https://developer.okta.com/), create an account and an application.

In you application make sure to have the following values configured for the callbacks:

![alt img](./docs/images/Screenshot%202022-08-16%20at%2009.25.28.png)

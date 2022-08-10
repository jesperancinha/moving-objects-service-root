declare var $ENV: Env;

interface Env {
    ENVIRONMENT: string;
    CLIENT_ID: string;
    CLIENT_SECRET: string;
    ISSUER: string;
    OKTA_TESTING_DISABLEHTTPSCHECK: string;
    USE_INTERACTION_CODE: string;
}

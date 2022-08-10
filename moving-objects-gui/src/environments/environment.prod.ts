export const environment = {
    APIKeys: {
        CLIENT_ID: $ENV.CLIENT_ID,
        CLIENT_SECRET: $ENV.CLIENT_SECRET,
        ISSUER: $ENV.ISSUER,
        OKTA_TESTING_DISABLEHTTPSCHECK: $ENV.OKTA_TESTING_DISABLEHTTPSCHECK,
        USE_INTERACTION_CODE: $ENV.USE_INTERACTION_CODE,
    },
    environment: "prod",
    production: true,
};

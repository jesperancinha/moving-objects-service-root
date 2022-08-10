import {environment} from "../../environments/environment";
const USE_INTERACTION_CODE = environment.APIKeys.USE_INTERACTION_CODE || false;
const { CLIENT_ID, ISSUER, OKTA_TESTING_DISABLEHTTPSCHECK } = environment.APIKeys;

export const config = {
    oidc: {
        clientId: `${CLIENT_ID}`,
        issuer: `${ISSUER}`,
        redirectUri: "http://localhost:8080/login/callback",
        scopes: ["openid", "profile", "email"],
        testing: {
            disableHttpsCheck: `${OKTA_TESTING_DISABLEHTTPSCHECK}`,
        },
    },
    resourceServer: {
        messagesUrl: "http://localhost:8000/api/messages",
    },
    widget: {
        useInteractionCodeFlow: `${USE_INTERACTION_CODE}`,
    },
};

import {oktaConfig} from "./okta.config";

const {CLIENT_ID, ISSUER, REDIRECT_URL, CLIENT_SECRET} = oktaConfig;

export const environment = {
    CLIENT_ID: `${CLIENT_ID}`,
    CLIENT_SECRET: `${CLIENT_SECRET}`,
    ISSUER: `${ISSUER}`,
    REDIRECT_URL: `${REDIRECT_URL}`,
    environment: "prod",
    production: true,
};

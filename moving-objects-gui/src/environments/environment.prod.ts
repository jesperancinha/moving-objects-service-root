import {oktaConfig} from "./okta.config";

const {CLIENT_ID, ISSUER, REDIRECT_URL, CLIENT_SECRET} = oktaConfig

export const environment = {
    environment: "prod",
    production: true,
    CLIENT_ID: CLIENT_ID,
    ISSUER: ISSUER,
    REDIRECT_URL: REDIRECT_URL,
    CLIENT_SECRET: CLIENT_SECRET
};

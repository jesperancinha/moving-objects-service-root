const {CLIENT_ID, ISSUER, REDIRECT_URL, CLIENT_SECRET}  = require( "../environments/environment");

export const config = {
    clientSecret: `${CLIENT_SECRET}`,
    clientId: `${CLIENT_ID}`,
    issuer: `${ISSUER}`,
    redirectUri: `${REDIRECT_URL}`,
};


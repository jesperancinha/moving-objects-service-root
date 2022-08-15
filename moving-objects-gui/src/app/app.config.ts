import {environment} from "../environments/environment";

export const config = {
    // clientSecret: `${environment.CLIENT_SECRET}`,
    clientId: `${environment.CLIENT_ID}`,
    issuer: `${environment.ISSUER}`,
    redirectUri: `${environment.REDIRECT_URL}`,
};

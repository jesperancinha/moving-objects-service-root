import {environment} from "../environments/environment";

export const config = {
    // clientSecret: `${environment.CLIENT_SECRET}`,
    clientId: `${environment.CLIENT_ID}`,
    issuer: `${environment.ISSUER}`,
    postLogoutRedirectUri: `${environment.REDIRECT_LOGOUT}`,
    redirectServiceUrl: `${environment.REDIRECT_SERVICE_URL}`,
    redirectUri: `${environment.REDIRECT_URL}`,
};

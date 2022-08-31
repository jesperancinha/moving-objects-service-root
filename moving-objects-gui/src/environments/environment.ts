export const environment = {
  CLIENT_ID: "TEST",
  CLIENT_SECRET: "TEST",
  ISSUER: "http://localhost:4200",
  REDIRECT_LOGOUT: "http://localhost:4200/signout/",
  REDIRECT_SERVICE_LOGOUT_URL: "http://localhost:4200/aggregator/signout",
  REDIRECT_SERVICE_URL: "http://localhost:4200/aggregator/webcams/camera/GAR",
  REDIRECT_URL: "http://localhost:4200/login/callback",
  environment: "dev",
  production: false,
};

import "zone.js/dist/zone-error";  // Included with Angular CLI.

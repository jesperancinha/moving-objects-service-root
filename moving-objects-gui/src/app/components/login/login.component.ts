import {Component, Inject, OnInit} from "@angular/core";
import {OKTA_AUTH} from "@okta/okta-angular";
import {Tokens} from "@okta/okta-auth-js";
import OktaSignIn from "@okta/okta-signin-widget";
import {config} from "../../app.config";

const DEFAULT_ORIGINAL_URI = window.location.origin;

@Component({
    selector: "app-login",
    styleUrls: ["./login.component.css"],
    templateUrl: "./login.component.html",
})
export class LoginComponent implements OnInit {
    public signIn: any;

    constructor(@Inject(OKTA_AUTH) public oktaAuth) {
        this.signIn = new OktaSignIn({
            authClient: oktaAuth,
            baseUrl: config.oidc.issuer.split("/oauth2")[0],
            clientId: config.oidc.clientId,
            i18n: {
                en: {
                    "primaryauth.title": "Sign in to Moving Object",
                },
            },
            logo: "assets/favicon.ico",
            redirectUri: config.oidc.redirectUri,
            useInteractionCodeFlow: config.widget.useInteractionCodeFlow === "true",
        });
    }

    public ngOnInit() {
        const originalUri = this.oktaAuth.getOriginalUri();
        if (!originalUri || originalUri === DEFAULT_ORIGINAL_URI) {
            this.oktaAuth.setOriginalUri("/");
        }

        this.signIn.showSignInToGetTokens({
            el: "#sign-in-widget",
            scopes: config.oidc.scopes,
        }).then((tokens: Tokens) => {
            // Remove the widget
            this.signIn.remove();

            // In this flow the redirect to Okta occurs in a hidden iframe
            this.oktaAuth.handleLoginRedirect(tokens);
        }).catch((err: any) => {
            // Typically due to misconfiguration
            throw err;
        });
    }

    public ngOnDestroy() {
        this.signIn.remove();
    }

}

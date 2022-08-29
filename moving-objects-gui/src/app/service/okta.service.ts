import {Inject, Injectable} from "@angular/core";
import {OKTA_CONFIG, OktaConfig} from "@okta/okta-angular";
import {config} from "../app.config";

@Injectable({
    providedIn: "root",
})
export class OktaService {

    constructor(@Inject(OKTA_CONFIG) private oktaConfig: OktaConfig) {
    }

    public async logout() {
        // tslint:disable-next-line:no-console
        console.log(`Is User Authenticated ${await this.oktaConfig.oktaAuth.isAuthenticated()}`);
        // tslint:disable-next-line:no-console
        console.log(`Origin: ${this.oktaConfig.oktaAuth.getIssuerOrigin()}`);
        this.oktaConfig.oktaAuth.signOut(config)
            .then((_) => {
                // tslint:disable-next-line:no-console
                console.log("Logging out!...Bye!");
            },
        );
    }
}

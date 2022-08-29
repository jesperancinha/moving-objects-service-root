import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Inject, Injectable} from "@angular/core";
import {OKTA_CONFIG, OktaConfig} from "@okta/okta-angular";
import {Observable} from "rxjs";

@Injectable({
    providedIn: "root",
})
export class AuthInterceptor implements HttpInterceptor {

    constructor(@Inject(OKTA_CONFIG) private oktaConfig: OktaConfig) {
    }

    public intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // tslint:disable-next-line:no-console
        console.log(request);
        // tslint:disable-next-line:no-console
        console.log(this.oktaConfig.oktaAuth);
        // tslint:disable-next-line:no-console
        console.log(this.oktaConfig.oktaAuth.getAccessToken());
        // tslint:disable-next-line:no-console
        console.log(this.oktaConfig.oktaAuth.getRefreshToken());
        // if (request.url.indexOf("signout") > -1) {
        //     this.oktaAuth.signOut();
        // }
        // else if (request.url.indexOf("localhost") > -1) {
        //     request = request.clone({
        //         setHeaders: {
        //             Authorization: `Bearer ${this.oktaAuth.getRefreshToken()}`,
        //         },
        //     });
        // }

        return next.handle(request = request.clone({
            withCredentials: true,
        }));
    }
}

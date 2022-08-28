import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {OktaAuth} from "@okta/okta-auth-js";
import {Observable} from "rxjs";

@Injectable({
    providedIn: "root",
})
export class AuthInterceptor implements HttpInterceptor {

    constructor(private oktaAuth: OktaAuth) {
    }

    public intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        debugger;
        if (request.url.indexOf("localhost") > -1) {
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${this.oktaAuth.getAccessToken()}`,
                },
            });
        }

        return next.handle(request);
    }
}

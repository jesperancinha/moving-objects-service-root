import {HttpClientModule} from "@angular/common/http";
import {Injector, NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatButtonModule} from "@angular/material/button";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatListModule} from "@angular/material/list";
import {MatSelectModule} from "@angular/material/select";
import {MatTabsModule} from "@angular/material/tabs";
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {Router, RouterModule} from "@angular/router";
import {OKTA_CONFIG, OktaAuthGuard, OktaCallbackComponent} from "@okta/okta-angular";
import {OktaAuth} from "@okta/okta-auth-js";
import {AppComponent} from "./app.component";
import {config} from "./app.config";
import {LoaderComponent} from "./components/loader.component";
import {LoginComponent} from "./components/login/login.component";
import {MetricsComponent} from "./components/metrics/metrics.component";
import {ObjectsComponent} from "./components/objects/objects.component";
import {WebCamsComponent} from "./components/webcamsearch/webcams.component";

const routes = [
    {
        canActivate: [ OktaAuthGuard ],
        component: AppComponent,
        path: "",
    },
    {
        component: OktaCallbackComponent,
        path: "login/callback",
    },
];

@NgModule({
    bootstrap: [
        AppComponent,
    ],
    declarations: [
        AppComponent,
        LoaderComponent,
        MetricsComponent,
        ObjectsComponent,
        LoaderComponent,
        ObjectsComponent,
        WebCamsComponent,
        LoginComponent,
    ],
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule.forRoot(routes, {useHash: true}),
        HttpClientModule,
        MatTabsModule,
        MatFormFieldModule,
        MatSelectModule,
        MatCardModule,
        BrowserAnimationsModule,
        MatButtonToggleModule,
        MatAutocompleteModule,
        ReactiveFormsModule,
        MatInputModule,
        MatListModule,
        MatIconModule,
        MatButtonModule,
    ],
    providers: [
        {
            provide: OKTA_CONFIG,
            useFactory: () => {
                return {
                    oktaAuth: new OktaAuth(config.oidc),
                    onAuthRequired: (oktaAuth: OktaAuth, injector: Injector) => {
                        const triggerLogin = () => {
                            const router = injector.get(Router);
                            router.navigate(["/login"]).then((_) => { });
                        };
                        if (!oktaAuth.authStateManager.getPreviousAuthState()?.isAuthenticated) {
                            triggerLogin();
                        }
                    },
                };
            },
        },
    ],
})
export class AppModule {
}

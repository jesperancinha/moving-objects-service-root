import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {NgModule} from "@angular/core";
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
import {RouterModule} from "@angular/router";
import {OKTA_CONFIG, OktaAuthGuard, OktaAuthModule, OktaCallbackComponent} from "@okta/okta-angular";
import {OktaAuth} from "@okta/okta-auth-js";
import {environment} from "../environments/environment";
import {AppComponent} from "./app.component";
import {config} from "./app.config";
import {LoaderComponent} from "./components/loader.component";
import {MetricsComponent} from "./components/metrics/metrics.component";
import {ObjectsComponent} from "./components/objects/objects.component";
import {WebCamsComponent} from "./components/webcamsearch/webcams.component";
import {AuthInterceptor} from "./service/interceptors";

const oktaAuth = new OktaAuth(config);

const routes = environment.production ? [
    {
        component: OktaCallbackComponent,
        path: "login/callback",
    },
    {
        component: OktaCallbackComponent,
        path: "callback",
    },
    {
        canActivate: [OktaAuthGuard],
        component: AppComponent,
        path: "",
    },
] : [{
    component: AppComponent,
    path: "",
},
];

function prodProviders() {
    return [
        {
            provide: OKTA_CONFIG,
            useValue: {oktaAuth},
        },
        {
            multi: true,
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
        },
    ];
}

const providers = environment.production ? prodProviders() : [];

const declarations = [
    AppComponent,
    LoaderComponent,
    MetricsComponent,
    ObjectsComponent,
    LoaderComponent,
    ObjectsComponent,
    WebCamsComponent,
];

const imports: any[] = [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes, {useHash: false}),
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
    OktaAuthModule,
];

const bootstrap: any[] = [
    AppComponent,
];

if (environment.production) {
    bootstrap.push(OktaCallbackComponent);
    imports.push(OktaAuthModule);
}

@NgModule({
    bootstrap,
    declarations,
    imports,
    providers,
})

export class AppModule {
}

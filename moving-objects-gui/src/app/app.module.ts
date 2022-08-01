import {HttpClientModule} from "@angular/common/http";
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
import {AppComponent} from "./app.component";
import {LoaderComponent} from "./components/loader.component";
import {MetricsComponent} from "./components/metrics/metrics.component";
import {ObjectsComponent} from "./components/objects/objects.component";
import {WebCamsComponent} from "./components/webcamsearch/webcams.component";

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
    ],
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule.forRoot([{
            component: AppComponent,
            path: "",
        }], {useHash: true}),
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
})
export class AppModule {
}

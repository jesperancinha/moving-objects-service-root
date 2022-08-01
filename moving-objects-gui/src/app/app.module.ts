import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {RouterModule} from "@angular/router";
import {LoaderComponent} from "./components/loader.component";
import {ObjectsComponent} from "./components/airports/objects.component";
import {MetricsComponent} from "./components/metrics/metrics.component";
import {WebCamsComponent} from "./components/webcamsearch/webcams.component";
import {HttpClientModule} from "@angular/common/http";
import {MatTabsModule} from "@angular/material/tabs";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatCardModule} from "@angular/material/card";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatInputModule} from "@angular/material/input";
import {MatListModule} from "@angular/material/list";
import {MatIconModule} from "@angular/material/icon";

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
            path: '',
        },], {useHash: true}),
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
    ],
})
export class AppModule {
}

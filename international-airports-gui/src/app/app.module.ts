import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {RouterModule} from "@angular/router";
import {LoaderComponent} from "./components/loader.component";
import {AirportsComponent} from "./components/airports/airports.component";
import {MetricsComponent} from "./components/metrics/metrics.component";
import {WebCamsComponent} from "./components/webcamsearch/webcams.component";
import {HttpClientModule} from "@angular/common/http";
import {MatTabsModule} from "@angular/material/tabs";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatCardModule} from "@angular/material/card";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonToggleModule} from "@angular/material/button-toggle";

@NgModule({
    bootstrap: [
        AppComponent,
    ],
    declarations: [
        AppComponent,
        LoaderComponent,
        MetricsComponent,
        AirportsComponent,
        LoaderComponent,
        AirportsComponent,
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
    ],
})
export class AppModule {
}

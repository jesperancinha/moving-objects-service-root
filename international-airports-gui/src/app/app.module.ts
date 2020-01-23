import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';


import {RouterModule} from "@angular/router";
import {AirportComponent} from "./components/webcamsearch/airports";
import {HttpClientModule} from "@angular/common/http";
import {MetricsComponent} from "./components/metrics/metrics";
import {FaresComponent} from "./components/airports/fares";
import {LoaderComponent} from "./components/loader";

@NgModule({
    bootstrap: [
        AppComponent,
    ],
    declarations: [
        AppComponent,
        LoaderComponent,
        FaresComponent,
        MetricsComponent,
        AirportComponent,
        FaresComponent,
        LoaderComponent,
    ],
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule.forRoot([{
            component: AppComponent,
            path: '',
        },], {useHash: true}),
        HttpClientModule,
    ],
})
export class AppModule {
}

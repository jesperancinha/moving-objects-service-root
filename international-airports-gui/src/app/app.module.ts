import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {RouterModule} from "@angular/router";
import {LoaderComponent} from "./components/loader";
import {AirportsComponent} from "./components/airports/airports.component";
import {MetricsComponent} from "./components/metrics/metrics.component";
import {WebCamsComponent} from "./components/webcamsearch/webcams.component";
import {HttpClientModule} from "@angular/common/http";
import {MatTabsModule} from "@angular/material/tabs";

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
    ],
})
export class AppModule {
}

import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';

import {FileUploadModule} from 'ng2-file-upload';
import {
    NbAlertModule,
    NbButtonModule,
    NbCardModule,
    NbInputModule,
    NbLayoutModule,
    NbSelectModule,
    NbSidebarModule,
    NbSpinnerModule,
    NbTabsetModule,
    NbThemeModule, NbTreeGridModule,
} from '@nebular/theme';
import {RouterModule} from "@angular/router";
import {ColorPickerModule} from "ngx-color-picker";
import {AirportComponent} from "./loader/airports/airports";
import {HttpClientModule} from "@angular/common/http";
import {Ng5SliderModule} from "ng5-slider";
import {MetricsComponent} from "./loader/metrics/metrics";
import {FaresComponent} from "./loader/fares/fares";
import {LoaderComponent} from "./loader/loader";

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
        NbButtonModule,
        NbThemeModule.forRoot(),
        NbLayoutModule,
        RouterModule.forRoot([{
            component: AppComponent,
            path: '',
        },], {useHash: true}),
        NbSidebarModule.forRoot(),
        NbInputModule,
        NbCardModule,
        NbSpinnerModule,
        FileUploadModule,
        NbAlertModule,
        NbTabsetModule,
        ColorPickerModule,
        NbSelectModule,
        HttpClientModule,
        Ng5SliderModule,
        NbTreeGridModule,
    ],
})
export class AppModule {
}

import {Component, OnInit} from '@angular/core';
import {MetricsComponent} from "./metrics/metrics.component";
import {WebCamsComponent} from "./webcamsearch/webcams.component";
import {AirportsComponent} from "./airports/airports.component";
import {MatTabChangeEvent} from "@angular/material/tabs";


@Component({
    selector: 'loader-selector',
    styleUrls: ['./loader.component.css'],
    templateUrl: './loader.component.html',
})
export class LoaderComponent implements OnInit {
    private metricsComponent: MetricsComponent;
    private airportComponent: AirportsComponent;
    private webCamComponent: WebCamsComponent;

    public ngOnInit(): void {
    }

    public tabChanged(
        $event: MatTabChangeEvent,
        faresComponent: WebCamsComponent,
        metricsComponent: MetricsComponent
        , airportComponent: AirportsComponent): void {
        this.webCamComponent = faresComponent;
        this.metricsComponent = metricsComponent;
        this.airportComponent = airportComponent;
    }

}

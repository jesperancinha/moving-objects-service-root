import {Component, OnInit} from '@angular/core';
import {MetricsComponent} from './metrics/metrics.component';
import {WebCamsComponent} from './webcamsearch/webcams.component';
import {ObjectsComponent} from './airports/objects.component';
import {MatTabChangeEvent} from '@angular/material/tabs';


@Component({
    selector: 'app-loader-selector',
    styleUrls: ['./loader.component.css'],
    templateUrl: './loader.component.html',
})
export class LoaderComponent implements OnInit {
    private metricsComponent: MetricsComponent;
    private airportComponent: ObjectsComponent;
    private webCamComponent: WebCamsComponent;

    public ngOnInit(): void {
    }

    public tabChanged(
        $event: MatTabChangeEvent,
        faresComponent: WebCamsComponent,
        metricsComponent: MetricsComponent,
        airportComponent: ObjectsComponent): void {
        this.webCamComponent = faresComponent;
        this.metricsComponent = metricsComponent;
        this.airportComponent = airportComponent;
    }

}

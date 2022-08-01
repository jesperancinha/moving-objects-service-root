import {Component, OnInit} from "@angular/core";
import {MatTabChangeEvent} from "@angular/material/tabs";
import {MetricsComponent} from "./metrics/metrics.component";
import {ObjectsComponent} from "./objects/objects.component";
import {WebCamsComponent} from "./webcamsearch/webcams.component";

@Component({
    selector: "app-loader-selector",
    styleUrls: ["./loader.component.css"],
    templateUrl: "./loader.component.html",
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

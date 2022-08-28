import {Component} from "@angular/core";
import {MatTabChangeEvent} from "@angular/material/tabs";
import {MetricsComponent} from "./metrics/metrics.component";
import {WebCamsComponent} from "./webcamsearch/webcams.component";

@Component({
    selector: "app-loader-selector",
    styleUrls: ["./loader.component.css"],
    templateUrl: "./loader.component.html",
})
export class LoaderComponent {
    private metricsComponent: MetricsComponent;
    private webCamComponent: WebCamsComponent;

    public tabChanged(
        $event: MatTabChangeEvent,
        faresComponent: WebCamsComponent,
        metricsComponent: MetricsComponent,
    ): void {
        this.webCamComponent = faresComponent;
        this.metricsComponent = metricsComponent;
    }
}

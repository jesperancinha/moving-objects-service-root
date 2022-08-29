import {HttpClient} from "@angular/common/http";
import {Component} from "@angular/core";
import {MatTabChangeEvent} from "@angular/material/tabs";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";
import {OktaService} from "../service/okta.service";
import {MetricsComponent} from "./metrics/metrics.component";
import {WebCamsComponent} from "./webcamsearch/webcams.component";

@Component({
    selector: "app-loader-selector",
    styleUrls: ["./loader.component.css"],
    templateUrl: "./loader.component.html",
})
export class LoaderComponent {
    public c = environment.environment;
    private metricsComponent: MetricsComponent;
    private webCamComponent: WebCamsComponent;

    constructor(private httpClient: HttpClient, private router: Router, private oktaService: OktaService) {
    }

    public tabChanged(
        $event: MatTabChangeEvent,
        faresComponent: WebCamsComponent,
        metricsComponent: MetricsComponent,
    ): void {
        this.webCamComponent = faresComponent;
        this.metricsComponent = metricsComponent;
    }

    public logout() {
        // this.httpClient.get("http://localhost:4200/aggregator/signout").subscribe();
        // this.httpClient.get("http://localhost:4200/signout").subscribe();
        // this.router.navigateByUrl("/").then();
        this.oktaService.logout().then();
    }
}

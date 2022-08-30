import {HttpClient} from "@angular/common/http";
import {Component, OnInit} from "@angular/core";
import {MatTabChangeEvent} from "@angular/material/tabs";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";
import {oktaConfig} from "../../environments/okta.config";
import {OktaService} from "../service/okta.service";
import {MetricsComponent} from "./metrics/metrics.component";
import {WebCamsComponent} from "./webcamsearch/webcams.component";

@Component({
    selector: "app-loader-selector",
    styleUrls: ["./loader.component.css"],
    templateUrl: "./loader.component.html",
})
export class LoaderComponent implements OnInit {
    public c = environment.environment;
    private metricsComponent: MetricsComponent;
    private webCamComponent: WebCamsComponent;
    private w = null;

    constructor(private httpClient: HttpClient, private router: Router, private oktaService: OktaService) {
    }

    public ngOnInit() {
        if (window.location.href.indexOf("localhost") >= 0) {
            this.w = window.open("http://localhost:4200/aggregator/webcams/camera/GAR", "_blank");
            this.w.addEventListener("load", () => this.w.close(), true);
        }
    }

    public ngAfterContentInit() {
        setTimeout(() => this.w.close(), 1000);
    }

    public ngOnDestroy() {
        this.w.close();
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
        const logout = window.open("http://localhost:4200/aggregator/signout", "_blank");
        logout.addEventListener("load", () => logout.close(), true);
        setTimeout(() => {
            logout.close();
            window.location.href =
                `${oktaConfig.ISSUER}/login/signout?fromURI=http://localhost:${window.location.port}`;
        }, 1000);

    }

    // ts-ignore
    public superLogout() {
        this.httpClient.get("http://localhost:4200/aggregator/signout").subscribe();
        this.httpClient.get("http://localhost:4200/signout").subscribe();
        this.router.navigateByUrl("/").then();
        this.oktaService.logout().then();
    }
}

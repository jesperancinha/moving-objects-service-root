import {Component, OnInit} from '@angular/core';
import {NbTabComponent} from "@nebular/theme";
import {MetricsComponent} from "./metrics/metrics";
import {AirportComponent} from "./airports/airports";
import {FaresComponent} from "./fares/fares";

@Component({
    selector: 'loader',
    styleUrls: ['./loader.css'],
    templateUrl: './loader.html',
})
export class LoaderComponent implements OnInit {
    private currentTab: NbTabComponent;
    private metricsComponent: MetricsComponent;
    private airportComponent: AirportComponent;
    private faresComponent: FaresComponent;

    public ngOnInit(): void {
    }

    public tabChanged(tab: NbTabComponent,
                      faresComponent: FaresComponent,
                      metricsComponent: MetricsComponent,
                      airportComponent: AirportComponent
    ): void {
        this.faresComponent = faresComponent;
        this.metricsComponent = metricsComponent;
        this.airportComponent = airportComponent;
        this.currentTab = tab;
        switch (this.currentTab.tabId) {
            case"metrics":
                metricsComponent.populateControls();
                break;
            case"airports":
                airportComponent.populateControls();
                break;

        }
    }

}

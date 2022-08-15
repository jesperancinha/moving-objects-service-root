import {Component, OnInit} from "@angular/core";
import {finalize} from "rxjs/operators";
import * as uuid from "uuid";
import {MetricTag} from "../../model/metric.tag";
import {Metrics} from "../../model/metrics";
import {Statistic} from "../../model/statistic";
import {Traces} from "../../model/traces";
import {MetricsService} from "../../service/metrics.service";

@Component({
    selector: "app-metrics-selector",
    styleUrls: ["./metrics.component.scss"],
    templateUrl: "./metrics.component.html",
})
export class MetricsComponent implements OnInit {
    private static METRIC_TAG_ALL: MetricTag = {tag: "All", value: null};
    public loading: boolean;
    public metrics: Metrics = new Metrics();
    public options: MetricTag[] = [];
    public option: MetricTag;
    public statistics: Statistic[];
    public statMax: number;
    public statMin: number;
    public statCount: number;
    public statTotal: number;
    public statAvg: number;
    public countLoaders: number;

    constructor(private metricService: MetricsService) {
        this.loading = false;
    }

    public ngOnInit(): void {
        this.populateControls();
    }

    public populateControls() {
        this.metricService
            .getSystemMetrics()
            .subscribe((value) => {
                this.options = [];
                this.options.push(MetricsComponent.METRIC_TAG_ALL);
                this.metrics = value;
                if (this.metrics) {
                    this.metrics.availableTags
                        .filter((tag) => tag.tag === "status")
                        .forEach((tag) => tag.values
                            .forEach((name) => this.options.push({
                                tag: name,
                                value: name,
                            })));
                }
                this.loadSelectedErrorMetrics();
            });
    }

    public changeErrorCode() {
        this.loadSelectedErrorMetrics();
    }

    private loadSelectedErrorMetrics() {
        this.loading = true;
        this.countLoaders = 2;
        this.metricService
            .getSystemMetrics(this.option)
            .pipe(finalize(() => this.countLoaders--))
            .subscribe((value) => {
                // tslint:disable-next-line:no-console
                console.log("async-task-" + uuid.v4());
                if (value) {
                    this.statistics = value.measurements;
                    this.statMax = value.measurements.find((stat) => stat.statistic === "MAX").value;
                    this.statCount = value.measurements.find((stat) => stat.statistic === "COUNT").value;
                    this.statTotal = value.measurements.find((stat) => stat.statistic === "TOTAL_TIME").value;
                    let totalTimeValue = value.measurements
                        .find((stat) => stat.statistic === "TOTAL_TIME").value;
                    this.statAvg = totalTimeValue ? totalTimeValue.valueOf() / this.statCount.valueOf() : 0;
                }
            });
        this.metricService
            .getHttpTraces()
            .pipe(finalize(() => this.countLoaders--))
            .subscribe((response?: Traces) => {
                if (response) {
                    let firstStatsMin = response.traces
                        .filter((trace) => trace)
                        .map((trace) => trace.timeTaken ? 1000000 : trace.timeTaken)
                        .sort((x1, x2) => x2.valueOf() - x1.valueOf())[0];
                    this.statMin = firstStatsMin ? firstStatsMin
                        .valueOf() / 1000 : 0;
                }
            });

    }
}

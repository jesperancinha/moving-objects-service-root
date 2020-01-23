import {Component, OnInit} from '@angular/core';
import {Metrics} from "../../model/metrics";
import {MetricTag} from "../../model/metrics-tag";
import {Statistic} from "../../model/statistic";
import * as uuid from 'uuid';
import {MetricsService} from "../../service/metrics.service";
import {finalize} from "rxjs/operators";

@Component({
    selector: 'metrics-selector',
    styleUrls: ['./metrics.component.scss'],
    templateUrl: './metrics.component.html',
})
export class MetricsComponent implements OnInit {
    public loading: boolean;
    public metrics: Metrics = new Metrics();
    private errorText: String;
    public options: MetricTag[] = [];
    private static METRIC_TAG_ALL: MetricTag = {tag: "All", value: null};
    public option: MetricTag;
    public statistics: Statistic[];
    public statMax: Number;
    public statMin: Number;
    public statCount: Number;
    public statTotal: Number;
    public statAvg: Number;
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
            .subscribe(value => {
                this.options = [];
                this.options.push(MetricsComponent.METRIC_TAG_ALL);
                this.metrics = value;
                this.metrics.availableTags.filter(tag => tag.tag === 'status').forEach(tag => tag.values.forEach(name => this.options.push({
                    tag: name,
                    value: name
                })));
                this.loadSelectedErrorMetrics();
            })
    }

    changeErrorCode() {
        this.loadSelectedErrorMetrics();
    }

    private loadSelectedErrorMetrics() {
        this.loading = true;
        this.countLoaders = 2;
        this.metricService
            .getSystemMetrics(this.option)
            .pipe(finalize(() => this.countLoaders--))
            .subscribe(value => {
                console.log("async-task-" + uuid.v4());
                this.statistics = value.measurements;
                this.statMax = value.measurements.find(stat => stat.statistic == 'MAX').value;
                this.statCount = value.measurements.find(stat => stat.statistic == 'COUNT').value;
                this.statTotal = value.measurements.find(stat => stat.statistic == 'TOTAL_TIME').value;
                this.statAvg = value.measurements.find(stat => stat.statistic == 'TOTAL_TIME').value.valueOf() / this.statCount.valueOf();
            });
        this.metricService
            .getHttpTraces()
            .pipe(finalize(() => this.countLoaders--))
            .subscribe(value => {
                this.statMin = value.traces.map(trace => trace.timeTaken)
                    .sort((x1, x2) => x2.valueOf() - x1.valueOf())[0].valueOf() / 1000;
            })

    }
}


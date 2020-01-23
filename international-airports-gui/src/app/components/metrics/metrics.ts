import {Component, OnInit} from '@angular/core';
import {Metrics} from "../../model/metrics";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {MetricTag} from "../../model/metrics-tag";
import {Statistic} from "../../model/statistic";
import {Traces} from "../../model/traces";
import * as uuid from 'uuid';

@Component({
    selector: 'metrics',
    styleUrls: ['./metrics.scss'],
    templateUrl: './metrics.html',
})
export class MetricsComponent implements OnInit {
    public loading: boolean;
    public metrics: Metrics = new Metrics();
    private errorText: String;
    public options: MetricTag[] = [];
    private static METRIC_TAG_ALL: MetricTag = {tag: "All", value: null};
    private headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
    public option: MetricTag;
    public statistics: Statistic[];
    public statMax: Number;
    public statMin: Number;
    public statCount: Number;
    public statTotal: Number;
    public statAvg: Number;
    public countLoaders: number;

    constructor(private httpClient: HttpClient) {
        this.loading = false;
    }

    public ngOnInit(): void {
        this.populateControls();
    }

    public populateControls() {
        this.httpClient.get<Metrics>('/iairports/actuator/metrics/http.server.requests', {headers: this.headers}).toPromise()
            .then(value => {
                this.options = [];
                this.options.push(MetricsComponent.METRIC_TAG_ALL);
                this.metrics = value;
                this.metrics.availableTags.filter(tag => tag.tag === 'status').forEach(tag => tag.values.forEach(name => this.options.push({
                    tag: name,
                    value: name
                })));
                this.loadSelectedErrorMetrics();
            })
            .catch(fail => {
                console.log(fail);
                this.errorText = fail;
            });
    }

    changeErrorCode($event: MetricTag[] | MetricTag) {
        this.loadSelectedErrorMetrics();
    }

    private loadSelectedErrorMetrics() {
        if (this.option) {
            var url = '/iairports/actuator/metrics/http.server.requests';
            if (this.option.value) {
                url += "?tag=status:" + this.option.value;
            }
            this.loading = true;
            this.countLoaders = 2;
            this.httpClient.get<Metrics>(url, {headers: this.headers}).toPromise()
                .then(value => {
                    console.log("async-task-" + uuid.v4());
                    this.statistics = value.measurements;
                    this.statMax = value.measurements.find(stat => stat.statistic == 'MAX').value;
                    this.statCount = value.measurements.find(stat => stat.statistic == 'COUNT').value;
                    this.statTotal = value.measurements.find(stat => stat.statistic == 'TOTAL_TIME').value;
                    this.statAvg = value.measurements.find(stat => stat.statistic == 'TOTAL_TIME').value.valueOf() / this.statCount.valueOf();
                })
                .catch(fail => {
                    console.log(fail);
                    this.errorText = fail;
                })
                .finally(() => {
                    this.countLoaders--;
                });

            this.httpClient.get<Traces>("/iairports/actuator/httptrace", {headers: this.headers}).toPromise()
                .then(value => {
                    this.statMin = value.traces.map(trace => trace.timeTaken)
                        .sort((x1, x2) => x2.valueOf() - x1.valueOf())[0].valueOf() / 1000;
                })
                .catch(fail => {
                    console.log(fail);
                    this.errorText = fail;
                })
                .finally(() => {
                    this.countLoaders--;
                });
        }
    }
}


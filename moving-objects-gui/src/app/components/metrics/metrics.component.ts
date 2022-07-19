import {Component, OnInit} from '@angular/core';
import {Metrics} from '../../model/metrics';
import {MetricTag} from '../../model/metrics-tag';
import {Statistic} from '../../model/statistic';
import * as uuid from 'uuid';
import {MetricsService} from '../../service/metrics.service';
import {finalize} from 'rxjs/operators';

@Component({
    selector: 'app-metrics-selector',
    styleUrls: ['./metrics.component.scss'],
    templateUrl: './metrics.component.html',
})
export class MetricsComponent implements OnInit {

    constructor(private metricService: MetricsService) {
        this.loading = false;
    }
    private static METRIC_TAG_ALL: MetricTag = {tag: 'All', value: null};
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
            });
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
                console.log('async-task-' + uuid.v4());
                this.statistics = value.measurements;
                this.statMax = value.measurements.find(stat => stat.statistic === 'MAX').value;
                this.statCount = value.measurements.find(stat => stat.statistic === 'COUNT').value;
                this.statTotal = value.measurements.find(stat => stat.statistic === 'TOTAL_TIME').value;
                this.statAvg = value.measurements.find(stat => stat.statistic === 'TOTAL_TIME').value.valueOf() / this.statCount.valueOf();
            });
        this.metricService
            .getHttpTraces()
            .pipe(finalize(() => this.countLoaders--))
            .subscribe(value => {
                this.statMin = value.traces.map(trace => trace.timeTaken)
                    .sort((x1, x2) => x2.valueOf() - x1.valueOf())[0].valueOf() / 1000;
            });

    }
}


import {Observable} from "rxjs";
import {Metrics} from "../model/metrics";
import {Traces} from "../model/traces";
import {MetricTag} from "../model/metrics-tag";

export interface MetricServiceInterface {
    getSystemMetrics(errorCode?: MetricTag): Observable<Metrics>;

    getHttpTraces(): Observable<Traces>;
}
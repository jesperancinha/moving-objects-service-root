import {Observable} from "rxjs";
import {MetricTag} from "../model/metric.tag";
import {Metrics} from "../model/metrics";
import {Traces} from "../model/traces";

// tslint:disable-next-line:interface-name
export interface MetricServiceInterface {
    getSystemMetrics(errorCode?: MetricTag): Observable<Metrics>;

    getHttpTraces(): Observable<Traces>;
}

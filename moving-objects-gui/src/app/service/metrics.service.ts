import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {catchError, Observable, of, retry} from "rxjs";
import {MetricServiceInterface} from "../interface/metrics.service.interface";
import {MetricTag} from "../model/metric.tag";
import {Metrics} from "../model/metrics";
import {Traces} from "../model/traces";

const metricsRootPath = "/aggregator/actuator";

@Injectable({
    providedIn: "root",
})
export class MetricsService implements MetricServiceInterface {
    private headers: HttpHeaders = new HttpHeaders({"Content-Type": "application/json"});

    constructor(private httpClient: HttpClient) {
    }

    public getSystemMetrics(errorCode?: MetricTag): Observable<Metrics> {
        let url = `${metricsRootPath}/metrics/http.server.requests`;
        if (errorCode && errorCode.value) {
            url += `?tag=status:${errorCode.value}`;
        }
        return this.httpClient.get<Metrics>(url, {headers: this.headers}).pipe(
            retry(3), catchError(this.handleError<Metrics>()));
    }

    public getHttpTraces(): Observable<Traces> {
        return this.httpClient.get<Traces>(`${metricsRootPath}/httptrace`, {headers: this.headers}).pipe(
            retry(3), catchError(this.handleError<Traces>()));
    }

    private handleError<T>(operation = "operation", result?: T) {
        return (error: any): Observable<T> => {
            // tslint:disable-next-line:no-console
            console.error(error);
            // tslint:disable-next-line:no-console
            console.log(`${operation} failed: ${error.message}`);
            return of(result);
        };
    }
}

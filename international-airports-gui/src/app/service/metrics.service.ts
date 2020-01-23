import {Metrics} from "../model/metrics";
import {Traces} from "../model/traces";
import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError, retry} from "rxjs/internal/operators";
import {MetricServiceInterface} from "../interface/metrics.service.interface";
import {MetricTag} from "../model/metrics-tag";

@Injectable({
    providedIn: 'root'
})
export class MetricsService implements MetricServiceInterface {
    private headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

    constructor(private httpClient: HttpClient) {
    }

    public getSystemMetrics(errorCode?: MetricTag): Observable<Metrics> {
        let url = '/iairports/actuator/metrics/http.server.requests';
        if (errorCode && errorCode.value) {
            url += `?tag=status:${errorCode.value}`;
        }
        return this.httpClient.get<Metrics>(url, {headers: this.headers}).pipe(
            retry(3), catchError(this.handleError<Metrics>()));
    }

    public getHttpTraces(): Observable<Traces> {
        return this.httpClient.get<Traces>("/iairports/actuator/httptrace", {headers: this.headers}).pipe(
            retry(3), catchError(this.handleError<Traces>()));
    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(error);
            console.log(`${operation} failed: ${error.message}`);

            return of(result);
        };
    }
}
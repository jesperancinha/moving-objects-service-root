import {catchError, retry} from 'rxjs/internal/operators';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Airport} from "../model/airport";
import {AirportServiceInterface} from "../interface/airport.service.interface";



@Injectable({
    providedIn: 'root'
})
export class AirportService implements AirportServiceInterface{

    constructor(private http: HttpClient) {
    }

    getAirportsPerTerm(term: String): Observable<Airport[]> {
        return this.http.get<Airport[]>(`/iairports/airports/code/${term}`).pipe(
            retry(3), catchError(this.handleError<Airport[]>()));
    }

    getAirportPerCode(code: String): Observable<Airport[]> {
        return this.http.get<Airport[]>(`/iairports/airports/code/${code}`).pipe(
            retry(3), catchError(this.handleError<Airport[]>()));
    }

    private handleError<Airport>(operation = 'operation', result?: Airport) {
        return (error: any): Observable<Airport> => {
            console.error(error);
            console.log(`${operation} failed: ${error.message}`);

            return of(result);
        };
    }
}


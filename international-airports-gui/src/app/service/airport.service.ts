import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError, Observable, of, retry} from 'rxjs';
import {Airport} from '../model/airport';
import {AirportServiceInterface} from '../interface/airport.service.interface';

@Injectable({
    providedIn: 'root'
})
export class AirportService implements AirportServiceInterface {

    constructor(private http: HttpClient) {
    }

    getAirportsPerTerm(term: string, radius: string): Observable<Airport[]> {
        return this.http.get<Airport[]>(`/iairports/airports/code/${term}/${radius}`).pipe(
            retry(3), catchError(this.handleError<Airport[]>()));
    }

    getAirportPerCode(code: string, radius: string): Observable<Airport> {
        return this.http.get<Airport>(`/iairports/airports/code/${code}/${radius}`).pipe(
            retry(3), catchError(this.handleError<Airport>()));
    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(error);
            console.log(`${operation} failed: ${error.message}`);

            return of(result);
        };
    }
}


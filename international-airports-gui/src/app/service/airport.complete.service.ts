import {catchError, map, retry} from 'rxjs/internal/operators';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Airport} from "../model/airport";
import {AirportServiceInterface} from "../interface/airport.service.interface";


@Injectable({
    providedIn: 'root'
})
export class AirportCompleteService implements AirportServiceInterface {

    constructor(private http: HttpClient) {
    }

    getAirportsPerTerm(term: String, radius: String): Observable<Airport[]> {
        return this.http.get<Airport[]>(`/iairports/airportwebcams/term/${term}/${radius}`).pipe(
            retry(3), catchError(this.handleError<Airport[]>()));
    }

    getAirportPerCode(code: String, radius: String): Observable<Airport> {
        return this.http.get<Airport>(`/iairports/airportwebcams/code/${code}/${radius}`)
            .pipe(retry(3), catchError(this.handleError<Airport[]>()))
            .pipe(map((airports: Airport[]) => airports[0]))

    }

    private handleError<Airport>(operation = 'operation', result?: Airport) {
        return (error: any): Observable<Airport> => {
            console.error(error);
            console.log(`${operation} failed: ${error.message}`);

            return of(result);
        };
    }
}


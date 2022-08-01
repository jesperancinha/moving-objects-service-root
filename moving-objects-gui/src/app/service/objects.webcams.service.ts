import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError, map, Observable, of, retry} from 'rxjs';
import {Airport} from '../model/airport';
import {AirportServiceInterface} from '../interface/airport.service.interface';

const objectsWebcamsRootPath = `/aggregator/objectswebcams`;

@Injectable({
    providedIn: 'root'
})
export class ObjectsWebcamsService implements AirportServiceInterface {

    constructor(private http: HttpClient) {
    }

    getAirportsPerTerm(term: string, radius: string): Observable<Airport[]> {
        return this.http.get<Airport[]>(`${objectsWebcamsRootPath}/term/${term}/${radius}`).pipe(
            retry(3), catchError(this.handleError<Airport[]>()));
    }

    getAirportPerCode(code: string, radius: string): Observable<Airport> {
        return this.http.get<Airport>(`${objectsWebcamsRootPath}/code/${code}/${radius}`)
            .pipe(retry(3), catchError(this.handleError<Airport[]>()))
            .pipe(map((airports: Airport[]) => airports[0]));
    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(error);
            console.log(`${operation} failed: ${error.message}`);

            return of(result);
        };
    }
}


import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, of, retry} from "rxjs";
import {Airport} from "../model/airport";
import {AirportServiceInterface} from "../interface/airport.service.interface";

const objectsRootPath = `/aggregator/objects`;

@Injectable({
    providedIn: "root"
})
export class ObjectsService implements AirportServiceInterface {

    constructor(private http: HttpClient) {
    }

    public getAirportsPerTerm(term: string, radius: string): Observable<Airport[]> {
        return this.http.get<Airport[]>(`${objectsRootPath}/code/${term}/${radius}`).pipe(
            retry(3), catchError(this.handleError<Airport[]>()));
    }

    public getAirportPerCode(code: string, radius: string): Observable<Airport> {
        return this.http.get<Airport>(`${objectsRootPath}/${code}/${radius}`).pipe(
            retry(3), catchError(this.handleError<Airport>()));
    }

    private handleError<T>(operation = "operation", result?: T) {
        return (error: any): Observable<T> => {
            console.error(error);
            console.log(`${operation} failed: ${error.message}`);

            return of(result);
        };
    }
}


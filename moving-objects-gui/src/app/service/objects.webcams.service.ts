import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {catchError, map, Observable, of, retry} from "rxjs";
import {MovingObject} from "../model/movingObject";
import {AirportServiceInterface} from "../interface/airport.service.interface";

const objectsWebcamsRootPath = `/aggregator/objectswebcams`;

@Injectable({
    providedIn: "root",
})
export class ObjectsWebcamsService implements AirportServiceInterface {

    constructor(private http: HttpClient) {
    }

    getAirportsPerTerm(term: string, radius: string): Observable<MovingObject[]> {
        return this.http.get<MovingObject[]>(`${objectsWebcamsRootPath}/term/${term}/${radius}`).pipe(
            retry(3), catchError(this.handleError<MovingObject[]>()));
    }

    getAirportPerCode(code: string, radius: string): Observable<MovingObject> {
        return this.http.get<MovingObject>(`${objectsWebcamsRootPath}/code/${code}/${radius}`)
            .pipe(retry(3), catchError(this.handleError<MovingObject[]>()))
            .pipe(map((airports: MovingObject[]) => airports[0]));
    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(error);
            console.log(`${operation} failed: ${error.message}`);

            return of(result);
        };
    }
}


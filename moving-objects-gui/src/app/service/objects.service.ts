import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, of, retry} from "rxjs";
import {MovingObject} from "../model/movingObject";
import {AirportServiceInterface} from "../interface/airport.service.interface";

const objectsRootPath = `/aggregator/objects`;

@Injectable({
    providedIn: "root"
})
export class ObjectsService implements AirportServiceInterface {

    constructor(private http: HttpClient) {
    }

    public getAirportsPerTerm(term: string, radius: string): Observable<MovingObject[]> {
        return this.http.get<MovingObject[]>(`${objectsRootPath}/code/${term}/${radius}`).pipe(
            retry(3), catchError(this.handleError<MovingObject[]>()));
    }

    public getAirportPerCode(code: string, radius: string): Observable<MovingObject> {
        return this.http.get<MovingObject>(`${objectsRootPath}/${code}/${radius}`).pipe(
            retry(3), catchError(this.handleError<MovingObject>()));
    }

    private handleError<T>(operation = "operation", result?: T) {
        return (error: any): Observable<T> => {
            console.error(error);
            console.log(`${operation} failed: ${error.message}`);

            return of(result);
        };
    }
}


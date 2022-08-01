import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {catchError, map, Observable, of, retry} from "rxjs";
import {ObjectsServiceInterface} from "../interface/objects.service.interface";
import {MovingObject} from "../model/moving.object";

const objectsWebcamsRootPath = `/aggregator/objectswebcams`;

@Injectable({
    providedIn: "root",
})
export class ObjectsWebcamsService implements ObjectsServiceInterface {

    constructor(private http: HttpClient) {
    }

    public getAirportsPerTerm(term: string, radius: string): Observable<MovingObject[]> {
        return this.http.get<MovingObject[]>(`${objectsWebcamsRootPath}/term/${term}/${radius}`).pipe(
            retry(3), catchError(this.handleError<MovingObject[]>()));
    }

    public getAirportPerCode(code: string, radius: string): Observable<MovingObject> {
        return this.http.get<MovingObject>(`${objectsWebcamsRootPath}/code/${code}/${radius}`)
            .pipe(retry(3), catchError(this.handleError<MovingObject[]>()))
            .pipe(map((airports: MovingObject[]) => airports[0]));
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

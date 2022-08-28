import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {catchError, Observable, of, retry} from "rxjs";
import {ObjectsServiceInterface} from "../interface/objects.service.interface";
import {MovingObject} from "../model/moving.object";

const objectsRootPath = `/aggregator/objects`;

@Injectable({
    providedIn: "root",
})
export class ObjectsService implements ObjectsServiceInterface {

    constructor(private http: HttpClient) {
    }

    public getObjectsPerTermAndRadius(term: string, radius: string): Observable<MovingObject[]> {
        return this.http.get<MovingObject[]>
        (`${objectsRootPath}/code/${term.trim()}/${radius}`, {withCredentials: true}).pipe(
            retry(3), catchError(this.handleError<MovingObject[]>()));
    }

    public getObjectsPerCodeAndRadius(code: string, radius: string): Observable<MovingObject> {
        return this.http.get<MovingObject>(`${objectsRootPath}/${code}/${radius}`, {withCredentials: true}).pipe(
            retry(3), catchError(this.handleError<MovingObject>()));
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


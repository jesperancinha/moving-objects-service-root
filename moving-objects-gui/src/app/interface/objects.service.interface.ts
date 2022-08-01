import {Observable} from "rxjs";
import {MovingObject} from "../model/moving.object";

export interface ObjectsServiceInterface {
    getObjectsPerTermAndRadius(term: string, radius: string): Observable<MovingObject[]>;

    getAirportPerCodeAndRadius(code: string, radius: string): Observable<MovingObject>;
}

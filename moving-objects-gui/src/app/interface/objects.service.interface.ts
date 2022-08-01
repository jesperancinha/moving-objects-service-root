import {Observable} from "rxjs";
import {MovingObject} from "../model/moving.object";

export interface ObjectsServiceInterface {
    getAirportsPerTerm(term: string, radius: string): Observable<MovingObject[]>;

    getAirportPerCode(code: string, radius: string): Observable<MovingObject>;
}

import {Observable} from "rxjs";
import {MovingObject} from "../model/moving.object";

// tslint:disable-next-line:interface-name
export interface ObjectsServiceInterface {
    getObjectsPerTermAndRadius(term: string, radius: string): Observable<MovingObject[]>;

    getAirportPerCodeAndRadius(code: string, radius: string): Observable<MovingObject>;
}

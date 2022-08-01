import {Observable} from "rxjs";
import {MovingObject} from "../model/movingObject";

export interface AirportServiceInterface {
    getAirportsPerTerm(term: String, radius: String): Observable<MovingObject[]>;

    getAirportPerCode(code: String, radius:String): Observable<MovingObject>;
}
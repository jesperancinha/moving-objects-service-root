import {Observable} from "rxjs";
import {Airport} from "../model/airport";

export interface AirportServiceInterface {
    getAirportsPerTerm(term: String): Observable<Airport[]>;

    getAirportPerCode(code: String): Observable<Airport[]>;
}
import {Observable} from "rxjs";
import {Airport} from "../model/airport";

export interface AirportServiceInterface {
    getAirportsPerTerm(term: String, radius: String): Observable<Airport[]>;

    getAirportPerCode(code: String, radius:String): Observable<Airport>;
}
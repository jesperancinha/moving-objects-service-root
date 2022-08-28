import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {catchError, Observable, of, retry} from "rxjs";
import {WebcamServiceInterface} from "../interface/webcam.service.interface";
import {WebCam} from "../model/webcam";

const webCamsRootPath = `/aggregator/webcams`;

@Injectable({
    providedIn: "root",
})
export class WebcamService implements WebcamServiceInterface {

    constructor(private http: HttpClient) {
    }

    public getWebCampsPerPageSizeAndOffSet(pageSize: number, pageOffSet: number): Observable<WebCam[]> {
        return this.http.get<WebCam[]>
        (`${webCamsRootPath}/page/${pageSize}/${pageOffSet}`, {withCredentials: true}).pipe(
            retry(3), catchError(this.handleError<WebCam[]>()));
    }

    public getWebCampsPerCoordinatesAndRadius(latitude: number, longitude: number, radius: number):
        Observable<WebCam[]> {
        return this.http.get<WebCam[]>
        (`${webCamsRootPath}/location/${latitude}/${longitude}/${radius}`, {withCredentials: true}).pipe(
            retry(3), catchError(this.handleError<WebCam[]>()));
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

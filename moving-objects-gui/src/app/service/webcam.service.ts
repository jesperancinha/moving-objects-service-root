import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError, Observable, of, retry} from 'rxjs';
import {WebCam} from '../model/webcam';
import {WebcamServiceInterface} from "../interface/webcam.service.interface";

@Injectable({
    providedIn: "root"
})
export class WebcamService implements WebcamServiceInterface {

    constructor(private http: HttpClient) {
    }

    public getWebCampsPerPageSizeAndOffSet(pageSize: number, pageOffSet: number): Observable<WebCam[]> {
        return this.http.get<WebCam[]>(`/iairports/webcams/page/${pageSize}/${pageOffSet}`).pipe(
            retry(3), catchError(this.handleError<WebCam[]>()));
    }

    public getWebCampsPerCoordinatesAndRadius(latitude: number, longitude: number, radius: number): Observable<WebCam[]> {
        return this.http.get<WebCam[]>(`/iairports/webcams/location/${latitude}/${longitude}/${radius}`).pipe(
            retry(3), catchError(this.handleError<WebCam[]>()));
    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(error);
            console.log(`${operation} failed: ${error.message}`);
            return of(result);
        };
    }
}


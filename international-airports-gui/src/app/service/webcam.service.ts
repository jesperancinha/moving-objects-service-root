import {catchError, retry} from 'rxjs/internal/operators';
import {Injectable, NgModule} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {WebCam} from "../model/webcam";
import {AirportServiceInterface} from "../interface/airport.service.interface";
import {WebcamServiceInterface} from "../interface/webcam.service.interface";

const urlPerPageAndOffSet = '/iairports/webcams/page/';

@Injectable({
  providedIn: 'root'
})
export class WebcamService implements WebcamServiceInterface{

  constructor(private http: HttpClient) {
  }

  getWebCampsPerPageSizeAndOffSet(pageSize: Number, pageOffSet: Number): Observable<WebCam[]> {
    return this.http.get<WebCam[]>(`/iairports/webcams/page/${pageSize}/${pageOffSet}`).pipe(
      retry(3), catchError(this.handleError<WebCam[]>()));
  }

  getWebCampsPerCoordinatesAndRadius(latitude: Number, longitude: Number, radius:Number): Observable<WebCam[]> {
    return this.http.get<WebCam[]>(`/iairports/webcams/location/${latitude}/${longitude}/${radius}`).pipe(
      retry(3), catchError(this.handleError<WebCam[]>()));
  }

  private handleError<WebCam>(operation = 'operation', result?: WebCam) {
    return (error: any): Observable<WebCam> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return of(result);
    };
  }
}


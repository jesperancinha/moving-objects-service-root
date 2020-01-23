import {Observable} from "rxjs";
import {WebCam} from "../model/webcam";

export interface WebcamServiceInterface {

    getWebCampsPerPageSizeAndOffSet(pageSize: Number, pageOffSet: Number): Observable<WebCam[]>;

    getWebCampsPerCoordinatesAndRadius(latitude: Number, longitude: Number, radius: Number): Observable<WebCam[]>;

}
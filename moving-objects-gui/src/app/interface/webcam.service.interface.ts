import {Observable} from "rxjs";
import {WebCam} from "../model/webcam";

// tslint:disable-next-line:interface-name
export interface WebcamServiceInterface {

    getWebCampsPerPageSizeAndOffSet(pageSize: number, pageOffSet: number): Observable<WebCam[]>;

    getWebCampsPerCoordinatesAndRadius(latitude: number, longitude: number, radius: number): Observable<WebCam[]>;

}

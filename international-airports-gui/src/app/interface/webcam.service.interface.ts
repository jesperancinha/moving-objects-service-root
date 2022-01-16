import {Observable} from 'rxjs';
import {WebCam} from '../model/webcam';

export interface WebcamServiceInterface {

    getWebCampsPerPageSizeAndOffSet(pageSize: number, pageOffSet: number): Observable<WebCam[]>;

    getWebCampsPerCoordinatesAndRadius(latitude: number, longitude: number, radius: number): Observable<WebCam[]>;

}

import {WebCamImage} from './webcamimage';
import {Coordinates} from './coordinates';

export class WebCam {

    public title: string;
    public coordinates: Coordinates[];
    public wikiInfo: string;
    public active: boolean;
    public webCamImage: WebCamImage;
}
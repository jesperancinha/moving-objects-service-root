import {Coordinates} from "./coordinates";
import {WebCamImage} from "./webcamimage";

export class WebCam {

    public title: string;
    public coordinates: Coordinates[];
    public wikiInfo: string;
    public active: boolean;
    public webCamImage: WebCamImage;
}
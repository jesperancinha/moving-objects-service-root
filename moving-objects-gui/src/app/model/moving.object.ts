import {Coordinates} from "./coordinates";
import {WebCam} from "./webcam";

export class MovingObject {
    public code: string;
    public name: string;
    public coordinates: Coordinates;
    public webCams: WebCam[];
}

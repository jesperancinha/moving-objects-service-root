import {Coordinate} from "./coordinate";

export class Location {
    public code: string;
    public name: string;
    public description: string;
    public coordinates: Coordinate;
    public longitude: Number;
    public latitude: Number;
}
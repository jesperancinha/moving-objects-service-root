import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Component, OnInit} from "@angular/core";
import {MovingObject} from "../../model/moving.object";

@Component({
    selector: "moving-objects-selector",
    styleUrls: ["./objects.component.scss"],
    templateUrl: "./objects.component.html",
    standalone: false
})
export class ObjectsComponent implements OnInit {

    public loading: boolean;
    public origins: MovingObject[] = [];
    public destinations: MovingObject[] = [];
    public origin: MovingObject;
    public destination: MovingObject;

    private headers: HttpHeaders = new HttpHeaders({"Content-Type": "application/json"});

    private errorText: string;

    constructor(private httpClient: HttpClient) {
        this.loading = false;
    }

    public ngOnInit(): void {
        this.populateControls();
    }

    private populateControls() {
        // tslint:disable-next-line:no-console
        console.log("TODO: Not implemented!");

    }
}

import {Component, OnInit} from "@angular/core";
import {FormControl} from "@angular/forms";
import {map, Observable, of} from "rxjs";
import {MovingObject} from "../../model/moving.object";
import {WebCam} from "../../model/webcam";
import {ObjectsWebcamsService} from "../../service/objects.webcams.service";

interface TreeNode<T> {
    data: T;
    children?: Array<TreeNode<T>>;
    expanded?: boolean;
}

@Component({
    selector: "app-webcams-selector",
    styleUrls: ["./webcams.component.scss"],
    templateUrl: "./webcams.component.html",
})
export class WebCamsComponent implements OnInit {

    public loading: boolean;
    public radiusAutoFilled: boolean;
    public selectedRadius = "10";
    public selectedAirport: MovingObject;
    public airportFormControl = new FormControl();
    public searchTerm: string;
    public filteredOptions: Observable<MovingObject[]>;
    public selectedCam: WebCam;

    constructor(private objectsWebcamsService: ObjectsWebcamsService) {
    }

    public ngOnInit(): void {
        this.populateControls();
    }

    public radiusChanged(radius: string) {
        this.selectedRadius = radius;
        this.filteredOptions = this.validateAndRunLiveFilter();
        this.loading = true;
        if (this.selectedAirport) {
            this.objectsWebcamsService.getAirportPerCodeAndRadius(this.selectedAirport.code, this.selectedRadius)
                .subscribe((airport) => {
                    this.selectedAirport.webCams = airport.webCams;
                    this.loading = false;
                });
        }
    }

    public selectWebCam(webCam: WebCam) {
        this.selectedCam = webCam;

    }

    public setCurrentAirport(airport: MovingObject) {
        this.selectedAirport = airport;
    }

    public searchTermChange(searchTerm: string) {
        this.searchTerm = searchTerm;
        this.loading = true;
        this.filteredOptions = this.validateAndRunLiveFilter();
    }

    private populateControls() {
        this.loading = false;
    }

    private validateAndRunLiveFilter(): Observable<MovingObject[]> {
        if (this.validateAirportQuery()) {
            return this.objectsWebcamsService.getObjectsPerTermAndRadius(this.searchTerm, this.selectedRadius)
                .pipe(map((airports: MovingObject[]) => {
                    this.loading = false;
                    return airports;
                }));
        }
        return of([]);
    }

    private validateAirportQuery() {
        return this.searchTerm;
    }
}

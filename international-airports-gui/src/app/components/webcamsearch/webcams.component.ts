import {Component, OnInit} from '@angular/core';
import {Airport} from '../../model/airport';
import {AirportCompleteService} from '../../service/airport.complete.service';
import {FormControl} from '@angular/forms';
import {WebCam} from '../../model/webcam';
import {Observable, of, map} from 'rxjs';

interface TreeNode<T> {
    data: T;
    children?: TreeNode<T>[];
    expanded?: boolean;
}

@Component({
    selector: 'app-webcams-selector',
    styleUrls: ['./webcams.component.scss'],
    templateUrl: './webcams.component.html',
})
export class WebCamsComponent implements OnInit {

    public loading: boolean;
    radiusAutoFilled: boolean;
    selectedRadius = '10';
    selectedAirport: Airport;
    airportFormControl = new FormControl();
    searchTerm: string;
    filteredOptions: Observable<Airport[]>;
    selectedCam: WebCam;


    constructor(private airportService: AirportCompleteService) {
    }

    public ngOnInit(): void {
        this.populateControls();
    }

    private populateControls() {
        this.loading = false;
    }

    radiusChanged(radius: string) {
        this.selectedRadius = radius;
        this.filteredOptions = this.validateAndRunLiveFilter();
        this.loading = true;
        if (this.selectedAirport) {
            this.airportService.getAirportPerCode(this.selectedAirport.code, this.selectedRadius)
                .subscribe(airport => {
                    this.selectedAirport.webCams = airport.webCams;
                    this.loading = false;
                });
        }
    }

    private validateAndRunLiveFilter(): Observable<Airport[]> {
        if (this.validateAirportQuery()) {
            return this.airportService.getAirportsPerTerm(this.searchTerm, this.selectedRadius)
                .pipe(map((airports: Airport[]) => {
                    this.loading = false;
                    return airports;
                }));
        }
        return of([]);
    }

    private validateAirportQuery() {
        return this.searchTerm;
    }


    selectWebCam(webCam: WebCam) {
        this.selectedCam = webCam;

    }

    setCurrentAirport(airport: Airport) {
        this.selectedAirport = airport;
    }

    searchTermChange(searchTerm: string) {
        this.searchTerm = searchTerm;
        this.loading = true;
        this.filteredOptions = this.validateAndRunLiveFilter();
    }
}

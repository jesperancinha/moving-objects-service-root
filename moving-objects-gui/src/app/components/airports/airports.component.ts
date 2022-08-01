import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MovingObject} from '../../model/movingObject';

@Component({
    selector: 'app-airports-selector',
    styleUrls: ['./airports.component.scss'],
    templateUrl: './airports.component.html',
})
export class AirportsComponent implements OnInit {

    public loading: boolean;
    public origins: MovingObject[] = [];
    public destinations: MovingObject[] = [];

    private headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

    private errorText: string;
    public origin: MovingObject;
    public destination: MovingObject;

    constructor(private httpClient: HttpClient) {
        this.loading = false;
    }

    public ngOnInit(): void {
        this.populateControls();
    }

    private populateControls() {

    }
}

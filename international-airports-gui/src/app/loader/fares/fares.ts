import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Location} from "../../command-types/location";
import {Fare} from "../../command-types/fare";
import * as uuid from 'uuid';

@Component({
    selector: 'fares',
    styleUrls: ['./fares.scss'],
    templateUrl: './fares.html',
})
export class FaresComponent implements OnInit {

    public loading: boolean;
    public origins: Location[] = [];
    public destinations: Location[] = [];

    private headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

    private errorText: string;
    public origin: Location;
    public destination: Location;
    public fare: Fare;

    constructor(private httpClient: HttpClient) {
        this.loading = false;
    }

    public ngOnInit(): void {
       this.populateControls();
    }

    private static getCompareFn() {
        return (item1, item2) => item1.description.localeCompare(item2.description);
    }

    changeFare($event: Location[]) {
        if (this.origin && this.destination) {
            this.loading = true;
            this.httpClient.get<Fare>('/travel/fares/' + this.origin.code + '/' + this.destination.code, {headers: this.headers}).toPromise()
                .then(value => {
                    console.log("async-task-"+ uuid.v4());
                    this.fare = value;
                })
                .catch(fail => {
                    console.log(fail);
                    this.errorText = fail;
                })
                .finally(() => this.loading = false);
        }
    }

    populateControls() {
        this.httpClient.get<Location[]>('/travel/locations', {headers: this.headers}).toPromise()
            .then(value => {
                console.log("async-task-"+ uuid.v4());
                this.origins =[];
                this.destinations =[];
                this.origin = null;
                this.destination = null;
                this.fare = null;
                value.sort(FaresComponent.getCompareFn()).forEach(item => this.origins.push(item));
                value.sort(FaresComponent.getCompareFn()).forEach(item => this.destinations.push(item));
            })
            .catch(fail => {
                console.log(fail);
                this.errorText = fail;
            });
    }
}

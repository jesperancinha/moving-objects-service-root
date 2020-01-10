import {Component, OnInit} from '@angular/core';
import {NbSortDirection, NbSortRequest, NbTreeGridDataSource, NbTreeGridDataSourceBuilder} from "@nebular/theme";
import {Location} from "../../command-types/location";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import * as uuid from 'uuid';

interface TreeNode<T> {
    data: T;
    children?: TreeNode<T>[];
    expanded?: boolean;
}

@Component({
    selector: 'airports',
    styleUrls: ['./airports.scss'],
    templateUrl: './airports.html',
})
export class AirportComponent implements OnInit {

    public loading: boolean;
    public dataSource: NbTreeGridDataSource<Location>;
    private data: TreeNode<Location>[];
    private headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
    private errorText: String;
    public customColumn = 'code';
    public defaultColumns = ['name', 'description', 'latitude', 'longitude'];
    public allColumns = [this.customColumn, ...this.defaultColumns];
    public pageSize: Number = 2;
    public language: String;
    public languageOptions: String[] = ['en', "nl"];
    public currentPage: Number = 1;
    public term: String;
    private sortColumn: String;
    private sortDirection: NbSortDirection;

    constructor(private dataSourceBuilder: NbTreeGridDataSourceBuilder<Location>, private httpClient: HttpClient) {
        this.loading = false;
    }

    public ngOnInit(): void {
        this.populateControls()
    }

    getSortDirection(column: string): NbSortDirection {
        if (this.sortColumn === column) {
            return this.sortDirection;
        }
        return NbSortDirection.NONE;
    }

    updateSort(sortRequest: NbSortRequest): void {
        this.sortColumn = sortRequest.column;
        this.sortDirection = sortRequest.direction;
    }


    getShowOn(index: number) {
        const minWithForMultipleColumns = 400;
        const nextColumnStep = 100;
        return minWithForMultipleColumns + (nextColumnStep * index);
    }

    changeLanguage($event: (string & String)[] | (string & String)) {
        this.populateControls();
    }

    changeTerm($event: Event) {
        this.populateControls();
    }

    changePageSize($event: Event) {
        this.populateControls();
    }

    forward() {
        this.currentPage = this.currentPage.valueOf() + 1;
        this.ngOnInit();
    }

    backward() {
        this.currentPage = this.currentPage.valueOf() - 1;
        this.ngOnInit();
    }

    populateControls() {
        let url = '/travel/locations';
        if (this.pageSize) {
            url += '/' + this.pageSize;
            if (this.currentPage) {
                url += '/' + this.currentPage;
                if (this.language) {
                    url += '/' + this.language;
                } else {
                    url += '/en';
                }
                if (this.term) {
                    url += '/' + this.term;
                }
            }
        }
        this.loading = true;
        this.httpClient.get<Location[]>(url, {headers: this.headers}).toPromise()
            .then(value => {
                console.log("async-task-" + uuid.v4());
                this.data = [];
                value.forEach(location => {
                    if (location.coordinates) {
                        location.longitude = location.coordinates.longitude;
                        location.latitude = location.coordinates.latitude;
                    }
                    this.data.push({'data': location})
                });
                this.dataSource = this.dataSourceBuilder.create(this.data);
            })
            .catch(fail => {
                console.log(fail);
                this.errorText = fail;
                if (this.currentPage > 1) {
                    this.currentPage = this.currentPage.valueOf() - 1;
                    this.ngOnInit();
                }
            })
            .finally(() => {
                this.loading = false;
                this.sortColumn = "";
                this.sortDirection = NbSortDirection.NONE;
            });
    }
}

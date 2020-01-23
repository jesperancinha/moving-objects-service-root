import {Component, OnInit} from '@angular/core';

interface TreeNode<T> {
    data: T;
    children?: TreeNode<T>[];
    expanded?: boolean;
}

@Component({
    selector: 'webcams-selector',
    styleUrls: ['./webcams.component.scss'],
    templateUrl: './webcams.component.html',
})
export class WebCamsComponent implements OnInit {

    public loading: boolean;

    constructor() {
        this.loading = false;
    }

    public ngOnInit(): void {
        this.populateControls()
    }

    private populateControls() {

    }
}

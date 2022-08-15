import {Component} from "@angular/core";

@Component({
    selector: "gui-app",
    styleUrls: ["./app.component.css"],
    templateUrl: "./app.component.html",
})
export class AppComponent {
    constructor() {
        window.open("http://localhost:8082/aggregator/objectswebcams/code/PUM")
    }
}

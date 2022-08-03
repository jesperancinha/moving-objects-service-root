import "es6-shim";
import moment from "moment";
import "reflect-metadata";

import apiMetrics from "prometheus-api-metrics";

import express from "express";

class Server {

    public static bootstrap(): Server {
        return new Server();
    }

    public app: any;
    private port = 4000;

    constructor() {
        this.app = express();
        this.app.use(apiMetrics());
        // tslint:disable-next-line:no-console
        this.app.listen(this.port, () => console.log(`Express http is started ${this.port}`));
        this.app.on("error", (error: any) => {
            // tslint:disable-next-line:no-console
            console.error(moment().format(), "An error has ocurred!", error);
        });
        process.on("uncaughtException", (error: any) => {
            // tslint:disable-next-line:no-console
            console.log(moment().format(), error);
        });
    }
}

const server = Server.bootstrap();
export default server.app;

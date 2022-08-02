import "es6-shim";
import {Request, Response} from "express-serve-static-core";
import moment from "moment";
import * as path from "path";
import "reflect-metadata";

import apiMetrics from "prometheus-api-metrics";

import express from "express";

const allowedExt = [
    ".js",
    ".ico",
    ".css",
    ".png",
    ".jpg",
    ".woff2",
    ".woff",
    ".ttf",
    ".svg",
];

class Server {

    public static bootstrap(): Server {
        return new Server();
    }

    public app: any;
    private port = 4000;

    constructor() {
        this.app = express();
        this.app.use(apiMetrics());
        this.app.get("*", (req: Request, res: Response) => {
            if (allowedExt.filter((ext) => req.url.indexOf(ext) > 0).length > 0) {
                res.sendFile(path.resolve(`dist/${req.url}`));
            } else {
                res.sendFile(path.resolve("dist/index.html"));
            }
        });
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

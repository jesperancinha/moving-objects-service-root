import 'es6-shim';
import 'reflect-metadata';
import { Request, Response } from 'express-serve-static-core';
import * as path from 'path';
import * as moment from 'moment';

const apiMetrics = require('prometheus-api-metrics');
const express = require('express');

const allowedExt = [
    '.js',
    '.ico',
    '.css',
    '.png',
    '.jpg',
    '.woff2',
    '.woff',
    '.ttf',
    '.svg',
];

class Server {
    public app: any;
    private port = 4000;

    public static bootstrap(): Server {
        return new Server();
    }

    constructor() {
        this.app = express();
        this.app.use(apiMetrics());
        this.app.get('*', (req: Request, res: Response) => {
            if (allowedExt.filter(ext => req.url.indexOf(ext) > 0).length > 0) {
                res.sendFile(path.resolve(`dist/${req.url}`));
            } else {
                res.sendFile(path.resolve('dist/index.html'));
            }
        });
        this.app.listen(this.port, () => console.log(`Express http is started ${this.port}`));
        this.app.on('error', (error: any) => {
            console.error(moment().format(), 'An error has ocurred!', error);
        });

        process.on('uncaughtException', (error: any) => {
            console.log(moment().format(), error);
        });
    }
}

const server = Server.bootstrap();
export default server.app;

"use strict";
exports.__esModule = true;
require("es6-shim");
require("reflect-metadata");
var path = require("path");
var moment = require("moment");
var apiMetrics = require('prometheus-api-metrics');
var express = require('express');
var allowedExt = [
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
var Server = /** @class */ (function () {
    function Server() {
        var _this = this;
        this.port = 4000;
        this.app = express();
        this.app.use(apiMetrics());
        this.app.get('*', function (req, res) {
            if (allowedExt.filter(function (ext) { return req.url.indexOf(ext) > 0; }).length > 0) {
                res.sendFile(path.resolve("dist/" + req.url));
            }
            else {
                res.sendFile(path.resolve('dist/index.html'));
            }
        });
        this.app.listen(this.port, function () { return console.log("Express http is started " + _this.port); });
        this.app.on('error', function (error) {
            console.error(moment().format(), 'An error has ocurred!', error);
        });
        process.on('uncaughtException', function (error) {
            console.log(moment().format(), error);
        });
    }
    Server.bootstrap = function () {
        return new Server();
    };
    return Server;
}());
var server = Server.bootstrap();
exports["default"] = server.app;

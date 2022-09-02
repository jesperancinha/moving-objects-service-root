import { getTestBed } from "@angular/core/testing";
import {
    BrowserDynamicTestingModule,
    platformBrowserDynamicTesting
} from "@angular/platform-browser-dynamic/testing";
import "zone.js/testing";

(globalThis as any).process = {
    env: {},
};

declare const require: {
    context(path: string, deep?: boolean, filter?: RegExp): {
        <T>(id: string): T;
        keys(): string[];
    };
};

getTestBed().initTestEnvironment(
    BrowserDynamicTestingModule,
    platformBrowserDynamicTesting(),
);

const context = require.context("./", true, /\.spec\.ts$/);
context.keys().forEach(context);

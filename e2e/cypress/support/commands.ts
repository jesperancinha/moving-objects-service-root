/// <reference types="cypress" />
// ***********************************************
// This example commands.ts shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })
//
// declare global {
//   namespace Cypress {
//     interface Chainable {
//       login(email: string, password: string): Chainable<void>
//       drag(subject: string, options?: Partial<TypeOptions>): Chainable<Element>
//       dismiss(subject: string, options?: Partial<TypeOptions>): Chainable<Element>
//       visit(originalFn: CommandOriginalFn, url: string, options: Partial<VisitOptions>): Chainable<Element>
//     }
//   }
// }


import stringify = Mocha.utils.stringify;

const host = Cypress.env('host') ? 'influxdb' : 'localhost';
const port = 8086;

Cypress.Commands.add('signIn', () => {
    cy.visit(`http://${host}:${port}`);
    cy.get('input[name="username').type('admin');
    cy.get('input[name="password').type('adminadmin');
    cy.get('span').contains('Sign In').click();
})

Cypress.on('uncaught:exception', (err, runnable) => {
    if (err.message && err.message.trim().length > 0 && err.name && err.name.trim().length > 0) {
        if (err.message.indexOf('setting getter-only property "data"') >= 0) {
            return false;
        }
        if (err.message.indexOf('Cannot read properties of null') >= 0) {
            return false;
        }
        if (err.message.indexOf('too much recursion') >= 0) {
            return false;
        }
        if (err.message.indexOf('The operation was aborted') >= 0) {
            return false;
        }
        if (err.message.indexOf('undefined') >= 0) {
            return false;
        }
    } else {
        return false;
    }
    return true;
})
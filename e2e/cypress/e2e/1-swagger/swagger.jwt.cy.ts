describe('Swagger Tests', () => {
    const host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
    const port = Cypress.env('port') ? Cypress.env('port') : '8081';

    it('shows swagger', () => {
        cy.visit(`http://${host}:${port}/objects/swagger-ui.html`);
        cy.get("form > input").clear();
        cy.get("form > input").eq(0).type("/objects/v3/api-docs{enter}");
        cy.get('h2', {timeout: 10000}).contains('OpenAPI definition', {timeout: 10000}).should('not.be.null');
        cy.wait(1000);

        cy.get('div[class="servers"] > label > select > option').should('have.value', `http://localhost:${port}/objects`)
    });

})
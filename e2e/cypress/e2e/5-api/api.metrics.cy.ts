describe('API Metrics test', () => {
    const host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
    const port = Cypress.env('port') ? Cypress.env('port') : '8081';

    it("should show metrics", () => {
        cy.visit(`http://${host}:${port}`);
        cy.get('div').contains('Metrics').click();
        cy.get('span').contains('Refresh').click()
        cy.wait(1000)
        cy.get('span').contains('Refresh').click()
    })
});
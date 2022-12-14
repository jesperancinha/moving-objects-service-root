describe('API Metrics test', () => {
    const host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
    const port = Cypress.env('port') ? Cypress.env('port') : '4200';
    const shortWait = 1000;

    it("should show metrics", () => {
        cy.visit(`http://${host}:${port}`);
        cy.get('div', {includeShadowDom: true}).contains('Metrics', {includeShadowDom: true}).click({force: true});
        cy.get('span', {includeShadowDom: true}).contains('Refresh').click()
        cy.wait(shortWait)
        cy.get('span', {includeShadowDom: true}).contains('Refresh').click()
    })
});
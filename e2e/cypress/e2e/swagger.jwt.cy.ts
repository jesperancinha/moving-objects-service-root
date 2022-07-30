describe('Swagger Tests', () => {
  const host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
  const port = Cypress.env('port') ? Cypress.env('port') : '8081';

  it('shows swagger', () => {
    cy.visit(`http://${host}:${port}/objects/webjars/swagger-ui/index.html`);
    cy.get('h2').contains('OpenAPI definition').should('not.be.null');
    cy.wait(1000);

    cy.get('div[class="servers"] > label > select > option').should('have.value', 'http://localhost:8080/objects')
  });

})
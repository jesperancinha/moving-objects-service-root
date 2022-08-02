describe('Grafana Tests', () => {
  const host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
  const port = 3000

  it('show grafana', () => {
    cy.visit(`http://${host}:${port}`);
    cy.get('h1').contains("Grafana").should('exist');
  });

})
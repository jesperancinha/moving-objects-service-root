describe('Prometheus Tests', () => {
  const host = Cypress.env('host') ? 'prometheus' : 'localhost';
  const port = 9090;

  it('show prometheus', () => {
    cy.visit(`http://${host}:${port}`);
    cy.get('a').contains("Prometheus").should('exist');
  });
})
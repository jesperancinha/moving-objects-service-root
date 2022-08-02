describe('Influx DB Tests', () => {
  const host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
  const port = 8086;

  it('show influx sb', () => {
    cy.visit(`http://${host}:${port}`);
    cy.get('h3').contains("InfluxDB").should('exist');
  });

})
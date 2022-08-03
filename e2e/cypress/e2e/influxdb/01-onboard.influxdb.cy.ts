describe('Influx DB Tests', () => {
    const host = Cypress.env('host') ? 'influxdb' : 'localhost';
    const port = 8086;

    it('show influxdb', () => {
        cy.visit(`http://${host}:${port}`);
        cy.get('h3').contains("InfluxDB").should("exist");
        cy.get('span').contains('Get Started').click();
        cy.get('input[title="Username').type('admin');
        cy.get('input[title="Password').type('adminadmin');
        cy.get('input[title="Confirm Password').type('adminadmin');
        cy.get('input[title="Initial Organization Name').type('Moving Objects');
        cy.get('input[title="Initial Bucket Name').type('mos');
        cy.get('span').contains('Continue').click();
    });
});

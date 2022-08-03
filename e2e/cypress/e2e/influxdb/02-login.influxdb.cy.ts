describe('Influx DB Tests', () => {
    const host = Cypress.env('host') ? 'influxdb' : 'localhost';
    const port = 8086;

    it('show influxdb', () => {
        cy.visit(`http://${host}:${port}`);
        cy.get('input[name="username').type('admin');
        cy.get('input[name="password').type('adminadmin');
        cy.get('span').contains('Sign In').click();
    });
});

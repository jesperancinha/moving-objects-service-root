describe('Grafana Tests', () => {
    const host = Cypress.env('host') ? 'grafana' : 'localhost';
    const port = 3000

    it('show grafana', () => {
        cy.visit(`http://${host}:${port}`);
        cy.log('1')
        cy.get('h1').contains("Grafana").should('exist');
        cy.log('1')
        cy.get('input[aria-label="Username input field"]').type('admin');
        cy.log('1')
        cy.get('input[aria-label="Password input field"]').type('admin');
        cy.log('1')
        cy.get('button[aria-label="Login button"]').click();
        cy.log('1')
        cy.get('input[name="newPassword"]').then($body => {
            cy.get('input[name="newPassword"]').type('admin');
            cy.get('input[name="confirmNew"]').type('admin');
        });
        cy.get('button[type = "submit"]').click();
        cy.log('1')
        cy.reload()
        cy.log('1')
        cy.get('a[aria-label="Dashboards"]').click();
        cy.log('1')
        cy.wait(500);
        cy.log('1')
        cy.get('div').contains(/(.*)Java(.*)/).should('exist');
        cy.log('1')
        cy.get('div').contains(/(.*)Node(.*)/).should('exist');
        cy.log('1')
        cy.get('div').contains(/(.*)Java(.*)/).click()
        cy.log('1')
        cy.wait(200);
        cy.wait(200);
        cy.wait(200);
        cy.wait(200);
        cy.wait(200);
        cy.wait(200);
        cy.get('a[aria-label="Dashboards"]').click();
        cy.log('1')
        cy.wait(200);
        cy.wait(200);
        cy.wait(200);
        cy.wait(200);
        cy.wait(200);
        cy.wait(200);
        cy.log('1')
        cy.get('div').contains(/(.*)Node(.*)/).click()
        cy.wait(200);
        cy.wait(200);
        cy.wait(200);
        cy.wait(200);
        cy.wait(200);
        cy.wait(200);
    });

})

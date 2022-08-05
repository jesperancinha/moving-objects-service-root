describe('Grafana Tests', () => {
    const host = Cypress.env('host') ? 'grafana' : 'localhost';
    const port = 3000

    const smallWait = 200;

    it('show grafana', () => {
        cy.visit(`http://${host}:${port}`);
        cy.get('h1').contains("Grafana").should('exist');
        cy.get('input[aria-label="Username input field"]').type('admin');
        cy.get('input[aria-label="Password input field"]').type('admin');
        cy.get('button[aria-label="Login button"]').click();
        cy.get('input[name="newPassword"]').then($body => {
            cy.get('input[name="newPassword"]').type('admin');
            cy.get('input[name="confirmNew"]').type('admin');
        });
        cy.get('button[type = "submit"]').click();
        cy.reload()
        cy.get('a[aria-label="Dashboards"]').click({force: true});
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.reload()
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.get('div').contains(/(.*)Java(.*)/).should('exist');
        cy.get('div').contains(/(.*)Node(.*)/).should('exist');
        cy.get('div').contains(/(.*)Java(.*)/).click()
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.get('a[aria-label="Dashboards"]').click();
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.get('div').contains(/(.*)Node(.*)/).click()
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.wait(smallWait);
        cy.wait(smallWait);
    });

})

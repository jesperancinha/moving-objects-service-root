describe('Influx Generic DB Tests', () => {
    const TIMEOUT_CONFIG = Cypress.env('TIMEOUT_CONFIG');

    function clickSubMenu() {
        cy.get('button[aria-label="Show sub menu"]').eq(0, TIMEOUT_CONFIG).click();
    }

    it('all menus check', () => {
        cy.signIn();
        cy.wait(1000);
        clickSubMenu();
        cy.reload();
        cy.get('div[id="sources"] > a', TIMEOUT_CONFIG).eq(0, TIMEOUT_CONFIG).click({force: true});
        cy.reload();
        clickSubMenu();
        cy.reload();
        cy.get('div[id="buckets"] > a', TIMEOUT_CONFIG).eq(0, TIMEOUT_CONFIG).click({force: true});
        cy.reload();
        clickSubMenu();
        cy.reload();
        cy.get('div[id="telegrafs"] > a', TIMEOUT_CONFIG).eq(0, TIMEOUT_CONFIG).click({force: true});
        cy.reload();
        clickSubMenu();
        cy.reload();
        cy.get('div[id="scrapers"] > a', TIMEOUT_CONFIG).eq(0, TIMEOUT_CONFIG).click({force: true});
        cy.reload();
        clickSubMenu();
        cy.reload();
        cy.get('div[id="tokens"] > a', TIMEOUT_CONFIG).eq(0, TIMEOUT_CONFIG).click({force: true});
    });
});

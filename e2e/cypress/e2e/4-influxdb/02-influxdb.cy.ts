describe('Influx Generic DB Tests', () => {
    function clickSubMenu() {
        cy.get('button[aria-label="Show sub menu"]').eq(0, {timeout: 10000}).click();
    }

    it('all menus check', () => {
        cy.signIn();
        cy.wait(1000);
        clickSubMenu();
        cy.reload();
        cy.get('div[id="sources"] > a').eq(0).click({force: true});
        cy.reload();
        clickSubMenu();
        cy.reload();
        cy.get('div[id="buckets"] > a').eq(0).click({force: true});
        cy.reload();
        clickSubMenu();
        cy.reload();
        cy.get('div[id="telegrafs"] > a').eq(0).click({force: true});
        cy.reload();
        clickSubMenu();
        cy.reload();
        cy.get('div[id="scrapers"] > a').eq(0).click({force: true});
        cy.reload();
        clickSubMenu();
        cy.reload();
        cy.get('div[id="tokens"] > a').eq(0).click({force: true});
    });
});

describe('Influx Generic DB Tests', () => {
    it ('all menus check', () => {
        cy.signIn();
        cy.wait(1000);
        cy.get('button[aria-label="Show sub menu"]').eq(0).click();
        cy.get('div[id="sources"] > a').eq(0).click({force: true});
        cy.get('button[aria-label="Show sub menu"]').eq(0).click();
        cy.get('div[id="buckets"] > a').eq(0).click({force: true});
        cy.get('button[aria-label="Show sub menu"]').eq(0).click();
        cy.get('div[id="telegrafs"] > a').eq(0).click({force: true});
        cy.get('button[aria-label="Show sub menu"]').eq(0).click();
        cy.get('div[id="scrapers"] > a').eq(0).click({force: true});
        cy.get('button[aria-label="Show sub menu"]').eq(0).click();
        cy.get('div[id="tokens"] > a').eq(0).click({force: true});
    });
});

describe('Influx Create Scraper DB Tests', () => {
    it('should creaate scraper', () => {
        cy.signIn();
        cy.wait(1000);
        cy.get('button[aria-label="Show sub menu"]').eq(0).click();
        cy.get('div[id="scrapers"] > a').eq(0).click({force: true});
        cy.get('button[title="Create a new Scraper"').eq(0).click();
        cy.get('input[title="Name"').eq(0).clear().type('Influx DB Scraper');
        cy.get('button[title="Create"').eq(0).click();
    });
});
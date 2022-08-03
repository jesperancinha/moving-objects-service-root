describe('Influx Create Scraper DB Tests', () => {
    it('should creaate scraper', () => {
        cy.signIn();
        const waitStep = 1;
        cy.wait(waitStep);
        cy.get('button[aria-label="Show sub menu"]').eq(0).click();
        cy.get('div[id="buckets"] > a').eq(0).click({force: true});
        cy.wait(waitStep);

        cy.get('button[title="Click to create a bucket"]').eq(0).click();
        cy.get('input[name="name"]').eq(0).clear().type('mos-influxDB');
        cy.get('button[title="Create"').eq(0).click();
        cy.wait(waitStep);

        cy.get('button[title="Click to create a bucket"]').eq(0).click();
        cy.get('input[name="name"]').eq(0).clear().type('mos-influxDB2');
        cy.get('button[title="Create"').eq(0).click();
        cy.wait(waitStep);

        cy.get('button[title="Click to create a bucket"').eq(0).click();
        cy.get('input[name="name"]').eq(0).clear().type('mos-gui');
        cy.get('button[title="Create"').eq(0).click();
        cy.wait(waitStep);

        cy.get('button[title="Click to create a bucket"').eq(0).click();
        cy.get('input[name="name"]').eq(0).clear().type('mos-rest');
        cy.get('button[title="Create"').eq(0).click();
        cy.wait(waitStep);

        cy.get('button[title="Click to create a bucket"').eq(0).click();
        cy.get('input[name="name"]').eq(0).clear().type('mos-jwt');
        cy.get('button[title="Create"').eq(0).click();
        cy.wait(waitStep);
    });
});
describe('Influx Create Scraper DB Tests', () => {
    it('should creaate scraper', () => {
        cy.signIn();
        const waitStep = 2000;
        cy.wait(waitStep);
        cy.get('button[aria-label="Show sub menu"]').eq(0).click();
        cy.get('div[id="scrapers"] > a').eq(0).click({force: true});

        cy.get('button[title="Create a new Scraper"]').eq(0).click();
        cy.get('input[title="Name"]').eq(0).clear().type('Influx DB Scraper');
        cy.get('input[title="Target URL"]').eq(0).clear().type('http://localhost:8086/metrics');
        cy.get('button[data-testid="bucket-dropdown--button"]').eq(0).click()
        cy.get('div').contains('mos-influxDB').eq(0).click()
        cy.get('button[title="Create"').eq(0).click();
        cy.wait(waitStep);

        cy.get('button[title="Create a new Scraper"]').eq(0).click();
        cy.get('input[title="Name"]').eq(0).clear().type('Moving Objects Okta REST Service');
        cy.get('input[title="Target URL"]').eq(0).clear().type('http://192.168.0.11:8082/aggregator/actuator/prometheus');
        cy.get('button[data-testid="bucket-dropdown--button"]').eq(0).click()
        cy.get('div').contains('mos-okta').eq(0).click()
        cy.get('button[title="Create"').eq(0).click();
        cy.wait(waitStep);

        cy.get('button[title="Create a new Scraper"]').eq(0).click();
        cy.get('input[title="Name"]').eq(0).clear().type('Moving Objects JWT Backend REST Service');
        cy.get('input[title="Target URL"]').eq(0).clear().type('http://192.168.0.17:8081/objects/actuator/prometheus');
        cy.get('button[data-testid="bucket-dropdown--button"]').eq(0).click()
        cy.get('div').contains('mos-jwt').eq(0).click()
        cy.get('button[title="Create"').eq(0).click();
        cy.wait(waitStep);

        cy.get('button[title="Create a new Scraper"]').eq(0).click();
        cy.get('input[title="Name"]').eq(0).clear().type('GUI Node Scraper');
        cy.get('input[title="Target URL"]').eq(0).clear().type('http://192.168.0.15:4000/metrics');
        cy.get('button[data-testid="bucket-dropdown--button"]').eq(0).click()
        cy.get('div').contains('mos-gui').eq(0).click()
        cy.get('button[title="Create"').eq(0).click();
        cy.wait(waitStep);
    });
});
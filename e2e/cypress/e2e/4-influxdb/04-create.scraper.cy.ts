describe('Influx Create Scraper DB Tests', () => {
    const LONG_TIMEOUT = {timeout: 10000};
    const waitStep = 1000;

    it('should create scraper', () => {
        cy.signIn();
        cy.get('div[id="scrapers"] > a').eq(0).click({force: true});
        addScraper('Influx DB Scraper', 'http://localhost:8086/metrics', 'mos-influxDB', 'go_info');
        addScraper('Moving Objects JWT Backend REST Service', 'http://moving-objects-jwt-service:8081/objects/actuator/prometheus', 'mos-jwt', 'application_started_time_seconds');
        addScraper('GUI Node Scraper', 'http://nginx:4000/metrics', 'mos-gui', 'app_version');
        addScraper('Moving Objects Okta REST Service', 'http://moving-objects-rest-service:8082/aggregator/actuator/prometheus', 'mos-rest', 'application_started_time_seconds');
        addScraper('Influx DB Scraper 2', 'http://localhost:8086/metrics', 'mos-influxDB2', 'go_info');
        cy.wait(waitStep);
    });

    function addScraper(name, url, bucket, testMetric) {
        cy.get('button[title="Create a new Scraper"]').eq(0).click();
        cy.get('input[title="Name"]').eq(0).clear().type(name);
        cy.get('input[title="Target URL"]').eq(0).clear().type(url);
        cy.get('button[data-testid="bucket-dropdown--button"]').eq(0).click()
        cy.get('div').contains(bucket).eq(0).click({force: true})
        cy.get('button[title="Create"').eq(0).click();
        performSwitch(bucket, testMetric)
    }

    function performSwitch(bucket, testMetric) {
        cy.wait(waitStep);
        cy.reload()
        cy.wait(waitStep);
        cy.get('div[class="cf-tree-nav--item"] > a', LONG_TIMEOUT).eq(0, LONG_TIMEOUT).click({force: true});
        cy.wait(10000);
        cy.reload()
        cy.wait(waitStep);
        cy.get('div', LONG_TIMEOUT).contains(bucket, LONG_TIMEOUT).click()
        cy.get('div[data-testid="list--contents"]').children().should('not.be.empty')
        cy.get('div').contains(testMetric).should('exist')
        cy.wait(waitStep);
        cy.get('div[id="scrapers"] > a').eq(0).click({force: true});
    }
});


describe('Influx Create Bucket DB Tests', () => {
    const waitStep = 1;

    it('should create bucket', () => {
        cy.signIn();
        cy.wait(waitStep);
        cy.get('button[aria-label="Show sub menu"]').eq(0).click();
        cy.get('div[id="buckets"] > a').eq(0).click({force: true});
        cy.wait(waitStep);

        createBucket('mos-influxDB');
        createBucket('mos-influxDB2');
        createBucket('mos-gui');
        createBucket('mos-rest');
        createBucket('mos-jwt');
    });

    function createBucket(bucketName: string) {
        cy.get('button[title="Click to create a bucket"]').eq(0).click();
        cy.get('input[name="name"]').eq(0).clear().type(bucketName);
        cy.get('button[title="Create"').eq(0).click();
        cy.wait(waitStep);
        performSwitch(bucketName)
    }

    function performSwitch(bucketName) {
        cy.wait(waitStep);
        cy.reload()
        cy.wait(waitStep);
        cy.get('div[class="cf-tree-nav--item"] > a', {timeout: 10000}).eq(0, {timeout: 10000}).click({force: true});
        cy.wait(waitStep);
        cy.reload()
        cy.wait(waitStep);
        cy.get('div').contains(bucketName, {timeout:10000}).click()
        cy.wait(waitStep);
        cy.get('div[id="buckets"] > a').eq(0).click({force: true});
    }
});

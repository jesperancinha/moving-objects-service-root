describe('Influx Create API Token Tests', () => {
    const waitStep = 1000;

    it('should create API token for mos bucket', () => {
        cy.signIn();
        cy.get('div[id="tokens"] > a').eq(0).click({force: true});
        cy.get('button > span > span').contains('Generate API Token').eq(0).click();
        cy.get('div').contains('Read/Write API Token').eq(0).click();
        cy.get('input[placeholder="Describe this new API Token"]').type("Mos Bucket Full Access API Token");
        cy.get('button[title="mos"').click({multiple: true});
        cy.get('span').contains('Save').click();
        cy.get('span').contains('Mos Bucket Full Access API Token').eq(0).click();
        cy.get('code').then(control => {
            cy.writeFile("../docker-files/telegraf/token", control.html());
        })
    });

});

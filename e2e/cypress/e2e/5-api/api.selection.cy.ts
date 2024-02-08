describe('API Image Selection test', () => {
    const host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
    const port = Cypress.env('port') ? Cypress.env('port') : '4200';
    const shortWait = 1000;

    function testAndFetchFilterInputField() {
        const secondFormField = cy.get('app-webcams-selector > div > div > mat-form-field').eq(1);
        secondFormField.should('exist');
        const filterInputField = secondFormField.get('div > div > div > input').eq(1);
        filterInputField.should('exist');
        filterInputField.should('not.have.value', 0);
        filterInputField.should('have.value', '');
        return filterInputField;
    }

    function performTestByName(object) {
        cy.visit(`http://${host}:${port}`);
        cy.get('div > div > div > input').should('exist');
        cy.get('div > div > div > input').should('have.value', 0);
        const filterInputField = testAndFetchFilterInputField();
        filterInputField.type(object);
        cy.reload();
        const filterInputFieldTake2 = testAndFetchFilterInputField();
        filterInputFieldTake2.type(object);
        cy.get('mat-option', {timeout: 10000}).contains(object, {timeout: 10000}).click({force: true});
        cy.get('mat-card', {timeout: 10000}).click({force: true, multiple: true});
    }

    it('show 1 element 0 radius garlic', () => {
        performTestByName('Garlic');
    });
    it('show 1 element 0 radius laurel', () => {
        performTestByName('Laurel');
    });
    it('show 1 element 0 radius lemon', () => {
        performTestByName('Lemon');
    });
    // it('show 1 element 0 radius onion', () => {
    //     performTestByName('Onion');
    // });
    it('show 1 element 0 radius pumpkin', () => {
        performTestByName('Pumpkin');
    });
    it('show 1 element 0 radius red-onion', () => {
        performTestByName('Red Onion');
    });
    it('show 1 element 0 radius snail', () => {
        performTestByName('Snail');
    });
    it('show 1 element 0 radius tomato', () => {
        performTestByName('Tomato');
    });
})
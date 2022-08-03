describe('API Image Selection test', () => {
    const host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
    const port = Cypress.env('port') ? Cypress.env('port') : '8081';

    function performTestByName(object) {
        cy.visit(`http://${host}:${port}`);
        cy.get('div > div > div > input').should('exist');
        cy.get('div > div > div > input').should('have.value', 0);
        const secondFormField = cy.get('app-webcams-selector > div > div > mat-form-field').eq(1);
        secondFormField.should('exist');
        const filterInputField = secondFormField.get('div > div > div > input').eq(1);
        filterInputField.should('exist');
        filterInputField.should('not.have.value', 0);
        filterInputField.should('have.value', '');
        filterInputField.type(object, {force: true});
        cy.get('span[class="mat-option-text"]').contains(object).click({force: true});
        cy.get('span[class="mat-button-wrapper"]').click({force: true});
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
    it('show 1 element 0 radius onion', () => {
        performTestByName('Onion');
    });
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
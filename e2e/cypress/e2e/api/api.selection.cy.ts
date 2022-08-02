describe('API Images test ', () => {
    const host = Cypress.env('host') ? Cypress.env('host') : 'localhost';
    const port = Cypress.env('port') ? Cypress.env('port') : '8081';

    it('show 1 element 0 radius garlic', () => {
        cy.visit(`http://${host}:${port}`);
        cy.get('div > div > div > input').should('exist');
        cy.get('div > div > div > input').should('have.value', 0);
        const secondFormField = cy.get('app-webcams-selector > div > div > mat-form-field').eq(1);
        secondFormField.should('exist');
        const filterInputField = secondFormField.get('div > div > div > input').eq(1);
        filterInputField.should('exist');
        filterInputField.should('not.have.value', 0);
        filterInputField.should('have.value', '');
        filterInputField.type("Garlic",{force: true});
        cy.get('span[class="mat-option-text"]').contains('Garlic').click({force: true});
        cy.get('span[class="mat-button-wrapper"]').click({force: true});

    });
})
module.exports = {
    roots: ['<rootDir>'],
    transform: {
        '^.+\\.ts?$': 'ts-jest'
    },
    testRegex: '(/__tests__/.*|(\\.|/)(test|spec))\\.ts?$',
    moduleFileExtensions: ['ts', 'js', 'json', 'node'],
    collectCoverage: true,
    clearMocks: true,
    coverageDirectory: "coverage",
    moduleNameMapper: {
        '^.+.(svg|css)$': 'jest-transform-stub',
    },
    testEnvironment: "jsdom",
    setupFilesAfterEnv: [
        "<rootDir>/src/setuptests.ts"
    ],
    modulePathIgnorePatterns: ["src/test.ts"]
}

import {Airport} from './airport';

test('should create an airport', () => {
    const airport = new Airport();
    expect(airport).not.toBeNull();
});

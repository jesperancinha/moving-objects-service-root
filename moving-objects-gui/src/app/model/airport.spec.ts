import {MovingObject} from './movingObject';

test('should create an object', () => {
    const airport = new MovingObject();
    expect(airport).not.toBeNull();
});

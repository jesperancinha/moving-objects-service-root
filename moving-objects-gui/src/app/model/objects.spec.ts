import {MovingObject} from "./moving.object";

test("should create an object", () => {
    const airport = new MovingObject();
    expect(airport).not.toBeNull();
});

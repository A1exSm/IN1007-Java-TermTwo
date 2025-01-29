package Game;

import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

public class EDynamicBody {
    private final DynamicBody dynamicBody;
    private Vec2 playerOrigin;
    private Vec2 halfParams;
    protected EDynamicBody(DynamicBody dynamicBody, Vec2 halfParams, Vec2 playerOrigin) {
        this.dynamicBody = dynamicBody;
        this.halfParams = halfParams;
        this.playerOrigin = playerOrigin;
    }

    protected Vec2 getPlayerOrigin() {
        return playerOrigin;
    }

    protected Vec2 getShapeParams() {
        return halfParams;
    }
}

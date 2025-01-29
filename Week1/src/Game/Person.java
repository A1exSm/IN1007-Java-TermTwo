package Game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Person extends DynamicBody {
    public Person(World world) {
        super(world);

        // Torso (Main Body)
        BoxShape torsoShape = new BoxShape(1, 2); // Width = 1, Height = 2
        new SolidFixture(this, torsoShape); // Attach to main body

        // Left Arm (Offset relative to the body's origin)
        BoxShape leftArmShape = new BoxShape(0.5f, 1, new Vec2(-1.5f, 0.5f)); // Offset left
        new SolidFixture(this, leftArmShape);

        // Right Arm (Offset relative to the body's origin)
        BoxShape rightArmShape = new BoxShape(0.5f, 1, new Vec2(1.5f, 0.5f)); // Offset right
        new SolidFixture(this, rightArmShape);
    }
}


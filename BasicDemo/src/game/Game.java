package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.JFrame;


/**
 * Your main game entry point
 */
public class Game {
    float[] studentPos = {7, -9};
    float[] bookPos = {-8, 7.5f};
    StaticBody ground;
    StaticBody platform1;
    DynamicBody student;
    DynamicBody book;

    /**
     * Initialise a new Game.
     */
    public Game() {
        World world = new World();
        //make the ground
        Shape shape = new BoxShape(11, 0.5f);
        ground = new StaticBody(world, shape);
        ground.setPosition(new Vec2(0f, -11.5f));

        // make a platform
        Shape platformShape = new BoxShape(3.5f, 0.5f);
        platform1 = new StaticBody(world, platformShape);
        platform1.setPosition(new Vec2(-8, 5.5f));


        //make a character (with an overlaid image)
        Shape studentShape = new BoxShape(1, 2);
        student = new DynamicBody(world, studentShape);
        student.setPosition(new Vec2(studentPos[0], studentPos[1]));
        student.addImage(new BodyImage("data/student.png", 4));

        Shape bookShape = new BoxShape(2, 1.5f);
        book = new DynamicBody(world, bookShape);
        book.setPosition(new Vec2(bookPos[0], bookPos[1]));
        book.addImage(new BodyImage("data/books.png", 3));

        UserView view = new UserView(world, 500, 500);

        view.setGridResolution(1);
        final JFrame frame = new JFrame("City Game");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        student.setAlwaysOutline(true);
        book.setAlwaysOutline(true);
        world.start();
    }
    public void jump(float vec1, float vec2) {
        student.setLinearVelocity(new Vec2(vec1, vec2));
    }
    /** Run the game. */
    public static void main(String[] args) {
        boolean space = true;
        Game game = new Game();
        if (space == true) {
            game.jump(0, 10);
        }
    }
}

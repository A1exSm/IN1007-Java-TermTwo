package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Game extends World{
    private UserView view;
    private final JFrame frame = new JFrame("City Game");
    private DynamicBody player;
    private Vec2 playerOrigin;

    public Game() {
        super();

        Shape shape = new BoxShape(11, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, 0));
        ground.setName("Ground");


        Shape platformShape = new BoxShape(3.5f, 0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-8, -5f));

        playerOrigin = new Vec2(7, 2.5f);
        Shape studentShape = new BoxShape(1,2);
        player = new DynamicBody(this, studentShape);
        player.setPosition(playerOrigin);
        player.setFillColor(java.awt.Color.orange);

       // Function calls
        this.createView();
        this.jFrameOperations();

        this.addStepListener(new StepListener() {
            @Override
            public void preStep(StepEvent stepEvent) {
                view.setCentre(player.getPosition());
                if (player.getPosition().y > 500 || player.getPosition().y < -500) {
                    resetPosition(player);
                }
            }

            @Override
            public void postStep(StepEvent stepEvent) {
                // empty
            }
        });

        player.setAlwaysOutline(true);
        this.start();
        this.addViewListener();
    }

    protected void addViewListener() {
        InputKeyListener listener = new InputKeyListener(this);
        view.addKeyListener(new InputKeyListener(this));
    }

    private void jFrameOperations() {
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private void createView() {
        view = new UserView(this, 500, 500);
        view.setGridResolution(1);
        view.setFocusable(true);
        view.requestFocus(); // when opened, the screen changes to that window (pulls the window focus)
    }
    protected DynamicBody getPlayer() {
        return player;
    }


    private void jump(DynamicBody body) {
        body.setLinearVelocity(body.getLinearVelocity().add(new Vec2(0, 10)));
    }
    protected void moveBody(DynamicBody body, String direction) {
        if (direction.equals("LEFT")) {
            if (body.getLinearVelocity().x < -5) {
                body.setLinearVelocity(new Vec2(-10, body.getLinearVelocity().y));
            }
            else {
                body.setLinearVelocity(body.getLinearVelocity().add(new Vec2(-5, 0)));
            }
        }
        else if (direction.equals("RIGHT")) {
            if (body.getLinearVelocity().x > 5) {
                body.setLinearVelocity(new Vec2(10, body.getLinearVelocity().y));
            }
            else {
                body.setLinearVelocity(body.getLinearVelocity().add(new Vec2(5, 0)));
            }
        }
        else if (direction.equals("UP")) {
            if (!body.getBodiesInContact().isEmpty()) {
                if (body.getLinearVelocity().y > 10) {
                    body.setLinearVelocity(new Vec2(body.getLinearVelocity().x, 20));
                } else {
                    this.jump(body);
                }
            }
        }
        else if (direction.equals("DOWN")) {
            if (body.getLinearVelocity().y < -10) {
                body.setLinearVelocity(new Vec2(body.getLinearVelocity().x, -20));
            }
            else {
                body.setLinearVelocity(body.getLinearVelocity().add(new Vec2(0, -10)));
            }
        }
        System.out.println(body.getLinearVelocity().x + " " + body.getLinearVelocity().y);
    }

    protected void focusView() {
        Vec2 position = player.getPosition();
    }

    protected void resetPosition(DynamicBody body) {
        body.setLinearVelocity(new Vec2(0, 0));
        body.setPosition(playerOrigin);
        body.setAngularVelocity(0);
        body.setAngle(0);
    }

}

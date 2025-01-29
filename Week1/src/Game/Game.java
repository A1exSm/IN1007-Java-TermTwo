package Game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.event.*;

public class Game extends World{
    private UserView view;
    private final JFrame frame = new JFrame("City Game");
    private final DynamicBody player;
    EDynamicBody playerInfo;

    public Game() {
        super();

        Shape shape = new BoxShape(11, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -0.5f));
        ground.setName("Ground");


        Shape platformShape = new BoxShape(3.5f, 0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-8, -5f));

        Shape studentShape = new BoxShape(1,2);
        player = new DynamicBody(this, studentShape);
        playerInfo = new EDynamicBody(player, new Vec2(1, 2), new Vec2(0f, 2f));
        player.setPosition(playerInfo.getPlayerOrigin());
        player.setFillColor(java.awt.Color.orange);


        Person person = new Person(this);
        person.setPosition(new Vec2(4,2));
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

        view.addMouseListener(new MouseAdapter() {
           @Override
           public void mousePressed(MouseEvent e) {
               if (checkPoint(player, playerInfo, view.viewToWorld(e.getPoint()))) {
                   player.setFillColor(java.awt.Color.green);
               }
               else if (player.getFillColor() == java.awt.Color.green) {
                   player.setFillColor(java.awt.Color.orange);
               }
           }
        });

        player.setAlwaysOutline(true);
        this.start();
        this.addViewListener();
    }

    protected void addViewListener() {
        view.addKeyListener(new InputKeyListener(this));
        view.setFocusable(true);
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
        body.setLinearVelocity(body.getLinearVelocity().add(new Vec2(body.getLinearVelocity().x, 10)));
    }
    protected void moveBody(DynamicBody body, String direction) {
        switch (direction) {
            case "LEFT" -> body.setLinearVelocity(new Vec2(-10, body.getLinearVelocity().y));
            case "RIGHT" -> body.setLinearVelocity(new Vec2(10, body.getLinearVelocity().y));
            case "UP" -> {
                if (!body.getBodiesInContact().isEmpty()) {
                    if (body.getLinearVelocity().y > 10) {
                        body.setLinearVelocity(new Vec2(body.getLinearVelocity().x, 20));
                    } else {
                        this.jump(body);
                    }
                }
            }
            case "DOWN" -> {
                if (body.getLinearVelocity().y < -10) {
                    body.setLinearVelocity(new Vec2(body.getLinearVelocity().x, -20));
                } else {
                    body.setLinearVelocity(body.getLinearVelocity().add(new Vec2(0, -10)));
                }
            }
        }
    }

    protected void resetPosition(DynamicBody body) {
        body.setLinearVelocity(new Vec2(0, 0));
        body.setPosition(playerInfo.getPlayerOrigin());
        body.setAngularVelocity(0);
        body.setAngle(0);
    }
    private static boolean checkPoint(DynamicBody body, EDynamicBody bodyInfo, Vec2 point) {
        Vec2 position = body.getPosition();
        float left = position.x - bodyInfo.getShapeParams().x;
        float right = position.x + bodyInfo.getShapeParams().x;
        float top = position.y + bodyInfo.getShapeParams().y;
        float bottom = position.y - bodyInfo.getShapeParams().y;

        return (point.x >= left && point.x <= right && point.y <= top && point.y >= bottom);
    }
}

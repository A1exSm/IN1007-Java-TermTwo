package game;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputKeyListener implements KeyListener {
    private final Game game;

    public InputKeyListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            game.moveBody(game.getPlayer(), "LEFT");
        }
        if (key == KeyEvent.VK_D) {
            game.moveBody(game.getPlayer(), "RIGHT");
        }
        if (key == KeyEvent.VK_S) {
            game.moveBody(game.getPlayer(), "DOWN");
        }
        if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W) {
            game.moveBody(game.getPlayer(), "UP");
        }
        if (key == KeyEvent.VK_1) {
            game.resetPosition(game.getPlayer());
        }
        if (key == KeyEvent.VK_LEFT) {
            game.getPlayer().setAngularVelocity(5);
        }
        if (key == KeyEvent.VK_RIGHT) {
            game.getPlayer().setAngularVelocity(-5);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
            game.getPlayer().setLinearVelocity(new Vec2(0,game.getPlayer().getLinearVelocity().y));
        }
//        if (key == KeyEvent.VK_W || key == KeyEvent.VK_SPACE || key == KeyEvent.VK_S) {
//            game.getPlayer().setLinearVelocity(new Vec2(game.getPlayer().getLinearVelocity().x,0));
//        }
        if (key == KeyEvent.VK_1) {
        // empty
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            game.getPlayer().setAngularVelocity(0);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
}

package Game;
import city.cs.engine.*;

import javax.swing.*;
public class Game {
    public Game() {
        World world = new World();
        // user view
        UserView view = new UserView(world, 500, 500);
        view.setGridResolution(1);
        // Jframe
        final JFrame frame = new JFrame("Game");
        this.jFrameHandler(frame, view);
        // world start
        world.start();
    }

    private void jFrameHandler(JFrame frame, UserView view) {
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
        frame.setVisible(true);
    }
}

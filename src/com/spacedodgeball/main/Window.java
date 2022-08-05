package com.spacedodgeball.main;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Window extends Canvas {


  private static final long serialVersionUID = 9034494958129720942L;

  public Window(int width, int height, String title, Game game) {

    // create a new JFrame
    JFrame frame = new JFrame(title);

    // set frame attributes
    frame.setPreferredSize(new Dimension(width, height));
    frame.setMaximumSize(new Dimension(width, height));
    frame.setMinimumSize(new Dimension(width, height));

    // exit application when 'X' clicked
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    // disables window resizing
    frame.setResizable(false);
    // centers window on screen center on start
    frame.setLocationRelativeTo(null);
    // add the game into the frame
    frame.add(game);
    frame.setVisible(true);
    // run game start() method
    game.start();

  }

}

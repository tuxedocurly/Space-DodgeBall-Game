package com.spacedodgeball.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import javax.imageio.ImageIO;

public class Player extends GameObject {

  Random r = new Random();
  Handler handler;
  BufferedImage up, down, left, right;

  public Player(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
    getPlayerImage();
    direction = "down";
  }

  public void getPlayerImage() {

    // load the player sprite image resources into their corresponding BufferedImage variables
    try {
      up = ImageIO.read(
          Objects.requireNonNull(getClass().getResourceAsStream("./res/playerUp.png")));
      down = ImageIO.read(
          Objects.requireNonNull(getClass().getResourceAsStream("./res/playerDown.png")));
      left = ImageIO.read(
          Objects.requireNonNull(getClass().getResourceAsStream("./res/playerLeft.png")));
      right = ImageIO.read(
          Objects.requireNonNull(getClass().getResourceAsStream("./res/playerRight.png")));

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  // get the bounds of the player
  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 32, 32);
  }

  public void tick() {
    x += velX;
    y += velY;

    // set bounds for player object within the game screen
    x = Game.clamp(x, 0, Game.WIDTH - 37);
    y = Game.clamp(y, 0, Game.HEIGHT - 60);

    collision();
  }

  //
  private void collision() {
    for (int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);

      if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {
        // tempObject is com.spacedodgeball.main.BasicEnemy
        if (getBounds().intersects(tempObject.getBounds())) {
          // code for player + enemy collision. Reduces health if collision detected
          HUD.HEALTH -= 2;

        }
      }
    }
  }

  public void render(Graphics g) {

    //Graphics2D g2 = (Graphics2D) g;

    BufferedImage image = null;

    switch (direction) {
      case "up":
        image = up;
        break;
      case "down":
        image = down;
        break;
      case "left":
        image = left;
        break;
      case "right":
        image = right;
        break;
    }


    if (id == ID.Player) {
      // legacy player
      //g.setColor(Color.WHITE);
      //g.drawImage(image, (int) x, (int) y, 32, 32, null);
    }
    g.drawImage(image, (int) x, (int) y, 32, 32, null);
    // legacy player
    //g.fillRect((int) x, (int) y, 32, 32);
  }

}

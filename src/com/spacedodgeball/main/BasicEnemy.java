package com.spacedodgeball.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// class that handles the behavior of the game's com.spacedodgeball.main.BasicEnemy
public class BasicEnemy extends GameObject {

  private Handler handler;

  public BasicEnemy(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;

    velX = 5;
    velY = 5;

  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 16, 16);
  }

  public void tick() {

    x += velX;
    y += velY;

    if (y <= 0 || y >= Game.HEIGHT - 32) {
      velY *= -1;
    }
    if (x <= 0 || x >= Game.WIDTH - 16) {
      velX *= -1;
    }

    handler.addObject(new Trail(x, y, ID.Trail, 16, 16, 0.015f, Color.RED, handler));

  }

  public void render(Graphics g) {

    g.setColor(Color.RED);
    g.fillRect((int) x, (int) y, 16, 16);


  }
}

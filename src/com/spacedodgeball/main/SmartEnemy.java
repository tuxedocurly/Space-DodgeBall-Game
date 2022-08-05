package com.spacedodgeball.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// smart enemy follows the players current position
public class SmartEnemy extends GameObject {

  private Handler handler;
  private GameObject player;

  public SmartEnemy(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;

    // if the object is a player, set com.spacedodgeball.main.GameObject to player
    for (int i = 0; i < handler.object.size(); i++) {
      if (handler.object.get(i).getId() == ID.Player) {
        player = handler.object.get(i);
      }
    }

  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 16, 16);
  }

  public void tick() {

    x += velX;
    y += velY;

    float diffX = x - player.getX() - 8;
    float diffY = y - player.getY() - 8;

    // get the distance from the com.spacedodgeball.main.SmartEnemy to the player object and cast that value to a float
    float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
    velX = ((-1 / distance) * diffX);
    velY = ((-1 / distance) * diffY);


    if (y <= 0 || y >= Game.HEIGHT - 32) {
      velY *= -1;
    }
    if (x <= 0 || x >= Game.WIDTH - 16) {
      velX *= -1;
    }

    handler.addObject(new Trail(x, y, ID.Trail, 16, 16, 0.015f, Color.GREEN, handler));

  }

  public void render(Graphics g) {

    g.setColor(Color.GREEN);
    g.fillRect((int) x, (int) y, 16, 16);


  }
}

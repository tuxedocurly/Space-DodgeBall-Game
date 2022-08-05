package com.spacedodgeball.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

// this class creates a trail for enemy objects for some visual flare
public class Trail extends GameObject {

  private float alpha = 1;
  private Handler handler;
  private Color color;
  private int width;
  private int height;
  // life between 0.01 and 0.1
  private float life;


  // constructor
  public Trail(float x, float y, ID id, int width, int height, float life, Color color, Handler handler) {
    super(x, y, id);
    this.handler = handler;
    this.color = color;
    this.width = width;
    this.height = height;
    this.life = life;
  }

  public void tick() {
    if (alpha > life) {
      alpha -= (life - 0.0001f);
    } else {
      handler.removeObject(this);
    }

  }

  public void render(Graphics g) {
    // cast graphics to Graphics2D
    Graphics2D g2d = (Graphics2D) g;
    g2d.setComposite(makeTransparent(alpha));

    g.setColor(color);
    g.fillRect((int) x, (int) y, 16, 16);

    g2d.setComposite(makeTransparent(1));

  }

  private AlphaComposite makeTransparent(float alpha) {
    int type = AlphaComposite.SRC_OVER;
    return (AlphaComposite.getInstance(type, alpha));
  }

  public Rectangle getBounds() {
    return null;
  }
}

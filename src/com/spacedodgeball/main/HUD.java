package com.spacedodgeball.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// class to handle the rendering of informaton to the com.spacedodgeball.main.HUD, i.e. health, score, current level
public class HUD {

  // starting HP of 100
  public static float HEALTH = 100;

  // store var to make health bar transition from green to red as health decreases
  private float greenValue = 255;

  // tracks player score in window
  private int score = 0;
  // tracks the current level in window
  private int level = 1;

  public void tick() {
    // keep health between 0 and 100 so that bar does not extend before or after health bar
    HEALTH = (int) Game.clamp(HEALTH, 0, 100);
    // provides a health bar that changes between green and red depending on health amount
    greenValue = (int) Game.clamp(greenValue, 0, 255);

    greenValue = HEALTH * 2;

    // increment score on tick
    score++;

  }

  public void render(Graphics g) {
    // health bar background color
    g.setColor(Color.GRAY);
    g.fillRect(15, 15, 200, 32);
    // health bar color depending on health level (green to red)
    g.setColor( new Color(75, (int) greenValue, 0));
    g.fillRect(15, 15,(int) HEALTH * 2, 32);
    // health bar border
    g.setColor(Color.WHITE);
    g.drawRect(15, 15, 200, 32);




    // draw the tracker player score to the display
    g.drawString("Score: " + score, 15, 80);
    // draw the tracked level to the display
    g.drawString("Level: " + level, 15, 96);

    // set the font for the health bar to bold
    g.setFont(new Font("default", Font.BOLD, 24));
    // draw the tracker HEALTH to the display
    g.drawString(String.valueOf("HP: " + HEALTH), 58, 40);


  }

  // setter
  public void setScore(int score) {
    this.score = score;
  }

  // getter
  public int getScore() {
    return score;
  }

  // getter
  public int getLevel() {
    return level;
  }

  // setter
  public void setLevel(int level) {
    this.level = level;
  }

}

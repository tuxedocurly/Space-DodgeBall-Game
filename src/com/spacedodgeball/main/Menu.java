package com.spacedodgeball.main;

import com.spacedodgeball.main.Game.STATE;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

// class to handle the game menu
public class Menu extends MouseAdapter {
  private Game game;
  private Handler handler;
  private Random r = new Random();
  private HUD hud;

  public Menu(Game game, Handler handler, HUD hud) {
    this.game = game;
    this.handler = handler;
    this.hud = hud;
  }

  public void mousePressed(MouseEvent e) {
    // store mouse position as variable
    int mouseX = e.getX();
    int mouseY = e.getY();

    // if the STATE.Menu is active
    if (game.gameState == STATE.Menu) {

      // play button
      if (mouseOver(mouseX, mouseY, 210, 150, 200, 64)) {
        game.gameState = STATE.Game;
        // add player object to game
        handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
        // first level should have 1 enemy, so spawn it
        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
      }

      // info button
      if (mouseOver(mouseX, mouseY, 210, 250, 200, 64)) {
        game.gameState = STATE.Info;
      }

      // exit button
      if (mouseOver(mouseX, mouseY, 210, 350, 200, 64)) {
        // quit game when pressed
        System.exit(1);
      }

    }

    // If gameState is Info
    if (game.gameState == STATE.Info) {
      // clicking the back button will return to Menu
      if (mouseOver(mouseX, mouseY, 20, 20, 50, 50)) {
        game.gameState = STATE.Menu;

      }
    }

    // STATE.End Play Again button
    if (game.gameState == STATE.End) {
      // clicking the Play Again button will return to Game
      if (mouseOver(mouseX, mouseY, 160, 345, 320, 60)) {
        game.gameState = STATE.Game;
        hud.setLevel(1);
        hud.setScore(0);
        handler.object.clear();
        // add player object to game
        handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
        // first level should have 1 enemy, so spawn it
        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));

      }
    }

  }

  public void mouseReleased(MouseEvent e) {

  }

  // helper method to see if the mouse is over a particular button's bounds or not
  private boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
    if (mouseX > x && mouseX < x + width) {
      if (mouseY > y && mouseY < y + height) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public void render(Graphics g) {
    // set color for menu boxes and text
    g.setColor(Color.WHITE);
    Font font = new Font("Default",1, 50);
    // set font for menu text
    g.setFont(font);

    if (game.gameState == STATE.Menu) {

      // draw title string
      g.drawString("Space DodgeBall", 110, 72);

      // draw menu boxes and strings
      g.drawRect(210, 150, 200, 64);
      g.drawString("Play", 260, 200);
      g.drawRect(210, 250, 200, 64);
      g.drawString("Help", 260, 300);
      g.drawRect(210, 350, 200, 64);
      g.drawString("Exit", 260, 400);

    } else if (game.gameState == STATE.Info) {

      g.drawString("About this game", 100, 70);
      // draw a line under text to make it pop more
      g.drawLine(100, 85, 520, 85);

      g.drawRect(20, 20, 50, 50);
      g.drawString("<", 22, 60);

      g.setFont(new Font("Default", 1, 15));
      g.drawString("Use the W, A, S, and D keys to move the white rectangle.", 100, 200);
      g.drawString("Avoid the enemies for as long as you can. Good Luck!", 110, 230);

    } else if (game.gameState == STATE.End) {
      g.setFont(font);
      g.drawString("Game Over!", 170, 70);
      // display player score
      g.setColor(Color.GREEN);
      g.drawString("Score: " + hud.getScore(), 190, 200);

      g.setColor(Color.WHITE);
      g.drawRect(160, 345, 320, 60);
      g.drawString("Play Again", 190, 390);
    }



  }

  public void tick() {

  }

}

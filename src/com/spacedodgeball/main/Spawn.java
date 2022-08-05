package com.spacedodgeball.main;

import java.util.Random;

public class Spawn {

  private Handler handler;
  private HUD hud;
  private Random r = new Random();
  private int scoreKeep = 0;

  public Spawn(Handler handler, HUD hud) {
    this.handler = handler;
    this.hud = hud;
  }

  public void tick() {

    scoreKeep++;

    // increment level for every +1000 score
    if (scoreKeep >= 1000) {
      scoreKeep = 0;

      hud.setLevel(hud.getLevel() + 1);

      // spawn a new com.spacedodgeball.main.BasicEnemy every 1000 points
      if (hud.getLevel() == 2 || hud.getLevel() == 3) {
        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
      } else if (hud.getLevel() == 4) {
        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
      } else if (hud.getLevel() == 5) {
          handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
      } else if (scoreKeep % 100 == 0 && r.nextInt(100) <= 50 && hud.getLevel() > 5) {
        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
      } else if (scoreKeep % 100 == 0 && r.nextInt(100) > 50 && hud.getLevel() > 5) {
        handler.addObject(
            new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
      }

    }

  }

}

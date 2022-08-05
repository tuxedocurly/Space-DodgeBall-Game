package com.spacedodgeball.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable{

  private static final long serialVersionUID = -1442798787354930462L;

  public static final int WIDTH = 640;
  public static final int HEIGHT = WIDTH / 12 * 9;
  private Thread thread;
  private boolean running = false;

  private Random r;
  private Handler handler;
  private HUD hud;
  private Spawn spawner;
  private Menu menu;

  // state enum to drive the menu system
  public enum STATE {
    Menu,
    Info,
    Game,
    End
  }

  // set state to com.spacedodgeball.main.Menu
  public STATE gameState = STATE.Menu;


  public Game() {
    // initialize handler
    handler = new Handler();
    // initialize hud
    hud = new HUD();
    // initialize menu
    menu = new Menu(this, handler, hud);

    // add keyListener for keyboard input
    this.addKeyListener(new KeyInput(handler));
    // add mouseListener for mouse input
    this.addMouseListener(menu);

    // render a new window with title
    new Window(WIDTH, HEIGHT, "Space DodgeBall Game", this);

    spawner = new Spawn(handler, hud);

    r = new Random();

    // if (gameState == STATE.Game) {
    //   // add player object to game
    //   handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
    //   // first level should have 1 enemy, so spawn it
    //   handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
    // }
  }

  public synchronized void start() {
    // assign a new thread to thread
    thread = new Thread(this);
    // start the thread
    thread.start();
    running = true;
  }

  public synchronized void stop() {
    try {
      // stop the thread
      thread.join();
      running = false;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() {
    // autofocus to window on app open
    this.requestFocus();
    
    // set up game loop
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;

    while (running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;

      while (delta >= 1) {
        tick();
        delta--;
      }

      if (running) {
        render();
      }
      frames++;

      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        System.out.println("FPS: " + frames);
        frames = 0;
      }
    }
    stop();
  }

  private void tick() {
    handler.tick();
    // if the gameSate is com.spacedodgeball.main.Game, render game components
    if (gameState == STATE.Game) {
      hud.tick();
      spawner.tick();

      if (HUD.HEALTH <= 0) {
        HUD.HEALTH = 100;
        handler.object.clear();
        gameState = STATE.End;
      }
    } else if (gameState == STATE.Menu || gameState == STATE.End) {
      menu.tick();
    }
  }

  private void render() {
    // create new BufferStrategy
    BufferStrategy bs = this.getBufferStrategy();

    BufferedImage background = null;

    // try to load a new BufferedImage and store in background object
    try {
      background = ImageIO.read(
          Objects.requireNonNull(getClass().getResourceAsStream("./res/background.png")));

    } catch (IOException e) {
      e.printStackTrace();
    }

    if (bs == null) {
      // create 3D buffers
      this.createBufferStrategy(3);
      return;
    }

    // create a new Graphics object
    Graphics g = bs.getDrawGraphics();

    // set the window background to the BufferedImage background
    g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);

    handler.render(g);

    if (gameState == STATE.Game) {
      hud.render(g);
    } else if (gameState == STATE.Menu || gameState == STATE.Info || gameState == STATE.End) {
      menu.render(g);
    }

    g.dispose();
    bs.show();
  }

  // method to handle bounding for various game objects to keep them from moving off screen
  public static float clamp(float var, float min, float max) {
    if (var >= max) {
      return var = max;
    } else if (var <= min) {
      return var = min;
    } else {
      return var;
    }
  }

  public static void main(String[] args) {
    // start the game
    new Game();
  }
  
}

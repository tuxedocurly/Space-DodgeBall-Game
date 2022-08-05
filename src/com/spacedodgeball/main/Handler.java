package com.spacedodgeball.main;

import java.awt.Graphics;
import java.util.LinkedList;

// class that loops through all objects in game, updates them, and renders them to screen
public class Handler {

  // LinkedList of all objects in the game
  LinkedList<GameObject> object = new LinkedList<>();

  // tick all game objects
  public void tick() {
    for (int i = 0; i < object.size(); i++) {
      GameObject tempObject = object.get(i);

      tempObject.tick();
    }
  }

  // render all game objects
  public void render(Graphics g) {
    for (int i = 0; i < object.size(); i++) {
      GameObject tempObject = object.get(i);

      tempObject.render(g);
    }
  }

  // helper method to add game object to the LinkedList of objects
  public void addObject(GameObject object) {
    this.object.add(object);
  }

  // helper method to remove game object from the LinkedList of objects
  public void removeObject(GameObject object) {
    this.object.remove(object);
  }


}

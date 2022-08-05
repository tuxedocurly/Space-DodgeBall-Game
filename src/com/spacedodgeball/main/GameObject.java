package com.spacedodgeball.main;

import java.awt.Graphics;
import java.awt.Rectangle;

// all objects in the game will inherit this class
public abstract class GameObject {

  // can only be accessed by an object which inherits the com.spacedodgeball.main.GameObject
  protected float x;
  protected float y;
  protected ID id;
  protected float velX;
  protected float velY;
  // direction string is used for game objects that require different sprites be loaded depending
  // on the direction they are traveling. This is currently only used for the Player object
  protected String direction;

  // constructor
  public GameObject(float x, float y, ID id) {
    this.x = x;
    this.y = y;
    this.id = id;
  }

  public abstract void tick();

  public abstract void render(Graphics g);

  // handle object collisions
  public abstract Rectangle getBounds();

  // getter and setter methods for object variables
  public void setX(int x) {
    this.x = x;
  }

  public float getX() {
    return x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public float getY() {
    return y;
  }

  public void setId(ID id) {
    this.id = id;
  }

  public ID getId() {
    return id;
  }

  public void setVelX(int velX) {
    this.velX = velX;
  }

  public float getVelX() {
    return velX;
  }

  public void setVelY(int velY) {
    this.velY = velY;
  }

  public float getVelY() {
    return velY;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public String getDirection() {
    return direction;
  }

}

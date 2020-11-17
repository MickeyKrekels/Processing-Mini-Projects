import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class growingCircle extends PApplet {

ArrayList<Circle> Circles;

public void setup() {
  
  Circles = new ArrayList<Circle>();
}
public void draw() {
  background(0);
  
  Circle c = NewCircle();
  if (c != null) {
    Circles.add(c);
  }

  for (Circle circle : Circles) {
    for (Circle other : Circles) {
      if (CheckCollision(circle, other)) {
        circle.growing = false;
        break;
      }
    }
    circle.Grow();
    circle.Display();
  }
}

public Circle NewCircle() {
  float x = random(0, width);
  float y = random(0, height);

  boolean valid = true;
  for (Circle circle : Circles) {
    float distance = dist(circle.x, circle.y, x, y);
    if (distance < circle.r) {
      valid = false;
      break;
    }
  }

  if (valid) {
    return new Circle(x, y);
  } else
  {
    return null;
  }
}


public boolean CheckCollision(Circle circle, Circle other) {
  float distance = dist(circle.x, circle.y, other.x, other.y);   
  return((other.r + circle.r) > distance && circle != other);
}
class Circle {
  float x;
  float y;
  float r;

  boolean growing = true;

  Circle(float x, float y) {
    this.x = x;
    this.y = y;

    r = 1;
  }

  public boolean AtBorder() {
    return(x + r > width 
      || x - r < 0 
      ||y + r > height 
      || y - r < 0);
  }

  public void Grow() {
    if (growing) {
      r += 0.2f;   

      if (AtBorder()) {
        growing = false;
      }
    }
  }

  public void Display() {
    stroke(255);
    noFill();
    ellipse(x, y, r*2, r*2);
  }
}
  public void settings() {  size(1000, 1000); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "growingCircle" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

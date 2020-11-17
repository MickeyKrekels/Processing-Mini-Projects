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

public class MetaBalls extends PApplet {

ArrayList<MetaBall>metaBalls = new ArrayList<MetaBall>();

public void setup() {
  
  //colorMode(HSB);

  for (int i = 0; i < 5; i++) {
    MetaBall metaball = new MetaBall(random(0, width), random(0, height));
    metaBalls.add(metaball);
  }
}

public void draw() {
  background(0);
  
  loadPixels();

  for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {
      int index = x + y * width;
      float col = 0;
      for (MetaBall metaBall : metaBalls) {
        float distance = dist(x, y, metaBall.position.x, metaBall.position.y);
        col +=  100 *(metaBall.radius/distance);
      }
      pixels[index] = color(col,0,0);
    }
  }

  updatePixels();

  for (MetaBall metaBall : metaBalls) {
    metaBall.update();
    //metaBall.show();
  }
}
public void mouseClicked() {
  
 for (MetaBall metaBall : metaBalls) {
   float distance = dist(mouseX,mouseY,metaBall.position.x,metaBall.position.y);
   
   if(metaBall.radius > distance){
     metaBall.grow();
   }
   
 }

}
class MetaBall {

  float radius;  
  PVector position;
  PVector velocity;

  MetaBall(float x, float y) {
    position = new PVector(x, y);
    velocity = PVector.random2D();
    velocity.mult(random(1,5));
    radius = random(10, 70);
  }

  public void grow() {
    radius+= 5;
  }

  public void update() {
    position.add(velocity);

    if (position.x > width 
      || position.x  < 0) {
      velocity.x *= -1;
    }

    if (position.y > height 
      || position.y < 0) {
      velocity.y *= -1;
    }
  }

  public void show() {
    noFill();
    stroke(255);
    strokeWeight(4);
    ellipse(position.x, position.y, radius*2, radius*2);
  }
}
  public void settings() {  size(600, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "MetaBalls" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

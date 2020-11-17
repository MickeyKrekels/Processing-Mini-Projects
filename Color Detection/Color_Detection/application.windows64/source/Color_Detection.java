import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.video.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Color_Detection extends PApplet {



// Variable for capture device
Capture video;

// A variable for the color we are searching for.
int trackColor; 

public void setup() {
  
  video = new Capture(this, width, height);
  video.start();
  // Start off tracking for blue
  trackColor = color(30, 0, 200);
}

public void captureEvent(Capture video) {
  video.read();
}

public void draw() {
  video.loadPixels();
  image(video, 0, 0);


  float colorRecord = 500; 

  // XY coordinate of closest color
  int closestX = 0;
  int closestY = 0;

  // Begin loop to walk through every pixel
  for (int x = 0; x < video.width; x ++ ) {
    for (int y = 0; y < video.height; y ++ ) {
      int loc = x + y*video.width;
      // What is current color
      int currentColor = video.pixels[loc];
      float r1 = red(currentColor);
      float g1 = green(currentColor);
      float b1 = blue(currentColor);
      float r2 = red(trackColor);
      float g2 = green(trackColor);
      float b2 = blue(trackColor);

      // Using euclidean distance to compare colors
      float d = dist(r1, g1, b1, r2, g2, b2); // We are using the dist( ) function to compare the current color with the color we are tracking.

      // If current color is more similar to tracked color than
      // closest color, save current location and current difference
      if (d < colorRecord) {
        colorRecord = d;
        closestX = x;
        closestY = y;
      }
    }
  }

 float distanceLimit = 10;
  if (colorRecord < distanceLimit) { 
    fill(trackColor);
    strokeWeight(4.0f);
    stroke(0);
    ellipse(closestX, closestY, 16, 16);
  }
}

public void mousePressed() {
  int loc = mouseX + mouseY*video.width;
  trackColor = video.pixels[loc];
}
class Target {
  float x, y, w, h;

  Target(float x, float y) {
    this.x = x;
    this.y = y;

    w = 1;
    h = 1;
  }
}
  public void settings() {  size(320, 240); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Color_Detection" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

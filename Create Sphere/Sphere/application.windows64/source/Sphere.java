import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import peasy.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Sphere extends PApplet {



int total = 100;
PeasyCam cam;

public void setup()
{
  
  cam = new PeasyCam(this,200);
}
public void draw() {
  background(0);
  fill(255);
  lights();
  //sphere(200);
  float r = 200;
  
  for (int i = 0; i< total; i++) {
    float lon = map(i, 0, total, -PI, PI);
    for (int j = 0; j< total; j++) {
      float lat = map(j, 0, total, -HALF_PI, HALF_PI);
      float x = r * sin(lon) * cos(lat);
      float y = r * sin(lon) * sin(lat);
      float z = r * cos(lon);
      stroke(255);
      point(x,y,z);
    }
  }
}
  public void settings() {  size(1000, 1000, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Sphere" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

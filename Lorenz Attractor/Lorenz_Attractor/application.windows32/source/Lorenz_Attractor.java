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

public class Lorenz_Attractor extends PApplet {




float x =0.01f;
float y =0;
float z =0;

//input constants
float sigma = 10;
float beta = 8.0f/3.0f;
float rho = 28;

PeasyCam cam;
ArrayList<PVector> points;

public void setup() {
  
  cam = new PeasyCam(this,500);


  points = new ArrayList<PVector>();
}

public void draw() {
  background(0);
  
  //calculate position
  float dx = sigma * (y - x);
  float dy = x * (rho - z) - y;
  float dz = x * y - beta * z;  
  float dt = 0.01f;

  dx = dx * dt;
  dy = dy * dt;
  dz = dz * dt;

  x = x + dx;
  y = y + dy;
  z = z + dz;

  points.add(new PVector(x, y, z));

  scale(5);
  translate(0,0,-50);

  for (PVector point : points) {
    stroke(90,120,225);
    point(point.x, point.y, point.z);
  }
}
  public void settings() {  size(800, 800, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Lorenz_Attractor" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

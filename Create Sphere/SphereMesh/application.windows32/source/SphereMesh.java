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

public class SphereMesh extends PApplet {



float r = 200;
int total = 100;
PVector[][] sphere; 
PeasyCam cam;

public void setup()
{
  
  cam = new PeasyCam(this, 400);
  sphere = new PVector[total +1][total+1];
}
public void draw() {
  background(0);
  lights();
  BuildPoints();
  CreateMesh();
}
public void CreateMesh() {
  for (int i = 0; i< total; i++) {
    beginShape(TRIANGLE_STRIP);
    for (int j = 0; j< total+1; j++) {
      noStroke();
      fill(0, 50, 200);     
      PVector v1 = sphere[i][j];
      PVector v2 = sphere[i+1][j];


      vertex(v1.x, v1.y, v1.z);
      vertex(v2.x, v2.y, v2.z);
    }
    endShape();
  }
}
public void BuildPoints()
{
  for (int i = 0; i< total+1; i++) {
    float lat = map(i, 0, total, 0, PI);

    for (int j = 0; j< total+1; j++) {
      float lon = map(j, 0, total, 0, TWO_PI);

      float x = r * sin(lat) * cos(lon);
      float y = r * sin(lat) * sin(lon);
      float z = r * cos(lat);

      PVector pos = new PVector(x, y, z);

      sphere[i][j] = pos;
    }
  }
}
  public void settings() {  size(600, 600, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SphereMesh" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

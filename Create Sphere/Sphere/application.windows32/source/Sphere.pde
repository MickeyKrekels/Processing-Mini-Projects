import peasy.*;

int total = 100;
PeasyCam cam;

void setup()
{
  size(1000, 1000, P3D);
  cam = new PeasyCam(this,200);
}
void draw() {
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

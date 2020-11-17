import peasy.*;

float r = 200;
int total = 100;
PVector[][] sphere; 
PeasyCam cam;

void setup()
{
  size(600, 600, P3D);
  cam = new PeasyCam(this, 400);
  sphere = new PVector[total +1][total+1];
}
void draw() {
  background(0);
  lights();
  BuildPoints();
  CreateMesh();
}
void CreateMesh() {
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
void BuildPoints()
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

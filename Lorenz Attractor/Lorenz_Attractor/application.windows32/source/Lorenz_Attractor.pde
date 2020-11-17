import peasy.*;


float x =0.01;
float y =0;
float z =0;

//input constants
float sigma = 10;
float beta = 8.0/3.0;
float rho = 28;

PeasyCam cam;
ArrayList<PVector> points;

void setup() {
  size(800, 800, P3D);
  cam = new PeasyCam(this,500);


  points = new ArrayList<PVector>();
}

void draw() {
  background(0);
  
  //calculate position
  float dx = sigma * (y - x);
  float dy = x * (rho - z) - y;
  float dz = x * y - beta * z;  
  float dt = 0.01;

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

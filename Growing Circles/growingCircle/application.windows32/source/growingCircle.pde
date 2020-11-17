ArrayList<Circle> Circles;

void setup() {
  size(1000, 1000);
  Circles = new ArrayList<Circle>();
}
void draw() {
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

Circle NewCircle() {
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


boolean CheckCollision(Circle circle, Circle other) {
  float distance = dist(circle.x, circle.y, other.x, other.y);   
  return((other.r + circle.r) > distance && circle != other);
}

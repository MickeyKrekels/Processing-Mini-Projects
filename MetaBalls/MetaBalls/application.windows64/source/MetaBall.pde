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

  void grow() {
    radius+= 5;
  }

  void update() {
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

  void show() {
    noFill();
    stroke(255);
    strokeWeight(4);
    ellipse(position.x, position.y, radius*2, radius*2);
  }
}

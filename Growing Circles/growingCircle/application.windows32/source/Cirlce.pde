class Circle {
  float x;
  float y;
  float r;

  boolean growing = true;

  Circle(float x, float y) {
    this.x = x;
    this.y = y;

    r = 1;
  }

  boolean AtBorder() {
    return(x + r > width 
      || x - r < 0 
      ||y + r > height 
      || y - r < 0);
  }

  void Grow() {
    if (growing) {
      r += 0.2;   

      if (AtBorder()) {
        growing = false;
      }
    }
  }

  void Display() {
    stroke(255);
    noFill();
    ellipse(x, y, r*2, r*2);
  }
}

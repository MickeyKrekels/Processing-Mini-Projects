ArrayList<MetaBall>metaBalls = new ArrayList<MetaBall>();

void setup() {
  size(600, 600);
  //colorMode(HSB);

  for (int i = 0; i < 5; i++) {
    MetaBall metaball = new MetaBall(random(0, width), random(0, height));
    metaBalls.add(metaball);
  }
}

void draw() {
  background(0);
  
  loadPixels();

  for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {
      int index = x + y * width;
      float col = 0;
      for (MetaBall metaBall : metaBalls) {
        float distance = dist(x, y, metaBall.position.x, metaBall.position.y);
        col +=  100 *(metaBall.radius/distance);
      }
      pixels[index] = color(col,0,0);
    }
  }

  updatePixels();

  for (MetaBall metaBall : metaBalls) {
    metaBall.update();
    //metaBall.show();
  }
}
void mouseClicked() {
  
 for (MetaBall metaBall : metaBalls) {
   float distance = dist(mouseX,mouseY,metaBall.position.x,metaBall.position.y);
   
   if(metaBall.radius > distance){
     metaBall.grow();
   }
   
 }

}

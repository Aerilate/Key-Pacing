import java.awt.Rectangle;
class InventoryKey extends Blocks{

  InventoryKey(int x, int y){
    super(x,y);
    boundingBox = new Rectangle (x,y,204,204);
    
  }
}
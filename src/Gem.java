/* 
 * [Gem.java]
 * Class for the gems which can be traded for potions
 * Author: Jason, Raymond 
 * June 14, 2018
 */

import java.awt.Rectangle;
class Gem extends Blocks {
  
  Gem(int x, int y){
    super(x,y);
    boundingBox = new Rectangle(x, y, 100, 100);
    
  }
}
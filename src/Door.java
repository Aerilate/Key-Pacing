/* 
 * [Door.java]
 * Class for the doors, which lock parts of the map
 * Author: Jason, Raymond 
 * June 14, 2018
 */

import java.awt.Rectangle;
class Door extends Blocks{
  
  Door(int x, int y, int width, int length){
    super(x,y);
    boundingBox = new Rectangle(x, y, width,length);
    
  }
}
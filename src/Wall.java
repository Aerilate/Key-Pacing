/* 
 * [Wall.java]
 * Class for all walls, walls make up the borders in each room
 * Author: Jason, Raymond 
 * June 14, 2018
 */

import java.awt.Rectangle;
class Wall extends Blocks{
  
  Wall(int x, int y, int width, int length){
    super(x,y);
    boundingBox = new Rectangle (x,y,width, length);
    
  } 
}

/* 
 * [Boulder.java]
 * Class for the boulders that can be pushed by the player
 * Author: Jason, Raymond 
 * June 14, 2018
 */

import java.awt.Rectangle;
class Boulder extends Blocks{
  
  Boulder(int x, int y){
    super(x,y);
    boundingBox = new Rectangle (x,y,204,204);
    
  }
}
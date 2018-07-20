/* 
 * [Key.java]
 * Class for keys that can be used to unlock doors
 * Author: Jason, Raymond 
 * June 14, 2018
 */

import java.awt.Rectangle;
class Key extends Blocks{
  
  Key(int x, int y){
    super(x,y);
    boundingBox = new Rectangle (x,y,200,200);
    
  }
}
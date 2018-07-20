/* 
 * [UFO.java]
 * Class for the enemy and boss UFO
 * Author: Jason, Raymond 
 * June 14, 2018
 */
import java.awt.Rectangle;
class UFO extends Moveable{
  
  UFO(int x, int y){
    super(x,y);
    boundingBox = new Rectangle(x, y, 222, 170);
    
  }
}
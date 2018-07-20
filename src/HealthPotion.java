/* 
 * [HealthPotion.java]
 * Class for the health potion, which restores health upon use
 * Author: Jason, Raymond 
 * June 14, 2018
 */


import java.awt.Rectangle;
class HealthPotion extends Blocks{
  
  boolean acquired = false; 
  
  HealthPotion(int x, int y){
    super(x,y);  
    boundingBox = new Rectangle (x,y,115,154);
    
  }
}
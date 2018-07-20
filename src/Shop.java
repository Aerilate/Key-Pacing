/* 
 * [Shop.java]
 * Class for the shop, which the user can make purchases from
 * Author: Jason, Raymond 
 * June 14, 2018
 */
import java.awt.Rectangle;
class Shop extends Blocks{
  
  boolean openShop = false; 
  
  Shop(int x, int y){
    super(x,y);
    boundingBox = new Rectangle(x, y, 250, 190);
    
  }
}
/* 
 * [Player.java]
 * Class for the user-controlled player
 * Author: Jason, Raymond 
 * June 14, 2018
 */

import java.awt.Rectangle;
class Player extends Moveable{
  
  private int health; 
  
  Player(int x, int y, int health){
    super(x, y); 
    
    boundingBox = new Rectangle(x, y, 70, 128);
    this.health = health;
  }
  
  public void changeHealth(int health){
    this.health = this.health + health;
  }
  
  public int getHealth(){
    return health;
    
  }
}


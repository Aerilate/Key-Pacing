/* 
 * [Inventory.java]
 * Class for the inventory which holds the player's items
 * Author: Jason, Raymond 
 * June 14, 2018
 */


import java.awt.Rectangle;
class Inventory{
  
  private int gems; 
  private int numKeys;
  private int healthPotion; 
  
  Inventory(int numKeys,int numGems){
    this.gems = numGems; 
    this.numKeys=numKeys;
    this.healthPotion = 0;
  }
  
  //Getters and Setters
  public void changeGems(int change){
    this.gems += change;
  }
  
  public int getGems(){
    return gems;
  }
  
  public void addNumKeys(){
    this.numKeys = this.numKeys+1; 
  }
  
  public int getNumKeys(){
    return numKeys;
  }
  
  public void changePotion(int healthPotion){
    this.healthPotion = this.healthPotion + healthPotion;
  }
  
  public int getHealthPotion(){
    return healthPotion;
  }
}

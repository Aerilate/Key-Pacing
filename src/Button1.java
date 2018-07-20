/* 
 * [Button1.java]
 * Class for the button, used to make purchases
 * Author: Jason, Raymond 
 * June 14, 2018
 */

import java.awt.Rectangle;
class Button1 extends Blocks{
  
  private boolean buttonClicked = false; 
  
  Button1(int x, int y){
    super(x,y);
    boundingBox = new Rectangle(x,y,150, 25);
  }
  
  public void setButtonClicked(boolean buttonClicked){
    this.buttonClicked = buttonClicked;
  }
  
  public boolean getButtonClicked(){
    return buttonClicked;
    
  }
}
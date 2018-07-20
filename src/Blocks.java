/* 
 * [Blocks.java]
 * Class for creating all objects in the game
 * Author: Jason, Raymond 
 * June 14, 2018
 */

import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;

abstract class Blocks{
  
  private int xLocation; 
  private int yLocation; 
  Rectangle boundingBox;
  
  Blocks(int x, int y){
    xLocation = x; 
    yLocation = y;
  }
  
  //Getters and Setters
  public int getXLocation(){
    return xLocation; 
  }
  
  public int getYLocation(){
    return yLocation; 
  }
  
  public void setXLocation(int xLocation){
    this.xLocation = xLocation;
  }
  
  public void setYLocation(int yLocation ){
    this.yLocation = yLocation; 
  }
  
  public void changeXLocation(int xLocation){
    this.xLocation = this.xLocation + xLocation;
  }
  
  public void changeYLocation(int yLocation){
    this.yLocation = this.yLocation + yLocation;
  }
  
  //draw and show the bounding boxes, only used in the testing process
  public void draw(Graphics g){
    g.setColor(Color.RED);
    g.fillRect(boundingBox.x,boundingBox.y,boundingBox.width,boundingBox.height );
    
  }
}


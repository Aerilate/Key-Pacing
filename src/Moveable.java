/* 
 * [Moveable.java]
 * Class for all of the moveable objects 
 * Author: Jason, Raymond 
 * June 14, 2018
 */

abstract class Moveable extends Blocks{
  
  private int xDirection,yDirection; 
  private int facingDirection; //Determine which direction the player is facing, 0 for front, 1 for back, 2 for left, 3 for right
  private boolean move = true; 
  private boolean endGame; 
  
  Moveable(int x, int y){
    super(x, y); 
    this.xDirection=0;
    this.yDirection=0;
    endGame = false; 
    facingDirection = 0; 
  }
  
  //Getters and Setters
  public boolean getMove(){
    return move; 
  }
  
  public void setMove(boolean move){
    this.move = move; 
  }
  
  public int getFacingDirection(){
    return facingDirection;
  }
  
  public void setFacingDirection(int facingDirection){
    this.facingDirection = facingDirection;
  }
  
  public int getXDirection(){
    return xDirection;  
  }
  
  public int getYDirection(){
    return yDirection;    
  }
  
  public void setYDirection(int yDirection){
    this.yDirection = yDirection; 
  }
  
  public void setXDirection(int xDirection){
    this.xDirection = xDirection; 
  }
  
  public boolean getEndGame(){
    return endGame; 
  }
  
  public void setEndGame(boolean endGame){
    this.endGame = endGame; 
  }
  
  
  //move the moveable based on the given direction 
  public void move(){
    
    if(getMove() == true){
      setXLocation(this.getXLocation() + this.xDirection);        
      setYLocation(this.getYLocation() + this.yDirection);
    }
    
  }
}
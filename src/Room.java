/* 
 * [Room.java]
 * Class for the boulders, which the players can push
 * Author: Jason, Raymond 
 * June 14, 2018
 */

class Room{
  
  private int roomNumber; 
  private int highestRoom; 
  
  Room(int room){
    this.roomNumber =room; 
  }
  
  //Getters and Setters
  public int getRoom(){
    return roomNumber;   
  }
  
  public void setRoom(int roomNumber){
    this.roomNumber = roomNumber;  
  }
  
  public int getRoomNumber(){
    return roomNumber;
  }
  
  public void setHighestRoom(int highestRoom){
    this.highestRoom = highestRoom;  
  }
  
  public int getHighestRoom(){
    return highestRoom;
    
  }
}
/* 
 * [GameFrame.java]
 * Main game loop for the game, updates constanly and plays the game
 * Author: Jason, Raymond 
 * June 14, 2018
 * version 8.2
 */

import javax.swing.*;
import java.awt.*;

import java.util.Scanner;

import java.io.PrintWriter;
import java.io.File;

import java.lang.Math;

//Graphics &GUI imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;

//Keyboard imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Mouse imports
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.awt.Rectangle;

/**
   * GameFrame
   * Class that initializes game variables
   * @param nothing
   * @return nothing
   */
class GameFrame extends JFrame { 
  
  //class variable (non-static)
  static double x, y;
  static GameAreaPanel gamePanel;
  
  //Setting intial image variables 
  
  Image characterFront,characterBack, characterLeft, characterRight;
  
  Image shopPic, healthPotion, speedPotion; 
  Image door, floor, forest, grass, seagull, tree, ufo, darkness, water;
  Image key;
  Image border,boulder;
  
  Image inventoryKey1, inventoryKey2, inventoryKey3, inventoryKey4, inventoryKey5, inventoryKey6;
  Image shop1;
  Image gem; 
  Image inventorySlots;
  Image inventoryGem1, inventoryGem2;
  
  Image room1, room2, room3, room4, room5, room6, room7, room8, room9, room10, room11, room12, room13, room14, room15;
  
  Image heart1, heart2, heart3;
  Image endGameScreen;
  
  //Setting intial object variables
  Inventory inventory; 
  
  Player player;
  UFO enemy1,enemy2;
  
  Room room; 
  
  Shop shop;
  Button1 button1;
  HealthPotion healthPotion1, healthPotion2;
  
  Gem gem1, gem2;
  
  boolean blockedUp, blockedDown, blockedRight, blockedLeft;
  boolean blocked;
  
  Door door1,door2, door3, door4; 
  Boulder boulder1, boulder2, boulder3; 
  Wall wall1, wall2,wall3, wall4;
  Key key1, key2, key3, key4, key5, key6;
  
  Image inventoryWords;
  
  //intializing variable starting values
  boolean gotKey1=false;
  boolean gotKey2=false;
  boolean gotKey3=false;
  boolean gotKey4=false;
  boolean gotKey5=false;
  boolean gotKey6=false;
  
  int  getGem1 = 0;
  int  getGem2 = 0;
  
  //Constructor - this runs first
  //runs new game with no previously loaded data
  GameFrame() { 
    
    super("Key Pacing");  
    
    //spawning in all objects 
    inventory = new Inventory(0,0);
    
    player = new Player(200,580,3); 
    
    room  = new Room(1);
    
    button1 = new Button1(1211, 775);
    enemy1=new UFO (500,500);
    enemy2=new UFO (500,500);
    shop = new Shop(390,400); 
    
    door1 = new Door(410,0,200,30);
    door2 = new Door(410,950,200,30);
    door3 = new Door(950,300, 30, 200);
    door4 = new Door(40,530, 30, 200);
    
    wall1=new Wall(70, 0, 900, 10);
    wall2=new Wall(70, 990, 900,10);
    wall3=new Wall(970, 95, 10, 900);
    wall4=new Wall(60, 95, 10, 900);
    
    boulder1 = new Boulder(500,500); 
    boulder2= new Boulder(500,500); 
    boulder3= new Boulder(500,500); 
    
    key1= new Key(80, 300);
    key2= new Key(400, 500);
    key3= new Key(400, 250);
    key4= new Key(400, 150);
    key5= new Key(200, 250);
    key6= new Key(400, 150);
    
    gem1 = new Gem(500,200);
    gem2 = new Gem(500,200);
    
    healthPotion1 = new HealthPotion(1188,230);
    healthPotion2 = new HealthPotion(1308,230);
    
    // Set the frame to full screen 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1500,1080);
    // this.setUndecorated(true);  //Set to true to remove title bar
    //frame.setResizable(false);
    
    
    //Set up the game panel (where we put our graphics)
    gamePanel = new GameAreaPanel();
    this.add(new GameAreaPanel());
    
    //Set up key listener
    MyKeyListener keyListener = new MyKeyListener();
    this.addKeyListener(keyListener);
    
    //Set up mouse listener
    MyMouseListener mouseListener = new MyMouseListener();
    this.addMouseListener(mouseListener);
    
    this.requestFocusInWindow(); //make sure the frame has focus   
    
    this.setVisible(true);
    
    //Start the game loop in a separate thread
    Thread t = new Thread(new Runnable() { public void run() { animate(); }}); //start the gameLoop 
    t.start();
    
  } //End of Constructor
  
  //Constructor - this runs first
  //runs game with previously loaded data
  GameFrame(boolean saved) { 
    
    super("Key Pacing");  
    
    //Setting up intial locations and stats based on saved file 
    try{
      File file = new java.io.File("load.txt"); 
      Scanner input = new Scanner(file); 
      
      player = new Player(200,580, input.nextInt()); 
      
      room  = new Room(input.nextInt());
      
      getGem1 = input.nextInt();
      getGem2 = input.nextInt();
      
      int numGem =0; 
      if(getGem1 == 1){
        numGem ++; 
      }
      if(getGem2 == 1){
        numGem ++; 
      }
      
      inventory = new Inventory(input.nextInt(),numGem);
      
      healthPotion1.acquired = input.nextBoolean();
      healthPotion2.acquired = input.nextBoolean();
      
      input.close(); 
    }catch(Exception e) {};
    
    button1 = new Button1(1211, 775);
    enemy1=new UFO (500,500);
    enemy2=new UFO (500,500);
    shop = new Shop(390,400); 
    
    door1 = new Door(410,0,200,30);
    door2 = new Door(410,950,200,30);
    door3 = new Door(950,300, 30, 200);
    door4 = new Door(40,530, 30, 200);
    
    wall1=new Wall(70, 0, 900, 10);
    wall2=new Wall(70, 990, 900,10);
    wall3=new Wall(970, 95, 10, 900);
    wall4=new Wall(60, 95, 10, 900);
    
    boulder1 = new Boulder(500,500); 
    boulder2= new Boulder(500,500); 
    boulder3= new Boulder(500,500); 
    
    key1= new Key(80, 300);
    key2= new Key(400, 500);
    key3= new Key(400, 250);
    key4= new Key(400, 150);
    key5= new Key(200, 250);
    key6= new Key(400, 150);
    
    gem1 = new Gem(500,200);
    gem2 = new Gem(500,200);
    
    healthPotion1 = new HealthPotion(1188,230);
    healthPotion2 = new HealthPotion(1308,230);
    
    // Set the frame to full screen 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1500,1080);
    // this.setUndecorated(true);  //Set to true to remove title bar
    //frame.setResizable(false);
    
    //Set up the game panel (where we put our graphics)
    gamePanel = new GameAreaPanel();
    this.add(new GameAreaPanel());
    
    MyKeyListener keyListener = new MyKeyListener();
    this.addKeyListener(keyListener);
    
    MyMouseListener mouseListener = new MyMouseListener();
    this.addMouseListener(mouseListener);
    
    this.requestFocusInWindow(); //make sure the frame has focus   
    
    this.setVisible(true);
    
    //Start the game loop in a separate thread
    Thread t = new Thread(new Runnable() { public void run() { animate(); }}); //start the gameLoop 
    t.start();
    
  } //End of Constructor
  
  
 
  /**
   * animate
   * the main gameloop - this is where the game state is updated
   * @param nothing
   * @return nothing
   */
  public void animate() { 
    
    //Loop for when the play game conditions are met
    while(player.getEndGame() == false){
      
      
      
      
      //updating bonding box of all moving objects(player,enemies,boulders) 
      player.boundingBox.x = player.getXLocation(); 
      player.boundingBox.y = player.getYLocation(); 
      
      enemy1.boundingBox.x = enemy1.getXLocation(); 
      enemy1.boundingBox.y = enemy1.getYLocation(); 
      
      enemy2.boundingBox.x = enemy2.getXLocation(); 
      enemy2.boundingBox.y = enemy2.getYLocation(); 
      
      boulder1.boundingBox.x = boulder1.getXLocation(); 
      boulder1.boundingBox.y = boulder1.getYLocation(); 
      
      boulder2.boundingBox.x = boulder2.getXLocation(); 
      boulder2.boundingBox.y = boulder2.getYLocation(); 
      
      boulder3.boundingBox.x = boulder3.getXLocation(); 
      boulder3.boundingBox.y = boulder3.getYLocation(); 
      
      //move player every turn
      player.move();
      
      //move enemy towards player every turn
      if(enemy1.getXLocation() - player.getXLocation() > 0){
        enemy1.setXDirection(-1);
      }else{
        enemy1.setXDirection(1);
      }
      
      if(enemy1.getYLocation() - player.getYLocation() > 0){
        enemy1.setYDirection(-1);
      }else{
        enemy1.setYDirection(1);
      }
      
      enemy1.move();
      
      if(enemy2.getXLocation() - player.getXLocation() > 0){
        enemy2.setXDirection(-1);
      }else{
        enemy2.setXDirection(1);
      }
      
      if(enemy2.getYLocation() - player.getYLocation() > 0){
        enemy2.setYDirection(-1);
      }else{
        enemy2.setYDirection(1);
      }
      
      enemy2.move();
      
      try{ Thread.sleep(10);} catch (Exception exc){}  //delay
      this.repaint();
      
    }    
  }
  
  
  
  /** --------- INNER CLASSES ------------- **/
  
  /**
   * GameAreaPanel
   * Inner class for the the game area - This is where all the drawing of the screen occurs
   * @param nothing
   * @return nothing
   */

  private class GameAreaPanel extends JPanel {
    
    GameAreaPanel(){
      //Loading in pictures
      characterFront = Toolkit.getDefaultToolkit().getImage("res/CharacterF.png");
      characterBack = Toolkit.getDefaultToolkit().getImage("res/CharacterB.png");
      characterLeft = Toolkit.getDefaultToolkit().getImage("res/CharacterL.png");
      characterRight = Toolkit.getDefaultToolkit().getImage("res/CharacterR.png");
      
      room1 = Toolkit.getDefaultToolkit().getImage("res/room1.png");
      room2=Toolkit.getDefaultToolkit().getImage("res/room2.png");
      room3=Toolkit.getDefaultToolkit().getImage("res/room3.png");
      room4=Toolkit.getDefaultToolkit().getImage("res/room4.png");
      room5=Toolkit.getDefaultToolkit().getImage("res/room5.png");
      room6=Toolkit.getDefaultToolkit().getImage("res/room6.png");
      room7=Toolkit.getDefaultToolkit().getImage("res/room7.png");
      room8=Toolkit.getDefaultToolkit().getImage("res/room8.png");
      room9=Toolkit.getDefaultToolkit().getImage("res/room9.png");
      room10=Toolkit.getDefaultToolkit().getImage("res/room10.png");
      room11=Toolkit.getDefaultToolkit().getImage("res/room11.png");
      room12=Toolkit.getDefaultToolkit().getImage("res/room12.png");
      room13=Toolkit.getDefaultToolkit().getImage("res/room13.png");
      room14=Toolkit.getDefaultToolkit().getImage("res/room14.png");
      room15=Toolkit.getDefaultToolkit().getImage("res/room15.png");
      shop1=Toolkit.getDefaultToolkit().getImage("res/shopmenu.png");
      
      shopPic = Toolkit.getDefaultToolkit().getImage("res/Shop.png");
      healthPotion = Toolkit.getDefaultToolkit().getImage("res/RedPotion.png");
      speedPotion = Toolkit.getDefaultToolkit().getImage("res/BluePotion.png");
      key = Toolkit.getDefaultToolkit().getImage("res/Key.png");
      door = Toolkit.getDefaultToolkit().getImage("res/Door.png");
      floor = Toolkit.getDefaultToolkit().getImage("res/Floor.png");
      forest = Toolkit.getDefaultToolkit().getImage("res/Forest.png");
      grass = Toolkit.getDefaultToolkit().getImage("res/Grass.png");
      seagull = Toolkit.getDefaultToolkit().getImage("res/Seagull.png");
      tree = Toolkit.getDefaultToolkit().getImage("res/Tree.png");
      ufo = Toolkit.getDefaultToolkit().getImage("res/UFO.png");
      darkness = Toolkit.getDefaultToolkit().getImage("res/Void.png");
      water = Toolkit.getDefaultToolkit().getImage("res/Water.png");
      border = Toolkit.getDefaultToolkit().getImage("res/Border.png");
      boulder = Toolkit.getDefaultToolkit().getImage("res/Boulder.png");
      endGameScreen =Toolkit.getDefaultToolkit().getImage("res/EndingScreen.png");
      
      inventoryKey1 = Toolkit.getDefaultToolkit().getImage("res/InventoryKey.png");
      inventoryKey2 = Toolkit.getDefaultToolkit().getImage("res/InventoryKey.png");
      inventoryKey3 = Toolkit.getDefaultToolkit().getImage("res/InventoryKey.png");
      inventoryKey4 = Toolkit.getDefaultToolkit().getImage("res/InventoryKey.png");
      inventoryKey5 = Toolkit.getDefaultToolkit().getImage("res/InventoryKey.png");
      inventoryKey6 = Toolkit.getDefaultToolkit().getImage("res/InventoryKey.png");
      
      inventoryGem1 = Toolkit.getDefaultToolkit().getImage("res/InventoryGem.png");
      inventoryGem2 = Toolkit.getDefaultToolkit().getImage("res/InventoryGem.png");
      
      inventorySlots=Toolkit.getDefaultToolkit().getImage("res/inventorySlots.png");
      
      heart1 = Toolkit.getDefaultToolkit().getImage("res/heart1.png");
      heart2 = Toolkit.getDefaultToolkit().getImage("res/heart2.png");
      heart3 = Toolkit.getDefaultToolkit().getImage("res/heart3.png");
      
      gem = Toolkit.getDefaultToolkit().getImage("res/gem.png");
      
      inventoryWords = Toolkit.getDefaultToolkit().getImage("res/InventoryWord.png");
    }
    
    
    /**
   * paintComponent
   * Loads images for objects
   * @param Graphics g
   * @return nothing
   */
    public void paintComponent(Graphics g) {   
      
      new GameAreaPanel();    
      super.paintComponent(g); //required
      setDoubleBuffered(true); 
      
      //Borders between moveables and wall avoid going through walls
      if (player.boundingBox.intersects(wall1.boundingBox)){
        player.changeYLocation(10); 
      } 
      if (player.boundingBox.intersects(wall2.boundingBox)){
        player.changeYLocation(-10); 
      } 
      if (player.boundingBox.intersects(wall3.boundingBox)){
        player.changeXLocation(-10); 
      } 
      if (player.boundingBox.intersects(wall4.boundingBox)){
        player.changeXLocation(10); 
      } 
      
      if (boulder1.boundingBox.intersects(wall1.boundingBox)){
        boulder1.changeYLocation(1); 
      } 
      if (boulder1.boundingBox.intersects(wall2.boundingBox)){
        boulder1.changeYLocation(-1); 
      } 
      if (boulder1.boundingBox.intersects(wall3.boundingBox)){
        boulder1.changeXLocation(-1); 
      } 
      if (boulder1.boundingBox.intersects(wall4.boundingBox)){
        boulder1.changeXLocation(1); 
      } 
      
      if (boulder2.boundingBox.intersects(wall1.boundingBox)){
        boulder2.changeYLocation(1); 
      } 
      if (boulder2.boundingBox.intersects(wall2.boundingBox)){
        boulder2.changeYLocation(-1); 
      } 
      if (boulder2.boundingBox.intersects(wall3.boundingBox)){
        boulder2.changeXLocation(-1); 
      } 
      if (boulder2.boundingBox.intersects(wall4.boundingBox)){
        boulder2.changeXLocation(1); 
      } 
      
      if (boulder3.boundingBox.intersects(wall1.boundingBox)){
        boulder3.changeYLocation(1); 
      } 
      if (boulder3.boundingBox.intersects(wall2.boundingBox)){
        boulder3.changeYLocation(-1); 
      } 
      if (boulder3.boundingBox.intersects(wall3.boundingBox)){
        boulder3.changeXLocation(-1); 
      } 
      if (boulder3.boundingBox.intersects(wall4.boundingBox)){
        boulder3.changeXLocation(1); 
      } 
      
      
      //adding keys to inventory if keys intercept with the player
      if ((player.boundingBox.intersects(key1.boundingBox))&& (room.getRoom()==4 )&& (!gotKey1)){
        gotKey1=true;
        inventory.addNumKeys();
      } 
      if ((player.boundingBox.intersects(key2.boundingBox))&& (room.getRoom()==7) && (!gotKey2)){
        gotKey2=true;
        inventory.addNumKeys();
      } 
      if ((player.boundingBox.intersects(key3.boundingBox))&& (room.getRoom()==5) && (!gotKey3)){
        gotKey3=true;
        inventory.addNumKeys();
      } 
      if ((player.boundingBox.intersects(key4.boundingBox))&& (room.getRoom()==8) && (!gotKey4)){
        gotKey4=true;
        inventory.addNumKeys();
      } 
      if ((player.boundingBox.intersects(key5.boundingBox))&& (room.getRoom()==12) && (!gotKey5)){
        gotKey5=true;
        inventory.addNumKeys();
      } 
      if ((player.boundingBox.intersects(key6.boundingBox))&& (room.getRoom()==13) && (!gotKey6)){
        gotKey6=true;
        inventory.addNumKeys();
      } 
      
      
      if(player.getEndGame() == false){
        
        //Draw Inventory slots
        g.drawImage(inventorySlots,1100,200,null,this);
        
        //Draw gems in inventory spots based on number of gems acquried
        if (getGem1 ==1){
          g.drawImage(inventoryGem1,1188,230,null,this); 
        }
        if (getGem2==1){
          g.drawImage(inventoryGem2,1308,230,null,this); 
        }
        
        
        //draw number of keys in inventory slots based on number of keys acquired
        if (inventory.getNumKeys()>=1){
          g.drawImage(inventoryKey1,1117,340,null,this); 
        }
        if (inventory.getNumKeys()>=2){
          g.drawImage(inventoryKey2,1237,340,null,this); 
        }
        if (inventory.getNumKeys()>=3){
          g.drawImage(inventoryKey3,1357,340,null,this); 
        }
        if (inventory.getNumKeys()>=4){
          g.drawImage(inventoryKey4,1117,460,null,this); 
        }
        if (inventory.getNumKeys()>=5){
          g.drawImage(inventoryKey5,1237,460,null,this); 
        }
        if (inventory.getNumKeys()>=6){
          g.drawImage(inventoryKey6,1357,460,null,this); 
        }
        
        //Drawing health potions in inventory based on number of potions
        
        if(healthPotion1.acquired == true){
          g.drawImage(healthPotion, healthPotion1.getXLocation(),healthPotion1.getYLocation(),null,this);
        }
        if(healthPotion2.acquired == true){
          g.drawImage(healthPotion, healthPotion2.getXLocation(),healthPotion2.getYLocation(),null,this);
        }
        
        
        
        
        if(room.getRoom() == 1){  
          //-------------------Room 1 load out-------------------//
          
          if (player.boundingBox.intersects(door1.boundingBox)){
            room.setRoom(2);
            player.setYLocation(810); 
          }
          
          g.drawImage(room1,0,0,null,this);      
          
        }else if(room.getRoom() == 2){  
          //-------------------Room 2 load out-------------------//
          
          //Move players between rooms based on which way they go
          if ((player.boundingBox.intersects(door1.boundingBox)) && (inventory.getNumKeys()>=6)){
            room.setRoom(14);
            player.setYLocation(810); 
          }
          if (player.boundingBox.intersects(door2.boundingBox)){
            room.setRoom(1);
            player.setYLocation(100); 
          }
          if (player.boundingBox.intersects(door3.boundingBox)){
            room.setRoom(3);
            player.setXLocation(70); 
          }         
          if ((player.boundingBox.intersects(door4.boundingBox)) && (inventory.getNumKeys()>=4)){
            room.setRoom(9);
            player.setXLocation(850); 
          } 
          
          //draw background room image
          g.drawImage(room2,0,0,null,this);   
          
          //draw locked doors if the correct keys are not acquired
          if (!(inventory.getNumKeys()>=6)){
            g.drawImage(door,380,-150,null,this);     
          } 
          
          if (!(inventory.getNumKeys()>=4)){
            g.drawImage(door,-50,375,null,this);     
          }           
          
          //draw in shop 
          if (player.boundingBox.intersects(shop.boundingBox)){
            //if players are close enough set openShop to true
            shop.openShop=true;
            
            //auto saving the game every turn 
            try{ 
              
              //output to file named load.txt
              File myFile = new File("load.txt");
              PrintWriter output1 = new PrintWriter(myFile);
              
              //player health 
              output1.println(player.getHealth());
              //room number 
              output1.println(room.getRoomNumber());
              //number gems 
              output1.println(getGem1);
              output1.println(getGem2);
              //number keys
              output1.println(inventory.getNumKeys());
              //number potion 
              output1.println(healthPotion1.acquired);
              output1.println(healthPotion2.acquired);
              
              
              output1.close();
              
            }catch(Exception e) {};
            
            //avoids collisiton with the shop
            if(Math.abs(Math.abs((player.getXLocation()+35)-(shop.getXLocation()+125))-125)<= Math.abs(Math.abs((player.getYLocation()+64)-(shop.getYLocation()+95) )-130)){
              if ((player.getXLocation()+35)-(shop.getXLocation()+125)>0 ){
                player.changeXLocation(10); 
              }else if ((player.getXLocation()+35)-(shop.getXLocation()+125)<0){
                player.changeXLocation(-10); 
              }
            }else if (Math.abs(Math.abs((player.getYLocation()+64)-(shop.getYLocation()+95))-130)< Math.abs(Math.abs((player.getXLocation()+35)-(shop.getXLocation()+125))-125)) {
              if ((player.getYLocation()+64)-(shop.getYLocation()+95)>0){
                player.changeYLocation(10); 
              }else if ((player.getYLocation()+64)-(shop.getYLocation()+95)<0){
                player.changeYLocation(-10); 
              }
            } 
            
          }
          
          //draw shop in inventory openShop is true
          if (shop.openShop){
            g.drawImage(shop1,1100,550,null,this);
          }
          
          //if player gets too far from physical shop, close shop in inventory 
          if((player.getXLocation() <  255) || (player.getXLocation() > 650) || (player.getYLocation() < 250) || (player.getYLocation()>700)){
            shop.openShop=false;       
          }
          
          //draw physical shop in center of the page
          g.drawImage(shopPic,shop.getXLocation(),shop.getYLocation(),null,this);  
          
          //allow player to click in shop to buy potion if they have enough gems
          //1 gem equals 1 red potion 
          if(button1.getButtonClicked() == true && shop.openShop == true ){
            if ( inventory.getGems()>0){
              if (getGem1 ==1){
                healthPotion1.acquired = true;
                getGem1=2;
              } else if (getGem2==1){
                healthPotion2.acquired = true;
                getGem2=2;
              }           
              inventory.changePotion(1);
              inventory.changeGems(-1);
            }        
            button1.setButtonClicked(false);
          }
          
          
          
        }else if(room.getRoom() == 3){  
          //-------------------Room 3 load out-------------------//
          
          //Move players between rooms based on which way they go 
          if ((player.boundingBox.intersects(door1.boundingBox)) && (inventory.getNumKeys()>=2)){
            room.setRoom(5);
            player.setYLocation(810); 
          }
          if (player.boundingBox.intersects(door2.boundingBox)){
            room.setRoom(4);
            player.setYLocation(100); 
          }
          if ((player.boundingBox.intersects(door3.boundingBox)) && (inventory.getNumKeys()>=1 )){
            room.setRoom(6);
            player.setXLocation(70); 
          }
          if (player.boundingBox.intersects(door4.boundingBox)){
            room.setRoom(2);
            player.setXLocation(850); 
          }
          
          //draw background room image
          g.drawImage(room3,0,0,null,this); 
          
          if (!(inventory.getNumKeys()>=2)){
            g.drawImage(door,380,-150,null,this);     
          } 
          
          if (!(inventory.getNumKeys()>=1)){
            g.drawImage(door,850,360,null,this);     
          } 
          
        }else if(room.getRoom() == 4){        
          //-------------------Room 4 load out-------------------//
          
          //Move players between rooms based on which way they go
          if (player.boundingBox.intersects(door1.boundingBox)){
            room.setRoom(3);
            player.setYLocation(810); 
          }
          
          //draw background room image
          g.drawImage(room4,0,0,null,this);   
          
          //Move boulders around 
          if (player.boundingBox.intersects(boulder1.boundingBox)){
            if (player.boundingBox.intersects(boulder1.boundingBox)){
              if(Math.abs(Math.abs((player.getXLocation()+35)-(boulder1.getXLocation()+102))-102)<= Math.abs(Math.abs((player.getYLocation()+64)-(boulder1.getYLocation()+102) )-102)){
                if ((player.getXLocation()+35)-(boulder1.getXLocation()+102)>0 ){
                  player.changeXLocation(10); 
                  boulder1.changeXLocation(-1);
                  boulder1.boundingBox = new Rectangle (boulder1.getXLocation(),boulder1.getYLocation(),204,204);
                }else if ((player.getXLocation()+35)-(boulder1.getXLocation()+102)<0){
                  player.changeXLocation(-10); 
                  boulder1.changeXLocation(1);
                  boulder1.boundingBox = new Rectangle (boulder1.getXLocation(),boulder1.getYLocation(),204,204);
                }
              }else if (Math.abs(Math.abs((player.getYLocation()+64)-(boulder1.getYLocation()+102))-102)< Math.abs(Math.abs((player.getXLocation()+35)-(boulder1.getXLocation()+102))-102)) {
                if ((player.getYLocation()+64)-(boulder1.getYLocation()+102)>0){
                  player.changeYLocation(10); 
                  boulder1.changeYLocation(-1);
                  boulder1.boundingBox = new Rectangle (boulder1.getXLocation(),boulder1.getYLocation(),204,204);
                }else if ((player.getYLocation()+64)-(boulder1.getYLocation()+102)<0){
                  player.changeYLocation(-10); 
                  boulder1.changeYLocation(1);
                  boulder1.boundingBox = new Rectangle (boulder1.getXLocation(),boulder1.getYLocation(),204,204);
                }
              }              
            }
          } 
          
          //draw boulder
          g.drawImage(boulder,boulder1.getXLocation(),boulder1.getYLocation(),null,this); 
          
          //Display key if not already acquired 
          if ((!gotKey1) && (inventory.getNumKeys()<1)){
            g.drawImage(key,key1.getXLocation(),key1.getYLocation(),null,this); 
          } 
          
        }else if(room.getRoom() == 5){ 
          //-------------------Room 5 load out-------------------//
          
          //Move players between rooms
          if (player.boundingBox.intersects(door2.boundingBox)){
            room.setRoom(3);
            player.setYLocation(100); 
          }
          
          //draw background room image
          g.drawImage(room5,0,0,null,this);  
          
          //draw key if not already acquired
          if ((!gotKey3) && (inventory.getNumKeys()<3)){
            g.drawImage(key,key3.getXLocation(),key3.getYLocation(),null,this); 
          }
          
        }else if(room.getRoom() == 6){  
          //-------------------Room 6 load out-------------------//
          
          //Move players between rooms      
          if ((player.boundingBox.intersects(door1.boundingBox)) && (inventory.getNumKeys()>=3)){
            room.setRoom(8);
            player.setYLocation(810); 
          }
          if (player.boundingBox.intersects(door2.boundingBox)){
            room.setRoom(7);
            player.setYLocation(100); 
          }
          if (player.boundingBox.intersects(door4.boundingBox)){
            room.setRoom(3);
            player.setXLocation(850); 
          }
          
          //draw background room image
          g.drawImage(room6,0,0,null,this);  
          
          //avoid collision with boulders
          if (player.boundingBox.intersects(boulder2.boundingBox)){
            if (player.boundingBox.intersects(boulder2.boundingBox)){
              if(Math.abs(Math.abs((player.getXLocation()+35)-(boulder2.getXLocation()+102))-102)<= Math.abs(Math.abs((player.getYLocation()+64)-(boulder2.getYLocation()+102) )-102)){
                if ((player.getXLocation()+35)-(boulder2.getXLocation()+102)>0 ){
                  player.changeXLocation(10); 
                  boulder2.changeXLocation(-1);
                  boulder2.boundingBox = new Rectangle (boulder2.getXLocation(),boulder1.getYLocation(),204,204);
                }else if ((player.getXLocation()+35)-(boulder2.getXLocation()+102)<0){
                  player.changeXLocation(-10); 
                  boulder2.changeXLocation(1);
                  boulder2.boundingBox = new Rectangle (boulder2.getXLocation(),boulder1.getYLocation(),204,204);
                }
              }else if (Math.abs(Math.abs((player.getYLocation()+64)-(boulder2.getYLocation()+102))-102)< Math.abs(Math.abs((player.getXLocation()+35)-(boulder2.getXLocation()+102))-102)) {
                if ((player.getYLocation()+64)-(boulder2.getYLocation()+102)>0){
                  player.changeYLocation(10); 
                  boulder2.changeYLocation(-1);
                  boulder2.boundingBox = new Rectangle (boulder2.getXLocation(),boulder2.getYLocation(),204,204);
                }else if ((player.getYLocation()+64)-(boulder2.getYLocation()+102)<0){
                  player.changeYLocation(-10); 
                  boulder2.changeYLocation(1);
                  boulder2.boundingBox = new Rectangle (boulder2.getXLocation(),boulder2.getYLocation(),204,204);
                }
              }              
            }
          } 
          
          //draw ouulder
          g.drawImage(boulder,boulder2.getXLocation(),boulder2.getYLocation(),null,this); 
          
          if (!(inventory.getNumKeys()>=3)){
            g.drawImage(door,380,-150,null,this);     
          } 
          
          
          //draw gem if not already acquired
          if((getGem1 == 0) && (inventory.getGems()<1)){
            g.drawImage(gem,gem1.getXLocation(),gem1.getYLocation(),null,this);
          }
          
          
          //if player intercept with the gem then remove it and add 1 gem to inventory
          if ((player.boundingBox.intersects(gem1.boundingBox)) && (getGem1==0)){
            getGem1 = 1;
            inventory.changeGems(1);
          }
          
          
        }else if(room.getRoom() == 7){ 
          //-------------------Room 7 load out-------------------//
          
          //Move players between rooms     
          if (player.boundingBox.intersects(door1.boundingBox)){
            room.setRoom(6);
            player.setYLocation(810); 
          }
          
          //draw background room image
          g.drawImage(room7,0,0,null,this); 
          
          //draw key
          if ((!gotKey2) && (inventory.getNumKeys()<2)){
            g.drawImage(key,key2.getXLocation(),key2.getYLocation(),null,this); 
          }
          
        }else if(room.getRoom() == 8){ 
          //-------------------Room 8 load out-------------------//
          
          //Move players between rooms   
          if (player.boundingBox.intersects(door2.boundingBox)){
            room.setRoom(6);
            player.setYLocation(100); 
          }
          
          //draw background room image
          g.drawImage(room8,0,0,null,this); 
          
          //draw key
          if ((!gotKey4) && (inventory.getNumKeys()<4 )){
            g.drawImage(key,key4.getXLocation(),key4.getYLocation(),null,this); 
          }
          
          //if enemy touches player, respawn player back at room 1, and decrease health  
          if (player.boundingBox.intersects(enemy1.boundingBox)){
            room.setRoom(1);
            player.setXLocation(200); 
            player.setYLocation(580);        
            player.changeHealth(-1);         
          } 
          
          //draw enemy
          g.drawImage(ufo,enemy1.getXLocation(),enemy1.getYLocation(),null,this);
          
        }else if(room.getRoom() == 9){ 
          //-------------------Room 9 load out-------------------//
          
          //Move players between rooms        
          if (player.boundingBox.intersects(door3.boundingBox)){
            room.setRoom(2);
            player.setXLocation(70); 
          }
          if (player.boundingBox.intersects(door4.boundingBox)){
            room.setRoom(10);
            player.setXLocation(850); 
          }
          
          //draw background room image
          g.drawImage(room9,0,0,null,this);
          
          //draw gem if not acquired
          if((getGem2 == 0) && (inventory.getGems()<2)){         
            g.drawImage(gem,gem2.getXLocation(),gem2.getYLocation(),null,this);
          }
          
          //if player gets to the gem, add it to inventory
          if ((player.boundingBox.intersects(gem2.boundingBox)) && (getGem2==0)){
            getGem2 = 1;
            inventory.changeGems(1);
          }
          
          
        }else if(room.getRoom() == 10){ 
          //-------------------Room 10 load out-------------------//
          
          //Move players between rooms   
          if ((player.boundingBox.intersects(door1.boundingBox))  && (inventory.getNumKeys()>=5)){
            room.setRoom(13);
            player.setYLocation(810); 
          }
          if (player.boundingBox.intersects(door2.boundingBox)){
            room.setRoom(11);
            player.setYLocation(100); 
          }
          if (player.boundingBox.intersects(door3.boundingBox)){
            room.setRoom(9);
            player.setXLocation(70); 
          }
          
          //draw background room image
          g.drawImage(room10,0,0,null,this);
          
          if (!(inventory.getNumKeys()>=5)){
            g.drawImage(door,380,-150,null,this);    
          } 
          
          
          
        }else if(room.getRoom() == 11){  
          //-------------------Room 11 load out-------------------//
          
          //Move players between rooms   
          if (player.boundingBox.intersects(door1.boundingBox)){
            room.setRoom(10);
            player.setYLocation(810); 
          }
          if (player.boundingBox.intersects(door3.boundingBox)){
            room.setRoom(12);
            player.setXLocation(70); 
          }
          
          //boulder collision detection with player
          if (player.boundingBox.intersects(boulder3.boundingBox)){
            if (player.boundingBox.intersects(boulder3.boundingBox)){
              if(Math.abs(Math.abs((player.getXLocation()+35)-(boulder3.getXLocation()+102))-102)<= Math.abs(Math.abs((player.getYLocation()+64)-(boulder3.getYLocation()+102) )-102)){
                if ((player.getXLocation()+35)-(boulder3.getXLocation()+102)>0 ){
                  player.changeXLocation(10); 
                  boulder3.changeXLocation(-1);
                  boulder3.boundingBox = new Rectangle (boulder3.getXLocation(),boulder1.getYLocation(),204,204);
                }else if ((player.getXLocation()+35)-(boulder3.getXLocation()+102)<0){
                  player.changeXLocation(-10); 
                  boulder3.changeXLocation(1);
                  boulder3.boundingBox = new Rectangle (boulder3.getXLocation(),boulder1.getYLocation(),204,204);
                }
              }else if (Math.abs(Math.abs((player.getYLocation()+64)-(boulder3.getYLocation()+102))-102)< Math.abs(Math.abs((player.getXLocation()+35)-(boulder3.getXLocation()+102))-102)) {
                if ((player.getYLocation()+64)-(boulder3.getYLocation()+102)>0){
                  player.changeYLocation(10); 
                  boulder3.changeYLocation(-1);
                  boulder3.boundingBox = new Rectangle (boulder3.getXLocation(),boulder3.getYLocation(),204,204);
                }else if ((player.getYLocation()+64)-(boulder3.getYLocation()+102)<0){
                  player.changeYLocation(-10); 
                  boulder3.changeYLocation(1);
                  boulder3.boundingBox = new Rectangle (boulder3.getXLocation(),boulder3.getYLocation(),204,204);
                }
              }              
            }
          } 
          
          //draw background room image
          g.drawImage(room11,0,0,null,this);
          
          //draw boulder
          g.drawImage(boulder,boulder3.getXLocation(),boulder3.getYLocation(),null,this); 
          
          
        }else if(room.getRoom() == 12){
          //-------------------Room 12 load out-------------------//
          
          //Move players between rooms   
          if (player.boundingBox.intersects(door4.boundingBox)){
            room.setRoom(11);
            player.setXLocation(850); 
          }
          
          //draw background room image
          g.drawImage(room12,0,0,null,this); 
          
          if ((!gotKey5) && (inventory.getNumKeys()<5)){
            g.drawImage(key,key5.getXLocation(),key5.getYLocation(),null,this); 
          }
          
        }else if(room.getRoom() == 13){  
          //-------------------Room 13 load out-------------------//
          
          //Move players between rooms   
          if (player.boundingBox.intersects(door2.boundingBox)){
            room.setRoom(10);
            player.setYLocation(100); 
          }
          
          //draw background room image
          g.drawImage(room13,0,0,null,this); 
          
          //draw key
          if ((!gotKey6) && (inventory.getNumKeys()<6)){
            g.drawImage(key,key6.getXLocation(),key6.getYLocation(),null,this); 
          }
          
          //respawn player back at spawn if hit by enemy and decrease 1 health
          if (player.boundingBox.intersects(enemy2.boundingBox)){
            room.setRoom(1);
            player.setXLocation(200); 
            player.setYLocation(580); 
            player.changeHealth(-1);
            
          } 
          
          g.drawImage(ufo,enemy2.getXLocation(),enemy2.getYLocation(),null,this);
          
        }else if(room.getRoom() == 14){  
          //-------------------Room 14 load out-------------------//
          
          //Move players between rooms   
          if (player.boundingBox.intersects(door2.boundingBox)){
            room.setRoom(2);
            player.setYLocation(100); 
          }
          if (player.boundingBox.intersects(door4.boundingBox)){
            room.setRoom(15);
            player.setXLocation(850); 
          }
          
          //draw background room image
          g.drawImage(room14,0,0,null,this);  
          
          
        }else if(room.getRoom() == 15){ 
          //-------------------Room 15 load out-------------------//
          
          //Move players between rooms   
          if (player.boundingBox.intersects(door3.boundingBox)){
            room.setRoom(14);
            player.setXLocation(70); 
          }
          
          //if player moves on to final game end pad, end game 
          if((player.getXLocation() >484) && (player.getXLocation() <620)){
            if((player.getYLocation() >400) && (player.getYLocation()<500)){
              player.setEndGame(true);
            }           
          }
          
          
          //draw background room image
          g.drawImage(room15,0,0,null,this); 
          
        }
        
        //draw player based on movement direction
        if(player.getFacingDirection() == 0){
          g.drawImage(characterFront,player.getXLocation(),player.getYLocation(),null,this);      
        }else if(player.getFacingDirection() == 1){
          g.drawImage(characterBack,player.getXLocation(),player.getYLocation(),null,this);  
        }else if(player.getFacingDirection() == 2){
          g.drawImage(characterLeft,player.getXLocation(),player.getYLocation(),null,this);  
        }else if(player.getFacingDirection() == 3){
          g.drawImage(characterRight,player.getXLocation(),player.getYLocation(),null,this);  
        }
        
        
        //show health in inventory bar 
        if(player.getHealth() >= 3){
          g.drawImage(heart3,1130,25,null,this);
        }else if(player.getHealth() == 2){
          g.drawImage(heart2,1130,25,null,this);  
        }else if(player.getHealth() == 1){
          g.drawImage(heart1,1130,25,null,this);  
        }else if(player.getHealth() <1){
          player.setEndGame(true);  
        }
        
        
        //draw text, inventory header
        g.drawImage(inventoryWords,1200,150,null,this);   
        
        //draw border between game and inventory
        g.drawImage(border,1020,0,null,this);  
        
        
      } else if(player.getEndGame() == true){
        //if gameEnd is set to true 
        
        //draw endGameScreeen             
        g.drawImage(endGameScreen,0,0,null,this);
        
        //keep track of high score
        try{ 
          File myFile = new java.io.File("highscore.txt"); 
          
          Scanner input = new Scanner(myFile); 
          if(room.getRoom() > input.nextInt()){
            
            PrintWriter output = new PrintWriter(myFile);
            
            //room number 
            output.println(room.getRoomNumber());
            //player health 
            output.println(player.getHealth());
            //number gems 
            output.println(inventory.getGems());
            //number keys
            output.println(inventory.getNumKeys());
            //number potion 
            output.println(inventory.getHealthPotion());
            output.close();
          }  
        }catch(Exception e) {};
        
        //if the end is reached or if the user died, then reset the loaded file and set everything to deault settings
        try{ 
          File myFile = new java.io.File("load.txt");           
          Scanner input = new Scanner(myFile); 
          
          PrintWriter output = new PrintWriter(myFile);
          
          //player health 
          output.println(3);
          //room number 
          output.println(1);
          //number gems 
          output.println(0);
          //number keys
          output.println(0);
          //number potion 
          output.println(0);
          output.close();
          
        }catch(Exception e) {};
        
        
        
      }  
      
    }
  }
  
  
  /**
   * MyKeyListener
   * Inner class for the keyboard listener - this detects key presses and runs the corresponding code
   * @param nothing
   * @return nothing
   */
  private class MyKeyListener implements KeyListener {
    
    /**
   * keyTyped
   * Checks for when the keys are typed
   * @param KeyEvent e
   * @return nothing
   */
    public void keyTyped(KeyEvent e) {  
    }
    
    /**
   * keyPressed
   * Checks for when the keys are pressed
   * @param KeyEvent e
   * @return nothing
   */
    public void keyPressed(KeyEvent e) {
      boolean alreadyMoved = false; 
      
      //move player immediately when one direction key is pressed
      if((e.getKeyChar() == 'w') && (!alreadyMoved) && (!blockedUp)){   
        //move up 
        player.setYDirection(-5);
        alreadyMoved = true;
        
      } else if((e.getKeyChar() == 's') && (!alreadyMoved) && (!blockedDown )){
        //move down
        player.setYDirection(5);
        alreadyMoved = true;
        
      } else if((e.getKeyChar() == 'd')  && (!alreadyMoved) && (!blockedRight)  ){
        //move right
        player.setXDirection(5);
        alreadyMoved = true;
        
      } else if((e.getKeyChar() == 'a') && (!alreadyMoved) && (!blockedLeft) ){
        //move left
        player.setXDirection(-5);
        alreadyMoved = true;
        
      } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {  //If ESC is pressed immediately end game
        //System.out.println("End game");
        player.setEndGame(true);
      }     
      
      //determine the direction the player is facing based on movement
      if ((player.getYDirection()<0) && (player.getXDirection()==0) && (!blocked)){
        player.setFacingDirection(1);
      }  if ((player.getYDirection()>0) && (player.getXDirection()==0) && (!blocked)){
        player.setFacingDirection(0);
      }if ((player.getXDirection()<0) && (player.getYDirection()==0) && (!blocked)){
        player.setFacingDirection(2);
      } if ((player.getXDirection()>0) && (player.getYDirection()==0) && (!blocked)){
        player.setFacingDirection(3);
      }
    }
    
      /**
   * keyReleased
   * Checks for when the keys are released
   * @param KeyEvent e
   * @return nothing
   */
    public void keyReleased(KeyEvent e) {
      
      //immediately stop movement as the keys are let go 
      if(e.getKeyChar() == 'w' ){    //Good time to use a Switch statement
        player.setYDirection(0);
      } else if(e.getKeyChar() == 's'){
        player.setYDirection(0);
        player.setMove(true);
      } else if(e.getKeyChar() == 'd'){
        player.setXDirection(0);
      } else if(e.getKeyChar() == 'a'){
        player.setXDirection(0);
      }if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {  //If ESC is pressed
        player.setEndGame(true);
      }   
      
      //set facing direction of the player
      if ((player.getYDirection()<0) && (player.getXDirection()==0) && (!blocked)){
        player.setFacingDirection(1);
      }  if ((player.getYDirection()>0) && (player.getXDirection()==0) && (!blocked)){
        player.setFacingDirection(0);
      }if ((player.getXDirection()<0) && (player.getYDirection()==0) && (!blocked)){
        player.setFacingDirection(2);
      } if ((player.getXDirection()>0) && (player.getYDirection()==0) && (!blocked)){
        player.setFacingDirection(3);
      }
    }
    
    
  } //end of keyboard listener
  
   /**
   * MyMouseListener
   * This detects mouse movement & clicks and runs the corresponding methods 
   * @param nothing
   * @return nothing
   */
  private class MyMouseListener implements MouseListener {
    
    /**
     * mouseClicked
     * Opens the shop menu and allows the player to use potions
     * @param MouseEvent e
     * @return nothing
     */
    public void mouseClicked(MouseEvent e) {
      //System.out.println("Mouse Clicked");
      //System.out.println("X:"+e.getX() + " y:"+e.getY());
      Rectangle boundingBox;
      boundingBox = new Rectangle (e.getX()-50,e.getY()-50,100,100);
      button1.setButtonClicked(false);
      
      //if clicked on inventory shop to purchase an item set clicked to true
      if (button1.boundingBox.intersects(boundingBox)){
        if(shop.openShop){
          button1.setButtonClicked(true);
        }
        
      }
      
      //if potion is clicked use it on the player
      if((healthPotion1.boundingBox.intersects(boundingBox)) && (player.getHealth()<3)){
        if(healthPotion1.acquired == true){
          healthPotion1.acquired = false;
          player.changeHealth(1);
        }
      }
      
      if((healthPotion2.boundingBox.intersects(boundingBox)) && (player.getHealth()<3)){
        if(healthPotion2.acquired == true){
          healthPotion2.acquired = false;
          player.changeHealth(1);
        }
      }
      
    }
    
    /**
     * mousePressed
     * Looks for when the mouse is pressed
     * @param nothing
     * @return nothing
     */
    public void mousePressed(MouseEvent e) {
    }
    
    /**
     * mouseReleased
     * Looks for when the mouse is released
     * @param nothing
     * @return nothing
     */
    public void mouseReleased(MouseEvent e) {
    }
    
    /**
     * mouseEntered
     * Looks for when the mouse enters
     * @param nothing
     * @return nothing
     */
    public void mouseEntered(MouseEvent e) {
    }
    
    /**
     * mouseExited
     * Looks for when the mouse exits
     * @param nothing
     * @return nothing
     */
    public void mouseExited(MouseEvent e) {
    }
    
  } //end of mouselistener
}







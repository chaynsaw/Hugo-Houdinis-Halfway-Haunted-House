package com.locallampoon.fiveh.core;

import java.util.ArrayList;
import java.util.List;


public class Player {

    //INSTANCE VARIABLE
    private String character;
    private List<String> inventory = new ArrayList<>();
    private int health;
    private boolean isStrong;//Players will use to move heavy items
    private boolean isSmart;
    private Room currentRoom;
    private int maxItemSize;
    private boolean isDead;
//    AttackMonster attackMonster;

    // CONSTRUCTOR
    public Player(){
        this.health=5;
        this.isDead=false;
        this.maxItemSize=5;
        this.isSmart=false;
        this.isStrong=false;
    }
    public Player(Room currentRoom) {
        this();
        this.currentRoom = currentRoom;
    }
//    public Player(List<String> inventory, int health, boolean isStrong, boolean isSmart){
//        this();
//        this.inventory = inventory;
//        this.isStrong = isStrong;
//        this.isSmart = isSmart;
//
//    }

    //METHODS
    public void move(Room room){
        // Players will be able to move based on the command. (GO EAST, GO SOUTH..ETC)
        currentRoom = room;
    }

    public void addItem(String item){
        // Players will be able to add Items to their inventory until the maximum items are reached
        if (inventory.size()< maxItemSize)
            this.inventory.add(item);
        else{
            System.out.println("You need to drop an item to add more to your inventory");
            System.out.println(inventory);
        }
         }

    public void dropItem(String item){
        // Players will be able to drop Items from inventory if they want to switch to different items.
            if(inventory.contains(item)){
                inventory.remove(item);
                System.out.println(item + " Dropped");
            }
            else {
                System.out.println("You do not have " + item + " in your inventory");
            }

    }

    public void  attack(Player jock){
        int damage =1;
        jock.takeDamage(damage);
    }

    public void takeDamage (int damage){
        if (health - damage <=0){
            health = 0;
            isDead = true;
        } else {
            health -= damage;
        }
    }

    public void getCurrentItemDetails(){
        if(inventory.isEmpty()){
            System.out.println("Your nothing in your inventory");
        }
        else{
            System.out.println("You have following items in your inventory ");
            System.out.println(inventory);

        }


//        for(Item item : items){
//            returnString += "Name:" + item.getName() + "\n";
////            returnString += "Description:" + item.getDescription() + "\n";
//        }
//        return returnString;
    }

    //ACCESSOR METHOD

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public int getMaxItemSize() {
        return maxItemSize;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public int getHealth() {
        return health;
    }

    public boolean isStrong() {
        return isStrong;
    }

    public boolean isSmart() {
        return isSmart;
    }

    public boolean isDead() {
        return isDead;
    }

    public static void main(String[] args) {
        Player p1 = new Player();

//        p1.addItem("Book");
//        p1.addItem("Pencil");
//        p1.addItem("Water");
//        p1.addItem("Fire");
//        p1.addItem("Pen");
//        p1.addItem("phone");
//        p1.addItem("rope");


        System.out.println(p1.inventory);

        p1.dropItem("Water");


        System.out.println(p1.inventory);
    }
}
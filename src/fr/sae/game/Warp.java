package fr.sae.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import fr.sae.game.caractere.Player;

public class Warp {
    private float startX; // Starting X position of the warp zone
    private float startY; // Starting Y position of the warp zone
    private float destinationX; // Destination X position to teleport to
    private float destinationY; // Destination Y position to teleport to
    private int width; // Width of the warp zone
    private int height; // Height of the warp zone

    // Constructor to initialize warp zone and destination coordinates
    public Warp(float startX, float startY, int width, int height, float destinationX, float destinationY) {
        this.startX = startX;
        this.startY = startY;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.width = width;
        this.height = height;
    }

    // Function to check if an object collides with the warp zone
    public boolean collidesWith(float x, float y, float objectWidth, float objectHeight) {
        return x < this.startX + this.width && x + objectWidth > this.startX && y < this.startY + this.height && y + objectHeight > this.startY;
    }

    // Function to teleport a rectangle (usually the player) to the destination coordinates
    public void teleport(Rectangle rectangle) {
        rectangle.setX(this.destinationX);
        rectangle.setY(this.destinationY);
    }

    // Function to draw the warp zone for debugging purposes
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(this.startX, this.startY, this.width, this.height);
        g.setColor(Color.white);
    }

    // Getters and setters for the private variables
    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(float destinationX) {
        this.destinationX = destinationX;
    }

    public float getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(float destinationY) {
        this.destinationY = destinationY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Function to change the map and teleport the player to the destination coordinates
    public void changeToMap(StateBasedGame game, int ID) {
        // Define --> change the current map
        Global.P1.getHitbox().setX(this.destinationX);
        Global.P1.getHitbox().setY(this.destinationY);
        game.enterState(ID);
    }

    // Function to warp the player if they collide with the warp zone
    public void warp(Player player, StateBasedGame game, int id) {
        if (this.collidesWith(player.getHitbox().getX(), player.getHitbox().getY(), 36, 46)) {
            this.changeToMap(game, id);
        }
    }
}

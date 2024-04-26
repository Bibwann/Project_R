package fr.sae.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;


public class Warp {
    private float startX;
    private float destinationX;
    private float destinationY;
    private int width;
    private int height;

    public Warp(float startX, float startY , int width, int height, float destinationX, float destinationY) {
        this.startX = startX;
        this.startY = startY;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.width = width;
        this.height = height;
    }

    public boolean collidesWith(float x, float y, float objectWidth, float objectHeight) {
    	//Fonction pour savoir si un object entre en collision avec un autre
        return x < this.startX + this.width && x + objectWidth > this.startX && y < this.startY + this.height && y + objectHeight > this.startY;
    }
    
    public void teleport(Rectangle rectangle) {
    	//Change les coordonnes du joueur
    	
    	rectangle.setX(this.destinationX);
    	rectangle.setY(this.destinationY);
    }
    
    public void draw(Graphics g) {
    	
    	//Dessine la zone de warp
    	
        g.drawRect(this.startX, this.startY, this.width, this.height);
    }

	public float getStartX() {
		return startX;
	}

	public void setStartX(float startX) {
		this.startX = startX;
	}

	private float startY;

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

	public void changeToMap(StateBasedGame game ,int ID) {
		// A definir --> change la map actuelle ( avec surment un this.addState(new Classe(ID));)
		game.enterState(ID);
		}
}

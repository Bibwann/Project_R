package fr.sae.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;

import org.newdawn.slick.geom.Shape;
import java.util.ArrayList;
import java.util.List;

public class Collisions {
    private List<Shape> collidables;

    public Collisions() {
        this.collidables = new ArrayList<>();
        
        //Bordures de la map
		Rectangle topBorder = new Rectangle(0, -1, Global.width, 1);
        Rectangle bottomBorder = new Rectangle(0, Global.height, Global.width, 1);
        Rectangle leftBorder = new Rectangle(-1, 0, 1, Global.height);
        Rectangle rightBorder = new Rectangle(Global.width, 0, 1, Global.height);
        
		this.collidables.add(topBorder);
        this.collidables.add(bottomBorder);
        this.collidables.add(leftBorder);
        this.collidables.add(rightBorder);
    }
    
    
    public List<Shape> getCollidables() {
		return collidables;
	}


	public void setCollidables(List<Shape> collidables) {
		this.collidables = collidables;
	}

	public void addCollidable(Shape shape) {
    	this.collidables.add(shape);
    }
	
	public void removeCollidable(Shape shape) {
    	this.collidables.remove(shape);
    }

	protected List<Shape> checkCollisions(Shape shape) {
    	// Fonction qui parcoutrt la liste des collisions et check si une collision a lieux en un instant T ( experimentale )
    	
        List<Shape> collisions = new ArrayList<>();
        for (Shape collidable : this.collidables) {
            if (collidable.intersects(shape)) {
                collisions.add(collidable);
            }
        }
        return collisions;
    }
    
	protected boolean willCollide(Shape shape1, Shape shape2,int x,int y) {
    	// Fonction qui parcoutrt la liste des collisions et check si une collision a lieux en un instant T + 1 ( va avoir lieux si on continue le mouvement ( experimentale ))


        Shape tempShape1 = createTempShape(shape1);
        Shape tempShape2 = createTempShape(shape2);


        float originalX1 = tempShape1.getX();
        float originalY1 = tempShape1.getY();
        float originalX2 = tempShape2.getX();
        float originalY2 = tempShape2.getY();

        tempShape1.setX(originalX1 + x);
        tempShape1.setY(originalY1 + y);
        tempShape2.setX(originalX2);
        tempShape2.setY(originalY2);


        boolean collision = tempShape1.intersects(tempShape2);


        tempShape1.setX(originalX1);
        tempShape1.setY(originalY1);
        tempShape2.setX(originalX2);
        tempShape2.setY(originalY2);

        //Resultat boolean de la collision en n+1 ( experimentale )
        return collision;
    }

	protected Shape createTempShape(Shape originalShape) {
    	//Fonction privee pour creer des shapes a partir de formes geometriques ( utilie pour creer des shapes temporaires a l'interieur de cette classe )
        
    	if (originalShape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) originalShape;
            return new Rectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            
        } else if (originalShape instanceof Circle) {
            Circle circle = (Circle) originalShape;
            return new Circle(circle.getCenterX(), circle.getCenterY(), circle.getRadius());
            
        } else if (originalShape instanceof Polygon) {
            Polygon polygon = (Polygon) originalShape;
            float[] points = polygon.getPoints();
            return new Polygon(points);
            
        } else {

            return null;
        }
    }
    
	protected boolean willCollideWithMap(Shape playerShape,int x,int y) {
    	
    	for (Shape collidable : this.collidables){
    		if (this.willCollide(playerShape, collidable, x,y)){
    			return true;
    		}
        }
		return false;
    }
    
    public void drawCollisions(Graphics c) {
    	for (Shape collidable : this.collidables){
    		c.draw(collidable);
    	}
    }
}

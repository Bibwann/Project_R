package fr.sae.game;

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

    public List<Shape> checkCollisions(Shape shape) {
    	// Fonction qui parcoutrt la liste des collisions et check si une collision a lieux en un instant T
    	
        List<Shape> collisions = new ArrayList<>();
        for (Shape collidable : this.collidables) {
            if (collidable.intersects(shape)) {
                collisions.add(collidable);
            }
        }
        return collisions;
    }
    
    public boolean willCollide(Shape shape1, Shape shape2) {
    	// Fonction qui parcoutrt la liste des collisions et check si une collision a lieux en un instant T + 1 ( va avoir lieux si on continue le mouvement)


        Shape tempShape1 = createTempShape(shape1);
        Shape tempShape2 = createTempShape(shape2);


        float originalX1 = tempShape1.getX();
        float originalY1 = tempShape1.getY();
        float originalX2 = tempShape2.getX();
        float originalY2 = tempShape2.getY();

        tempShape1.setX(originalX1 + 1);
        tempShape1.setY(originalY1);
        tempShape2.setX(originalX2 + 1);
        tempShape2.setY(originalY2);


        boolean collision = tempShape1.intersects(tempShape2);


        tempShape1.setX(originalX1);
        tempShape1.setY(originalY1);
        tempShape2.setX(originalX2);
        tempShape2.setY(originalY2);

        //Resultat boolean de la collision en n+1
        return collision;
    }

    private Shape createTempShape(Shape originalShape) {
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
    
    public boolean willCollideWithMap(Shape playerShape) {
    	
    	// Verifie si le player va collisionner la map ( experimentale )
        for (Shape mapObject : this.collidables) {
            if (willCollide(playerShape, mapObject)) {
                return true;
            }
        }
        return false;
    }
}

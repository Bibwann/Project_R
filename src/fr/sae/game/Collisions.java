package fr.sae.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;
import java.util.List;

public class Collisions {
    private final List<Shape> collidables;

    // Constructor to initialize the collidables list and set up the borders
    public Collisions() {
        this.collidables = new ArrayList<>();
        initializeBorders();
    }

    // Method to initialize the borders of the game area
    private void initializeBorders() {
        this.collidables.add(new Rectangle(0, -1, Global.width, 1)); // Top border
        this.collidables.add(new Rectangle(0, Global.height, Global.width, 1)); // Bottom border
        this.collidables.add(new Rectangle(-1, 0, 1, Global.height)); // Left border
        this.collidables.add(new Rectangle(Global.width, 0, 1, Global.height)); // Right border
    }

    // Method to get the list of collidable shapes
    public List<Shape> getCollidables() {
        return collidables;
    }

    // Method to add a new collidable shape
    public void addCollidable(Shape shape) {
        this.collidables.add(shape);
    }

    // Method to remove a collidable shape
    public void removeCollidable(Shape shape) {
        this.collidables.remove(shape);
    }

    // Method to check for collisions with a given shape
    public List<Shape> checkCollisions(Shape shape) {
        List<Shape> collisions = new ArrayList<>();
        for (Shape collidable : this.collidables) {
            if (collidable.intersects(shape)) {
                collisions.add(collidable);
            }
        }
        return collisions;
    }

    // Method to check if two shapes will collide after moving shape1 by (x, y)
    public boolean willCollide(Shape shape1, Shape shape2, int x, int y) {
        Shape tempShape1 = createTempShape(shape1);
        if (tempShape1 == null) return false;

        float originalX1 = tempShape1.getX();
        float originalY1 = tempShape1.getY();

        tempShape1.setX(originalX1 + x);
        tempShape1.setY(originalY1 + y);

        boolean collision = tempShape1.intersects(shape2);

        tempShape1.setX(originalX1);
        tempShape1.setY(originalY1);

        return collision;
    }

    // Method to create a temporary shape with the same properties as the original shape
    private Shape createTempShape(Shape originalShape) {
        if (originalShape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) originalShape;
            return new Rectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());

        } else if (originalShape instanceof Circle) {
            Circle circle = (Circle) originalShape;
            return new Circle(circle.getCenterX(), circle.getCenterY(), circle.getRadius());

        } else if (originalShape instanceof Polygon) {
            Polygon polygon = (Polygon) originalShape;
            return new Polygon(polygon.getPoints());

        } else {
            return null;
        }
    }

    // Method to check if the player shape will collide with any map borders or objects after moving by (x, y)
    public boolean willCollideWithMap(Shape playerShape, int x, int y) {
        for (Shape collidable : this.collidables) {
            if (willCollide(playerShape, collidable, x, y)) {
                return true;
            }
        }
        return false;
    }

    // Method to draw all collidable shapes
    public void drawCollisions(Graphics g) {
        for (Shape collidable : this.collidables) {
            g.draw(collidable);
        }
    }
}

package fr.sae.game;

import org.newdawn.slick.Input;

public class InputHandler { // Class to determine if a key is pressed or not
    private boolean keyPressed; // Flag to indicate if a key is currently pressed
    
    private long time; // Time when the key was last pressed or released
    private long delta; // Time difference between current time and last key event
    private long interval; // Interval threshold to consider key pressed

    // Constructor to initialize with a specified interval
    public InputHandler(long interval) {
        this.interval = interval;
        this.time = System.currentTimeMillis();
        this.delta = 0;
        this.keyPressed = false;
    }

    // Method to handle key press event
    public void keyPressed() {
        keyPressed = true;
        this.time = System.currentTimeMillis();
    }

    // Method to handle key release event
    public void keyReleased() {
        keyPressed = false;
    }

    // Method to check if the key is currently considered pressed
    public boolean isPressed() {
        this.delta = (System.currentTimeMillis() - this.time);
        
        if (this.delta >= this.interval) {
            this.keyPressed = false;
            return false;
        } else {
            this.keyPressed = true;
            return true;
        }
    }
}

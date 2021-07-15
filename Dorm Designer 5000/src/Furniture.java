//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Dorm Designer 5000
// Files: Button.java, ClearButton.java, DormGUI.java, Furniture.java, LoadButton.java, Main.java,
//////////////////// SaveButton.java, DormDesigner.jar, background.png, bed.png, desk.png,
//////////////////// dresser.png, sink.png, sofa.png
// Course: CS 300
//
// Author: Christian Henke
// Email: chenke@wisc.edu
// Lecturer's Name: Alexi Brooks
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/*
 * Furniture class for CS300 project "Dorm Designer 5000". Creates a furniture object in the room
 * 
 * @author Christian Henke
 */
public class Furniture implements DormGUI {

    // declare variables
    private PApplet processing;
    private PImage image;
    private float[] position = new float[2];
    private boolean isDragging;
    private int rotations;
    private String type;

    /*
     * Constructor. Initializes the fields of a new furniture object positioned in the center of the
     * display
     * 
     * @param type The type of furniture this object is
     * 
     * @param processing Application that runs the graphics of this program
     */
    public Furniture(String type, PApplet processing) {
        this.processing = processing;
        this.type = type;
        image = this.processing.loadImage("images/" + this.type + ".png");
        position[0] = this.processing.width / 2;
        position[1] = this.processing.height / 2;
        isDragging = false;
        rotations = 0;
    }

    /*
     * Overloaded Furniture constructor. Allows the LoadButton to load a .ddd file's room
     * 
     * @param type The type of furniture this object is
     * 
     * @param position The position of the object in the room
     * 
     * @param rotations The rotation of the object
     * 
     * @param processing Application that runs the graphics of this program
     */
    public Furniture(String type, float[] position, int rotations, PApplet processing) {
        this.processing = processing;
        this.type = type;
        image = this.processing.loadImage("images/" + this.type + ".png");
        this.position = position;
        isDragging = false;
        this.rotations = rotations;
    }

    /*
     * draws this furniture object at its current position
     */
    public void update() {
        if (isDragging) {
            position[0] = processing.mouseX;
            position[1] = processing.mouseY;
        }
        processing.image(image, position[0], position[1], rotations * PApplet.PI / 2);
    }

    /*
     * Used to start dragging when the mouse is over this furniture and the mouse is pressed
     *
     * @param unused Parameter just to implement the DormGUI interface
     */
    public void mouseDown(Furniture[] unused) {
        if (isMouseOver()) {
            isDragging = true;

        }
    }

    /*
     * Used to indicate that the furniture object is no longer being dragged
     */
    public void mouseUp() {
        isDragging = false;
    }

    /*
     * Helper method to determine whether the mouse is currently over this furniture
     */
    public boolean isMouseOver() {
        if (rotations % 2 == 0) { // normal image
            if (processing.mouseX < position[0] + (image.width / 2)
                            && processing.mouseX > position[0] - (image.width / 2)
                            && processing.mouseY < position[1] + (image.height / 2)
                            && processing.mouseY > position[1] - (image.height / 2))
                return true;
        } else { // rotated image
            if (processing.mouseX < position[0] + (image.height / 2)
                            && processing.mouseX > position[0] - (image.height / 2)
                            && processing.mouseY < position[1] + (image.width / 2)
                            && processing.mouseY > position[1] - (image.width / 2))
                return true;
        }
        return false;
    }

    /*
     * Rotates the furniture object 90 degrees
     */
    public void rotate() {
        rotations++;
    }


    /*
     * Overrides the toString method. Essentially a helper method for storing the room's objects in
     * a .ddd file
     * 
     * @return String The string representation of the furniture
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String item = type + ": " + Float.toString(position[0]) + ", " + Float.toString(position[1])
                        + ", " + Integer.toString(rotations);
        return item;
    }
}

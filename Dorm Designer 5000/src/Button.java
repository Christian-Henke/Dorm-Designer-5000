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
 * Basic Button class for CS300 project "Dorm Designer 5000". Creates a basic button, can be
 * extended for button-y purposes
 * 
 * @author Christian Henke
 */
public class Button implements DormGUI {

    // declare necessary variables
    private static final int WIDTH = 96;
    private static final int HEIGHT = 32;
    protected PApplet processing;
    private float[] position;
    protected String label; // sub classes require access

    /*
     * Constructor. Initializes variables
     * 
     * @param x Horizontal position of button
     * 
     * @param y Vertical position of button
     * 
     * @param processing Runs the graphical part of the program
     */
    public Button(float x, float y, PApplet processing) {
        position = new float[2];
        position[0] = x;
        position[1] = y;
        this.processing = processing;
        label = "Button"; // default
    }

    /*
     * Draws this button at its current position in its current state
     */
    public void update() {
        if (isMouseOver()) {
            processing.fill(100);
        } else {
            processing.fill(200);
        }
        processing.rect(position[0] - WIDTH / 2, position[1] - HEIGHT / 2, position[0] + WIDTH / 2,
                        position[1] + HEIGHT / 2);
        processing.fill(0);
        processing.text(label, position[0], position[1]);
    }

    /*
     * Default response to a click on this button
     */
    public void mouseDown(Furniture[] furniture) {
        System.out.println("A button was pressed.");
    }

    /*
     * Empty method in order to implement DormGUI
     */
    public void mouseUp() {}

    /*
     * Helper method to check if the mouse is over the button
     * 
     * return boolean Whether the mouse is over this button
     */
    public boolean isMouseOver() {
        if (processing.mouseX < position[0] + (WIDTH / 2)
                        && processing.mouseX > position[0] - (WIDTH / 2)
                        && processing.mouseY < position[1] + (HEIGHT / 2)
                        && processing.mouseY > position[1] - (HEIGHT / 2))
            return true;

        return false;
    }
}

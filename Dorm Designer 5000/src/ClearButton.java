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
 * ClearButton class for CS300 project "Dorm Designer 5000". A button that can clears all furniture
 * from a room
 * 
 * @author Christian Henke
 */
public class ClearButton extends Button implements DormGUI {

    /*
     * Constructor. A button that clears all furniture. Initializes variables
     * 
     * @param x Horizontal position of button
     * 
     * @param y Vertical position of button
     * 
     * @param processing Runs the graphical part of the program
     */
    public ClearButton(float x, float y, PApplet processing) {
        super(x, y, processing);
        label = "Clear Room";
    }

    /*
     * If this button was cicked, clears all furniture in the room
     * 
     * @param f The reference to all the room's furniture to be cleared
     */
    @Override
    public void mouseDown(Furniture[] f) {
        if (isMouseOver()) {
            for (int i = 0; i < f.length; i++) {
                f[i] = null;
            }
        }
    }
}

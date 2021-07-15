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
 * CreateFurnitureButton class for CS300 project "Dorm Designer 5000". General button class that
 * creates furniture of a certian type
 * 
 * @author Christian Henke
 */
public class CreateFurnitureButton extends Button implements DormGUI {

    // declare variables
    private String type;

    /*
     * Constructor. A button that, when clicked, creates a type of furniture. Initializes variables
     * 
     */
    public CreateFurnitureButton(String type, float x, float y, PApplet processing) {
        super(x, y, processing);
        this.type = type;
        label = "Create " + type;

    }

    /*
     * Adds a furniture object if the mouse clicked on this button
     * 
     * @param furniture the array where we add the bed
     */
    @Override
    public void mouseDown(Furniture[] furniture) {
        // if the user wants a new furniture
        if (isMouseOver()) {
            // check for an empty slot
            for (int counter = 0; counter < furniture.length; counter++) {
                if (furniture[counter] == null) {
                    // then add a bed there
                    furniture[counter] = new Furniture(type.toLowerCase(), processing);
                    break;
                }
            }
        }
    }
}

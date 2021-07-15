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

import java.util.ArrayList;

/*
 * Main class for CS300 project "Dorm Designer 5000". Contains everything for the project to run
 * 
 * @author Christian Henke
 */
public class Main {

    // initialize constant
    public final int FURNITURE_COUNT = 6;

    // declare variables
    private PApplet processing;
    private PImage backgroundImage;
    private ArrayList<DormGUI> guiObjects;

    // Max number of furniture that LoadButton will be allowed to load at once. Used for helper
    // method
    // extractFurnitureFromGUIObjects()
    private final static int MAX_LOAD_FURNITURE = 100;

    /*
     * Constructor. Initialize the room, including the background and underlying room variables
     * 
     * @param processing The application that runs the graphics of this program
     */
    public Main(PApplet processing) {
        this.processing = processing;

        // a nice blue background
        this.processing.background(100, 150, 250);
        // prepare dorm background image
        backgroundImage = processing.loadImage("images/background.png");
        this.processing.image(backgroundImage, processing.width / 2, processing.height / 2);

        guiObjects = new ArrayList<DormGUI>();

        // initialize all the buttons
        guiObjects.add(new CreateFurnitureButton("Bed", 50, 24, processing));
        guiObjects.add(new CreateFurnitureButton("Sofa", 150, 24, processing));
        guiObjects.add(new CreateFurnitureButton("Dresser", 250, 24, processing));
        guiObjects.add(new CreateFurnitureButton("Desk", 350, 24, processing));
        guiObjects.add(new CreateFurnitureButton("Sink", 450, 24, processing));
        guiObjects.add(new ClearButton(550, 24, processing));
        guiObjects.add(new SaveButton(650, 24, processing));
        guiObjects.add(new LoadButton(750, 24, processing));
    }

    /*
     * main method that starts the application, which runs itself
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        Utility.startApplication();
    }


    /*
     * The application constantly updates, this method refreshes the new visuals
     * 
     */
    public void update() {
        // refresh the background
        processing.background(100, 150, 250);
        processing.image(backgroundImage, processing.width / 2, processing.height / 2);

        // show all objects
        for (int i = 0; i < guiObjects.size(); i++) {
            guiObjects.get(i).update();
        }
    }

    /*
     * When the application notices the mouse as down, this method is called. The method then lets
     * all the room objects know the mouse is down
     * 
     */
    public void mouseDown() {
        // call mouse down for each object
        Furniture[] furniture;
        for (int i = 0; i < guiObjects.size(); i++) {
            furniture = extractFurnitureFromGUIObjects(); // pass furniture to let it be modified
            guiObjects.get(i).mouseDown(furniture);
            replaceFurnitureInGUIObjects(furniture); // pass furniture to let it be modified
        }
    }

    /*
     * When the application notices the mouse as up, this method is called. This method then lets
     * all the room objects know the mouse is up
     * 
     */
    public void mouseUp() {
        // call mouse up for each object
        for (int i = 0; i < guiObjects.size(); i++) {
            guiObjects.get(i).mouseUp();
        }
    }

    /*
     * When there is a key press, this method is called. The user can rotate furniture with the 'R'
     * key or delete furniture with the 'D' key.
     * 
     */
    public void keyPressed() {
        // initialize furniture array to traverse through the furniture
        Furniture[] furniture = extractFurnitureFromGUIObjects();

        // if the user wants to rotate furniture
        if (processing.key == 'r' || processing.key == 'R') {
            // check for which furniture object
            for (int counter = 0; counter < furniture.length; counter++) {
                if (furniture[counter].isMouseOver()) {
                    // then rotate the furniture there
                    furniture[counter].rotate();
                    break;
                }
            }
        }

        // if the user wants to delete furniture
        if (processing.key == 'd' || processing.key == 'D') {
            // check for which furniture slot
            for (int counter = 0; counter < furniture.length; counter++) {
                if (furniture[counter].isMouseOver()) {
                    // then remove the object there
                    furniture[counter] = null;
                    break;
                }
            }
        }
        
        // if any furniture was deleted, update the room
        replaceFurnitureInGUIObjects(furniture);
    }

    /*
     * Given helper methods
     */
    
    /**
     * This method creates a new Furniture[] for the old mouseDown() methods to make use of. It does
     * so by copying all Furniture references from this.guiObjects into a temporary array of size
     * MAX_LOAD_FURNITURE.
     * 
     * @return that array of Furniture references.
     */
    private Furniture[] extractFurnitureFromGUIObjects() {
        Furniture[] furniture = new Furniture[MAX_LOAD_FURNITURE];
        int nextFreeIndex = 0;
        for (int i = 0; i < guiObjects.size() && nextFreeIndex < furniture.length; i++)
            if (guiObjects.get(i) instanceof Furniture)
                furniture[nextFreeIndex++] = (Furniture) guiObjects.get(i);
        return furniture;
    }

    /**
     * This method first removes all Furniture references from this.guiObjects, and then adds back
     * in all of the non-null references from it's parameter.
     * 
     * @param furniture contains the only furniture that will be left in this.guiObjects after this
     *        method is invoked (null references ignored).
     */
    private void replaceFurnitureInGUIObjects(Furniture[] furniture) {
        for (int i = 0; i < guiObjects.size(); i++)
            if (guiObjects.get(i) instanceof Furniture)
                guiObjects.remove(i--);
        for (int i = 0; i < furniture.length; i++)
            if (furniture[i] != null)
                guiObjects.add(furniture[i]);
    }
}

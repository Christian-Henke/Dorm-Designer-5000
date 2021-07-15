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


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
 * SaveButton class for CS300 project "Dorm Designer %000". A button that can save the room info to
 * a file
 * 
 * @author Christian Henke
 */
public class SaveButton extends Button implements DormGUI {


    /*
     * Constructor. Creates a button that saves the room into a RoomData.ddd file The constructor
     * initializes variables
     * 
     * @param x Horizontal position of button
     * 
     * @param y Vertical position of button
     * 
     * @param processing Runs the graphical part of the program
     */
    public SaveButton(float x, float y, PApplet processing) {
        super(x, y, processing);
        label = "Save";
    }


    /*
     * When this button is clicked, it saves the room's info into a RoomData.ddd file
     * 
     * @param currentRoom The room information to save
     */
    @Override
    public void mouseDown(Furniture[] currentRoom) {
        // if this button was pressed
        if (isMouseOver()) {
            // initialize variables
            File outFile;
            PrintWriter saveRoom = null;
            try {
                // declare output variables
                outFile = new File("RoomData.ddd"); // could throw exception
                saveRoom = new PrintWriter(outFile); // could throw exception

                // write each room object into the output file
                for (Furniture f : currentRoom) {
                    if (f != null)
                        saveRoom.println(f.toString());
                }

                // close the output writer (flushes it)
                saveRoom.close();
            } catch (FileNotFoundException f) {
                System.out.println("WARNING: Could not save room contents to file RoomData.ddd.");
            }
        }
    }
}

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
import java.util.Scanner;

/*
 * LoadButton class for CS300 project "Dorm Designer 5000". A button that can load a room from a
 * file
 * 
 * @author Christian Henke
 */
public class LoadButton extends Button implements DormGUI {

    // initialize constant name of file to be loaded from
    private static final String FILE_NAME = "RoomData.ddd";

    /*
     * Constructor. A button that load's a room file into this room. Initializes variables
     * 
     * @param x Horizontal position of button
     * 
     * @param y Vertical position of button
     * 
     * @param processing Runs the graphical part of the program
     */
    public LoadButton(float x, float y, PApplet processing) {
        super(x, y, processing);
        label = "Load";
    }


    /*
     * Loads a file's room data into this application's room
     * 
     * @param room The reference to the application's room, so this method can modify it
     */
    @Override
    public void mouseDown(Furniture[] room) {
        // if this button was pressed
        if (isMouseOver()) {
            // initialize variables
            File inFile;
            File image;
            Scanner s;
            int furnitureCount = 0;
            try {
                // declare input variables
                inFile = new File(FILE_NAME); // could throw exception
                s = new Scanner(inFile); // could throw exception

                // read each line in the file
                while (s.hasNextLine()) {
                    // using the format, "read" the line's furniture info
                    String nextFurniture = s.nextLine();
                    nextFurniture = nextFurniture.trim();
                    if (!nextFurniture.isEmpty()) { // skip an empty line
                        try {
                            // check the furniture type (label)
                            String label = nextFurniture.split(":")[0].trim(); // could throw error
                                                                               // from incorrect
                                                                               // formatting

                            // check if there is such a furniture type
                            image = new File("images/" + label + ".png");
                            if (!image.exists())
                                System.out.println(
                                                "WARNING: Could not find an image for a furniture object of type: "
                                                                + label);
                            else {
                                // check the furniture position and rotation
                                // could throw error from incorrect formatting
                                float[] position = new float[2];
                                position[0] = Float.parseFloat(
                                                nextFurniture.split(":")[1].split(",")[0].trim());
                                position[1] = Float.parseFloat(
                                                nextFurniture.split(":")[1].split(",")[1].trim());
                                int rotation = Integer.parseInt(
                                                nextFurniture.split(":")[1].split(",")[2].trim());

                                // warn if the room cannot hold more furniture
                                if (furnitureCount >= room.length) {
                                    System.out.println("WARNING: Unable to load more furniture.");
                                    break; // stop reading the file
                                }

                                // add the specified furniture object to our room
                                room[furnitureCount] = new Furniture(label, position, rotation,
                                                processing);

                                furnitureCount++;
                            }
                        } catch (Exception e) {
                            System.out.println("WARNING: Found incorrectly formatted line in file: "
                                            + nextFurniture);
                        }
                    }
                }

                // clear the rest of the room
                for (; furnitureCount < room.length; furnitureCount++)
                    room[furnitureCount] = null;

            } catch (FileNotFoundException e) {
                System.out.println("WARNING: Could not load room contents from file " + FILE_NAME
                                + ".");
            }
        }
    }
}

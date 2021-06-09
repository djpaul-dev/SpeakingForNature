import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Subclass of PApplet. Contains the functions to show what is being shown to the user.
 * 
 * @author Dhruba Jyoti Paul
 */
public class NatureGame extends PApplet {
  private PImage backgroundImage;
  protected ArrayList<GUIListener> objects;
  private PImage[] kettle = new PImage[6];
  private int currentFrame;
  public static ArrayList<String> quotes = new ArrayList<>();
  public static ArrayList<String> writers = new ArrayList<>();
  public static int currentQuestion;
  public static boolean isAnswered;
  public static boolean isCorrect;
  public static String MESSAGE = "";
  public static int SCORE = 1;
  public static boolean skipOn = false;
  public static boolean restart = false;

  /**
   * Defines the initial environment properties such as screen size and to load background images
   * and fonts as the program starts. It also initializes the backgroundImage, the data fields, and
   * sets the display window for the different graphic objects which will be drawn in this
   * application
   */
  @Override
  public void setup() {
    
    loadQuoteData(); // Storing data into the quote and writers arraylists
    natureGameSettings(); // define the graphic settings of this application

    // create the list of objects
    objects = new ArrayList<GUIListener>();

    // load the background image
    backgroundImage = this.loadImage("images" + File.separator + "background.png");

    // Sets this CarrotPatch as display window for all the graphic Objects (Button, Animal, and
    // Carrots)
    Button.setProcessing(this);
    Quote.setProcessing(this);
    Tree.setProcessing(this);

    // Add Tree
    Tree tree = new Tree(225, 375, "images" + File.separator + "tree1.png");
    objects.add(tree);
    
    // Add Animated Kettle (for watering the plant)
    for (int i = 0; i < kettle.length; i++) {
      kettle[i]= loadImage("images" + File.separator + "wateringAnimation" + (i + 1) + ".png");
    }
    
    currentFrame = kettle.length;
    
    // Add buttons to the display window
    SkipButton skip = new SkipButton(25, 582);
    skip.setWidthAndHeight(45, 30);
    objects.add(skip);
    RestartButton restart = new RestartButton(770, 582);
    restart.setWidthAndHeight(55, 25);
    objects.add(restart);
    
    currentQuestion = nextQuestion();
  }
  
  /**
   * Sets the display window title, text alignment, image mode, rectangle mode, and activates this
   * PApplet object to listen to the mouse events and user inputs
   */
  private void natureGameSettings() {
    this.getSurface().setTitle("Nature Game"); // Displays text in the title of the display window
    this.textAlign(PApplet.CENTER, PApplet.CENTER); // Sets the current alignment for drawing text
                                                    // to CENTER
    this.imageMode(PApplet.CENTER); // Sets the location from which images are drawn to CENTER
    this.rectMode(PApplet.CORNERS); // Sets the location from which rectangles are drawn.
    // rectMode(CORNERS) interprets the first two parameters of rect() method as the location of one
    // corner, and the third and fourth parameters as the location of the opposite corner.
    // rect() method draws a rectangle to the display window
    this.focused = true; // Confirms that our Processing program is "focused," meaning that it is
                         // active and will accept mouse or keyboard input.
  }


  /**
   * Sets the size of the application display window
   */
  @Override
  public void settings() {
    size(800, 600); // sets the size of the display window to 800 x 600 pixels
  }

  /**
   * Callback method called in an infinite loop. It draws the Jungle Park's window display
   */
  @Override
  public void draw() {
    
    // draw the background image at the center of the display window
    this.image(backgroundImage, this.width / 2, this.height / 2);
    
    // Refreshing (checking if answered)
    refresh();
         
    // traverse the objects list and draw all the interactive objects
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i) instanceof SkipButton || objects.get(i) instanceof RestartButton) {
        textFont(createFont("Arial", 15));
      } else {
        textFont(createFont("Arial Bold", 20));
      }
      objects.get(i).draw();
    }
    
    // Writing the Title! :D
    textFont(createFont("Arial Bold", 30));
    fill(255);
    text("Choose the correct answer to water the plant! :D", 400, 50); 
    textFont(createFont("Arial Bold", 15));
    text("Who said this?", 600, 90); 

    // Writing FeedBack Message
    textFont(createFont("Arial Bold", 20));
    fill(255);
    text(MESSAGE, 225, 530); 
    
    // Drawing Kettle
    if (currentFrame < kettle.length) {
      image(kettle[currentFrame], 100, 350);
      // currentFrame = (currentFrame + 1) % kettle.length;
      currentFrame = (currentFrame + 1);
      try {
          Thread.sleep(500);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      
      // Draw Tree (bigger)
      if (currentFrame == kettle.length) {
        growTree();
      }
    }

  }

  /**
   * Callback method called each time the mouse is pressed
   */
  @Override
  public void mousePressed() {
    // traversing the objects list and call mousePressed() method defined for every interactive
    // object
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).isMouseOver()) {
        objects.get(i).mousePressed();
        break;
      }
    }

  }

  /**
   * Callback method called each time the mouse is released
   */
  @Override
  public void mouseReleased() {
    // traversing the objects list and call mouseReleased() method defined for every interactive
    // object
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).mouseReleased();
    }

  }

  /**
   * Callback method called each time the user presses a key
   */
  @Override
  public void keyPressed() {

    switch (Character.toUpperCase(this.key)) {
      case 'R':
        // Add a new Rabbit to this CarrotPath's objects list
//        objects.add(new Rabbit()); TODO
        waterPlant();
        break;
      case 'W':
        // Add a new Wolf to this CarrotPath's objects list
//        objects.add(new Wolf()); TODO

        break;
      case 'D':
        // Remove an animal from this carrot patch if the mouse is over it
        // Traverse the objects list and consider only animal objects in your search
        // Only one animal will be removed. If the mouse is over more than one animal
        // (overlapping animals) when the key-D is pressed, the first one in the objects
        // list (stored at the lowest index) will be removed.
        for (int i = 0; i < objects.size(); i++) {
//          if (objects.get(i).isMouseOver() && objects.get(i) instanceof Tree) {
//            objects.remove(i);
//            break;
//          }
        }
    }

  }
  
  private void loadQuoteData() {
    try {
      Scanner scnr = new Scanner(new File("Quotes.txt"));
      while (scnr.hasNextLine()) {
        String line = "";
        String next = "";
        while (scnr.hasNextLine()) {
          next = scnr.nextLine();
          if (next.equals("")) {
            break;
          }
          line += next;
        }
        writers.add(line);
        line = "";
        while (scnr.hasNextLine()) {
          next = scnr.nextLine();
          if (next.equals("")) {
            break;
          }
          line += next + "\n";
        }
        quotes.add(line);
      }
      
      for (int i = 0; i < quotes.size(); i++) {
        quotes.set(i, quotes.get(i).trim());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Triggers the water kettle animation.
   */
  public void waterPlant() {
    currentFrame = 0;
    playSound("sounds/wateringPlantSound.wav");
  }
  
  public void wrongAnswerEffect() {
    playSound("sounds/wrongSound.wav");
  }
  
  public void growTree() {
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i) instanceof Tree) {
        objects.remove(i);
        i--;
      }
    }
    
    if (SCORE < 5) {
      SCORE++;
    }
        
    switch (SCORE) {
      case 1:
        Tree tree = new Tree(225, 375, "images" + File.separator + "tree1.png");
        objects.add(tree);
        break;
      case 2:
        Tree tree2 = new Tree(220, 415, "images" + File.separator + "tree2.png");
        objects.add(tree2);
        break;
      case 3:
        Tree tree3 = new Tree(225, 392, "images" + File.separator + "tree3.png");
        objects.add(tree3);
        break;
      case 4:
        Tree tree4 = new Tree(225, 340, "images" + File.separator + "tree4.png");
        objects.add(tree4);
        break;
      case 5:
        Tree tree5 = new Tree(240, 305, "images" + File.separator + "tree5.png");
        objects.add(tree5);
        break;
    }
  }

  
  public int nextQuestion() {
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i) instanceof OptionButton || objects.get(i) instanceof Quote) {
        objects.remove(i);
        i--;
      }
    }
    
    Random random = new Random();
    // generate random number from 0 to 3
    int num = random.nextInt(quotes.size());
    
    objects.add(new Quote(quotes.get(num)));
    
    String wrongAnswer = writers.get(random.nextInt(writers.size()));
    while (wrongAnswer.equals(writers.get(num))) {
      wrongAnswer = writers.get(random.nextInt(writers.size()));
    }
    
    if (random.nextInt(2) == 0) {
      objects.add(new OptionButton(writers.get(num), 500, 500));
      objects.add(new OptionButton(wrongAnswer, 700, 500));
    } else {
      objects.add(new OptionButton(wrongAnswer, 500, 500));
      objects.add(new OptionButton(writers.get(num), 700, 500));
    }
    
    return num;
  }
  
  public void refresh() {
    if (isAnswered) {
      currentQuestion = nextQuestion();
      if (isCorrect) {
        waterPlant();
      } else {
        wrongAnswerEffect();
      }
      isAnswered = false;
    }
    
    if (skipOn) {
      currentQuestion = nextQuestion();
      skipOn = false;
    }  
    
    if (restart) {
      NatureGame.SCORE = 1;
      NatureGame.MESSAGE = "";
      
      for (int i = 0; i < objects.size(); i++) {
        if (objects.get(i) instanceof Tree) {
          objects.remove(i);
          i--;
        }
      }
      
      Tree tree = new Tree(225, 375, "images" + File.separator + "tree1.png");
      objects.add(tree);
      
      restart = false;
    }  
  }
  
  public static void updateMessage() {
      if (isCorrect) {
        MESSAGE = "Correct! Tree says \"Thank You! :-)\"";
        if (SCORE >= 4) {
          MESSAGE = "Correct! Tree says \"Thank You! :-)\"\nCONGRATULATIONS!\nYou have a healthy plant! :D";
          playSound("sounds/winSound.wav");
        }
      } else {
        MESSAGE = "Incorrect! Try Again! ;-;";
        if (SCORE >= 5) {
          MESSAGE = "Correct! Tree says \"Thank You! :-)\"\nCONGRATULATIONS!\nYou have a healthy plant! :D";
          playSound("sounds/winSound.wav");
        }
      }
  }
  
  /**
   * This method plays a sound file.
   * <p>
   * Many sound files are available on the web or you can make your
   * own in the .wav format. Some are free for personal use.
   * http://www.animal-sounds.org/farm-animal-sounds.html
   * http://soundbible.com/
   * https://www.freesoundeffects.com/free-sounds/wind-sounds-10041/
   * <p>
   * The call:
   * playSound("sounds/wumpus.wav");
   * means there is a sounds folder within the IDE project folder.
   * Within that folder there is a sound file named wumpus.wav.
   * Play the "sounds/wumpus.wav" sound file.
   * <p>
   * Oracle's Java Tutorials can be helpful in learning much more about a topic:
   * https://docs.oracle.com/javase/tutorial/sound/index.html
   *
   * @param wavFileName Name of the .wav file to play.
   */
  public static void playSound(String wavFileName) {
      File file = new File(wavFileName);
      if (file.exists()) {
          try {
              AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
              Clip clip = AudioSystem.getClip();
              clip.open(audioInputStream);
              clip.start();
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
  }
  
  /**
   * Results in a program that opens a small window (as a PApplet client) when it is run
   * 
   * @param args not used
   */
  public static void main(String[] args) {
    PApplet.main("NatureGame"); // do not add any other statement to the main method
    // The PApplet.main() method takes a String input parameter which represents
    // the name of your PApplet class.
  }
}

import processing.core.PImage;

/**
 * Contains all the characteristics and properties of an animal in general for the purposes of the
 * P04 Carrot Patch project. Implements the GUIListener interface.
 * 
 * @author Dhruba
 */
public class Tree implements GUIListener {
  protected static NatureGame processing; // PApplet object representing the display window
  protected PImage image; // image of this
  private int x; // x-position of this animal in the display window
  private int y; // y-position of this animal in the display window

  /**
   * Creates a new Animal object positioned at a given position of the display window
   * 
   * @param processing    PApplet object that represents the display window
   * @param x             x-position of the image of this animal in the display window
   * @param y             y-position of the image of this animal in the display window
   * @param imageFileName filename of the animal image
   */
  public Tree(int x, int y, String imageFileName) {

    // Set Animal drawing parameters
    this.x = x; // sets the x-position of this animal
    this.y = y; // sets the y-position of this animal
    this.image = processing.loadImage(imageFileName);
  }

  /**
   * Sets the CarrotPatch PApplet object where this animal is drawn and operates
   * 
   * @param processing processing to set
   */
  public static void setProcessing(NatureGame processing) {
    Tree.processing = processing;
  }

  /**
   * Draws the animal to the display window. This animal must follow the mouse moves if it is being
   * dragged (i.e. if its isDragging field is set to true).
   */
  @Override
  public void draw() {
    // draw this animal at its current position
    processing.image(this.image, x, y);
  }

  /**
   * Returns the image of this animal
   * 
   * @return the image of type PImage of the tiger object
   */
  public PImage getImage() {
    return image;
  }

  @Override
  public void mousePressed() {
  }

  @Override
  public void mouseReleased() {
  }

  @Override
  public boolean isMouseOver() {
    return false;
  }
}

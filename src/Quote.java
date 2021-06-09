/**
 * This class implements a Button in the Nature Game graphic application
 * 
 * @author mouna
 * @author Dhruba
 *
 */
public class Quote implements GUIListener {
  protected static NatureGame processing; // PApplet object where this button will be displayed

  public static String quote; // text/label that represents the name of this button

  /**
   * Creates a new quote at a given position within a pApplet object
   * 
   * @param label      label to be assigned to this button
   * @param x          x-position where this button will be added to the display window
   * @param y          y-position where this button will be added to the display window
   * @param processing a reference to a PApplet object
   */
  public Quote(String quote) {
    Quote.quote = quote;
  }

  /**
   * Sets the PApplet display window where this button is displayed and handled
   * 
   * @param processing the processing to set
   */
  public static void setProcessing(NatureGame processing) {
    Quote.processing = processing;
  }


  /**
   * Draws this button to the display window
   */
  @Override
  public void draw() {
    processing.fill(255); // set the fill color to white
    processing.text(quote, 420, 100, 780, 400); // display the text of the current quote
  }

  /**
   * Implements the default behavior of this button when the mouse is pressed. This method must be
   * overridden in the sub-classes to implement a specific behavior if needed.
   */
  @Override
  public void mousePressed() {
  }

  /**
   * Implements the default behavior of this button when the mouse is released. This method must be
   * overridden in the sub-classes to implement a specific behavior if needed.
   */
  @Override
  public void mouseReleased() {
  }


  /**
   * Checks whether the mouse is over this button
   * 
   * @return true if the mouse is over this button, false otherwise.
   */
  @Override
  public boolean isMouseOver() {
    return false;
  }
}

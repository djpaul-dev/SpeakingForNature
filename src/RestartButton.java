/**
 * Subclass of the button class. Represents the button to add a rabbit to the Carrot Patch.
 * 
 * @author Dhruba
 */
public class RestartButton extends Button {
  /**
   * Creates a new Button at a given position within a pApplet object with the label, "Add Rabbit"
   * 
   * @param xPosition where this button will be added to the display window
   * @param yPosition where this button will be added to the display window
   */
  public RestartButton(float xPosition, float yPosition) {
    super("Restart", xPosition, yPosition);
  }

  /**
   * Adds a new rabbit in the carrot patch when the mouse is pressed on this button.
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      NatureGame.restart = true;
    }
  }

}

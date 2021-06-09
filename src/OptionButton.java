/**
 * Subclass of the button class. Represents the button to add a rabbit to the Carrot Patch.
 * 
 * @author Dhruba
 */
public class OptionButton extends Button {
  
  private String label;
  
  /**
   * Creates a new Button at a given position within a pApplet object with the label, "Add Rabbit"
   * 
   * @param xPosition where this button will be added to the display window
   * @param yPosition where this button will be added to the display window
   */
  public OptionButton(String label, float xPosition, float yPosition) {
    super(label, xPosition, yPosition);
    this.label = label;
  }

  /**
   * Adds a new rabbit in the carrot patch when the mouse is pressed on this button.
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      NatureGame.isCorrect = isCorrect();
      NatureGame.isAnswered = true;
      
      // Refreshing (checking if correct)
      NatureGame.updateMessage();

    }
  }
  
  private boolean isCorrect() {
    if (label.equals(NatureGame.writers.get(NatureGame.currentQuestion))) {
      NatureGame.writers.remove(NatureGame.currentQuestion);
      NatureGame.quotes.remove(NatureGame.currentQuestion);
      return true;
    } else {
      return false;
    }

  }
}

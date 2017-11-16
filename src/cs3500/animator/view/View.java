package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.Action;
import cs3500.animator.EasyShape;

public interface View {
  /**
   * Draws the shapes.
   * @param shapes the shapes to be drawn.
   * @param actions the actions to be drawn.
   */
   void run(ArrayList<EasyShape> shapes, ArrayList<Action> actions);

  /**
   * Returns a text representation of the shapes and actions.
   * @param shapes the shapes to be converted to text.
   * @param actions the actions to be converted to text.
   * @param rate the rate of the animation.
   * @return the text representation of the shapes and actions.
   */
   String getText(ArrayList<EasyShape> shapes, ArrayList<Action> actions, float rate);
}

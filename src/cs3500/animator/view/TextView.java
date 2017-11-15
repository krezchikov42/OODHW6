package cs3500.animator.view;

import java.util.Comparator;
import java.util.List;

import cs3500.animator.Action;
import cs3500.animator.EasyShape;
import cs3500.animator.model.EasyAnimatorOperations;

/**
 * Represents a view that gives a text representation of an animation.
 */
public class TextView extends AView {

  /**
   * Makes a new TextView.
   *
   * @param model is the model the view will use.
   * @param rate  is the rate of ticks.
   */
  public TextView(EasyAnimatorOperations model, float rate) {
    super(model, rate);
  }


  @Override
  public String getText() {
    String ret = "";
    List<EasyShape> shapes = model.getShapes();
    for (EasyShape shape : shapes) {
      ret += shape.getText(rate);
    }
    ret += "\n";
    List<Action> actions = model.getActions();

    //yay java 8 stuff
    actions.sort(Comparator.comparing(Action::getStartTime));
    for (Action action : actions) {
      ret += action.getText(rate);
    }
    return ret;
  }
}

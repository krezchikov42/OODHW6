package cs3500.animator.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cs3500.animator.Action;
import cs3500.animator.EasyShape;
import cs3500.animator.model.EasyAnimatorOperations;

/**
 * Represents a view that gives a text representation of an animation.
 */
public class TextView extends AView {




  @Override
  public String getText(ArrayList<EasyShape> shapes, ArrayList<Action> actions) {
    String ret = "";
    for (EasyShape shape : shapes) {
      ret += shape.getText(rate);
    }
    ret += "\n";

    //yay java 8 stuff
    actions.sort(Comparator.comparing(Action::getStartTime));
    for (Action action : actions) {
      ret += action.getText(rate);
    }
    return ret;
  }
}

package cs3500.animator.view;

import java.util.List;

import cs3500.animator.Action;
import cs3500.animator.EasyShape;
import cs3500.animator.model.EasyAnimatorOperations;

/**
 * Represents a factory that will make a view.
 */
public class ViewFactory{


  /**
   * Constructs a view.
   *
   * @param s specifier for which type of view to create
   * @return the view
   */
  public View create(String s) {
    if (s.equals("text")) {
      return new TextView();
    } else if (s.equals("svg")) {
      //not done
      return new SVGView();
    } else if (s.equals("visual")) {
      //not done
      return new VisualView();
    }
    else if (s.equals("interaction")){
      return null;//new HybridView();
    }
    else {
      throw new IllegalArgumentException("Not Valid View for Factory");
    }

  }
}

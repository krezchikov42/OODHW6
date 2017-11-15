package cs3500.animator.view;

import cs3500.animator.model.EasyAnimatorOperations;

/**
 * Represents a factory that will make a view.
 */
public class ViewFactory extends AView {

  /**
   * Creates a ViewFactory.
   *
   * @param model is the model in the view.
   * @param rate  is the rate the model will be run at.
   */
  public ViewFactory(EasyAnimatorOperations model, float rate) {
    super(model, rate);
  }

  /**
   * Constructs a view.
   *
   * @param s specifier for which type of view to create
   * @return the view
   */
  public View create(String s) {
    if (s.equals("text")) {
      return new TextView(model, rate);
    } else if (s.equals("svg")) {
      //not done
      return new SVGView(model, rate);
    } else if (s.equals("visual")) {
      //not done
      return new VisualView(model, rate);
    } else {
      throw new IllegalArgumentException("Not Valid View for Factory");
    }

  }
}

package cs3500.animator.view;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.Action;
import cs3500.animator.EasyShape;
import cs3500.animator.model.EasyAnimatorOperations;

/**
 * Represents a SVG view of our animation.
 */
public class SVGView extends AView {



  /**
   * Returns a String containing an SVG representation of the animation.
   *
   * @return an svg representation of the animation.
   */
  public String getText(ArrayList<EasyShape> shapes, ArrayList<Action> actions) {
    String text = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n";

    for (EasyShape s : shapes) {
      text += s.getSVG(this.rate);
    }
    text += "\n</svg>";

    return text;
  }
}
package cs3500.animator.view;

import cs3500.animator.EasyShape;
import cs3500.animator.model.EasyAnimatorOperations;

public class SVGView extends AView {


  public SVGView(EasyAnimatorOperations model, float rate) {
    super(model, rate);
  }

  @Override
  public void run() {
    // this is not a visual view
  }

  /**
   * Returns a String containing an SVG representation of the animation.
   *
   * @return an svg representation of the animation.
   */
  public String getText() {
    String text = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n";

    for (EasyShape s : this.model.getShapes()) {
      text += s.getSVG(this.rate);
    }
    text += "\n</svg>";

    return text;
  }
}
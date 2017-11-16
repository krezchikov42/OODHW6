package cs3500.animator.view;

import cs3500.animator.Action;
import cs3500.animator.EasyShape;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HybridView extends AView {
  boolean window;


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


  @Override
  public void run(ArrayList<EasyShape> shapes, ArrayList<Action> actions) {
    int time = 0;
    JPanel animator = null;
    //makes sure the constructor only happens once
    if(!window) {
      JFrame frame = new JFrame();
      animator = new AnimationPanel(shapes);

      frame.setSize(1500, 1000);
      frame.setMinimumSize(new Dimension(1500, 1000));
      frame.add((JPanel) animator);
      frame.setVisible(true);
      frame.setLayout(new BorderLayout());
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window = true;
    }
    animator.validate();
    animator.repaint();
  }
}

package cs3500.animator.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;

import cs3500.animator.Action;
import cs3500.animator.EasyShape;
import cs3500.animator.model.EasyAnimatorOperations;

/**
 * Represents a view that gives a visual representation of shapes and actions.
 */
public class VisualView extends AView {

  //ensure that the window only pops up once
  boolean window;

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


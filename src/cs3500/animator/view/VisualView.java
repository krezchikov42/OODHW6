package cs3500.animator.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import java.util.List;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;

import cs3500.animator.Action;
import cs3500.animator.EasyShape;
import cs3500.animator.model.EasyAnimatorOperations;

/**
 * Represents a visual view of some shapes.
 */
public class VisualView extends AView {


  private boolean window;
  private JPanel animator;
  @Override
  public void run(List<EasyShape> shapes) {
    int time = 0;
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
      animator.revalidate();
      animator.repaint();
  }
}


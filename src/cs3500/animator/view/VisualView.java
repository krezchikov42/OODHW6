package cs3500.animator.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;


import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;
import cs3500.animator.model.EasyAnimatorOperations;

public class VisualView extends AView {



  VisualView(EasyAnimatorOperations model, float rate) {
    super(model, rate);
  }

  @Override
  public void run() {
    int time = 0;

    JFrame frame = new JFrame();
    ActionListener animator = new AnimationPanel(this.model);
    EasyAnimatorOperations m = this.model;

    frame.setSize(1500, 1000);
    frame.setMinimumSize(new Dimension(1500, 1000));
    frame.add((JPanel) animator);
    frame.setVisible(true);
    frame.setLayout(new BorderLayout());
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    double delay = 1000 / rate;

    Timer timer = new Timer((int) delay, animator);
    timer.setInitialDelay(0);
    timer.start();
    while (!this.model.animationOver()) {
      time++;
    }
  }
}


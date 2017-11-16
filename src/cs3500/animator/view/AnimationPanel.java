package cs3500.animator.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import cs3500.animator.EasyShape;
import cs3500.animator.model.EasyAnimatorOperations;

class AnimationPanel extends JPanel implements ActionListener {

  public EasyAnimatorOperations model;

  public AnimationPanel(EasyAnimatorOperations model) {
    setSize(800, 600);
    setVisible(true);
    this.model = model;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    modelToPaintFunctionCalls(g);
  }

  private void modelToPaintFunctionCalls(Graphics g) {
    for (EasyShape s : this.model.getShapes()) {
      s.draw(g);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.revalidate();
    this.repaint();
  }
}

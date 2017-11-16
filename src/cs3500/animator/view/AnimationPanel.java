package cs3500.animator.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.List;
import javax.swing.JPanel;

import cs3500.animator.EasyShape;
import cs3500.animator.model.EasyAnimatorOperations;

class AnimationPanel extends JPanel {

  List<EasyShape> shapes;

  public AnimationPanel(List<EasyShape> shapes) {
    this.shapes = shapes;

    setSize(800, 600);
    setVisible(true);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    modelToPaintFunctionCalls(g);
  }

  private void modelToPaintFunctionCalls(Graphics g) {
    for (EasyShape s : shapes  ) {
      s.draw(g);
    }
  }

  public void setShapes(List<EasyShape> shapes) {
    this.shapes = shapes;
  }

  /*@Override
  public void actionPerformed(ActionEvent e) {
    this.revalidate();
    this.repaint();
  }*/
}

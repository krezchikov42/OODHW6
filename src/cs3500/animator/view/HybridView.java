package cs3500.animator.view;

import static javafx.scene.paint.Color.*;

import cs3500.animator.Action;
import cs3500.animator.EasyShape;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

public class HybridView extends AView {

  private boolean window;
  private AnimationPanel animator = null;
  ActionListener listener = null;

  JTextField textField = null;
  public JCheckBox loopCheckBox = null;

  //return [shape.clone() for shape in shapes if shape not in shapeNames]

  /**
   * Returns a String containing an SVG representation of the animation.
   *
   * @return an svg representation of the animation.
   */
  @Override
  public String getText(List<EasyShape> shapes, List<Action> actions, float rate,int endTime) {
    String text = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n<rect>\n" +
            "   <animate id=\"base\" begin=\"0;base.end\" dur=\"" + endTime*rate+"ms\"" +
            " attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" +
            "</rect>";

    for (EasyShape s : shapes) {
      text += s.getSVG(rate,loopCheckBox.isSelected());
    }
    text += "\n</svg>";

    return text;
  }

  public void setListener(ActionListener l) {this.listener = l;}

  public String getTextFromTextField() {
    String t = this.textField.getText();
    this.textField.setText("");
    return t;
  }

  @Override
  public void run(List<EasyShape> shapes) {
    int time = 0;

    //makes sure the constructor only happens once
    if(!window) {
      JFrame frame = new JFrame();
      animator = new AnimationPanel(shapes);

      frame.setSize(1000, 700);
      frame.setMinimumSize(new Dimension(1000, 700));
      frame.add((JPanel) animator, BorderLayout.CENTER);
      // add other buttons and functionalities
      this.addButtons(frame);
      this.addSlider(frame);
      this.addShapeSelectionPanel(frame);

      frame.setVisible(true);
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window = true;
    }
    //animator.revalidate();
    animator.setShapes(shapes);
    animator.repaint();
  }

  private void addButtons(JFrame frame) {

    JPanel buttonsPanels = new JPanel();
    buttonsPanels.setBackground(Color.lightGray);

    JButton playButton = new JButton("Play/Resume");
    playButton.addActionListener(this.listener);
    JButton pauseButton = new JButton("Pause");
    pauseButton.addActionListener(this.listener);
    JButton restartButton = new JButton("Restart");
    restartButton.addActionListener(this.listener);

    buttonsPanels.add(pauseButton);
    buttonsPanels.add(playButton);
    buttonsPanels.add(restartButton);

    frame.add(buttonsPanels, BorderLayout.PAGE_END);
  }

  private void addSlider(JFrame frame) {

    JSlider ticksPerSecond = new JSlider(JSlider.HORIZONTAL,
        1, 50, 15);
    ticksPerSecond.addChangeListener((ChangeListener) this.listener);

    ticksPerSecond.setMajorTickSpacing(20);
    ticksPerSecond.setMinorTickSpacing(5);
    ticksPerSecond.setPaintTicks(true);
    ticksPerSecond.setPaintLabels(true);

    frame.add(ticksPerSecond, BorderLayout.PAGE_START);
  }

  private void addShapeSelectionPanel(JFrame frame) {

    // Label prompt
    JLabel prompt = new JLabel("Enter a shape name into the text field below");

    // The entire right-side panel
    JPanel selectionPanel = new JPanel();
    selectionPanel.setBackground(Color.lightGray);

    // the text field where shape names are inputted
    textField = new JTextField(20);

    // the button that sends the contents of text field to the controller
    JButton goButton = new JButton("add shape");
    goButton.addActionListener(this.listener);
    goButton.setBackground(Color.DARK_GRAY);

    // the button that resets all visibility to every shape
    JButton resetVisibilityButton = new JButton("reset visibility");
    resetVisibilityButton.addActionListener(this.listener);
    resetVisibilityButton.setBackground(Color.DARK_GRAY);

    // The Button to export to SVG format
    JButton SVGButton = new JButton("export to SVG");
    SVGButton.addActionListener(this.listener);
    SVGButton.setBackground(Color.DARK_GRAY);

    // The Looping checkbox
    loopCheckBox = new JCheckBox("Looping on:");
    loopCheckBox.addActionListener(this.listener);

    selectionPanel.setLayout(new GridLayout(6, 1));

    selectionPanel.add(prompt);
    selectionPanel.add(textField);
    selectionPanel.add(goButton);
    selectionPanel.add(resetVisibilityButton);
    selectionPanel.add(SVGButton);
    selectionPanel.add(loopCheckBox);

    frame.add(selectionPanel, BorderLayout.LINE_END);

  }
}

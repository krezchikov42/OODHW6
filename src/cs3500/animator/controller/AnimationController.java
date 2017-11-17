package cs3500.animator.controller;

import com.sun.xml.internal.ws.addressing.model.ActionNotSupportedException;
import cs3500.animator.EasyAnimator;
import cs3500.animator.EasyShape;
import cs3500.animator.model.EasyAnimatorModel;
import cs3500.animator.model.EasyAnimatorOperations;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.View;
import cs3500.animator.view.ViewFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AnimationController implements Controller, ActionListener, ChangeListener {

  private EasyAnimatorOperations model;
  private List<EasyShape> initialModelShapes;
  private View view;
  private Timer timer;

  private int currentTime;
  private float rate;
  private boolean running;

  public AnimationController(EasyAnimatorOperations model, View view, float rate) {
    this.model = model;
    this.initialModelShapes = model.getShapesCopy();
    this.view = view;

    this.currentTime = 0;
    this.rate = rate;
    this.running = false;
  }

  public String getTextFromTextualView() {
    return this.view.getText(this.model.getShapes(),
        this.model.getActions(), this.rate);
  }

  public void runViewWithVisualComponent() {
    this.running = true;

    timer = new javax.swing.Timer((int) (1000.0f / this.rate), new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (running) {
          model.updateAnimation(currentTime);
          view.run(model.getShapes());

          currentTime++;
        }
      }
    });
    timer.start();
  }

  private void pause() {
    this.running = false;
  }

  private void resume() {
    this.running = true;
  }

  private void rewindToStart() {
    this.pause();
    this.currentTime = 0;
    view.run(initialModelShapes);
    this.model.setShapes(initialModelShapes);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    switch (command) {
      case "Play/Resume": resume(); break;
      case "Pause": pause(); break;
      case "Restart": rewindToStart(); break;
      default: return;
    }
  }

  // pops up a Jpanel and ends the propgram.
  private static void endGame() {
    JOptionPane.showMessageDialog(null, "Incorrect command");
    System.exit(0);
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    int ticksPerSecond = (int)source.getValue();
    this.rate = (float) ticksPerSecond;
    timer.setDelay((int) (1000.0f / rate));
  }
}

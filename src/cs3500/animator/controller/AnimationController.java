package cs3500.animator.controller;

import cs3500.animator.EasyAnimator;
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
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.JOptionPane;

public class AnimationController implements Controller {

  private EasyAnimatorOperations model;
  private View view;

  private int currentTime;
  private float rate;
  private boolean running;

  public AnimationController(EasyAnimatorOperations model, View view, float rate) {
    this.model = model;
    this.view = view;

    this.currentTime = 0;
    this.rate = rate;
    this.running = running;
  }

  public String getTextFromTextualView() {
    return this.view.getText(this.model.getShapes(),
        this.model.getActions(), this.rate);
  }

  public void runViewWithVisualComponent() {

    this.running = true;

    new javax.swing.Timer((int) (1000.0f / this.rate), new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        if (running) {
          model.updateAnimation(currentTime);
          //System.out.print(String.format("no. actions: %d\n",model.getActions().size()));
          view.run(model.getShapes());
          //System.out.print(model.getShapes().get(0).getPostition().getY());
          //System.out.print("\ncurrent time:");System.out.print(currentTime);
          //System.out.println();
          currentTime++;
        }
      }
    }).start();
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
  }


  // pops up a Jpanel and ends the propgram.
  private static void endGame() {
    JOptionPane.showMessageDialog(null, "Incorrect command");
    System.exit(0);
  }
}

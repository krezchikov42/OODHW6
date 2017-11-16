package cs3500.animator.controller;

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

public class Controller {

  private int currentTime;
  private float rate;
  private boolean running;

  public Controller(float rate) {
    this.currentTime = 0;
    this.rate = rate;
    this.running = running;
  }

  private void runViewWithVisualComponent(EasyAnimatorOperations model, View view, float rate) {

    this.running = true;

    new javax.swing.Timer((int) (1000.0f / rate), new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        if (running) {
          model.updateAnimation(currentTime);
          view.run(model.getShapes());

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

  private void restart() {
    this.pause();
    this.currentTime = 0;
  }


  // pops up a Jpanel and ends the propgram.
  private static void endGame() {
    JOptionPane.showMessageDialog(null, "Incorrect command");
    System.exit(0);
  }
}

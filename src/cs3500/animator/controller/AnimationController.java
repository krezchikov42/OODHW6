package cs3500.animator.controller;

import com.sun.xml.internal.ws.addressing.model.ActionNotSupportedException;
import cs3500.animator.Action;
import cs3500.animator.EasyAnimator;
import cs3500.animator.EasyShape;
import cs3500.animator.model.EasyAnimatorModel;
import cs3500.animator.model.EasyAnimatorOperations;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.View;
import cs3500.animator.view.ViewFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This is the controller that puts together a model and a view.
 */
public class AnimationController implements Controller, ActionListener, ChangeListener {

  private EasyAnimatorOperations model;
  private List<EasyShape> initialModelShapes;
  private View view;
  private Timer timer;
  private List<String> shapeNames;
  private boolean loop;

  private int currentTime;
  private float rate;
  private boolean running;

  /**
   * Makes an Animation Controller.
   * @param model the model for the controller.
   * @param view the view for the controller.
   * @param rate the rate of the animation.
   */
  public AnimationController(EasyAnimatorOperations model, View view, float rate) {
    this.model = model;
    this.initialModelShapes = model.getShapesCopy();
    this.view = view;
    shapeNames = new ArrayList<>();

    this.currentTime = 0;
    this.rate = rate;
    this.running = false;
    this.loop = false;
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

          view.run(makeInvisble(model.getShapes()));

          //makes the time loop or not
          if(loop && currentTime==model.getEndTime()){
            currentTime = 0;
          }
          else {
            currentTime++;
          }
        }
      }
    });
    timer.start();

    while(true){

    }
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
    // Make new clone


    setActionShapeLinks(initialModelShapes);
    view.run(initialModelShapes);
    this.model.setShapes(initialModelShapes);

    List<EasyShape> copy = new ArrayList<EasyShape>(); // = new ArrayList<>(initialModelShapes);
    for (EasyShape s: initialModelShapes) {
      copy.add(s.clone());
    }
    //copy.forEach(EasyShape::clone);
    System.out.println(String.format("ShapePos: %s\n", copy.get(0).getPostition()));

    this.initialModelShapes = copy;
  }

  private void setActionShapeLinks(List<EasyShape> shapes) {
    for (EasyShape s: shapes) {
      for (Action a: s.getActions()) {
        a.setShape(s);
      }
    }
  }

  private void addToHiddenList() {
    this.shapeNames.add(((HybridView) this.view).getTextFromTextField());
  }


  private void exportToSVG() {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.loop = ((HybridView) this.view).loopCheckBox.isSelected();

    String command = e.getActionCommand();
    switch (command) {
      case "Play/Resume": resume(); break;
      case "Pause": pause(); break;
      case "Restart": rewindToStart(); break;
      case "add shape": addToHiddenList(); break;
      case "reset visibility": makeVisible(); break;
      case "export to SVG": exportToSVG(); break;
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

  //makes shapes invisible that the user has selected by removing them from the list
  private List<EasyShape> makeInvisble(List<EasyShape> shapes){
    List<EasyShape> ret = new ArrayList<>();
    for(EasyShape shape: shapes){
      ret.add(shape.clone());
    }

    ret.removeIf(easyShape ->
    {
      boolean match = false;
      for(String s: shapeNames){
        match |= easyShape.getName().equals(s);
      }
      return match;
    });

    return ret;
  }

  //makes all the shapes in the animation visible
  private void makeVisible(){
    shapeNames = new ArrayList<>();
  }
}

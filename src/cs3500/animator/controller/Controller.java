package cs3500.animator.controller;

import cs3500.animator.model.EasyAnimatorModel;
import cs3500.animator.model.EasyAnimatorOperations;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.View;
import cs3500.animator.view.ViewFactory;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Controller {

  private int time;
  private boolean running;

  /**
   * Method that runs the class.
   *
   * @param args the command line arguments that will be used in the model.
   */
  public static void main(String[] args) {
    String view = null;
    float rate = 1;
    EasyAnimatorOperations model = null;
    boolean ifhap = false;
    boolean ivhap = false;
    // boolean print = false;
    String output = "out";
    for (int i = 0; i < args.length; i++) {
      String id = args[i];
      if (id.equals("-if")) {
        //open file and use it as the input
        try {
          model = (new AnimationFileReader()).readFile(args[i + 1],
              new EasyAnimatorModel.Builder());
          ifhap = true;
        } catch (FileNotFoundException e) {
          throw new IllegalArgumentException("File does not exist to read from");
        }
      } else if (id.equals("-iv")) {
        view = args[i + 1];
        ivhap = true;
      } else if (id.equals("-o")) {
        //send output to a file
        output = args[i + 1];
        //     print = true;
      } else if (id.equals("-speed")) {
        rate = Float.valueOf(args[i + 1]);
      } else {
        endGame();
      }
      i++;
    }
    if (ifhap && ivhap) {

      View view1 = (new ViewFactory(model, rate)).create(view);
      if (view.equals("visual")) {
        runHybridView(model, view1);
      } else {

        if (output.equals("out")) {
          System.out.print(view1.getText());
        } else {
          try {
            FileWriter write = new FileWriter(output, true);
            PrintWriter print = new PrintWriter(write);
            print.print(view1.getText());
            print.close();
          } catch (IOException e) {
            throw new IllegalArgumentException("Can't write to file");
          }
        }

      }
    } else {
      throw new IllegalArgumentException("No view or input file selected");
    }
  }

  private static void runViewWithVisualComponent(EasyAnimatorOperations model, View view) {

  }

  public void pause() {

  }

  public void resume() {

  }

  public void start() {

  }


  // pops up a Jpanel and ends the propgram.
  private static void endGame() {
    JOptionPane.showMessageDialog(null, "Incorrect command");
    System.exit(0);
  }
}

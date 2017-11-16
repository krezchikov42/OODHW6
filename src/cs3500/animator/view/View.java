package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.Action;
import cs3500.animator.EasyShape;

public interface View {

  public void run(ArrayList<EasyShape> shapes, ArrayList<Action> actions);

  public String getText(ArrayList<EasyShape> shapes, ArrayList<Action> actions, float rate);
}

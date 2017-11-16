package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.Action;
import cs3500.animator.EasyShape;
import java.util.List;

public interface View {

  public void run(List<EasyShape> shapes);

  public String getText(ArrayList<EasyShape> shapes, ArrayList<Action> actions, float rate);
}

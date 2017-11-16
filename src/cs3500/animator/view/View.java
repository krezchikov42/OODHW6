package cs3500.animator.view;

import cs3500.animator.Action;
import cs3500.animator.EasyShape;
import java.util.List;

public interface View {

  public void run(List<EasyShape> shapes);

  public String getText(List<EasyShape> shapes, List<Action> actions, float rate);
}

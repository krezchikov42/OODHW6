package cs3500.animator.view;

import cs3500.animator.model.EasyAnimatorOperations;

/**
 * The abstract class for views.
 */
public abstract class AView implements View {
  EasyAnimatorOperations model;
  protected float rate;
  protected int current;

  /**
   * Creates and AView object.
   * @param model that the view has.
   * @param rate speed of view.
   */
  protected AView(EasyAnimatorOperations model, float rate) {
    this.model = model;
    this.rate = rate;
    current = 0;
  }

  @Override
  public String getText() {
    return null;
  }

  @Override
  public void run() {
  //should do nothing for most views
  }
}

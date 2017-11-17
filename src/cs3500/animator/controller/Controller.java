package cs3500.animator.controller;

import cs3500.animator.model.EasyAnimatorOperations;
import cs3500.animator.view.View;

/**
 * Interface for Controller.
 */
public interface Controller {
  /**
   * Runs a visual view.
   */
  void runViewWithVisualComponent();

  /**
   * Runs a text view.
   * @return the text representation of an animation.
   */
  String getTextFromTextualView();
}

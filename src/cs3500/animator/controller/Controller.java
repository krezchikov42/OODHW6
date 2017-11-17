package cs3500.animator.controller;

import cs3500.animator.model.EasyAnimatorOperations;
import cs3500.animator.view.View;

public interface Controller {

  void runViewWithVisualComponent();

  String getTextFromTextualView();
}

import org.junit.Test;

import java.util.ArrayList;

import cs3500.animator.Action;
import cs3500.animator.Color;
import cs3500.animator.EasyShape;
import cs3500.animator.EasyShape.PinHole;
import cs3500.animator.MoveAction;
import cs3500.animator.Oval;
import cs3500.animator.Point;
import cs3500.animator.Rectangle;
import cs3500.animator.ScaleAction;
import cs3500.animator.model.EasyAnimatorModel;

import static org.junit.Assert.assertEquals;

/**
 * Tests tbe model.
 */
public class EasyAnimatorModelTest {

  ArrayList<Action> emptyActions = new ArrayList<Action>();
  ArrayList<EasyShape> emptyShapes = new ArrayList<EasyShape>();

  /*
  // Ensures that calling isAnimationOver on a model returns true when it's over
  @Test
  public void testAnimationOverActuallyOver() {
    EasyAnimatorModel m = new EasyAnimatorModel();

    assertEquals(m.animationOver(), true);
  }*/

  /*
  // Ensures that calling isAnimationOver on a model returns false when it's not over
  @Test
  public void testAnimationOverNotOverYet() {
    EasyAnimatorModel m = new EasyAnimatorModel(10, new ArrayList<Action>(emptyActions),
        new ArrayList<EasyShape>(emptyShapes));

    assertEquals(m.animationOver(), false);
  }*/

  // Ensures that calling a model's updateAnimation method twice with a move action and a shape
  // loaded into it will actually move the shape twice
  @Test
  public void testMoveSquareRightTwiceThroughModel() {
    EasyAnimatorModel m = new EasyAnimatorModel();

    Rectangle rect = new Rectangle(50, 50, new Point(0, 0), PinHole.Top,
            "R", 0, 10, new Color(1.0f, 0.0f, 0.0f));

    m.addShape(rect);

    MoveAction a = new MoveAction(rect, new Point(0, 0), new Point(2, 0),
            0, 2);
    m.addAction(a);

    m.updateAnimation();
    m.updateAnimation();

    assertEquals(rect.getPostition().getX(), 2);
  }

  // Ensures that the Rectangle class has an accurate toString method
  @Test
  public void testRectangleToString() {
    Rectangle rect = new Rectangle(50, 50, new Point(0, 0), PinHole.Top,
            "R", 0, 10, new Color(1.0f, 0.0f, 0.0f));

    String correct = "Name: R\n"
            + "Type: rectangle\n"
            + "top: (200.0,200.0), Width: 50.000000, Height: 50.000000,"
            + " Color: java.awt.Color[r=255,g=0,b=0]\n"
            + "Appears at t=0\n"
            + "Disappears at t=10";

    assertEquals(correct, rect.toString());
  }

  // Ensures that the Oval class has an accurate toString method
  @Test
  public void testOvalToString() {
    Oval o = new Oval(20, 20, new Point(5, 5), PinHole.Bottom, "O",
            0, 10, new Color(0.0f, 1.0f, 0.0f));

    String correct = "Name: O\n"
            + "Type: oval\n"
            + "bottom: (200.0,200.0), X radius: 20.000000, Y radius: 20.000000, "
            + "Color: java.awt.Color[r=0,g=255,b=0]\n"
            + "Appears at t=0\n"
            + "Disappears at t=10";

    assertEquals(correct, o.toString());
  }

  // Ensures that an action accurately knows if it should be currently run or not
  @Test
  public void testActionIsCurrentActuallyCurrent() {
    Rectangle rect = new Rectangle(50, 50, new Point(0, 0), PinHole.Top,
            "R", 0, 10, new Color(1.0f, 0.0f, 0.0f));

    MoveAction a = new MoveAction(rect, new Point(0, 0), new Point(2, 0), 0, 2);

    assertEquals(true, a.isCurrent(1));
  }

  // Ensures that an action accurately knows if it should be currently run or not
  @Test
  public void testActionIsCurrentNotActuallyCurrent() {
    Rectangle rect = new Rectangle(50, 50, new Point(0, 0), PinHole.Top,
            "R", 0, 10, new Color(1.0f, 0.0f, 0.0f));

    MoveAction a = new MoveAction(rect, new Point(0, 0), new Point(2, 0), 5, 10);

    assertEquals(false, a.isCurrent(1));
  }

  // Ensures that calling the applyToShape method on a scale action indeed scales a shape
  @Test
  public void testScaleRectangleWithoutModel() {
    Rectangle rect = new Rectangle(50, 50, new Point(0, 0), PinHole.Top,
            "R", 0, 10, new Color(1.0f, 0.0f, 0.0f));

    ScaleAction a = new ScaleAction(rect, 50, 50, 2, 2, 0, 2);

    a.applyToShape(0);

    assertEquals(rect.getWidth(), 2);
    assertEquals(rect.getHeight(), 2);
  }

  // Ensures that constructing a valid model and printing it's state shows the proper ouput
  @Test
  public void testGetStateEmptyModel() {
    EasyAnimatorModel m = new EasyAnimatorModel();

    String correct = "Shapes\n";
    assertEquals(correct, m.getTextDescription());
  }

  // Ensures that the text description is correct with one shape and no actions
  @Test
  public void testGetStateOneShape() {
    EasyAnimatorModel m = new EasyAnimatorModel();

    Rectangle rect = new Rectangle(50, 50, new Point(0, 0), PinHole.Top,
            "R", 0, 10, new Color(1.0f, 0.0f, 0.0f));

    m.addShape(rect);

    String correct = "Shapes\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "top: (200.0,200.0), Width: 50.000000, Height: 50.000000, "
            + "Color: java.awt.Color[r=255,g=0,b=0]\n"
            + "Appears at t=0\n"
            + "Disappears at t=10\n";

    assertEquals(correct, m.getTextDescription());
  }

  // Ensures that the text description is correct with one shape and no actions
  @Test
  public void testGetStateOneShapeOneAction() {
    EasyAnimatorModel m = new EasyAnimatorModel();

    Rectangle rect = new Rectangle(50, 50, new Point(0, 0), PinHole.Top,
            "R", 0, 10, new Color(1.0f, 0.0f, 0.0f));

    m.addShape(rect);

    String correct = "Shapes\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "top: (200.0,200.0), Width: 50.000000, Height: 50.000000, "
            + "Color: java.awt.Color[r=255,g=0,b=0]\n"
            + "Appears at t=0\n"
            + "Disappears at t=10\n";

    assertEquals(correct, m.getTextDescription());
  }

}
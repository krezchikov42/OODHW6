import org.junit.Assert;
import org.junit.Test;

import cs3500.animator.Color;
import cs3500.animator.ColorAction;
import cs3500.animator.EasyShape;
import cs3500.animator.MoveAction;
import cs3500.animator.Oval;
import cs3500.animator.Point;
import cs3500.animator.Rectangle;
import cs3500.animator.ScaleAction;
import cs3500.animator.model.EasyAnimatorModel;
import cs3500.animator.model.EasyAnimatorOperations;
import cs3500.animator.view.TextView;
import cs3500.animator.view.View;
import cs3500.animator.view.ViewFactory;

import static org.junit.Assert.assertEquals;

/**
 * Tests the text view.
 */
public class TextViewTest {
/**
  //tests the text with multiple shapes and actions
  @Test
  public void testText() {

    EasyAnimatorOperations model = new EasyAnimatorModel();
    EasyShape oval = new Oval(10, 10, new Point(1, 1), EasyShape.PinHole.Top,
            "Oval", 1, 10, new Color(20, 30, 50));
    EasyShape rect = new Rectangle(30, 40, new Point(50, 50), EasyShape.PinHole.Top,
            "Rect", 10, 30, new Color(40, 40, 40));
    model.addShape(oval);
    model.addShape(rect);
    model.addAction(new MoveAction(oval, new Point(1, 1), new Point(10, 10), 5, 8));
    model.addAction(new ColorAction(rect, new Color(20, 30, 50),
            new Color(100, 100, 100), 15, 20));
    model.addAction(new ScaleAction(rect, 30, 40, 60, 80, 17, 25));
    View view = new TextView(model, 1);
    assertEquals("Name: Oval\n" +
            "Type: rectangle\n" +
            "top: (200.0,200.0), X radius: 10.000000, Y radius: 10.000000," +
            " Color: 0.08,0.12,0.20\n" +
            "Appears at t=1.000000\n" +
            "Disappears at t=10.000000Name: Rect\n" +
            "Type: rectangle\n" +
            "top: (200.0,200.0), Width: 30.000000, Height: 40.000000, Color: 0.16,0.16,0.16\n" +
            "Appears at t=10.000000\n" +
            "Disappears at t=30.000000\n" +
            " Oval moves from (1.00,1.00) to (10.00,10.00) from t=5.00 to t=8.00\n" +
            " Rect changes color from 0.16,0.16,0.16 to 0.39,0.39,0.39 from t=15.00 to t=20.00\n" +
            " Rect scales from Width 30.00, Height 40.00 to Width 60.00, Height 80.00 from t=17.00 "
            + "to t=25.00\n", view.getText());
  }

  //test svg for rectangle moving right
  @Test
  public void testRectangleMovesRight() {
    EasyAnimatorOperations m = new EasyAnimatorModel();
    Rectangle r = new Rectangle(50, 50, new Point(20, 20), EasyShape.PinHole.BottomLeft,
            "R", 0, 100, new Color(1.0f, 0.0f, 0.0f));
    m.addShape(r);

    MoveAction a = new MoveAction(r, new Point(20, 20), new Point(400, 20), 5, 20);
    m.addAction(a);

    View v = new ViewFactory(m, 1f).create("text");

    Assert.assertEquals("Name: R\n" +
            "Type: rectangle\n" +
            "bottom-left: (200.0,200.0), Width: 50.000000, " +
            "Height: 50.000000, Color: 1.00,0.00,0.00\n" +
            "Appears at t=0.00\n" +
            "Disappears at t=100.00\n" +
            "R moves from (20.00,20.00) to (400.00,20.00) from t=5.00 to t=20.00\n", v.getText());

  }

  //test svg view with rectangle with moves and color
  @Test
  public void testRectangleMovesAndChangesRedToGreen() {
    EasyAnimatorOperations m = new EasyAnimatorModel();
    Rectangle r = new Rectangle(50, 50, new Point(20, 20), EasyShape.PinHole.BottomLeft,
            "R", 0, 100, new Color(1.0f, 0.0f, 0.0f));
    m.addShape(r);

    MoveAction a = new MoveAction(r, new Point(20, 20), new Point(400, 20), 5, 20);
    m.addAction(a);

    ColorAction a2 = new ColorAction(r, new Color(1.0f, 0.0f, 0.0f), new Color(0.0f, 1.0f, 0.0f),
            5, 20);
    m.addAction(a2);

    View v = new TextView(m, 1.0f);

    Assert.assertEquals("Name: R\n" +
            "Type: rectangle\n" +
            "bottom-left: (200.0,200.0), Width: 50.000000, Height: 50.000000," +
            " Color: 1.00,0.00,0.00\n" +
            "Appears at t=0.00\n" +
            "Disappears at t=100.00\n" +
            "R moves from (20.00,20.00) to (400.00,20.00) from t=5.00 to t=20.00\n" +
            "R changes color from 1.00,0.00,0.00 to 0.00,1.00,0.00 from t=5.00 to t=20.00\n",
            v.getText());
  }

  //tests the svg with a rectangle and a scale
  @Test
  public void testRectangleScalesTimes4() {
    EasyAnimatorOperations m = new EasyAnimatorModel();
    Rectangle r = new Rectangle(50, 50, new Point(20, 20),
            EasyShape.PinHole.BottomLeft,
            "R", 0, 100, new Color(1.0f, 0.0f, 0.0f));
    m.addShape(r);

    ScaleAction a = new ScaleAction(r, 50, 50, 200,
            200, 1, 8);
    m.addAction(a);

    View v = new TextView(m, 1.0f);

    Assert.assertEquals("Name: R\n" +
            "Type: rectangle\n" +
            "bottom-left: (200.0,200.0), Width: 50.000000, Height: 50.000000, " +
            "Color: 1.00,0.00,0.00\n" +
            "Appears at t=0.00\n" +
            "Disappears at t=100.00\n" +
            "R scales from Width 50, Height 50 to Width 200, Height 200 from t=1.00 to t=8.00\n",
            v.getText());
  }*/
}
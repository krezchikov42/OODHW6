import org.junit.Test;

import cs3500.animator.Color;
import cs3500.animator.ColorAction;
import cs3500.animator.EasyShape.PinHole;
import cs3500.animator.MoveAction;
import cs3500.animator.Point;
import cs3500.animator.Rectangle;
import cs3500.animator.ScaleAction;
import cs3500.animator.model.EasyAnimatorModel;
import cs3500.animator.model.EasyAnimatorOperations;
import cs3500.animator.view.SVGView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the SVG view.
 */
public class SVGViewTest {

  //tests the svg with a rectangle
  @Test
  public void testJustRectangle() {
    EasyAnimatorOperations m = new EasyAnimatorModel();
    Rectangle r = new Rectangle(50, 50, new Point(20, 20), PinHole.BottomLeft,
            "R", 0, 100, new Color(1.0f, 0.0f, 0.0f));
    m.addShape(r);

    SVGView v = new SVGView();


    String correct = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"20.00\" y=\"20.00\" width=\"50.00\" height=\"50.00\" " +
            "fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
            + "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" " +
            "begin=\"0.00s\" dur=\"100.00s\" fill=\"remove\" />\n"
            + "</rect>\n"
            + "</svg>";

    assertEquals(correct, v.getText(m.getShapes(),m.getActions(),1.0f,m.getEndTime()));
  }

  //test svg for rectangle moving right
  @Test
  public void testRectangleMovesRight() {
    EasyAnimatorOperations m = new EasyAnimatorModel();
    Rectangle r = new Rectangle(50, 50, new Point(20, 20), PinHole.BottomLeft,
            "R", 0, 100, new Color(1.0f, 0.0f, 0.0f));
    m.addShape(r);

    MoveAction a = new MoveAction(r, new Point(20, 20), new Point(400, 20), 5, 20);
    m.addAction(a);

    SVGView v = new SVGView();

    String correct = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"20.00\" y=\"20.00\" width=\"50.00\" height=\"50.00\"" +
            " fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\"" +
            " begin=\"0.00s\" dur=\"100.00s\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"5.00s\" dur=\"15.00s\" attributeName=\"x\"" +
            " from=\"20.00\" to=\"400.00\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"5.00s\" dur=\"15.00s\" attributeName=\"y\"" +
            " from=\"20.00\" to=\"20.00\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "</svg>";
    assertEquals(correct, v.getText(m.getShapes(),m.getActions(),1.0f, m.getEndTime()));
  }

  //test svg view with rectangle with moves and color
  @Test
  public void testRectangleMovesAndChangesRedToGreen() {
    EasyAnimatorOperations m = new EasyAnimatorModel();
    Rectangle r = new Rectangle(50, 50, new Point(20, 20), PinHole.BottomLeft,
            "R", 0, 100, new Color(1.0f, 0.0f, 0.0f));
    m.addShape(r);

    MoveAction a = new MoveAction(r, new Point(20, 20), new Point(400, 20), 5, 20);
    m.addAction(a);

    ColorAction a2 = new ColorAction(r, new Color(1.0f, 0.0f, 0.0f), new Color(0.0f, 1.0f, 0.0f),
            5, 20);
    m.addAction(a2);

    SVGView v = new SVGView();

    String correct = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"20.00\" y=\"20.00\" width=\"50.00\" height=\"50.00\" " +
            "fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
            + "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\"" +
            " begin=\"0.00s\" dur=\"100.00s\" fill=\"remove\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5.00s\" dur=\"15.00s\" attributeName=\"x\"" +
            "from=\"20.00\" to=\"400.00\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5.00s\" dur=\"15.00s\" attributeName=\"y\" " +
            "from=\"20.00\" to=\"20.00\" fill=\"freeze\" />\n"
            + "<animate attributeName=\"fill\" attributeType=\"CSS\"\n"
            + "           from=\"rgb(255,0,0)\" to=\"rgb(0,255,0)\"\n"
            + "           begin=\"5.00s\" dur=\"15.00s\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "</svg>";

    assertEquals(correct, v.getText(m.getShapes(),m.getActions(),1.0f, m.getEndTime()));
  }

  //tests the svg with a rectangle and a scale
  @Test
  public void testRectangleScalesTimes4() {
    EasyAnimatorOperations m = new EasyAnimatorModel();
    Rectangle r = new Rectangle(50, 50, new Point(20, 20), PinHole.BottomLeft,
            "R", 0, 100, new Color(1.0f, 0.0f, 0.0f));
    m.addShape(r);

    ScaleAction a = new ScaleAction(r, 50, 50, 200, 200, 1, 8);
    m.addAction(a);

    SVGView v = new SVGView();

    String correct = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"20.00\" y=\"20.00\" width=\"50.00\" height=\"50.00\"" +
            " fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
            + "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\"" +
            " begin=\"0.00s\" dur=\"100.00s\" fill=\"remove\" />\n"
            + "<animateTransform attributeName=\"transform\" attributeType=\"XML\"\n"
            + "           type=\"scale\" from=\"50.00 50.00\" to=\"200.00 200.00\" " +
            "additive=\"sum\"\n"
            + "           begin=\"1.00s\" dur=\"8.00s\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "</svg>";
    assertEquals(correct, v.getText(m.getShapes(),m.getActions(),1.0f, m.getEndTime()));
  }

}
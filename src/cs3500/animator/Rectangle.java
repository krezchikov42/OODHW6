package cs3500.animator;


import java.awt.Graphics;
import java.util.List;

/**
 * Represents a 2D rectangle.
 */
public class Rectangle extends EasyShape {

  /**
   * Constructs a new rectangle.
   *
   * @param width      the width of the rectangle
   * @param height     the height of the rectangle
   * @param pos        the position of the rectangle
   * @param pinHole    the pinhole of the position
   * @param name       the name of this shape
   * @param appearTime the time this shape appears
   * @param hideTime   the time this shape disappears
   * @param color      the color of this shape
   */
  public Rectangle(float width, float height, Point pos, PinHole pinHole, String name,
                   int appearTime, int hideTime, Color color) {
    super(pos, pinHole, name, appearTime, hideTime, color, width, height);
    this.shapeType = "rectangle";
  }

  private Rectangle(Rectangle r, List<Action> a) {
    super(r.getPostition().clone(), r.getPinhole(), r.getName(), r.getAppearTime(),
            r.getHideTime(), r.getColor().clone(), r.getWidth(), r.getHeight());
    this.shapeType = "rectangele";
    this.actions = a;
  }

  @Override
  String sizeToString() {
    return String.format("Width: %f, Height: %f", this.width, this.height);
  }


  @Override
  public String getSVG(float ticksOverSeconds) {
    // opening rect tag
    String svg = String.format("<rect id=\"%s\" x=\"%.2f\" y=\"%.2f\" width=\"%.2f\" " +
                    "height=\"%.2f\" fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\" >\n",
            this.name, this.position.getX(), this.position.getY(), this.width, this.height,
            getColor().getRed255(), getColor().getGreen255(), getColor().getBlue255());

    // Add visibility animation
    svg += String.format("<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\""
                    + " begin=\"%.2fs\" dur=\"%.2fs\" fill=\"remove\" />\n",
            appearTime / ticksOverSeconds, (hideTime - appearTime) / ticksOverSeconds);

    // Add all other animations
    for (Action a : this.actions) {
      svg += a.getSVG(ticksOverSeconds) + "\n";
    }

    // closing rect tag
    svg += "</rect>";
    return svg;
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(new java.awt.Color((float) this.color.getRed(), (float) this.color.getGreen(),
            (float) this.color.getBlue()));
    g.fillRect((int) this.position.getX(), (int) this.position.getY(), (int) this.width,
            (int) this.height);
  }

  @Override
  public EasyShape clone() {
    return new Rectangle(this, actions);
  }
}

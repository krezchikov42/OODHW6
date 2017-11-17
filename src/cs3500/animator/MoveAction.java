package cs3500.animator;


/**
 * Represents an action to move a shape to a different position over time.
 */
public class MoveAction extends Action {

  private Point endPos;
  private Point startPos;
  private double dx;
  private double dy;

  /**
   * Constructs an action to move a shape to a different position over time.
   *
   * @param shape     The one and only shape that this action will be applied to
   * @param startPos  The location where this action's shape begins
   * @param endPos    Where the shape will be at the end of this action
   * @param startTime The time at which this action starts being applied
   * @param endTime   The time at which this action is no longer to be applied
   */
  public MoveAction(EasyShape shape, Point startPos, Point endPos, int startTime, int endTime) {
    super(shape, startTime, endTime);
    this.endPos = endPos;

    this.startPos = startPos;

    dx = (endPos.getX() - startPos.getX()) / (endTime - startTime);
    dy = (endPos.getY() - startPos.getY()) / (endTime - startTime);
    textaction = String.format("moves from %s to %s", startPos, endPos);
  }

  private MoveAction(MoveAction action) {
    this(action.getShape(), action.getStartPos(), action.getEndPos(),
            action.getStartTime(), action.getEndTime());
  }

  /**
   * returns a copy of the end point.
   *
   * @return a copy of the end point.
   */
  public Point getEndPos() {
    return endPos.clone();
  }

  public Point getStartPos() {
    return startPos.clone();
  }

  /**
   * Increments the x and y values of the shape's position with calculated values.
   *
   * @param currentTime The current time in the animation of the model using this action
   */
  @Override
  public void applyToShape(int currentTime) {
    //EasyShape clone = shape.clone();
    double moveDX = currentTime * dx;
    double moveDY = currentTime * dy;
    //clone.setPosition(shape.getPostition().add(moveDX, moveDY));
    shape.setPosition(startPos.add(moveDX, moveDY));
    //System.out.print(String.format("ClonePos:%s\n", clone.getPostition().toString()));
    //return clone;
  }

  @Override
  public String toString() {
    return String.format("Shape %s moves from %s to %s from t=%d to t=%d",
            this.shape.getName(), this.startPos.toString(), this.endPos.toString(),
            this.startTime, this.endTime);
  }

  @Override
  public String getSVG(float ticksOverSeconds) {
    String s = String.format("<animate attributeType=\"xml\" begin=\"%.2fs\" dur=\"%.2fs\""
                    + " attributeName=\"x\" from=\"%.2f\" to=\"%.2f\" fill=\"freeze\" />\n",
            this.startTime / ticksOverSeconds, (endTime - startTime) / ticksOverSeconds,
            this.startPos.getX(), this.endPos.getX());
    s += String.format("<animate attributeType=\"xml\" begin=\"%.2fs\" dur=\"%.2fs\""
                    + " attributeName=\"y\" from=\"%.2f\" to=\"%.2f\" fill=\"freeze\" />",
            this.startTime / ticksOverSeconds, (endTime - startTime) / ticksOverSeconds,
            this.startPos.getY(), this.endPos.getY());

    return s;
  }

  @Override
  public Action clone() {
    return new MoveAction(this);
  }
}

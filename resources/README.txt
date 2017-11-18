EasyAnimator Documentation/README

How to use the HybridView User Interface
—————————————————————

The slider at the top changes the rate of the animation in real time

The pause, resume, and restart button pauses, resumes, and restarts the animation
After pressing restart, you must press play to begin the animation again.

To export to SVG, you simply press export to svg and enter the name of the output file, which will appear in the current
directory

To control which shapes are viewed/exported to svg, type the name of a shape into the text field and press the button below it.
This will cause that shape to no longer be viewed in the animation. There is a button to reset visibility settings.
———————————————————————






In the code, the term "Animation" refers to a large-scale scene with multiple shapes that is run by the model, and the term
"action" refers to an animation of one type on one specific shape. An Animation is full of shapes and actions.


////////////////////////////////////////////////
Our Design
////////////////////////////////////////////////

There is a model interface EasyAnimatorOperations, an abstract shape class EasyShape, and an abstract action class Action

The shape class knows very little about the fact that it's been built for an Animation program, it's just a representation
of a 2D shape. I've included two implementations: oval and rectangle.

The Action class is built around applying itself to one, and only one, shape object when called upon to do so with a given
current time.

The model interface has a function called updateAnimation() which increments time by 1 and
tells all the actions in its list of actions to apply themselves to
their respective shapes (if it is the proper time for them to do so).


////////////////////////////////////////////////
The Important Classes with broken down methods:
////////////////////////////////////////////////

**** Controller ****
—————————————-
Combines the model and the view. It has functions getTextFromTextualView, which gets the text from the view
if the view has a textual aspect, and runViewWithVisualComponent, which opens the animation in visual window
if the view is a VisualView or HybridView.

**** AnimationController ****
—————————————————————————
This class acts as an actionListener for the view when it is a HybridView, and all components of the view,
when selected or pressed or changed by the user, notify the controller to call certain functions and
respond accordingly.


EasyAnimator
-----------------------
This is the class that contains main. It is the command line program that runs a particular view
given a specific input file.

View
-----------------------
This is the interface for the different types of views. It represents a manner in which the user
can view an animation. It has two methods--go() for visual views and getText for textual views.

AView
-----------------------
An abstract class that implements View. It abstracts the fields needed by each view and provides
base implementations of the methods.

**** Hybrid View ****
———————————
This view offers the ability to run the animation visually, pause the animation, resume the
animation, restart the animation, and change the speed of the animation. It also allows the 
user to select individual shapes by their names that they don’t want to be viewed. Furthermore,
there is a dialog box that can be opened to export the animation to SVG.


VisualView
-----------------------
The view that opens a window and displays the animation to the user. It uses swing to draw the
animation, updating each tick with a given speed rate. It updates by calling the updateAnimation
method in the model. It has each shape in the model draw itself onto a Graphics object, which is
overseen in a JPanel child class called Animator.

SVGView
-----------------------
The view that returns an svg text output to run the animation. This class operates by providing an
initial <svg> tag then calling on each shape in the model to return its own custom tag. Each
shape in turn then calls upon each Action inside of it to make their own custom tag. This produces
a full svg file.


TextView
-----------------------
The view that returns a text description of the animation. This works by having each shape and
action in the model return it's own description and then conglomerates them into one big description


EasyAnimatorOperations
-----------------------
This is the interface to represent a model of an animation. It can return a clone of its shapes
and a clone of its actions. It's is operated by calling the function updateAnimation() every tick.

Cloneable
-----------------------
This allows the model to return a list of its shapes and actions without giving out references to
them directly.


Action
-----------------------
This is the class to represent a single animation applied to a single shape. An action knows which
shape it can be applied to, and can tell you whether or not it's current based on its start and
end time.



EasyShape
-----------------------
This interface represents a shape.




//////////////////////////////////////////////////////
Changes in Design since previous animation assignment:
//////////////////////////////////////////////////////

The main per-tick function updateAnimation in the model actually utilizes the currentTime now,
so Actions are able to return the shape’s updated state at a given time, enabling restarting
and looping.

There is one Controller that can control any type of view, including the newly added HybridView.

The getSVG functions of shapes and views were changed in order to enable looping.



///////////////////////////////////////////////////
Changes in Design since first animation assignment:
///////////////////////////////////////////////////

EasyAnimatorOperations:
    This interface was given a getActions() and getShapes() method that returns a clone of the
    model's lists of actions and shapes. This allows viewing access into the happenings of the model

Action:
    Actions now have a clone method to work in conjunction with the model. Action also now
    implements the Textable and Cloneable interfaces.

ColorAction, MoveAction, ScaleAction:
    All given implementations of getSVG and getText to support Textable interface requirements

ScaleAction:
    Now accepts two scale arguments (an x dimension scale and a y dimension scale), so each
    dimension can be scaled to a different factor

EasyAnimatorModel:
    No longer accepts any arguments to the constructor, because the one's from assignment 1 are
    no longer necessary. Also contains a builder class now.

EasyShape:
    Now implements Cloneable and Textable. Now has setter methods for all necessary fields.
    Has a checkLegAct() method that confirms whether an Action is legitimate before adding it to
    the shape. Shapes now also store a list of their actions. Shapes now also have a draw method
    that accepts a Graphics object from the java.awt library.

Rectangle:
    Implements a SVG representation and can now add itself to a Graphics object.

Oval:
    Implements a SVG representation and can now add itself to a Graphics object.
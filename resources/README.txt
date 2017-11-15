EasyAnimator Documentation/README


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

VisualView
-----------------------
The view that opens a window and displays the animation to the user. It uses swing to draw the
animation, updating each tick with a given speed rate. It updates by calling the updateAnimation
method in the model. It has each shape in the model draw itself onto a Graphics obejct, which is
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




////////////////////////////////////////////////
Changes in Design since last assignment:
////////////////////////////////////////////////

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
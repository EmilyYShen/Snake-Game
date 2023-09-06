
===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays
  Feedback: no further changes

  I plan to create a Board class that is the 2D array of the board that will be the
  parameters for the game and the bounds where the snake can go. The snake will be
  bounded by the squares of the game, and an apple will be randomly generated in
  one of the 2D array cells.

  2. File I/O
  Feedback: Must store high scores in different manner

  A file will store and keep track of the high score of the user. A separate file will
  store the highest score of the user and this will change if a new high score is
  achieved.

  3. Inheritance and subtyping
  Feedback: The snake should be stored in a LinkedList

  The snake will be stored as a LinkedList, making this component the Collections component.
  This component will have a list of x,y pairs where the first element is the head of the
  snake and the last component is the tail of the snake.

  4. JUnit Testable Component
  Feedback: no further changes

  The JUnit testing will test the game model, including things like where the snake
  head is, which direction the snake is going, the length of the snake, how to increase
  the length of the snake, different movements of the snake, whether the head of the snake
  updates after it moves, and testing many other things that highlight the snake's movement
  and how that relates to the position of the apple and the walls surrounding the game.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  Apple: Stores the image of the apple that the snake will try and eat.

  Direction: Has four states (UP, DOWN, LEFT, RIGHT) that are the potential directions
  the snake can go in.

  GameCourt: Contains all the logic that allows the snake to move whenever an arrowkey
  is pressed, it updates the user's score, checks if the user has lost or not, spawns apples
  in random locations, and writes the high score if the player achieved it or else it doesn't
  write a high score.

  GameObj: This is used for testing purposes, and contains methods that keep track of the
  position and direction of the snake. It is used by SnakeBody.

  RunSnake: The main class which uses Java swing to display the panels.

  Snake: Contains a list of SnakeBody and has several methods, such as a method
  to move all the SnakeBody parts and add another SnakeBody whenever the head
  comes into contact with an Apple.

  SnakeBody: Contains a draw method to draw the snake and takes a point so that
  it knows where to draw the snake.

  Tile: The 2D array that stores the board where the apple is and contains points
  that the snake can move to.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  The biggest stumbling block was designing the classes of my implementation. I
  spent quite a lot of time planning out what each of my classes would do, and
  how they would relate to each other, drawing inspiration from the
  sample games. However, I had to change my classes and their implementations
  several times as I realised easier ways I could have implemented certain aspects.
  For example, I originally had a class dedicated to storing the highest score. However,
  I realised that I could just read and write into a text file in the RunSnake class,
  without having to create a separate class just for this function. Thus, as I was
  writing my code, I realised other ways I could implement things, so I modified
  my design quite a lot.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  I think the functions were separated well, as each was in a different class that
  could be accessed. The private states are also encapsulated that way. I would
  refactor a lot if I had the chance, as I modified my game quite a lot just
  before the deadline because I wasn't storing the snake correctly in the 2D
  array. Thus, if given the chance, I would add more features to the game,
  such as saving a particular instance of the game.

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

  I read the JavaDocs for most of the libraries that I implemented.

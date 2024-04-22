
// Imports required libraries
import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  // Initializes image variables
  PImage imgRunesBackground;
  PImage imgRuneSymbolOne;
  PImage imgRuneSymbolTwo;
  PImage imgRuneSymbolThree;

  // Initializes variables for image dimensions and speed
  float fltRuneWidth = 50;
  float fltRuneHeight = 84;
  float fltRuneSpeed = 2;

  // Creates variables for the first rune's motion
  float fltRuneOneX = 0;
  float fltRuneOneY = 0;
  float fltRuneOneDirectionX = 1;
  float fltRuneOneDirectionY = 1;

  // Creates variables for the second rune's motion
  float fltRuneTwoX = 310;
  float fltRuneTwoY = 300;
  float fltRuneTwoDirectionX = -1;
  float fltRuneTwoDirectionY = -1;

  // Creates variables for the third rune's motion
  float fltRuneThreeX = 0;
  float fltRuneThreeY = 500;
  float fltRuneThreeDirectionX = -1;
  float fltRuneThreeDirectionY = -1;

  // Initializes variables for the shape's motion
  float fltStartingX = (float) 10.0;
  float fltStartingY = (float) 10.0;
  float fltFinalX = (float) 800.0;
  float fltFinalY = (float) 800.0;
  float fltDistanceX;
  float fltDistanceY;
  float fltExponent = 4;
  float fltCurrentX = (float) 0.0;
  float fltCurrentY = (float) 0.0;
  float fltPercentAdd = (float) 0.01;
  float fltPercent = (float) 0.0;

  /**
   * Called once at the beginning of execution to set the size of the screen
   * 
   */
  public void settings() {
    // put your size call here
    size(600, 600);
  }

  /**
   * Called once at the beginning of execution to set up variables
   */
  public void setup() {
    fltDistanceX = fltFinalX - fltStartingX;
    fltDistanceY = fltFinalY - fltStartingY;
    background(0, 0, 0, 0);
    imgRunesBackground = loadImage("images/AncientRunes.png");
    imgRunesBackground.resize(imgRunesBackground.width - 424, imgRunesBackground.height - 425);

    imgRuneSymbolOne = loadImage("images/RuneSymbolOne.png");
    imgRuneSymbolTwo = loadImage("images/RuneSymbolTwo.png");
    imgRuneSymbolThree = loadImage("images/RuneSymbolThree.png");

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

    // Resets the background of the screen
    image(imgRunesBackground, 0, 0);

    // Calls the methods required to output objects
    runeOne();
    runeTwo();
    runeThree();
    object();

    // Creates a conditional selective algorithm to determine if Rune 1 and Rune 3 have collided
    if (fltRuneOneX + fltRuneWidth + fltRuneSpeed > fltRuneThreeX
        && fltRuneOneX + fltRuneSpeed < fltRuneThreeX + fltRuneWidth && fltRuneOneY + fltRuneHeight > fltRuneThreeY
        && fltRuneOneY < fltRuneThreeY + fltRuneHeight) {

          // Changes the direction of images in the x-axis
          fltRuneOneDirectionX = fltRuneOneDirectionX * -1;
          fltRuneThreeDirectionX = fltRuneThreeDirectionX * -1;

    } else if (fltRuneOneX + fltRuneWidth > fltRuneThreeX && fltRuneOneX < fltRuneThreeX + fltRuneWidth
        && fltRuneOneY + fltRuneHeight + fltRuneSpeed > fltRuneThreeY
        && fltRuneOneY + fltRuneSpeed < fltRuneThreeY + fltRuneHeight) {

          // Changes the direction of the images in the y-axis
          fltRuneOneDirectionY = fltRuneOneDirectionY * -1;
          fltRuneThreeDirectionY = fltRuneThreeDirectionY * -1;
    }

    // Creates a conditional selective algorithm to determine if Rune 1 and Rune 2 have collided
    if (fltRuneOneX + fltRuneWidth + fltRuneSpeed > fltRuneTwoX
        && fltRuneOneX + fltRuneSpeed < fltRuneTwoX + fltRuneWidth && fltRuneOneY + fltRuneHeight > fltRuneTwoY
        && fltRuneOneY < fltRuneTwoY + fltRuneHeight) {

          // Changes the direction of images in the x-axis
          fltRuneOneDirectionX = fltRuneOneDirectionX * -1;
          fltRuneTwoDirectionX = fltRuneTwoDirectionX * -1;

    } else if (fltRuneOneX + fltRuneWidth > fltRuneTwoX && fltRuneOneX < fltRuneTwoX + fltRuneWidth
        && fltRuneOneY + fltRuneHeight + fltRuneSpeed > fltRuneTwoY
        && fltRuneOneY + fltRuneSpeed < fltRuneTwoY + fltRuneHeight) {
          
          // Changes the direction of the images in the y-axis
          fltRuneOneDirectionY = fltRuneOneDirectionY * -1;
          fltRuneTwoDirectionY = fltRuneTwoDirectionY * -1;

    }
    
    // Creates a conditional selective algorithm to determine if Rune 2 and Rune 3 have collided
    if (fltRuneTwoX + fltRuneWidth + fltRuneSpeed > fltRuneThreeX
        && fltRuneTwoX + fltRuneSpeed < fltRuneThreeX + fltRuneWidth && fltRuneTwoY + fltRuneHeight > fltRuneThreeY
        && fltRuneTwoY < fltRuneThreeY + fltRuneHeight) {
          
          // Changes the direction of images in the x-axis
          fltRuneTwoDirectionX = fltRuneTwoDirectionX * -1;
          fltRuneThreeDirectionX = fltRuneThreeDirectionX * -1;

    } else if (fltRuneTwoX + fltRuneWidth > fltRuneThreeX && fltRuneTwoX < fltRuneThreeX + fltRuneWidth
        && fltRuneTwoY + fltRuneHeight + fltRuneSpeed > fltRuneThreeY
        && fltRuneTwoY + fltRuneSpeed < fltRuneThreeY + fltRuneHeight) {
          
          // Changes the direction of the images in the y-axis
          fltRuneTwoDirectionY = fltRuneTwoDirectionY * -1;
          fltRuneThreeDirectionY = fltRuneThreeDirectionY * -1;

    }

    // Creates a conditional selective algorithm to determine if the elipse has hit the edge of the screen
    if (fltCurrentX > width - 10) {

      // Reconfigures the ellipse variables to head to its next destination
      fltPercent = (float) 0.0;
      fltStartingX = fltCurrentX;
      fltStartingY = fltCurrentY;
      fltFinalX = -10;
      fltFinalY = 500;
      fltDistanceX = fltFinalX - fltStartingX;
      fltDistanceY = fltFinalY - fltStartingY;

    } else if (fltCurrentX < 10) {
      
      // Reconfigures the ellipse variables to head to its next destination
      fltPercent = (float) 0.0;
      fltStartingX = fltCurrentX;
      fltStartingY = fltCurrentY;
      fltFinalX = 590;
      fltFinalY = 590;
      fltDistanceX = fltFinalX - fltStartingX;
      fltDistanceY = fltFinalY - fltStartingY;

    } else if (fltCurrentY > height - 20 && fltCurrentX > width - 20) {
      
      // Reconfigures the ellipse variables to head to its next destination
      fltPercent = (float) 0.0;
      fltStartingX = fltCurrentX;
      fltStartingY = fltCurrentY;
      fltFinalX = 4;
      fltFinalY = 4;
      fltDistanceX = fltFinalX - fltStartingX;
      fltDistanceY = fltFinalY - fltStartingY;

    } else if (fltCurrentX < 15 && fltCurrentY < 10) {
      
      // Reconfigures the ellipse variables to head to its next destination
      fltPercent = (float) 0.0;
      fltStartingX = fltCurrentX;
      fltStartingY = fltCurrentY;

      // Recalls the shape method
      object();

    }

  }

  /**
   * Creates a method to output the firs rune
   */
  private void runeOne() {

    // OUtputs the image and changes the position of the image
    image(imgRuneSymbolOne, fltRuneOneX, fltRuneOneY);
    fltRuneOneX = fltRuneOneX + (fltRuneSpeed * fltRuneOneDirectionX);
    fltRuneOneY = fltRuneOneY + (fltRuneSpeed * fltRuneOneDirectionY);

    // Creates a conditional selective algorithm to detect if the object has hit a wall
    if (fltRuneOneX > width - 55 || fltRuneOneX < 0) {

      // Changes the direction of the rune in the x-axis
      fltRuneOneDirectionX = fltRuneOneDirectionX * -1;

    } else if (fltRuneOneY > height - 85 || fltRuneOneY < 0) {

      // Changes the direction of the rune in the y-axis 
      fltRuneOneDirectionY = fltRuneOneDirectionY * -1;

    }
  }

  /**
   * Creates a method to output the second rune
   */
  private void runeTwo() {

    // Outputs the rune onto the screen and changes its position
    image(imgRuneSymbolTwo, fltRuneTwoX, fltRuneTwoY);
    fltRuneTwoX = fltRuneTwoX + (fltRuneSpeed * fltRuneTwoDirectionX);
    fltRuneTwoY = fltRuneTwoY + (fltRuneSpeed * fltRuneTwoDirectionY);

    // Creates a conditional selective algorithm to detect if the object has hit a wall
    if (fltRuneTwoX > width - 55 || fltRuneTwoX < 0) {

      // Changes the direction of the rune in the x-axis
      fltRuneTwoDirectionX = fltRuneTwoDirectionX * -1;

    } else if (fltRuneTwoY > height - 85 || fltRuneTwoY < 0) {

      // Changes the direction of the rune in the y-axis
      fltRuneTwoDirectionY = fltRuneTwoDirectionY * -1;

    }
  }
  
  /**
   * Creates a method to output the third rune
   */
  private void runeThree() {

    //Outputs the rune onto the screen and changes its position
    image(imgRuneSymbolThree, fltRuneThreeX, fltRuneThreeY);
    fltRuneThreeX = fltRuneThreeX + (fltRuneSpeed * fltRuneThreeDirectionX);
    fltRuneThreeY = fltRuneThreeY + (fltRuneSpeed * fltRuneThreeDirectionY);

    // Creates a conditional selective algorithm to detect if the object has hit a wall
    if (fltRuneThreeX > width - 55 || fltRuneThreeX < 0) {

      // Changes the direction of the rune in the x-axis
      fltRuneThreeDirectionX = fltRuneThreeDirectionX * -1;

    } else if (fltRuneThreeY > height - 85 || fltRuneThreeY < 0) {

      // Changes the direction of the rune in the y-axis
      fltRuneThreeDirectionY = fltRuneThreeDirectionY * -1;

    }
  }

  /**
   * Creates a method to print out the ellipse object
   */
  private void object() {

    // Adds to the percentage of motion completed
    fltPercent += fltPercentAdd;

    // Creates a conditional selective algorithm to determine if the motion of the ellipse is completed
    if (fltPercent < 1.0) {
      
      // Updates the position of the ellipse
      fltCurrentX = fltStartingX + (fltPercent * fltDistanceX);
      fltCurrentY = (float) (fltStartingY + (Math.pow(fltPercent, fltExponent) * fltDistanceY));

    }

    // Sets the colour of the ellipse
    fill(255);

    // Prints out the object onto the screen
    ellipse(fltCurrentX, fltCurrentY, 20, 20);
    
  }
}
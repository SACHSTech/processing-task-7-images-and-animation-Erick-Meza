import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  PImage imgRunesBackground;
  PImage imgRuneSymbolOne;
  PImage imgRuneSymbolTwo;
  PImage imgRuneSymbolThree;

  float fltRuneWidth = 50;
  float fltRuneHeight = 84;
  float fltRuneSpeed = 2;

  float fltRuneOneX = 0;
  float fltRuneOneY = 0;
  float fltRuneOneDirectionX = 1;
  float fltRuneOneDirectionY = 1;

  float fltRuneTwoX = 310;
  float fltRuneTwoY = 300;
  float fltRuneTwoDirectionX = -1;
  float fltRuneTwoDirectionY = -1;

  float fltRuneThreeX = 0;
  float fltRuneThreeY = 500;
  float fltRuneThreeDirectionX = -1;
  float fltRuneThreeDirectionY = -1;

  float beginX = (float) 10.0; // Initial x-coordinate
  float beginY = (float) 10.0; // Initial y-coordinate
  float endX = (float) 800.0; // Final x-coordinate
  float endY = (float) 800.0; // Final y-coordinate
  float distX; // X-axis distance to move
  float distY; // Y-axis distance to move
  float exponent = 4; // Determines the curve
  float x = (float) 0.0; // Current x-coordinate
  float y = (float) 0.0; // Current y-coordinate
  float step = (float) 0.01; // Size of each step along the path
  float pct = (float) 0.0;


  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    // put your size call here
    size(600, 600);
  }

  /**
   * Called once at the beginning of execution. Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    distX = endX - beginX;
    distY = endY - beginY;
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
    image(imgRunesBackground, 0, 0);

    runeOne();
    runeTwo();
    runeThree();
    object();

    if (fltRuneOneX + fltRuneWidth + fltRuneSpeed > fltRuneThreeX
        && fltRuneOneX + fltRuneSpeed < fltRuneThreeX + fltRuneWidth && fltRuneOneY + fltRuneHeight > fltRuneThreeY
        && fltRuneOneY < fltRuneThreeY + fltRuneHeight) {
      fltRuneOneDirectionX = fltRuneOneDirectionX * -1;
      fltRuneThreeDirectionX = fltRuneThreeDirectionX * -1;
    } else if (fltRuneOneX + fltRuneWidth > fltRuneThreeX && fltRuneOneX < fltRuneThreeX + fltRuneWidth
        && fltRuneOneY + fltRuneHeight + fltRuneSpeed > fltRuneThreeY
        && fltRuneOneY + fltRuneSpeed < fltRuneThreeY + fltRuneHeight) {
      fltRuneOneDirectionY = fltRuneOneDirectionY * -1;
      fltRuneThreeDirectionY = fltRuneThreeDirectionY * -1;
    }

    if (fltRuneOneX + fltRuneWidth + fltRuneSpeed > fltRuneTwoX
        && fltRuneOneX + fltRuneSpeed < fltRuneTwoX + fltRuneWidth && fltRuneOneY + fltRuneHeight > fltRuneTwoY
        && fltRuneOneY < fltRuneTwoY + fltRuneHeight) {

      fltRuneOneDirectionX = fltRuneOneDirectionX * -1;
      fltRuneTwoDirectionX = fltRuneTwoDirectionX * -1;
    } else if (fltRuneOneX + fltRuneWidth > fltRuneTwoX && fltRuneOneX < fltRuneTwoX + fltRuneWidth
        && fltRuneOneY + fltRuneHeight + fltRuneSpeed > fltRuneTwoY
        && fltRuneOneY + fltRuneSpeed < fltRuneTwoY + fltRuneHeight) {

      fltRuneOneDirectionY = fltRuneOneDirectionY * -1;
      fltRuneTwoDirectionY = fltRuneTwoDirectionY * -1;
    }

    if (fltRuneTwoX + fltRuneWidth + fltRuneSpeed > fltRuneThreeX
        && fltRuneTwoX + fltRuneSpeed < fltRuneThreeX + fltRuneWidth && fltRuneTwoY + fltRuneHeight > fltRuneThreeY
        && fltRuneTwoY < fltRuneThreeY + fltRuneHeight) {
      fltRuneTwoDirectionX = fltRuneTwoDirectionX * -1;
      fltRuneThreeDirectionX = fltRuneThreeDirectionX * -1;
    } else if (fltRuneTwoX + fltRuneWidth > fltRuneThreeX && fltRuneTwoX < fltRuneThreeX + fltRuneWidth
        && fltRuneTwoY + fltRuneHeight + fltRuneSpeed > fltRuneThreeY
        && fltRuneTwoY + fltRuneSpeed < fltRuneThreeY + fltRuneHeight) {
      fltRuneTwoDirectionY = fltRuneTwoDirectionY * -1;
      fltRuneThreeDirectionY = fltRuneThreeDirectionY * -1;
    }

    if (x > width - 10) {
      pct = (float) 0.0;
      beginX = x; 
      beginY = y;
      endX = -10;
      endY = 500;
      distX = endX - beginX;
      distY = endY - beginY;
    } else if (x < 10){
      pct = (float) 0.0;
      beginX = x;
      beginY = y;
      endX = 590;
      endY = 590;
      distX = endX - beginX;
      distY = endY - beginY;
    } else if (y > height - 20 && x > width - 20){
      pct = (float) 0.0;
      beginX = x;
      beginY = y;
      endX = 4;
      endY = 4;
      distX = endX - beginX;
      distY = endY - beginY;
    } else if (x < 15 && y < 10){
      pct = (float) 0.0;
      beginX = x;
      beginY = y;
      object();
    }

  }

  // define other methods down here.
  private void runeOne() {
    image(imgRuneSymbolOne, fltRuneOneX, fltRuneOneY);
    fltRuneOneX = fltRuneOneX + (fltRuneSpeed * fltRuneOneDirectionX);
    fltRuneOneY = fltRuneOneY + (fltRuneSpeed * fltRuneOneDirectionY);

    if (fltRuneOneX > width - 55 || fltRuneOneX < 0) {
      fltRuneOneDirectionX = fltRuneOneDirectionX * -1;

    } else if (fltRuneOneY > height - 85 || fltRuneOneY < 0) {
      fltRuneOneDirectionY = fltRuneOneDirectionY * -1;
    }
  }

  private void runeTwo() {
    image(imgRuneSymbolTwo, fltRuneTwoX, fltRuneTwoY);
    fltRuneTwoX = fltRuneTwoX + (fltRuneSpeed * fltRuneTwoDirectionX);
    fltRuneTwoY = fltRuneTwoY + (fltRuneSpeed * fltRuneTwoDirectionY);
    if (fltRuneTwoX > width - 55 || fltRuneTwoX < 0) {
      fltRuneTwoDirectionX = fltRuneTwoDirectionX * -1;
    } else if (fltRuneTwoY > height - 85 || fltRuneTwoY < 0) {
      fltRuneTwoDirectionY = fltRuneTwoDirectionY * -1;
    }
  }

  private void runeThree() {
    image(imgRuneSymbolThree, fltRuneThreeX, fltRuneThreeY);
    fltRuneThreeX = fltRuneThreeX + (fltRuneSpeed * fltRuneThreeDirectionX);
    fltRuneThreeY = fltRuneThreeY + (fltRuneSpeed * fltRuneThreeDirectionY);
    if (fltRuneThreeX > width - 55 || fltRuneThreeX < 0) {
      fltRuneThreeDirectionX = fltRuneThreeDirectionX * -1;
    } else if (fltRuneThreeY > height - 85 || fltRuneThreeY < 0) {
      fltRuneThreeDirectionY = fltRuneThreeDirectionY * -1;
    }
  }

  private void object() {
    pct += step;
    if (pct < 1.0) {
      x = beginX + (pct * distX);
      y = (float) (beginY + (Math.pow(pct, exponent) * distY));
    }
    fill(255);
    ellipse(x, y, 20, 20);
    }
  }
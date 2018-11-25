//Tester.java
//Dylan Taylor

import javax.swing.*;

public class Tester
{
 public static void main(String [] args)
 {
  new Tester();
 }
 public Tester()
 {
  LevelBuilder levelBuilder = new LevelBuilder();
  Level[] levels = levelBuilder.getLevels();
  JFrame frame = new JFrame();
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setTitle("Gravity Golf");
  GravityGolf currentLevel = new GravityGolf(levels[2]);
  frame.add(currentLevel);
  currentLevel.setDoubleBuffered(true);
  currentLevel.canCheat(true);
  frame.pack();
  frame.setVisible(true);
  frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
 }
}
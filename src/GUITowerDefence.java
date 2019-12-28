import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Basic GUI for very basic "Tower Defence" game
 */
public class GUITowerDefence<List> extends JFrame {

  // A map that assigns a panel to each position in the game
  private Map<Position, JPanel> positionPanels = new HashMap<>();

  // The size of each position panel
  private static final int POSITION_SIZE = 100;

  // A timer that will automatically advance the game each second.
  private final Timer timer;
  private static final int SPEED = 1000;
  private static final int PAUSE = 3000;

  // A representation of the complete game
  public TowerDefenceLevel level;
  public Monster benny;
  public Position[][] panels = new Position[9][9];
  public Tower[] towers = new Tower[0];
  java.util.List<Position> towerList = new ArrayList<Position>();

  public static void main(String[] args) {

    // Change this to try out different levels
    TowerDefenceLevel level = TowerDefenceLevel.buildSpiralLevel();








    // Create the GUI and set it to be visible
    GUITowerDefence gui = new GUITowerDefence(level);
    gui.setVisible(true);

  }


  public GUITowerDefence(TowerDefenceLevel level) {

    this.setTitle("Tower Defence");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.level = level;
    this.benny = new Monster(level);
    int levelHeight = level.getHeight();
    int levelWidth = level.getWidth();

    this.setSize(levelWidth*POSITION_SIZE, levelHeight*POSITION_SIZE);
    this.setResizable(false);

    // A 'main panel' that contains all other components of the GUI
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(levelHeight, levelWidth));
    this.add(mainPanel);

    for (int row = 0; row < levelHeight; row++) {
      for (int col = 0; col < levelWidth; col++) {
        JPanel positionPanel = new JPanel();
        positionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        if(level.checkPassable(row, col)){
          positionPanel.setBackground(Color.WHITE);
        } else {
          JButton myButton = new JButton();
          positionPanel.setBackground(Color.GREEN);
          positionPanel.add(myButton);
          myButton.setPreferredSize(new Dimension(100, 90));
          myButton.setOpaque(false);
          myButton.setContentAreaFilled(false);
          myButton.setBorderPainted(false);
          int finalRow = row;
          int finalCol = col;
          myButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            positionPanel.add(buildTowerLabel());
            positionPanel.remove(myButton);
            int i = towers.length;
            increaseSize();
            towers[i] = new Tower(level.getPosition(finalRow, finalCol),level.firePower);


          }
          });

        }
        mainPanel.add(positionPanel);

        // Add the panel to the 'positionPanels' map so we can access it
        // later (with positionPanels.get(position)).
        Position position = level.getPosition(row, col);
        panels[row][col] = position;
        positionPanels.put(position, positionPanel);
      }
    }




    // Start the timer and set it to call the event loop each second
    EventLoop loop = new EventLoop();
    timer = new Timer(SPEED, loop);
    timer.setInitialDelay(PAUSE);
    timer.start();

  }

  // ---------- Event handling --------------------

  class EventLoop implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

      // Here you can implement the logic to advance the game by one step
      // and update the GUI.



      for(int i = 0; i < towers.length; i++){
          java.util.List<Position> list = new ArrayList<Position>(Arrays.asList(towers[i].reach));
          if (list.contains(benny.currentPos)){
            benny.healthPoints= benny.healthPoints-towers[i].attack();
        }
      }
      System.out.println(benny.healthPoints);
      updateUI();
      benny.move();








      boolean gameOver = false;
      if (benny.currentPos.equals(new Position(level.targetRow,level.targetCol))){
        gameOver = true;
      } else if (benny.healthPoints == 0){
        gameOver = true;
      }

      if (gameOver && benny.healthPoints == 0) {
        setTitle("Game over! You win!");
        timer.stop();
      } else if (gameOver){
        setTitle("Game over! You Lose!");
        timer.stop();
      }
      // These two commands are necessary to properly
      // display all the updated elements of the GUI.
      revalidate();
      repaint();

    }
  }

  // ----------- Helper methods ---------------------

  // Helper method to construct a JLabel with a given image
  private void updateUI(){
    Position k = panels[benny.currentPos.row][benny.currentPos.col];
    Position h = panels[benny.previousPos.row][benny.previousPos.col];
    positionPanels.get(h).removeAll();
    positionPanels.get(k).add(buildMonsterPanel(benny.healthPoints));
  }
  private void increaseSize(){
    Tower[] temp = new Tower[towers.length+1];
    for (int i = 0; i < towers.length; i++){
      temp[i] = towers[i];
    }
    towers = temp;
  }
  private JLabel getIconLabel(String fileName) {
    URL url = this.getClass().getResource(fileName);
    ImageIcon ii = new ImageIcon(url);
    return new JLabel(ii);
  }

  // Just some examples, you can change them however you like.
  private JPanel buildTowerLabel() {
    JPanel panel = new JPanel();
    panel.setBackground(Color.green);
    panel.setLayout(new BorderLayout());

    JLabel monsterIcon = getIconLabel("icons/tower-icon.png");
    panel.add(monsterIcon, BorderLayout.CENTER);
    return panel;
  }

  private JPanel buildMonsterPanel(int monsterHealth) {
    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    panel.setLayout(new BorderLayout());
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    JLabel monsterIcon = getIconLabel("icons/monster10.gif");
    panel.add(monsterIcon, BorderLayout.CENTER);

    JLabel healthLabel = new JLabel(Integer.toString(monsterHealth));
    healthLabel.setFont(new Font("Serif", Font.BOLD, 10));
    panel.add(healthLabel, BorderLayout.SOUTH);

    return panel;
  }

}

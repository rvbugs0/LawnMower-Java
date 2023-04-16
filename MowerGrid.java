import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;

public class MowerGrid implements MowerStateChangedListener {

  private JPanel gridPanel;
  private JPanel[] tilePanels; // Array to store tile panels
  Border tileBorder = BorderFactory.createLineBorder(Color.BLACK);

  Color darkGreen = new Color(0, 100, 0);

  private final int n;
  private final int m;

  public MowerGrid(int n, int m) {
    this.n = n;
    this.m = m;
    initializeComponents();
  }

  public void initializeComponents() {
    gridPanel = new JPanel(new GridLayout(n, m));
    tilePanels = new JPanel[n * m]; // Initialize tilePanels array
    for (int i = 0; i < n * m; i++) {
      JPanel tilePanel = new JPanel();
      tilePanel.setBackground(darkGreen);
      tilePanel.setBorder(tileBorder); // Set the border
      tilePanels[i] = tilePanel; // Store tile panel in array
      gridPanel.add(tilePanel);
    }
  }

  public JPanel getPanel() {
    return this.gridPanel;
  }

  public void mowerMoved(MowerEvent mowerEvent) {
    int x = mowerEvent.getLocation().getX();
    int y = mowerEvent.getLocation().getY();

    if (x >= n && y >= m) {
      return;
    }
    int tileIndex = x * n + y;
    // System.out.println("Colored "+x+","+y);
    tilePanels[tileIndex].setBackground(Color.GREEN);
  }

  public void stopButtonClicked(MowerEvent mowerEvent) {}

  public void startButtonClicked(MowerEvent mowerEvent) {
    // tilePanels[0].setBackground(Color.GREEN);
  }
}

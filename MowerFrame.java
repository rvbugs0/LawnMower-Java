import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;

public class MowerFrame {

  private ArrayList<MowerStateChangedListener> listeners = new ArrayList<>();
  private Location currentLocation;
  enum STATE {
    STOPPED,RUNNING,RESET
  };
  private STATE currentState = STATE.RESET;
  private JFrame frame;
  private JPanel panel;
  private JButton startButton;
  private JButton stopButton;
  private Timer timer;

  private final int n;
  private final int m;

  public MowerFrame(int n, int m) {
    this.n = n;
    this.m = m;
    initializeComponents();
  }

  public void addGrid(MowerGrid grid) {
    frame.add(grid.getPanel(), BorderLayout.CENTER);
    panel.revalidate();
    panel.repaint();
  }

  public void initializeComponents() {
    currentLocation = new Location(-1, -1, n, m);
    panel = new JPanel();
    frame = new JFrame("Swing Frame Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);

    startButton = new JButton("Start");
    
    startButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          System.out.println("Start button clicked.");

          notifyStartPressed();
        }
      }
    );

    stopButton = new JButton("Stop");
    stopButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

          currentState = STATE.STOPPED;

        }
      }
    );

    // Creating grid panel

    // Adding buttons to the frame

    panel.setLayout(new FlowLayout());
    panel.add(startButton);
    panel.add(stopButton);
    frame.add(panel, BorderLayout.NORTH);
    frame.setVisible(true);
  }

  public void addListener(MowerStateChangedListener l) {
    if (l != null) {
      listeners.add(l);
    }
  }

  public void removeListener(MowerStateChangedListener l) {
    listeners.remove(l);
  }

  public static void main(String[] args) {
    MowerFrame mowerFrame = new MowerFrame(5, 5);
    MowerGrid mowerGrid = new MowerGrid(5, 5);
    mowerFrame.addGrid(mowerGrid);
    mowerFrame.addListener(mowerGrid);
  }

  private boolean makeMove() {
    // System.out.println(currentLocation.getX()+","+currentLocation.getY()+" - current");
    boolean moved = currentLocation.next();

    if (moved) {
      notifyMoveToListeners();
      // System.out.println("Moved to - " + currentLocation.getX() + "," + currentLocation.getY());
    } else {
      System.out.println("No more moves");
    }
    return moved;
  }

  private void notifyMoveToListeners() {
    MowerEvent mowerEvent = new MowerEvent(currentLocation);
    for (MowerStateChangedListener l : listeners) {
      l.mowerMoved(mowerEvent);
    }
  }

  private void notifyStartPressed() {
    currentState = STATE.RUNNING;
    timer =
      new Timer(
        1000,
        new ActionListener() {
          int count = 0;

          @Override
          public void actionPerformed(ActionEvent e) {
            if(currentState==STATE.STOPPED){
              timer.stop();
            }
            if (currentState==STATE.RUNNING && !makeMove()) {
              timer.stop();
            }
          }
        }
      );
    // Change color of first tile to red
    timer.start();
  }
}

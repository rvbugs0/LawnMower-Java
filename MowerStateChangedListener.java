public interface MowerStateChangedListener {
  public void stopButtonClicked(MowerEvent mowerEvent);
  public void startButtonClicked(MowerEvent mowerEvent);
  public void mowerMoved(MowerEvent mowerEvent);
}

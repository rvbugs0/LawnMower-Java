class Run {

  public static void main(String[] args) {
    MowerFrame mowerFrame = new MowerFrame(5, 5);
    MowerGrid mowerGrid = new MowerGrid(5, 5);
    mowerFrame.addGrid(mowerGrid);
    mowerFrame.addListener(mowerGrid);
  }
}

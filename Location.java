public class Location {

  private int x;
  private int y;
  private int n =-1;
  private int m= -1;

  public Location(int xLocation, int yLocation, int n, int m) {
    this.n=n;
    this.m=m;
    this.x = xLocation;
    this.y = yLocation;
  }

  public boolean next(){

    if(x==n-1 && y==m-1){
      return false;
    }
    if(x==-1 && y==-1){
      x= 0;
      y=0;
      return true;
    }

    if(x<n && x%2==0){
      // moving left to right 
      
      if(y<m-1){
        // row not mowed completely
        y++;
      }
      else if(y==m-1){
        // row mowed completely
        x++;
        
      }
      return true;
    }
    if(x<n && x%2!=0){
      if(y>0){
        y--;
      }
      else if(y==0){
        x++;
        
      }
      return true;
      
    }
    return false;



  }


  public Location(int xLocation, int yLocation) {
    this.x = xLocation;
    this.y = yLocation;
  }




  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }


}

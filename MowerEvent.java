public class MowerEvent {

    private Location location;
    



    public MowerEvent(Location location){
        this.location = new Location(location.getX(),location.getY());
    }

    public Location getLocation(){
        return this.location;
    }

}

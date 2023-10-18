public class Vehicle {
    public int vehicleId;
    public boolean leftRight;

    //Always leftmost x coord which is lowest actual value
    public int xCoord;

    //Always highest on grid y value which is lowest actual value
    public int yCoord;

    public int length;

    @Override
    public Vehicle clone(){
        Vehicle clonedVehicle = new Vehicle();
        clonedVehicle.xCoord = this.xCoord;
        clonedVehicle.yCoord = this.yCoord;
        clonedVehicle.length = this.length;
        clonedVehicle.vehicleId = this.vehicleId;
        clonedVehicle.leftRight = this.leftRight;
        return clonedVehicle;
    }
}

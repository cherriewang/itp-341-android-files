package itp341.wang.cherrie.a8.model;

import java.io.Serializable;

/**
 * Created by Cherrie on 10/23/16.
 */

public class Ticket implements Serializable{
    // starting location
    private String startLoc;
    // ending location
    private String endLoc;
    // trip type
    private String tripType;
    // priorities
    private String priority;
    // radio id
    private int radioBtnId_type;
    private int radioBtnId_priority;

    // CONSTRUCTOR
    public Ticket () {
        super();
    }

    // GETTERS
    public String getStartLoc() {
        return startLoc;
    }
    public String getEndLoc() {
        return endLoc;
    }
    public String getTripType() {
        return tripType;
    }
    public String getPriority() {
        return priority;
    }
    public int getRadioBtnId_type(){
        return radioBtnId_type;
    }
    public int getRadioBtnId_priority(){
        return radioBtnId_priority;
    }
    // SETTERS
    public void setEndLoc(String endLoc) {
        this.endLoc = endLoc;
    }

    public void setStartLoc(String startLoc) {
        this.startLoc = startLoc;
    }
    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public void setRadioBtnId_type(int radioBtnId_type) {
        this.radioBtnId_type = radioBtnId_type;
    }

    public void setRadioBtnId_priority(int radioBtnId_priority) {
        this.radioBtnId_priority = radioBtnId_priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}

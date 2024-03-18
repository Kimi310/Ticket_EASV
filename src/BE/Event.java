package BE;

public class Event {
    private int id;
    private String name;
    private String time;
    private String location;
    private String notes;
    private String endDate;
    private String locationGuidance;

    public Event(int id, String name, String time, String location, String notes, String endDate, String locationGuidance) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.location = location;
        this.notes = notes;
        this.endDate = endDate;
        this.locationGuidance = locationGuidance;
    }

    //test event addition, will delete later
    public Event(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getNotes() {
        return notes;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setLocationGuidance(String locationGuidance) {
        this.locationGuidance = locationGuidance;
    }

    public String getLocationGuidance() {
        return locationGuidance;
    }
}

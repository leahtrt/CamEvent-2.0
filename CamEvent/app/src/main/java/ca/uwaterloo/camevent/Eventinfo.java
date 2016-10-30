package ca.uwaterloo.camevent;

/**
 * Created by sound on 2016/10/24.
 */
public class Eventinfo {
    private String EventTitle;
    private String EventLocationName;
    private String EventLatitude;
    private String EventLongitude;
    private String  EventDescriptionRow;
    private String  EventLink;
    private String  EventDate;
    public Eventinfo() {

    }
    public  Eventinfo(String eventTitle,String eventLocationName,String eventLatitude,String eventLongitude,String eventDescriptionRow,String eventLink,String eventDate) {
        this.EventTitle=eventTitle;
        this.EventLocationName=eventLocationName;
        this.EventLatitude=eventLatitude;
        this.EventLongitude=eventLongitude;
        this.EventDescriptionRow=eventDescriptionRow;
        this.EventLink=eventLink;
        this.EventDate=eventDate;
    }
    public String getEventDescriptionRow() {
        return EventDescriptionRow;
    }

    public void setEventDescriptionRow(String eventDescriptionRow) {
        EventDescriptionRow = eventDescriptionRow;
    }

    public String getEventTitle() {
        return EventTitle;
    }

    public void setEventTitle(String eventTitle) {
        EventTitle = eventTitle;
    }

    public String getEventLocationName() {
        return EventLocationName;
    }

    public void setEventLocationName(String eventLocationName) {
        EventLocationName = eventLocationName;
    }

    public String getEventLatitude() {
        return EventLatitude;
    }

    public void setEventLatitude(String eventLatitude) {
        EventLatitude = eventLatitude;
    }

    public String getEventLongitude() {
        return EventLongitude;
    }

    public void setEventLongitude(String eventLongitude) {
        EventLongitude = eventLongitude;
    }

    public String getEventLink() {
        return EventLink;
    }

    public void setEventLink(String eventLink) {
        EventLink = eventLink;
    }

    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String eventDate) {
        EventDate = eventDate;
    }


}

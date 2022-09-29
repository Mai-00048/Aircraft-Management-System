package SAAMS;
import SAAMS.FlightDescriptor;
import SAAMS.Itinerary;
import SAAMS.PassengerDetails;
import SAAMS.PassengerList;

// Generated by Together


/**
 * An individual aircraft management record:
 * Either FREE or models an aircraft currently known to SAAMS.
 * See MRState diagram for operational details, and written documentation.
 * This class has public static int identifiers for the individual status codes.
 * An MR may be "FREE", or may contain a record of the status of an individual aircraft under the management of SAAMS.
 * An instance of AircraftManagementDatabase holds a collection of ManagementRecords, and sends the ManagementRecords messages to control/fetch their status.
 *
 * @stereotype entity
 * @url element://model:project::SAAMS/design:node:::id15rnfcko4qme4cko4swib.node107
 * @url element://model:project::SAAMS/design:view:::id3oolzcko4qme4cko4sx40
 * @url element://model:project::SAAMS/design:view:::id4tg7xcko4qme4cko4swuu
 * @url element://model:project::SAAMS/design:node:::id4tg7xcko4qme4cko4swuu.node152
 * @url element://model:project::SAAMS/design:node:::id3oolzcko4qme4cko4sx40.node171
 * @url element://model:project::SAAMS/design:view:::id2wdkkcko4qme4cko4svm2
 * @url element://model:project::SAAMS/design:view:::id15rnfcko4qme4cko4swib
 * @url element://model:project::SAAMS/design:node:::id2wdkkcko4qme4cko4svm2.node41
 */
public class ManagementRecord {

    public static int FREE = 0;
    
    
    public static int IN_TRANSIT = 1;
    public static int WANTING_TO_LAND = 2;
    public static int GROUND_CLEARANCE_GRANTED = 3;
    public static int LANDING = 4;
    public static int LANDED = 5;
    public static int TAXIING = 6;
    public static int UNLOADING = 7;
    public static int READY_CLEAN_AND_MAINT = 8;
    public static int FAULTY_AWAIT_CLEAN = 9;
    public static int OK_AWAIT_CLEAN = 11;
    public static int CLEAN_AWAIT_MAINT = 10;
    public static int AWAIT_REPAIR = 12;
    public static int READY_REFUEL = 13;
    public static int READY_PASSENGERS = 14;
    public static int READY_DEPART = 15;
    public static int AWAITING_TAXI = 16;
    public static int AWAITING_TAKEOFF = 17;
    public static int DEPARTING_THROUGH_LOCAL_AIRSPACE = 18;

    private int status; //status for Managment recored (MR)
    private int gateNumber; //gate number
    private String flightCode;
    private Itinerary itinerary;
    private PassengerList passengerList;
    private String faultDescription;


// for change the status 
    public void setStatus(int newStatus) {
        status = newStatus;
    }

// get the status of MR
    public int getStatus() {
        return status; //Status code
    }


    public String getStatus(int status) {
        switch (status) { // it will returen the status in MR
            case 0:
                return "FREE";
            case 1:
                return "IN_TRANSIT";
            case 2:
                return "WAITING_TO_LAND";
            case 3:
                return "GROUND_CLEARANCE_GRANTED";
            case 4:
                return "LANDING";
            case 5:
                return "LANDED";
            case 6:
                return "TAXING";
            case 7:
                return "UNLOADING";
            case 8:
                return "READY_CLEAN_AND_MAINT";
            case 9:
                return "FAULTY_AWAIT_CLEAN";
            case 10:
                return "CLEAN_AWAIT_MAINT";
            case 11:
                return "OK_AWAIT_CLEAN";
            case 12:
                return "AWAIT_REPAIR";
            case 13:
                return "READY_REFUEL";
            case 14:
                return "READY_PASSENGERS";
            case 15:
                return "READY_DEPART";
            case 16:
                return "AWAITING_TAXI";
            case 17:
                return "AWAITING_TAKEOFF";
            case 18:
                return "DEPARTING_THROUGH_LOCAL_AIRSPACE";
            default:
                return "UNKNOWN";
        }
    }


    public String getFlightCode() {
        return flightCode; // flight code
    }

    public void radarDetect(FlightDescriptor fd) { // should the first status be FREEv = 0 then change regarding the status in MR
        if (status == 0) {
            flightCode = fd.getFlightCode();
            passengerList = fd.getList();
            itinerary = fd.getItinerary();
            if (itinerary.getTo().equals("Muscat")) {
                status = 2;
            } else {
                status = 1;
            }
        }
    }

  
    public int getGateNumber() { // gate number in MR
        return gateNumber;
    }


    public void radarLostContact() {
        if (status == 1 || status == 18) {
            status = 0;
            flightCode = "";
            passengerList = null;
            faultDescription = "";
            itinerary = null;
            gateNumber = -1;
        }
    }

    public void taxiTo(int gateNumber) {
        if (status == 5) {
            status = 6;
            this.gateNumber = gateNumber;
        } 
    }

    public void addPassenger(PassengerDetails details) {
        passengerList.addPassenger(details);
    } 

    public PassengerList getPassengerList() {
        return passengerList;
    } 

  
    public Itinerary getItinerary() {
        return itinerary;
    }
 
    @Override
    public String toString() {
        if (!(itinerary.getNext() == null)) {
            return " From: " + itinerary.getFrom() + " Landing At: " + itinerary.getTo() + " Next Stop: " + itinerary.getNext();
        } else {
            return  " From: " + itinerary.getFrom() + " Landing At: " + itinerary.getTo();
        } 
    }
    
public void faultsFound(String description){
      
        faultDescription = description;

    if (getStatus() == READY_CLEAN_AND_MAINT) {
            setStatus(FAULTY_AWAIT_CLEAN);
        } 
else if (getStatus() == CLEAN_AWAIT_MAINT) {
            setStatus(AWAIT_REPAIR);
        } 

else {
            throw new IllegalStateException("The plane is still not signed for MAINT maintenance");
        }

        
  }

} 
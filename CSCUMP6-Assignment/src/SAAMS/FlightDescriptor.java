package SAAMS;
import SAAMS.Itinerary;
import SAAMS.PassengerList;

// Generated by Together

public class FlightDescriptor {
    
    private PassengerList list;
    private Itinerary itinerary;
    private String flightCode;
    
    public FlightDescriptor(String flightCode, Itinerary itinerary, PassengerList list) {
        this.flightCode = flightCode;
        this.itinerary = itinerary;
        this.list = list;
    }

    public PassengerList getList() {
        return list; //method will returen Passenger List.
    }

    public Itinerary getItinerary() {
        return itinerary; //method will returen Itinerary of plane.
    } 

    public String getFlightCode() {
        return flightCode; //method will returen flight code of plane.
    } 

} 
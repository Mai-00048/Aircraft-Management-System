package SAAMS;
import java.awt.TextField;
import java.awt.event.ActionEvent;



/**
 * The Main class.
 *
 * The principal component is the usual main method required by Java application to launch the application.
 *
 * Instantiates the databases.
 * Instantiates and shows all the system interfaces as Frames.
 * @stereotype control
 */
public class Main {




public static void main(String[] args) {
  
        AircraftManagementDatabase aircraftManagementDatabase = new AircraftManagementDatabase();
        GateInfoDatabase gateInfoDatabase = new GateInfoDatabase();   
         
        final PassengerList list = new PassengerList();
     /**
      * load user info and flights
      */  
        list.addPassenger(new PassengerDetails("Mai Hamed"));
        list.addPassenger(new PassengerDetails("Anwaar Sarhan"));
        list.addPassenger(new PassengerDetails("Malik AbdulMalik"));
        list.addPassenger(new PassengerDetails("Talal Ahmed"));    

        
aircraftManagementDatabase.radarDetect(new FlightDescriptor("CDC789",new Itinerary("Canada", "Dubai", "Qatar"),new PassengerList()));
aircraftManagementDatabase.radarDetect(new FlightDescriptor("HGF986",new Itinerary("Canada", "Muscat", "Qatar"),new PassengerList()));


//------------------------------|GUI screens|--------------------------------\\

LATC ltc = new LATC(aircraftManagementDatabase, gateInfoDatabase); 
GOC goc = new GOC( aircraftManagementDatabase,  gateInfoDatabase);
RefuellingSupervisor RefSuper = new RefuellingSupervisor(aircraftManagementDatabase); 
RadarTransceiver radarTrans = new RadarTransceiver(aircraftManagementDatabase); 
GateConsole cnsl0 = new GateConsole(aircraftManagementDatabase,gateInfoDatabase, 0);
GateConsole cnsl1 = new GateConsole(aircraftManagementDatabase,gateInfoDatabase,1);
CleaningSupervisor CleaningSup = new CleaningSupervisor(aircraftManagementDatabase, gateInfoDatabase); 
Maintenanceispector MntnancSpctr = new Maintenanceispector(aircraftManagementDatabase, gateInfoDatabase);     
PublicInfo pub = new PublicInfo(aircraftManagementDatabase,gateInfoDatabase);
    
   
{

}

    
}}
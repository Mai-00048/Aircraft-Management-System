package SAAMS;
import java.util.ArrayList;

// Generated by Together

//------------------------| class name|----------------------------\\
public class PassengerList {

private ArrayList<PassengerDetails> details; //array of paasenger list

//------------------------(Counstructer)---------------------------\\
public PassengerList() {
        this.details = new ArrayList<>();
    }

    public ArrayList<PassengerDetails> getDetails() {
        return details; //flight passengers details
    }

  public void addPassenger(PassengerDetails details){ //recored the passenger detauils
        this.details.add(details);
  }
  
      public int getNumberOfAllPassengers() { //how many passengers in the flightts
        return details.size();
    }
      
      //remove passenger from the array
public void ClearPassenger(){
     details = new ArrayList<>();
}
}
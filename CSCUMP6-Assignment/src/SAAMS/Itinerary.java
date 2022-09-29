package SAAMS;

public class Itinerary {
    
    //declared FROM, TO, NEXT
    private String from;
     private String to;
     private String next;
  public Itinerary(String from, String to, String next){
      this.from= from;
      this.next= next;
      this.to= to;
  }

  
  public String getFrom(){ // method will return the from flight
      return from;
  }

  public String getTo(){ // method will return the to flight
      return to;
  }

  public String getNext(){ // method will return the Next flight
      return next;
  }

 

}

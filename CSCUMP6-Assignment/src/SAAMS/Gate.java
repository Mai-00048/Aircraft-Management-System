package SAAMS;
import java.util.Observable;

public class Gate extends Observable  {
  public static int FREE = 0;
  public static int RESERVED = 1;
  public static int OCCUPIED = 2;
  private int status = FREE;
  int mCode;
  
    public Gate() { //constructer
        status = FREE;
    }

 
 public int getStatus(){ //get the gate ststus then return status
      return status;
  }


    public int getmCode() {
        return mCode;
    }
//it will change the status of the gate from FREE to RESERVED
public void allocate(int mCode){ // status =  FREE
            if (status == FREE) {
            status = RESERVED;
            this.mCode = mCode;
            setChanged();
            notifyObservers();
        } else {
            throw new IllegalStateException("ERROR, The Gate you have choose is NOT FREE");
        }

  }

public void docked(){
    //it will change the status of the gate from RESERVED to OCCUPIED
      if (status == RESERVED) { // status =  RESERVED
            status = OCCUPIED;
            setChanged();
            notifyObservers();
        } else {
            throw new IllegalStateException("ERROR, The Gate you have choose is NOT RESERVED");
        }

  }


  public void departed(){
         //it will change the status of the gate from OCCUPIED to FREE
      if (status == OCCUPIED) { // status =  OCCUPIED
            status = FREE;
            setChanged();
            notifyObservers();
        } else {
            throw new IllegalStateException("ERROR, The Gate you have choose is NOT OCCUPIED");
        }

  }


    protected void setChanged() {
     
    }

    public void notifyObservers() {

    }
    
          @Override
    public String toString() {
        return String.valueOf(status);
    }

}

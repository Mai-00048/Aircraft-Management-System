package SAAMS;

import SAAMS.AircraftManagementDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.gray;
import static java.awt.Color.green;
import static java.awt.Color.red;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import static java.util.GregorianCalendar.AD;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


//---------------------------------(LATC Class)-------------------------------\\

public class LATC extends JFrame implements ActionListener , Observer{ 
   
    //declared buttons
    JButton BtnDwnlod;  
    JButton BtnWntingToLand;
    JButton BtnLanded;
    JButton BtnTakeOff;
    JButton BtnTakePrmitTxi;
    JButton BtnDsplyInfo;
    JButton BtnLanding;
    JButton BtndepAird;
   

    //declared JLabel
    JLabel JlControls;
    JLabel JLInbound;
    JLabel JLOutBund;
    JLabel JLPlaneDetails;
    JLabel JLGateStatus;
    JLabel JLPlanes;
    
    //declared JList
    JList list1;

    JTextArea TextArea;
    DefaultListModel<String> FlighCode = new DefaultListModel();
    DefaultListModel<String> FlightInfo = new DefaultListModel<>();
    private AircraftManagementDatabase AD;
    private GateInfoDatabase GID;
    private int managementRecordIndex = -1;
    
    public LATC(AircraftManagementDatabase AD, GateInfoDatabase GID) {
        
             this.AD = AD;
             this.GID = GID;
             
          JPanel pan = new JPanel(new BorderLayout());
          setTitle("Local Air Traffic Controller");
          setLocation(100, 300);
          Container window = getContentPane();
       
          TitledBorder border = new TitledBorder("Local Air Traffic Controls"); //<-- border for gui
          border.setTitleJustification(TitledBorder.CENTER);
          border.setTitlePosition(TitledBorder.TOP);
          
          TitledBorder border2 = new TitledBorder("");//<-- border for JList one
          border2.setTitleColor(Color.BLACK);
          
          TitledBorder border3 = new TitledBorder("");//<-- border for JList two
          TitledBorder border4 = new TitledBorder("");//<-- border for JList three

       
          //-------------------(Border Left and right)--------------------------\\        
          //left panel
          JPanel JpanelLeft = new JPanel(); 
          JpanelLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
          JpanelLeft.setPreferredSize(new Dimension(2,97)); //set the size of left panel
          JpanelLeft.setSize(10, 10);
          JpanelLeft.setPreferredSize(new Dimension(-70, 100));
          
         //right panel
          JPanel JpaneRight = new JPanel(); 
          JpaneRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
          JpaneRight.setPreferredSize(new Dimension(10,19)); // set the size of the right panel
          
          JpaneRight.setSize(10, 10);

          pan.setLayout(new BoxLayout(pan,BoxLayout.X_AXIS));
          pan.setBorder(border);
          
                    
          //border line left screen
          JpanelLeft.setBorder(BorderFactory.createTitledBorder(
          BorderFactory.createEtchedBorder(), "", TitledBorder.LEFT, TitledBorder.TOP));
          
                    
          //border line left screen
          JpaneRight.setBorder(BorderFactory.createTitledBorder(
          BorderFactory.createEtchedBorder(), "", TitledBorder.LEFT, TitledBorder.TOP));
          

          
        JLPlanes = new JLabel("               Planes");
        JpanelLeft.add(JLPlanes);  
        
         list1 = new JList<>(FlighCode);      
         list1.setBounds(100,100, 85,85);      
         JpanelLeft.add(list1);
         list1.setPreferredSize(new Dimension(170, 391));
         list1.setBorder(border3);


         
        setSize(480,490); //size of gui
        pan.add(JpanelLeft);
        pan.add(JpaneRight);  
         window.add(pan);
  
     
        setVisible(true);
        JpanelLeft.setBackground(gray.brighter());
        JpaneRight.setBackground(gray.brighter());
        
        JlControls = new JLabel("Controls                                  ");
        JpaneRight.add(JlControls);
       
         JLInbound = new JLabel("Inbound                                  ");
         JpaneRight.add(JLInbound);
        
                 
         BtnDwnlod= new JButton("Download Flight Info");
         JpaneRight.add(BtnDwnlod);
         BtnDwnlod.addActionListener(this);
         BtnDwnlod.setPreferredSize(new Dimension(250,26));
         
         BtnDsplyInfo= new JButton("Display Flight Info");
         JpaneRight.add(BtnDsplyInfo);
         BtnDsplyInfo.addActionListener(this);
         BtnDsplyInfo.setPreferredSize(new Dimension(250,26));
                  
         BtnWntingToLand= new JButton("WANTING_TO_LAND");
         JpaneRight.add(BtnWntingToLand);
         BtnWntingToLand.addActionListener(this);
         BtnWntingToLand.setPreferredSize(new Dimension(250,26));         
         JLOutBund = new JLabel("Outbound                                  ");
         JpaneRight.add(JLOutBund);
         
         BtnLanding= new JButton("Approch Clearnce");
         JpaneRight.add(BtnLanding);
         BtnLanding.addActionListener(this);
         BtnLanding.setPreferredSize(new Dimension(250,26));
         
         BtnLanded= new JButton("Confirm Landing");
         JpaneRight.add(BtnLanded);
         BtnLanded.addActionListener(this);
         BtnLanded.setPreferredSize(new Dimension(250,26));
         
         
         BtnTakeOff= new JButton("Take off Permit");
         JpaneRight.add(BtnTakeOff);
         BtnTakeOff.addActionListener(this);
         BtnTakeOff.setPreferredSize(new Dimension(250,26));
         
         
         BtnTakePrmitTxi= new JButton("Permit Taxi");
         JpaneRight.add(BtnTakePrmitTxi);
         BtnTakePrmitTxi.addActionListener(this);
         BtnTakePrmitTxi.setPreferredSize(new Dimension(250,26));

         JLPlaneDetails = new JLabel("Plane Details                             ");
         JpaneRight.add(JLPlaneDetails);
         
         BtndepAird= new JButton("Departing through Airdrome");
         JpaneRight.add(BtndepAird);
         BtndepAird.addActionListener(this);
         BtndepAird.setPreferredSize(new Dimension(250,26));

         
          JList<String> list2 = new JList<>(FlightInfo);      
          list2.setBounds(100,100, 85,85);         
          JpaneRight.add(list2);
          list2.setPreferredSize(new Dimension(250, 140)); // size of JList (W,H)
          list2.setBorder(border3);
          
          //---------------------------------------\\
          AD.addObserver(this); // added observer 
    }
    
    @Override
    public void actionPerformed(ActionEvent e){  
        
        // method for download flights 
   if( e.getSource() == BtnDwnlod) {
          int managementRecordIndex =list1.getSelectedIndex();
            
             UpdateInfo();
  
  }
   
    // method for display the info aboutr the floght code 
      if( e.getSource() == BtnDsplyInfo) {
          
               DisplayInfo();
           
        } 
      
      //method to change the ststus of to LANDING
         if( e.getSource() == BtnWntingToLand) {       
             Lnding();
           

  }
        //method to change the ststus of to LANDED 
        if( e.getSource() == BtnLanded) {
                      

      ConfirmLonding();
    
  }
           //method to change the ststus of to TAKE OFF        
            if( e.getSource() == BtnTakeOff) {
             
   
        TakeOff();
    
  }
          
            //method to change the ststus of to AWAITING_TAXI
         if( e.getSource() == BtnTakePrmitTxi) {
          Taxi();
    
  }
                  if( e.getSource() == BtnLanding) {
          
    Lnding();
  }
                 //method to change the ststus of to DEPARTING_THROUGH_LOCAL_AIRSPACE  
                 if( e.getSource() == BtndepAird) {
          
    BtndepAird();
  }
   
             }
    
    
    // this method like filter, collect all the ststuds in it  
        public void UpdateInfo(){
                     
            
           FlightInfo.clear();
           FlighCode.clear();
           int managementRecordIndex = -1;
           FlighCode.setSize(AD.maxMRs);
          
           FlightInfo.setSize(AD.maxMRs);
           
        for (int i = 0; i < AD.maxMRs; i++) {

            ManagementRecord record = AD.getMR(i);
             
            if (record == null){
                FlighCode.set(i, null);
            break;}
            if(record.getStatus() == 1 ||record.getStatus() == 2 ||record.getStatus() == 3 ||record.getStatus() == 4||record.getStatus() == 16||record.getStatus() == 17||record.getStatus() == 15||record.getStatus() == 18){
                FlighCode.set(i,record.getFlightCode());
                FlightInfo.set(i, record.getFlightCode() + "  Status :" +record.getStatus(record.getStatus()));
            }
            
            else {

                managementRecordIndex = i;
            }
           
           
 
            }
        }
        public void Taxi(){ // add ststus number 16 
     
      managementRecordIndex = list1.getSelectedIndex();
      int assignedGate = AD.getGateNumber(managementRecordIndex);
      AD.setStatus(managementRecordIndex, 16);
      GID.departed(assignedGate);
      UpdateInfo();
   
            }
        
                public void BtndepAird(){ // add ststus number 18
     
      managementRecordIndex = list1.getSelectedIndex();
      int assignedGate = AD.getGateNumber(managementRecordIndex);
      AD.setStatus(managementRecordIndex, 18);
      GID.departed(assignedGate);
      UpdateInfo();
   
            }
        
      public void Lnding(){// add ststus number 4
     
      managementRecordIndex = list1.getSelectedIndex();
      int assignedGate = AD.getGateNumber(managementRecordIndex);
      AD.setStatus(managementRecordIndex, 4);
      GID.departed(assignedGate);
      UpdateInfo();
   
            }
      
      public void ConfirmLonding(){ // add ststus number 5
     
      managementRecordIndex = list1.getSelectedIndex();
      int assignedGate = AD.getGateNumber(managementRecordIndex);
      AD.setStatus(managementRecordIndex, 5);
      GID.departed(assignedGate);
      UpdateInfo();
   
            }
      
      public void TakeOff(){ // add ststus number 17
     
      managementRecordIndex = list1.getSelectedIndex();
      int assignedGate = AD.getGateNumber(managementRecordIndex);
      AD.setStatus(managementRecordIndex, 17);
      GID.departed(assignedGate);
      UpdateInfo();
   
            }
       
            public void DisplayInfo(){ // display all info about gthe flight code
     
      managementRecordIndex = list1.getSelectedIndex();
      int assignedGate = AD.getGateNumber(managementRecordIndex);
    
      GID.departed(assignedGate);
      UpdateInfo();
      
      
            JOptionPane.showMessageDialog(null, "Flight Code: " + AD.getFlightCode(managementRecordIndex) +
            "\nFlight Status: " + AD.getStatus(managementRecordIndex) + "\nFlight From: " +
            AD.getItinerary(managementRecordIndex).getFrom() + "\nFlight To: " + AD.getItinerary(managementRecordIndex).getTo() +
            "\n Next Trip: " + AD.getItinerary(managementRecordIndex).getNext());
   
            }
                 
  
        
    public void update(Observable o, Object arg) { //add UpdateInfo method to observer
        
        UpdateInfo();
    }     
}
    
 
      



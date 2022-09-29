package SAAMS;


import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.gray;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


//--------------------------(RadarTransceiver Class)--------------------------\\

    public class RadarTransceiver extends JFrame implements ActionListener, Observer {
    private AircraftManagementDatabase AD;   
    private PassengerList PsngRlist = new PassengerList();

    
    int messageType = -1;
    int managementRecord = -1;
    private int managementRecordIndex = -1;
    String message = "";
    String dst;
    String dst2;
    ArrayList<PassengerDetails> PD ;
    PassengerDetails passengerName ;
    StringBuilder sb = new StringBuilder();
    final JFrame frame = new JFrame("JOptionPane Demo");
    
    //declared buttons   
    JButton BtnLosRaCon;
    JButton BtnLveAirSpc;
    JButton BtnAddPsngr;
    JButton BtnDtctFlight;
    JButton BtnShowDet;
    JButton BtnShwPasngr;  
    
    //declared JLabel
    JLabel JLRadarTran;
    JLabel JLFlightCode;
    JLabel JLFlightTo;
    JLabel JLFlightFrom;
    JLabel JLFlightNext;
    JLabel JLPsngrNum;
    JLabel JLCurrentPlanes;
    JLabel JLPsngr;     
    
    //declared JTextField
    JTextField JTFlightCode;
    JTextField JTFlightTo;
    JTextField JTFlightFrom;  
    JTextField JTPsngrNum;
    JTextField JTCurrentPlanes;
    JTextField JTPsngr;
    JTextField JTFlightNext;
    
    //declared JList
    JList<String> list1;
    JList listFlightInfo ;
    JList listReceive ;
    
    DefaultListModel modelLost = new DefaultListModel(); //JList1
    DefaultListModel modelInfo = new DefaultListModel(); //JList2
    DefaultListModel modelReceive = new DefaultListModel(); //JList3
    private GateInfoDatabase GID;
    
    // Array List    
    String FC,FT,FF,PN,FN;
    final PassengerList PNGRlist = new PassengerList();
    
    //---------------------------<|(Constructors)|>---------------------------\\
    
    public RadarTransceiver(AircraftManagementDatabase AD) {
     
          this.AD = AD;      
          setTitle("Radar Transceiver");
          setLocation(300, 100);
          Container window = getContentPane();
       
          TitledBorder border = new TitledBorder("Radar Transceiver Controle"); //<-- border for gui
          border.setTitleJustification(TitledBorder.CENTER);
          border.setTitlePosition(TitledBorder.TOP);         
 
          TitledBorder border2 = new TitledBorder("");//<-- border for JList one
          border2.setTitleColor(Color.BLACK);         
          
          TitledBorder border3 = new TitledBorder("");//<-- border for JList two
          TitledBorder border4 = new TitledBorder("");//<-- border for JList three
          
          JPanel pan = new JPanel();
          pan.setLayout(new BoxLayout(pan,BoxLayout.X_AXIS));
          pan.setBorder(border);
          
          //left panel
          JPanel JpanelLeft = new JPanel(); 
          JpanelLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
          JpanelLeft.setPreferredSize(new Dimension(10,97)); //set the size of left panel
          
          //right panel
          JPanel JpaneRight = new JPanel(); 
          JpaneRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
          JpaneRight.setPreferredSize(new Dimension(50,200)); // set the size of the right panel     
         
          
          //flight code
          JLFlightCode = new JLabel("Flight Code:                          ");
          JpanelLeft.add(JLFlightCode);
          JTFlightCode = new JTextField (10);
          JpanelLeft.add(JTFlightCode);
          
          //flight to
          JLFlightTo = new JLabel("Flight To:                              ");
          JpanelLeft.add(JLFlightTo);
          JTFlightTo = new JTextField (10);
          JpanelLeft.add(JTFlightTo);
          
          //flight from          
          JLFlightFrom = new JLabel("Flight From:                          ");
          JpanelLeft.add(JLFlightFrom);
          JTFlightFrom = new JTextField (10);
          JpanelLeft.add(JTFlightFrom);
          
          
          //Flight Next
          JLFlightNext = new JLabel("Flight Next:                          ");
          JpanelLeft.add(JLFlightNext);         
          JTFlightNext = new JTextField (10);
          JpanelLeft.add(JTFlightNext);
          
          //passenger Name
          JLPsngrNum = new JLabel("Flight Passenger Name:    ");
          JpanelLeft.add(JLPsngrNum);
          
          JTPsngrNum = new JTextField (10);
          JpanelLeft.add(JTPsngrNum);

          BtnAddPsngr= new JButton("Add Passenger!");
          JpanelLeft.add(BtnAddPsngr);
          BtnAddPsngr.addActionListener(this);
          BtnAddPsngr.setPreferredSize(new Dimension(130,26));                
           
          BtnDtctFlight = new JButton(" Detect Flight!");
          JpanelLeft.add(BtnDtctFlight);
          BtnDtctFlight.addActionListener(this);          	
          BtnDtctFlight.setPreferredSize(new Dimension(130,26));
           
          BtnLosRaCon = new JButton(" Lost contact");
          JpanelLeft.add(BtnLosRaCon);
          BtnLosRaCon.addActionListener(this);
          BtnLosRaCon.setPreferredSize(new Dimension(130,26));
          
          BtnShowDet = new JButton(" Display Flight Details");
          JpanelLeft.add(BtnShowDet);
          BtnShowDet.addActionListener(this);
          BtnShowDet.setPreferredSize(new Dimension(130,26));
          
          BtnShwPasngr = new JButton(" Show Passenger ");
          JpanelLeft.add(BtnShwPasngr);
          BtnShwPasngr.addActionListener(this);
          BtnShwPasngr.setPreferredSize(new Dimension(267,26));

          //border line left screen
          JpaneRight.setBorder(BorderFactory.createTitledBorder(
          BorderFactory.createEtchedBorder(), "", TitledBorder.LEFT, TitledBorder.TOP));
         
          //border line right screen        
          JpanelLeft.setBorder(BorderFactory.createTitledBorder(
          BorderFactory.createEtchedBorder(), "", TitledBorder.LEFT, TitledBorder.TOP));        
          
          //List number one
          list1 = new JList<>(modelLost);      
          list1.setBounds(90,90, 75,75);
          JpaneRight.add(list1);        
          list1.setPreferredSize(new Dimension(80, 180));
          list1.setBorder(border2);

          //List number two
          JList<String> list2 = new JList<>(modelInfo);      
          list2.setBounds(100,100, 85,85);         
          JpaneRight.add(list2);
          list2.setPreferredSize(new Dimension(150, 180));
          list2.setBorder(border3);
        
       
          //List number three
          DefaultListModel<String> l3 = new DefaultListModel<>();   
          JList<String> list3 = new JList<>(modelReceive);      
          list3.setBounds(90,90, 75,75);
          JpaneRight.add(list3);
          list3.setPreferredSize(new Dimension(80, 180));
        

         //leave airspace button
         BtnLveAirSpc = new JButton("Leave AirSpace");
         JpaneRight.add(BtnLveAirSpc);
         BtnLveAirSpc.addActionListener(this);
         JpaneRight.add(BtnLveAirSpc, BorderLayout.CENTER);
         BtnLveAirSpc.setPreferredSize(new Dimension(320,26));
         list3.setBorder(border4);

         setSize(650,290); //size of gui
         pan.add(JpanelLeft);
         pan.add(JpaneRight);
         window.add(pan);
         setVisible(true);
         JpanelLeft.setBackground(gray.brighter());
         JpaneRight.setBackground(gray.brighter());    
         AD.addObserver(this);
         UpdateInfo();
         AD.Notify();
    }
    
    //-----------------------------------||<>||-------------------------------\\   
    private void ObserRec() {
        modelReceive.setSize(AD.maxMRs);
          for (int i = 0; i < AD.maxMRs; i++) {
            ManagementRecord record = AD.getMR(i);
             if (record == null)
                modelReceive.set(i, null);
             else {
                modelReceive.set(i, record);
                managementRecord = i;
            } 
          } 
        }
    //----------------------------(actionPerformed)---------------------------\\
      @Override 
    public void actionPerformed(ActionEvent e) {        
        if (e.getSource() == BtnShwPasngr)
            
         {
             managementRecordIndex = list1.getSelectedIndex(); 
              String passengers = "";
              StringBuilder PassngerLits = new StringBuilder();
            PassengerList list = AD.getPassengerList(managementRecordIndex);
            ArrayList<PassengerDetails> details = list.getDetails();
            for (int i = 0; i < list.getDetails().size(); i++) {
                PassngerLits.append(details.get(i).getName() + "\n");
               
               
            } 

            JOptionPane.showMessageDialog(null, "Passengers:\n\n" + PassngerLits);
        }
      
        if (e.getSource() == BtnDtctFlight)
         {
              FC = JTFlightCode.getText();
              FT =JTFlightTo.getText();
              FF =JTFlightFrom.getText(); 
              FN=JTFlightNext.getText();
              PNGRlist.addPassenger(new PassengerDetails(JTPsngrNum.getText()));
              AD.radarDetect(new FlightDescriptor(FC,new Itinerary(FF, FT, FN),PNGRlist ));
              
              JTFlightCode.setText("");
              JTFlightTo.setText("");
              JTFlightFrom.setText("");
              JTFlightNext.setText("");
         
         }       
                
        if (e.getSource() == BtnLosRaCon)
         {
            managementRecordIndex = list1.getSelectedIndex();     
            AD.setStatus(managementRecordIndex, 0);     
         }      
        
        if (e.getSource() == BtnAddPsngr)
         {
                 managementRecordIndex = list1.getSelectedIndex(); 
//         FC = JTFlightCode.getText();
//         FT =JTFlightTo.getText();
//         FF =JTFlightFrom.getText();  
        
         PassengerDetails passengerName = new PassengerDetails(JTPsngrNum.getText());
      
         AD.addPassenger(managementRecordIndex, passengerName);
         JTPsngrNum.setText("");
         UpdateInfo();
         }
        
                if (e.getSource() == BtnLveAirSpc)
         {
               managementRecordIndex = list1.getSelectedIndex();     
                AD.setStatus(managementRecordIndex, 0);                           
         }
        
              if (e.getSource() == BtnShowDet)
         {
                  managementRecordIndex = list1.getSelectedIndex();
                  //int assignedGate = AD.getGateNumber(managementRecordIndex);    
//                  GID.departed(assignedGate);
               //   UpdateInfo();
  
            JOptionPane.showMessageDialog(null, "Flight Code: " + AD.getFlightCode(managementRecordIndex) +
            "\nFlight Status: " + AD.getStatus(managementRecordIndex) + "\nFlight From: " +
            AD.getItinerary(managementRecordIndex).getFrom() + "\nFlight To: " + AD.getItinerary(managementRecordIndex).getTo() +
            "\n Next Trip: " + AD.getItinerary(managementRecordIndex).getNext());
                               
         }
    }   
    //--------------------------------|     |--------------------------------\\
 
     
      public void UpdateInfo(){
 
         modelLost.setSize(AD.maxMRs);
         modelInfo.setSize(AD.maxMRs);
         modelReceive.setSize(AD.maxMRs);
      
          for (int i = 0; i < AD.maxMRs; i++) {

              ManagementRecord record = AD.getMR(i);

               if (record == null)
                  modelLost.set(i, null);
               else {
                  modelLost.set(i, record.getFlightCode());
                  // model2.set(i, "From : "+record.getItinerary().getFrom()+"To : "+record.getItinerary().getTo()+"Next : "+record.getItinerary().getNext());
                  modelInfo.set(i, record.toString());
                  modelReceive.set(i, record.getStatus(record.getStatus()));                  
                  managementRecord = i;
                  
            }
        }  
      
    }

    @Override
    public void update(Observable o, Object arg) {
        
        UpdateInfo();
    }
 }


        
        
            
            

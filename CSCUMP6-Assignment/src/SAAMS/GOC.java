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
import java.util.ArrayList;
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


//-------------------------------(GOC Class)---------------------------------\\

public class GOC extends JFrame implements ActionListener, Observer { 
   private AircraftManagementDatabase AD;
   private GateInfoDatabase GID;
    //declared buttons
    JButton BtnDwnldInfo; 
    JButton BtnGOC;  
    JButton BtnTxiGate;
    JButton BtnLcateGate;

    //declared JLabel
    JLabel JLControls;
    JLabel JLInbound;
    JLabel JLOutBound;
    JLabel JLPlaneDetails;
    JLabel JLGateStatus;
    JLabel JLPlanes;
    
    //declared JList
    private JList list1;
    private JList list2;
    private JList list3;
    
      String FC ="MAI";
      DefaultListModel<String> FlighCode = new DefaultListModel();
      DefaultListModel<String> GateInfo = new DefaultListModel<>();
      DefaultListModel<String> FlightInfo = new DefaultListModel<>();
      private int managementRecordIndex = -1;
    
 //-------------------------------(Constructors)------------------------------\\
      
    public GOC(AircraftManagementDatabase AD,GateInfoDatabase GID) {
        
             this.AD = AD;
             this.GID = GID;
          JPanel pan = new JPanel(new BorderLayout());
          setTitle("Ground Operations Controller");
          setLocation(100, 300);
          Container window = getContentPane();
          AD.addObserver(this);
       
          TitledBorder border = new TitledBorder("Ground Operations Controls"); //<-- border for gui
          border.setTitleJustification(TitledBorder.CENTER);
          border.setTitlePosition(TitledBorder.TOP);
          
          TitledBorder border2 = new TitledBorder("");//<-- border for JList one
          border2.setTitleColor(Color.BLACK);
          
          TitledBorder border3 = new TitledBorder("");//<-- border for JList two
          TitledBorder border4 = new TitledBorder("");//<-- border for JList three
          
          JScrollPane scrollPane = new JScrollPane(list2);
          
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
        
          //List number one
         
         list1 = new JList<>(FlighCode);      
         list1.setBounds(100,100, 85,85);
         
         JpanelLeft.add(list1);
         list1.setPreferredSize(new Dimension(170, 150));
         list1.setBorder(border3);
      
         
         JLGateStatus = new JLabel("            Gate Status");
         JpanelLeft.add(JLGateStatus);
                   
         //List number two
         JList<String> list2 = new JList<>(GateInfo);      
         list2.setBounds(100,100, 85,85);
         
         JpanelLeft.add(list2);
         list2.setPreferredSize(new Dimension(170, 150));
         list2.setBorder(border3);
 
         setSize(480,430); //size of gui
         pan.add(JpanelLeft);
         pan.add(JpaneRight);
      
         scrollPane.setPreferredSize(new Dimension(20, 20));
         list2.setVisibleRowCount(8);        
         getContentPane().add(scrollPane);
         scrollPane.setVisible(true);
         window.add(pan);
     
         setVisible(true);
         JpanelLeft.setBackground(gray.brighter());
         JpaneRight.setBackground(gray.brighter());
        
         JLControls = new JLabel("Controls                                  ");
         JpaneRight.add(JLControls);

         JLInbound = new JLabel("Inbound                                  ");
         JpaneRight.add(JLInbound);      
                 
         BtnGOC= new JButton("Grant Ground Clearance");
         JpaneRight.add(BtnGOC);
         BtnGOC.addActionListener(this);
         BtnGOC.setPreferredSize(new Dimension(250,26));
                  
         BtnTxiGate= new JButton("Permit Taxi");
         JpaneRight.add(BtnTxiGate);
         BtnTxiGate.addActionListener(this);
         BtnTxiGate.setPreferredSize(new Dimension(250,26));      
                  
         JLOutBound = new JLabel("Outbound                                  ");
         JpaneRight.add(JLOutBound);
        
         BtnLcateGate= new JButton("Allocate Gate");
         JpaneRight.add(BtnLcateGate);
         BtnLcateGate.addActionListener(this);
         BtnLcateGate.setPreferredSize(new Dimension(250,26));
 
         JLPlaneDetails = new JLabel("Plane Details                             ");
         JpaneRight.add(JLPlaneDetails);
         
         BtnDwnldInfo= new JButton("Download Flight Info");
         JpaneRight.add(BtnDwnldInfo);
         BtnDwnldInfo.addActionListener(this);
         BtnDwnldInfo.setPreferredSize(new Dimension(250,26));
        
          //List number three       
          JList<String> list3 = new JList<>(FlightInfo);      
          list3.setBounds(100,100, 85,85);         
          JpaneRight.add(list3);
          list3.setPreferredSize(new Dimension(250, 140));
          list3.setBorder(border3);
          
    }
    
    //---------------------------(actionPerformed)---------------------------\\
    
    public void actionPerformed(ActionEvent e){    
      
        
        if( e.getSource() == BtnGOC) {
             int managementRecordIndex =list1.getSelectedIndex();
             AD.setStatus(managementRecordIndex, 3);
             UpdateInfo();
  }
    
         if( e.getSource() == BtnDwnldInfo) {
        
             UpdateInfo();
  }
    

        if( e.getSource() == BtnTxiGate) {
            Taxi();

  }
        
        
        if( e.getSource() == BtnLcateGate) {
        LocateGates();
  }

    
    }
    
 
   //---------------------------(methods)---------------------------\\
    
        public void UpdateInfo(){
            
           FlightInfo.clear();
           GateInfo.clear();
           FlighCode.clear();        
           updateGates();
           int managementRecordIndex = -1;
           FlighCode.setSize(AD.maxMRs);
           GateInfo.setSize(AD.maxMRs);
           FlightInfo.setSize(AD.maxMRs);
           
        for (int i = 0; i < AD.maxMRs; i++) {

            ManagementRecord record = AD.getMR(i);
             
            if (record == null){
                FlighCode.set(i, null);
            break;}
            if(record.getStatus() == 4||record.getStatus() == 3||record.getStatus() == 2 || record.getStatus() == 5 ||record.getStatus() == 6 || record.getStatus() == 7||  record.getStatus() == 8 ||record.getStatus() == 15||record.getStatus() == 16||record.getStatus() == 17){
                FlighCode.set(i,record.getFlightCode());
                FlightInfo.set(i, record.getFlightCode() + "  Status :" +record.getStatus(record.getStatus()));
            }
            
            else {

                managementRecordIndex = i;
            }
            
           
        } 
 }
      
        public void updateGates() {
         
               GateInfo.clear();
               Gate gt =new Gate();
              
     
               int firstGateStatus = GID.getStatus(0);
               int secondGateStatus = GID.getStatus(1);
        
               GateInfo.add(0,"Gate "+0+"  :"+GID.statusOfGate(firstGateStatus)+"");
               GateInfo.add(1, "Gate "+1+"  :"+GID.statusOfGate(secondGateStatus));
              
        }
      
        
           // Method to allocate gate 
            public void LocateGates() {
                
              int Index = list1.getSelectedIndex();
              FC = list1.getSelectedValue().toString();
              int GateNumber =-1;
              GateInfo.clear();
              for (int i = 0; i < 2; i++) {
                if (GID.getStatus(i) == 0) {
                    GateNumber = i;
                    break;
                }
            }

            
            if (GateNumber == -1) {
                JOptionPane.showMessageDialog(null, "No gates are currently free!");
            } else {
               GID.allocate(GateNumber,Index);
               AD.setStatus(GateNumber, 1);

               int status1 = GID.getStatus(GateNumber);
               int gateNumber = GID.getGateAssignedToMR(Index);
               GateInfo.add(0,"Gate "+gateNumber+"  :"+GID.statusOfGate(status1)+"" +gateNumber);
               AD.setStatus(Index, 6);           
               UpdateInfo();
            }
         

 }
            public void Taxi(){
     
              managementRecordIndex = list1.getSelectedIndex();
              int assignedGate = AD.getGateNumber(managementRecordIndex);
              AD.setStatus(managementRecordIndex, 16);
              GID.departed(assignedGate);
              UpdateInfo();
              
            }

    @Override
    public void update(Observable o, Object arg) {
    UpdateInfo();
     updateGates();
    }
 
}
 
 
    

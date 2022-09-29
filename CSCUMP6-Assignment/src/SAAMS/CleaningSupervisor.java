package SAAMS;
import SAAMS.AircraftManagementDatabase;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.gray;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


 //---------------------------(CleaningSupervisor Class)---------------------\\

   public class CleaningSupervisor extends JFrame implements Observer, ActionListener {

    GateInfoDatabase GID;  
    private AircraftManagementDatabase AD;
    GateInfoDatabase lnkUnnamed;
    private AircraftManagementDatabase lnkUnnamed1;
    private int managementRecordIndex = -1;
    
    //declared buttons
    JButton buttonAwaitMain; 
    JButton buttonAwaitRepair;  
    JButton buttonFinishedClean;

    
    //declared JLabel
    JLabel JlabelPlensList;
   
    //declared JList
    private JList list;    

    DefaultListModel FlightInfo = new DefaultListModel();
        
 //-------------------------------(Constructors)------------------------------\\
    
     public CleaningSupervisor (AircraftManagementDatabase AD,
             GateInfoDatabase GID) {
        
        this.GID = GID;
        this.AD = AD;
        AD.addObserver(this);
        JPanel panl = new JPanel(new BorderLayout());
          
          setTitle("Clean Sup");
          setLocation(100, 300);
          Container window = getContentPane();        
         
          TitledBorder border = new TitledBorder("Cleaning Supervisor"); //<-- border for gui
          border.setTitleJustification(TitledBorder.CENTER);
          border.setTitlePosition(TitledBorder.TOP);
          
          panl.setLayout(new BoxLayout(panl,BoxLayout.X_AXIS));
          panl.setBorder(border);
          
           //left panel
          JPanel JpanelLeft = new JPanel(); 
          JpanelLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
          JpanelLeft.setPreferredSize(new Dimension(20,200)); //set the size of left panel

          
          TitledBorder border2 = new TitledBorder("");//<-- border for JList one
          border2.setTitleColor(Color.BLACK);
          
          //border line left screen
          JpanelLeft.setBorder(BorderFactory.createTitledBorder(
          BorderFactory.createEtchedBorder(), "", TitledBorder.LEFT, TitledBorder.TOP));
          
           JlabelPlensList = new JLabel("                      Planes List:");
           JpanelLeft.add(JlabelPlensList);
          
          //List number TWO
           list = new JList<>(FlightInfo);      
           list.setBounds(90,90, 75,75);
           JpanelLeft.add(list);        
           list.setPreferredSize(new Dimension(200, 200));
           list.setBorder(border2);     
    
           buttonAwaitMain= new JButton("          Await maintenance      ");
           JpanelLeft.add(buttonAwaitMain);
           buttonAwaitMain.addActionListener(this);
           buttonAwaitMain.setPreferredSize(new Dimension(198,26));
         
           buttonAwaitRepair= new JButton("          Await Repair         ");
           JpanelLeft.add(buttonAwaitRepair);
           buttonAwaitRepair.addActionListener(this);
           buttonAwaitRepair.setPreferredSize(new Dimension(198,26));
          
           buttonFinishedClean= new JButton("          Finished Cleaning   ");
           JpanelLeft.add(buttonFinishedClean);
           buttonFinishedClean.addActionListener(this);
           buttonFinishedClean.setPreferredSize(new Dimension(198,26));
         
           setSize(240,390); //size of gui
           panl.add(JpanelLeft);
           window.add(panl);
           setVisible(true);
           JpanelLeft.setBackground(gray.brighter());
        
         
     }
    
//-------------------------------(ActionPerformed)------------------------------\\
     
         public void actionPerformed(ActionEvent e){
             
     
             
               if (e.getSource() == buttonAwaitMain) {
                   AwaitMaintenance();
               }
          
               if (e.getSource() == buttonAwaitRepair) {
                 AwaitRepair();
               }
          
               if (e.getSource() == buttonFinishedClean) {
             
                   System.out.println("PASSED");
                   FinishedCleaning();
               }
            
           } 
                 
               
          public void UpdateInfo(){ // method to recored all the status
          
            FlightInfo.clear();
            FlightInfo.setSize(AD.maxMRs);
            int managementRecordIndex = -1;
          
            for (int i = 0; i < AD.maxMRs; i++) {
            ManagementRecord record = AD.getMR(i);
            
            if (record == null){
                FlightInfo.set(i, null);
            break;}
            if(record.getStatus() == 8 ||record.getStatus() == 9 || (record.getStatus() == 10 || (record.getStatus() == 11 ))){
                FlightInfo.set(i,record.getFlightCode());
                FlightInfo.set(i, record.getFlightCode() + "  Status :" +record.getStatus(record.getStatus()));
            }
            
            else {

                managementRecordIndex = i;
            }
 
            }
          } 
          
          public void AwaitMaintenance(){//add the number AWAIT_REPAIR status from MR
     
             managementRecordIndex = list.getSelectedIndex();
             int assignedGate = AD.getGateNumber(managementRecordIndex);
             AD.setStatus(managementRecordIndex, 12);
             GID.departed(assignedGate);
             UpdateInfo();
   
            }
         
          public void AwaitRepair(){ //add the number FAULTY_AWAIT_CLEAN status from MR
     
            managementRecordIndex = list.getSelectedIndex();
            int assignedGate = AD.getGateNumber(managementRecordIndex);
            AD.setStatus(managementRecordIndex, 8);
            GID.departed(assignedGate);
            UpdateInfo();
   
            }
            
          public void FinishedCleaning(){ 
     
           managementRecordIndex = list.getSelectedIndex();
           
           if(AD.getStatus(managementRecordIndex).equals("FAULTY_AWAIT_CLEAN")){
           
            AD.setStatus(managementRecordIndex, 12);
           }
           else{
          int assignedGate = AD.getGateNumber(managementRecordIndex);
           AD.setStatus(managementRecordIndex, 13);
           GID.departed(assignedGate);
           UpdateInfo();
                   
           }
          
   
           }

    @Override
    public void update(Observable o, Object arg) { // added the observer to UpdateInfo methods
        UpdateInfo();
    }
         
}
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


//-------------------------------(Maintenanceispector)------------------------\\

public class Maintenanceispector extends JFrame implements Observer, ActionListener {

  GateInfoDatabase GID;  
  private AircraftManagementDatabase AD;
  GateInfoDatabase lnkUnnamed;
  private AircraftManagementDatabase lnkUnnamed1;
  private int managementRecordIndex = -1;


    //declared buttons
    JButton BtnOkAwaitCleaning; 
    JButton BtnOk;    
    JButton BtnFaultFound;
    JButton BtnFaultyAwaitCleaning;
    
    
    //declared JLabel
    JLabel JLGateStatus;
    JLabel JLPlaneStatus;
    JLabel JLFlitCode;
    JLabel JLabelFlitFrom;
    JLabel JLFlitTo;
    JLabel JLNextStop;
    JLabel JLNoPsngr;
    JLabel JLPsngrNum;
    
    //declared JList
    private JList list;
    private JList list2;
    DefaultListModel model = new DefaultListModel();
    DefaultListModel model2 = new DefaultListModel();
    
    //-------------------------------(Constructors)------------------------------\\
    
     public Maintenanceispector(AircraftManagementDatabase AD,
            GateInfoDatabase gateInfoDatabase) {        
            this.GID = gateInfoDatabase;
            this.AD = AD;       
            JPanel panl = new JPanel(new BorderLayout());
          
          setTitle("Maintenancei Spector");
          setLocation(100, 300);
          Container window = getContentPane();        
         
          TitledBorder border = new TitledBorder("Maintenanceispector"); //<-- border for gui
          border.setTitleJustification(TitledBorder.CENTER);
          border.setTitlePosition(TitledBorder.TOP);
          
          panl.setLayout(new BoxLayout(panl,BoxLayout.X_AXIS));
          panl.setBorder(border);
          
           //left panel
          JPanel JpanelLeft = new JPanel(); 
          JpanelLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
          JpanelLeft.setPreferredSize(new Dimension(50,200)); //set the size of left panel
          
         //right panel
          JPanel JpaneRight = new JPanel(); 
          JpaneRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
          JpaneRight.setPreferredSize(new Dimension(10,97)); // set the size of the right panel
          
          TitledBorder border2 = new TitledBorder("");//<-- border for JList one
          border2.setTitleColor(Color.BLACK);
          
          //border line left screen
          JpanelLeft.setBorder(BorderFactory.createTitledBorder(
          BorderFactory.createEtchedBorder(), "", TitledBorder.LEFT, TitledBorder.TOP));
          
                    
          //border line left screen
          JpaneRight.setBorder(BorderFactory.createTitledBorder(
          BorderFactory.createEtchedBorder(), "", TitledBorder.LEFT, TitledBorder.TOP));
 
          JLGateStatus = new JLabel("Comment:                  ");
          JpaneRight.add(JLGateStatus);  
         
          //List number one
          list = new JList<>(model);      
          list.setBounds(90,90, 75,75);
          JpaneRight.add(list);        
          list.setPreferredSize(new Dimension(179, 170));
          list.setBorder(border2);
          
         BtnOkAwaitCleaning= new JButton("   OK_AWAIT_CLEAN       ");
         JpaneRight.add(BtnOkAwaitCleaning);
         BtnOkAwaitCleaning.addActionListener(this);
         BtnOkAwaitCleaning.setPreferredSize(new Dimension(180,26));
         
         BtnFaultyAwaitCleaning= new JButton("	FAULTY_AWAIT_CLEAN    ");
         JpaneRight.add(BtnFaultyAwaitCleaning);
         BtnFaultyAwaitCleaning.addActionListener(this);
         BtnFaultyAwaitCleaning.setPreferredSize(new Dimension(180,26));

          JLPlaneStatus = new JLabel("           Maintenance's needed:");
          JpanelLeft.add(JLPlaneStatus);
          
          //List number two
           list2 = new JList<>(model2);      
          list2.setBounds(90,90, 75,75);
          JpanelLeft.add(list2);        
          list2.setPreferredSize(new Dimension(218, 170));
          list2.setBorder(border2);
          
          BtnOk= new JButton("Repair completed              ");
          JpanelLeft.add(BtnOk);
          BtnOk.addActionListener(this);
          BtnOk.setPreferredSize(new Dimension(212,26));
          
        
                           
         BtnFaultFound= new JButton("	AWAIT_REPAIR      ");
         JpanelLeft.add(BtnFaultFound);
         BtnFaultFound.addActionListener(this);
         BtnFaultFound.setPreferredSize(new Dimension(212,26));
  
         
        setSize(450,330); //size of gui
        panl.add(JpanelLeft);
        panl.add(JpaneRight);
        window.add(panl);
        AD.addObserver(this);
        setVisible(true);
        JpanelLeft.setBackground(gray.brighter());
        JpaneRight.setBackground(gray.brighter());       
         
     }
         
//-------------------------------(actionPerformed)------------------------\\
     
         public void actionPerformed(ActionEvent e){ 
             
     
       if( e.getSource() == BtnOk) {
             managementRecordIndex =list2.getSelectedIndex();
            AD.setStatus(managementRecordIndex, 8);
             UpdateInfo();
        }
   
       if( e.getSource() == BtnOkAwaitCleaning) {
                  
             OKAwaitCleaning();
        }
      
       if( e.getSource() == BtnFaultyAwaitCleaning) {
                  
             FaultyAwaitCleaning();
        }
            
               
       if( e.getSource() == BtnFaultFound) {
                  
             FaultFound();
       }
  
      
    }
         
         
           public void UpdateInfo(){
              model.clear();
              model2.clear();
              int managementRecordIndex = -1;
              model2.setSize(AD.maxMRs);
              model.setSize(AD.maxMRs);
              for (int i = 0; i < AD.maxMRs; i++) {

           ManagementRecord record = AD.getMR(i);
             
            if (record == null){
                model2.set(i, null);
            break;}
            if(record.getStatus() == 8 ||record.getStatus() == 9||record.getStatus() == 10||record.getStatus() == 11||record.getStatus() == 12||record.getStatus() == 13 ){
                model2.set(i,record.getFlightCode());
                model.set(i, record.getFlightCode() + "  Status :" +record.getStatus(record.getStatus()));
            }
            
            else {

               
            }            
           
        }
     }

     public void OKAwaitCleaning(){  //== 11
     
      managementRecordIndex = list2.getSelectedIndex();
      int assignedGate = AD.getGateNumber(managementRecordIndex);
      AD.setStatus(managementRecordIndex, 11);
      GID.departed(assignedGate);
      UpdateInfo();
   
            }
     
          public void FaultyAwaitCleaning(){ //==12
     
      managementRecordIndex = list2.getSelectedIndex();
      int assignedGate = AD.getGateNumber(managementRecordIndex);
      AD.setStatus(managementRecordIndex, 9);
      GID.departed(assignedGate);
      UpdateInfo();
   
            }
          
   
      
           public void FaultFound(){
     
      managementRecordIndex = list2.getSelectedIndex();
      int assignedGate = AD.getGateNumber(managementRecordIndex);
      AD.setStatus(managementRecordIndex, 12);
      GID.departed(assignedGate);
      UpdateInfo();
   
            }

    @Override
    public void update(Observable o, Object arg) {
            UpdateInfo();
    }

            }

 
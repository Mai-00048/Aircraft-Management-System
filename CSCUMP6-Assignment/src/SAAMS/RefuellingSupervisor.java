package SAAMS;

/**
 *
 * @author Mai-2840080
 */


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


//------------------------(RefuellingSupervisor Class)------------------------\\

public  class RefuellingSupervisor extends JFrame implements Observer, ActionListener {

    GateInfoDatabase GID;  
    private AircraftManagementDatabase AD;
    DefaultListModel model = new DefaultListModel();
    private int managementRecordIndex = -1;

    //declared buttons
    JButton ButtonPlanRef; 
    
    //declared JLabel
    JLabel JlabelPlanRef;
    
    //declared JList
     JList list;

//------------------------------|Constructors|--------------------------------\\
    
    public RefuellingSupervisor(AircraftManagementDatabase AD) {
       
          this.AD = AD;     
          JPanel panl = new JPanel(new BorderLayout());
          
          setTitle("Refuelling Supervisor");
          setLocation(100, 300);
          Container window = getContentPane();        
         
          TitledBorder border = new TitledBorder("Refuelling Supervisor"); //<-- border for gui
          border.setTitleJustification(TitledBorder.CENTER);
          border.setTitlePosition(TitledBorder.TOP);
         
          panl.setLayout(new BoxLayout(panl,BoxLayout.X_AXIS));
          panl.setBorder(border);
 
          //right panel
          JPanel Jpane = new JPanel(); 
          Jpane.setLayout(new FlowLayout(FlowLayout.RIGHT));
          Jpane.setPreferredSize(new Dimension(10,97)); // set the size of the panel
          
          TitledBorder border2 = new TitledBorder("");//<-- border for JList one
          border2.setTitleColor(Color.BLACK);
               
          //border line left screen
          Jpane.setBorder(BorderFactory.createTitledBorder(
          BorderFactory.createEtchedBorder(), "", TitledBorder.LEFT, TitledBorder.TOP));

          JlabelPlanRef = new JLabel("           Planes Being Refuelled         ");
          Jpane.add(JlabelPlanRef);
     
          //List number one
          list = new JList<>(model);      
          list.setBounds(90,90, 75,75);
          Jpane.add(list);        
          list.setPreferredSize(new Dimension(175, 320));
          list.setBorder(border2);
          
          //Button
          ButtonPlanRef= new JButton("  Plane is Refuelled      ");
          Jpane.add(ButtonPlanRef);
          ButtonPlanRef.addActionListener(this);
          ButtonPlanRef.setPreferredSize(new Dimension(176,26));
    
           setSize(213,450); //size of gui    
           panl.add(Jpane); //add panl to Jpane
           window.add(panl); //add panl to window
           setVisible(true); //set it as visible
           Jpane.setBackground(gray.brighter()); //colour of gui  
           //Adds LATC as an observer.
           AD.addObserver(this);
      
     }
    

      
    public void UpdateInfo(){
 model.clear();
    
     model.setSize(AD.maxMRs);
     for (int i = 0; i < AD.maxMRs; i++) {

            ManagementRecord record = AD.getMR(i);
             
            if (record == null){
                model.set(i, null);
            break;}
            if((record.getStatus() == 13)){
                model.set(i,record.getFlightCode());
                //model.set(i, record.getFlightCode() + "  Status :" +record.getStatus(record.getStatus()));
            }
            
                     
           
        }      
 
            }

    public void update(Observable o, Object arg) {
UpdateInfo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
             
   if( e.getSource() == ButtonPlanRef) {
       
     managementRecordIndex = list.getSelectedIndex();
      AD.setStatus(managementRecordIndex, 14); //added the READY_PASSENGERS status
     
      UpdateInfo(); 
  }
    }

    
    

}

package SAAMS;
import SAAMS.AircraftManagementDatabase;
import SAAMS.ManagementRecord;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

 //-------------------------------(PublicInfo class)--------------------------\\
public class PublicInfo extends JFrame implements Observer {
     private int publicNumber;
     private AircraftManagementDatabase AD;
     GateInfoDatabase GID;
     private DefaultListModel<ManagementRecord> listModelOfManagement;
     private int managementRecordIndex = -1;
     //JPanel pan = new JPanel(new BorderLayout());          
     JFrame f;  
     JTable j; //table
     // Column Names of the table
        String[] columns = { "Flight's", "From", "To", "Gate", "Status" };
 String data[][] = new String[10][10];;
  DefaultTableModel model;
  private ManagementRecord assignedMR;
  //-------------------------------(Constructors)------------------------------\\
     
       public PublicInfo(AircraftManagementDatabase AD,GateInfoDatabase GID) {
           this.AD =AD;
           this.GID =GID;
           AD.addObserver(this);
          setLocation(100, 300);
          Container window = getContentPane();
          TitledBorder border = new TitledBorder("Ground Operations Controls"); //<-- border for gui
          border.setTitleJustification(TitledBorder.CENTER);
          border.setTitlePosition(TitledBorder.TOP);

        //table 
        f = new JFrame();
        f.setTitle("Public Info");
        
      
       
        
         
 
        // Initializing the JTable
        j = new JTable();
        j.setSize(200,100);
     //   j.setLocation(500, 500);
        f.setSize(550, 200);
        j.setBounds(10, 10, 100, 100);        
       
        f.add(j);
        
        f.setVisible(true);
       
         LoadInfo();
    }

    @Override
    public void update(Observable o, Object arg) {
    UpadteTabel();
    }
 
public void LoadInfo(){



   for (int i = 0; i< AD.maxMRs; i++){
        ManagementRecord record = AD.getMR(i);
 
       
        if (record == null)
                  j.setModel(model);
               else {
            int GN =record.getGateNumber();
        String FC = record.getFlightCode();
        String FF = record.getItinerary().getFrom();
        String FT = record.getItinerary().getTo();
        String FS = record.getStatus(record.getStatus());
       String FG = GN+"";
                 data[i][0] = FC ;
        data[i][1] = FF;
        data[i][2] = FT;
        data[i][3] = GN+"";
        data[i][4] = FS;
            }
        
        
          
      
       
            
        model = new DefaultTableModel(data, columns);
        j.setModel(model);
   }
    
}
public void UpadteTabel(){
    
        LoadInfo();
      
}
    
}
 

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class theGui {

	  private Action check_action = new AbstractAction("Check hits") {
			private static final long serialVersionUID = 1L;
	        public void actionPerformed(ActionEvent evt) {
	        	the_state = 0;
	        }
	    };
	    
	    private Action add_action = new AbstractAction("Add hits") {
			private static final long serialVersionUID = 1L;
	        public void actionPerformed(ActionEvent evt) {        	
	        	the_save_name = new String(actionName.getText());
	        	the_state = 1;
	        	System.out.println("The state was changed");
	        }
	    };
	    
	    private Action save_action = new AbstractAction("Save") {
			private static final long serialVersionUID = 1L;
	        public void actionPerformed(ActionEvent evt) {
	        	the_state = 2;
	        }
	    };
	    
	    
	    private Action restore_action = new AbstractAction("Restore") {
	 		private static final long serialVersionUID = 1L;
	         public void actionPerformed(ActionEvent evt) {
	        	 the_state = 3;
	         }
	     };
	// Initialize all swing objects.
    private JFrame f = new JFrame("Basic GUI"); //create Frame
    private JPanel top_panel = new JPanel(); // North quadrant 
    private JPanel below = new JPanel(); // South quadrant
    
    private JButton check_button = new JButton(check_action);
    private JButton add_button = new JButton(add_action);
    private JButton save_button = new JButton(save_action);
    private JButton restore_button = new JButton(restore_action);
    
    private JTextField actionName = new JTextField(20);
    
    /** Constructor for the GUI */
    public theGui(){
	        
        // Add Buttons
        top_panel.add(check_button);
        top_panel.add(add_button);
        top_panel.add(save_button);
        top_panel.add(restore_button);
        below.add(actionName);
       // below.add(check);        
        
        // Setup Main Frame
        f.getContentPane().setLayout(new BorderLayout());	
        f.getContentPane().add(top_panel, BorderLayout.NORTH);
		f.getContentPane().add(below, BorderLayout.SOUTH);
        

        
		// Allows the Swing App to be closed
        f.addWindowListener(new ListenCloseWdw());
		
		//Add Menu listener
        
    }
	
    public class ListenMenuQuit implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);         
        }
    }
	
    public class ListenCloseWdw extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);         
        }
    }
	
    public void launchFrame(){
        // Display Frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack(); //Adjusts panel to components for display
        
		//should be full screen - Works
		//f.setUndecorated(true);  
        //f.setExtendedState(Frame.MAXIMIZED_BOTH);
        
        
        f.setVisible(true);
    }
    
   
}

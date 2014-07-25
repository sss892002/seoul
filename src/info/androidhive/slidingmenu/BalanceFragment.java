package info.androidhive.slidingmenu;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class BalanceFragment extends Fragment {
	
	public BalanceFragment(){}
	
	ImageButton statusbutton;
	TextView balancestatus;
	ImageView phaseview;
	int balanceindex=1;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_balance, container, false);
         
        statusbutton = (ImageButton)rootView.findViewById(R.id.startwalkbtn);
	    balancestatus = (TextView)rootView.findViewById(R.id.balancestatus);
	    phaseview = (ImageView)rootView.findViewById(R.id.balancephase0);
	    
		statusbutton.setOnClickListener (new OnClickListener(){
	    	public void onClick(View v) {
	    		if(balanceindex==1){
	       		statusbutton.setImageResource(R.drawable.nowwalking);
	    		phaseview.setImageResource(R.drawable.balancephase1);
	    		balancestatus.setText("Recording your leftside gait");
	    			balanceindex=2;
	    		/** Stop receiving 
	    		 Data Save**/
	    		}
	    		else if(balanceindex==2){
	    			statusbutton.setImageResource(R.drawable.nowwalking);
		    		phaseview.setImageResource(R.drawable.balancephase2);
		    		balancestatus.setText("Recording your rightside gait");
		    			/**  Bluetooth data receiving**/
	    			balanceindex=3;
	    		/** Stop receiving 
	    		 Data Save**/
		    		
	    		}
	    		else if(balanceindex==3){
	    			statusbutton.setImageResource(R.drawable.nowwalking);
		    		phaseview.setImageResource(R.drawable.balancephase3);
		    		balancestatus.setText("Analyzing Your Balance");
		    		/** Execute Balance Analyzing function
		    		/*show result*/
	    		
		    		balanceindex=1;
	    		}
	    		
	    		
	    		}
	    });
        
        
        return rootView;
    }
}

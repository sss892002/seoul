package info.androidhive.slidingmenu;



import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterFragment extends Fragment {
	
	public RegisterFragment(){}
	
	ImageView characterview ;
	EditText weightinput;
	EditText heightinput;
	int weight;
	int height;

	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        final View rootView = inflater.inflate(R.layout.fragment_regi, container, false);
        characterview = (ImageView)rootView.findViewById(R.id.imageView1);
		
		 
	       
		 
        ImageButton malebtn = (ImageButton)rootView.findViewById(R.id.malebutton);
        ImageButton femalebtn = (ImageButton)rootView.findViewById(R.id.femalebutton);
        ImageButton registerbtn = (ImageButton)rootView.findViewById(R.id.registerbtn);
        ImageButton submitbtn2 = (ImageButton)rootView.findViewById(R.id.registerbtn);

        
        
        int gender=0;
       

        femalebtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				characterview.setImageResource(R.drawable.marge2);
				int gender=1;
				
			
			}
        	
        });
        
        malebtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				characterview.setImageResource(R.drawable.homer2);
				int gender=0;
			}
        	
        });
        registerbtn.setOnClickListener(new OnClickListener(){

    		
    				public void onClick(View v) {
    					
    					weightinput = (EditText)rootView.findViewById(R.id.weightnum); 
    			       
    			        heightinput = (EditText)rootView.findViewById(R.id.heightnum);
    			        String weight=weightinput.getText().toString();
    			        String height=heightinput.getText().toString();

    			        
    			        if (weight.matches("")||height.matches("")){
			        	    Toast.makeText(getActivity(),"Type your information correctly asshole!",Toast.LENGTH_LONG).show();

    			        int weightnum=0;
    			        int heightnum=0;
    			       
    			        }
    			        else{
    			        	
    			        int weightnum= Integer.parseInt(weight);
    			        int heightnum=Integer.parseInt(height);
    			        
    			        
    			        		
    			        	    Toast.makeText(getActivity(),"You have been registered successfully!",Toast.LENGTH_LONG).show();
    			        }
    			        
    			      /*  Intent intent = new Intent(MainActivity.this, GaitID.class); 
    			        startActivity(intent);*/
    			        
    				}
    	        	
    	        });
        return rootView;
    }
}

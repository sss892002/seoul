package info.androidhive.slidingmenu;



import android.app.Fragment;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GaitFragment extends Fragment {
	
	public GaitFragment(){}
	
	ImageButton startwalkbtn;
	ImageView standingman;
	TextView gaitstatus;
	TextView scoreview;
	
    int gaitint=1;
	
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_gait, container, false);
       
        
        startwalkbtn = (ImageButton)rootView.findViewById(R.id.startwalkbtn);
	    standingman = (ImageView)rootView.findViewById(R.id.posview);
		

	startwalkbtn.setOnClickListener (new OnClickListener(){
		
		public void onClick(View v) {
		
	        Typeface typeface = Typeface.createFromAsset(getAssets(), "SeoulHangangB.mp3");
	        TextView scoreview = (TextView) findViewById(R.id.walkscore);
	        scoreview.setTypeface(typeface);
	        scoreview.setText("테스트 ");
	        
		if(gaitint==1){
		startwalkbtn.setImageResource(R.drawable.walkagain);
		standingman.setImageResource(R.drawable.walk);
		gaitstatus.setText("Currently Recording your walk pattern");
		gaitint=2;
	
		}
		
		else if(gaitint==2){
			startwalkbtn.setImageResource(R.drawable.walkagain);
			standingman.setImageResource(R.drawable.okay);
			gaitstatus.setText("Recording Finished");
			gaitint=0;
			}
		else if(gaitint==0){
			startwalkbtn.setImageResource(R.drawable.startwalk);
			standingman.setImageResource(R.drawable.standing);
			gaitstatus.setText("Waiting for Start");
			gaitint=1;
			}
		
		}
	    // TODO Auto-generated method stub

		private TextView findViewById(int walkscore) {
			// TODO Auto-generated method stub
			return null;
		}

		private AssetManager getAssets() {
			// TODO Auto-generated method stub
			return null;
		}
	});
	 return rootView;
    }
}

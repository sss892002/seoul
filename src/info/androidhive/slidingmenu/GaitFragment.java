package info.androidhive.slidingmenu;



import android.app.Fragment;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GaitFragment extends Fragment {
	
	public GaitFragment(){}
	

	ImageView standingman;
	TextView gaitstatus;
	TextView scoreview;
	
    int gaitint=1;
	
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

     
        View rootView = inflater.inflate(R.layout.fragment_gait, container, false);
        ProgressBar pb = (ProgressBar) rootView.findViewById(R.id.progressBarToday);

        Animation an = new RotateAnimation(0.0f, 90.0f, 100f, 100f);
        an.setFillAfter(true);
        pb.startAnimation(an);

        ProgressBar pb2 = (ProgressBar) rootView.findViewById(R.id.progressBarToday2);

        Animation an2 = new RotateAnimation(0.0f, 90.0f, 100f, 100f);
        an2.setFillAfter(true);
        pb2.startAnimation(an2);
 
	
	 return rootView;
    }
}

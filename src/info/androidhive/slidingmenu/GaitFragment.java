package info.androidhive.slidingmenu;



import android.app.Fragment;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GaitFragment extends Fragment {
	
	private int score;

	ImageView standingman;
	ImageView mapview;
	TextView gaitstatus;
	TextView scoreview;
	
    int gaitint=1;
    private View rootView;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

     
        rootView = inflater.inflate(R.layout.fragment_gait, container, false);
        ProgressBar pb = (ProgressBar) rootView.findViewById(R.id.progressBarToday);

        Animation an = new RotateAnimation(0.0f, 90.0f, 100f, 100f);
        an.setFillAfter(true);
        pb.startAnimation(an);

        ProgressBar pb2 = (ProgressBar) rootView.findViewById(R.id.progressBarToday2);

        Animation an2 = new RotateAnimation(0.0f, 90.0f, 100f, 100f);
        an2.setFillAfter(true);
        pb2.startAnimation(an2);
        

        ImageButton magicMe = (ImageButton) rootView.findViewById(R.id.shopping);
        magicMe.setOnClickListener(new OnClickListener()
        {

			@Override
			public void onClick(View v) {
				change(85);
				
			}
        	
        });
 
	
	 return rootView;
    }
    public void change(int score)
    {
    	Log.d("change",""+score);
    	TextView walkscore2 = (TextView)rootView.findViewById(R.id.walkscore2);
    	ImageView mapview=(ImageView)rootView.findViewById(R.id.seoulstatus);
    	walkscore2.setText(score+"점 : 양호한 편입니다.");
    	mapview.setImageResource(R.drawable.seoulmap2);
    }
}

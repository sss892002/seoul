package info.androidhive.slidingmenu;



import java.util.ArrayList;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;

import info.androidhive.slidingmenu.bt.BluetoothChatService;
import info.androidhive.slidingmenu.bt.DeviceListActivity;
import android.app.Activity;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BalanceFragment extends Fragment {
	
	public BalanceFragment(){}
	private float value;
	ImageButton statusbutton;
	TextView balancestatus;
	ImageView phaseview;
	int balanceindex=1;
	// Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;

    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";

    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;
    
	// Name of the connected device
    private String mConnectedDeviceName = null;
    // Array adapter for the conversation thread
    private ArrayAdapter<String> mConversationArrayAdapter;
    // String buffer for outgoing messages
    private StringBuffer mOutStringBuffer;
    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;
    // Member object for the chat services
    private BluetoothChatService mChatService = null;
    
    private static final int DISPLAY_SIZE = 500; 
	private static final int HISTORY_SIZE = 30000; 
	
	
	private XYPlot plot;
	
	private SimpleXYSeries magSeries = null; ///Acceleration Magnitude Series
	private ArrayList<Float> magList = new ArrayList<Float>();
	
	private static Integer lineColor = Color.argb(255, 255,0,0);
	private static Integer pointColor = Color.argb(200, 250, 250, 250);
	private TextView score;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_balance, container, false);
        
        
     // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


        // Initialize the BluetoothChatService to perform bluetooth connections
        mChatService = new BluetoothChatService(getActivity(), mHandler);

        // Initialize the buffer for outgoing messages
        mOutStringBuffer = new StringBuffer("");
        
		plot = (XYPlot) rootView.findViewById(R.id.mainPlot);
		score = (TextView) rootView.findViewById(R.id.score);
        
        Intent serverIntent = new Intent(this.getActivity(), DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_INSECURE);
        magSeries = new SimpleXYSeries("mag");
		magSeries.useImplicitXVals();
		LineAndPointFormatter magFormat = new LineAndPointFormatter(lineColor, null,null,null);
		plot.addSeries(magSeries, magFormat);
		plot.setTicksPerRangeLabel(3);
		
		plot.getBackgroundPaint().setColor(Color.WHITE);
		plot.setBorderStyle(XYPlot.BorderStyle.NONE, null, null);
		plot.getGraphWidget().getBackgroundPaint().setColor(Color.WHITE);
		plot.getGraphWidget().getGridBackgroundPaint().setColor(Color.WHITE);
		
		plot.getGraphWidget().getBackgroundPaint().setColor(Color.WHITE);
		plot.getGraphWidget().setDomainLabelOrientation(-45);
		plot.setRangeBoundaries(0,30, BoundaryMode.AUTO);
		plot.setDomainBoundaries(0, DISPLAY_SIZE, BoundaryMode.FIXED);
		
		plot.getGraphWidget().setDomainLabelPaint(null);
		plot.getGraphWidget().setDomainOriginLinePaint(null);
		
		plot.getLayoutManager().remove(plot.getLegendWidget());
		plot.getLayoutManager().remove(plot.getDomainLabelWidget());
		plot.getLayoutManager().remove(plot.getRangeLabelWidget());
		
		Button send = (Button) rootView.findViewById(R.id.sendButton);
		send.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				done();
			}

		});
        
        
        return rootView;
    }
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case REQUEST_CONNECT_DEVICE_SECURE:
            // When DeviceListActivity returns with a device to connect
            if (resultCode == Activity.RESULT_OK) {
                connectDevice(data, true);
            }
            break;
        case REQUEST_CONNECT_DEVICE_INSECURE:
            // When DeviceListActivity returns with a device to connect
            if (resultCode == Activity.RESULT_OK) {
                connectDevice(data, false);
            }
            break;
        }
    }

    private void connectDevice(Intent data, boolean secure) {
        // Get the device MAC address
        String address = data.getExtras()
            .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
        // Get the BluetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        // Attempt to connect to the device
        mChatService.connect(device, secure);
    }
    
    public void addValue(float value)
	{
		if(magList.size() > HISTORY_SIZE)
		{
			magList.remove(0);
		}
		if(magSeries.size() > DISPLAY_SIZE)
		{
			magSeries.removeFirst();
		}

		magList.add(value);
		magSeries.addLast(null, value);
		plot.redraw();
	}

	public void clear()
	{
		for(int  i = 0 ; i < HISTORY_SIZE;i++)
		{
			if(magSeries.size() != 0)
				magSeries.removeFirst();
		}
		magList.clear();
	}
	public void done()
	{
		MainActivity activity = (MainActivity)this.getActivity();
		
		activity.displayView(2);
		activity.magic((int)value);
	}
	// The Handler that gets information back from the BluetoothChatService
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case MESSAGE_STATE_CHANGE:
                switch (msg.arg1) {
                case BluetoothChatService.STATE_CONNECTED:

                    break;
                case BluetoothChatService.STATE_CONNECTING:
                    break;
                case BluetoothChatService.STATE_LISTEN:
                case BluetoothChatService.STATE_NONE:
                    break;
                }
                break;
            case MESSAGE_WRITE:
                byte[] writeBuf = (byte[]) msg.obj;
                // construct a string from the buffer
                String writeMessage = new String(writeBuf);
                break;
            case MESSAGE_READ:
                byte[] readBuf = (byte[]) msg.obj;
                // construct a string from the valid bytes in the buffer
                String readMessage = new String(readBuf, 0, msg.arg1);
                
                String[] split = readMessage.split(",");
                if(split.length == 2)
                {
                	try
                	{
                		if( Integer.parseInt(split[0])==5)
                		{
	                		addValue(Float.parseFloat(split[1]));
	                		Log.d("msg", split[1]);
                		}
                		else if(Integer.parseInt(split[0])==0)
        				{
                			value = Float.parseFloat(split[1]);
                			float min = 0.5f;
                			float max = 5.0f;
                			value = (value - min)/max*100;
                			if(value < 0)
                				value =0;
                			if(value > 100)
                				value = 100;
                			score.setText(""+value);
        				}
                	}
                	catch(Exception e)
                	{
                		
                	}
                }
                break;
            case MESSAGE_DEVICE_NAME:
                // save the connected device's name
                break;
            case MESSAGE_TOAST:
                break;
            }
        }
    };
}

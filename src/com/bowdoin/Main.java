package com.bowdoin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main extends ListActivity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setListAdapter(new ArrayAdapter<String>(this, R.layout.main, OPTIONS));
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int posistion, long id) {
        		if ( id == 0 ) {
        			Intent myIntent = new Intent(Main.this, Dining.class);
        			Main.this.startActivity(myIntent);
        		}
        		if ( id == 1 ){
        			copyPDF();
        			Uri path = Uri.parse("file://" + Environment.getExternalStorageDirectory().getPath()+"/bowdoin/bowdoin-campus-map-2008.pdf"); 
        			Intent intent = new Intent(Intent.ACTION_VIEW, path); 
        			intent.setDataAndType(path, "application/pdf"); 
        			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
        			startActivity(intent);
        			
        		}
        		if ( id == 2 ){
        			Intent myIntent = new Intent(Main.this, AcademicCalendar.class);
        			Main.this.startActivity(myIntent);
        		}
        	}
        });
        
    }
  

    
    private void copyPDF() {
    	AssetManager assetManager = getAssets();
        InputStream inputStream = null;
    	try {
    		File directory = new File(Environment.getExternalStorageDirectory().getPath()+"/bowdoin");
    		if (!directory.exists()) {
    		    directory.mkdir();
    		}
    		File outfile = new File(directory.getPath() + "/bowdoin-campus-map-2008.pdf");
    		if (!outfile.exists()) {
        		AssetFileDescriptor af = assetManager.openFd("bowdoin-campus-map-2008.pdf"); 
        		long filesize = af.getLength(); 
        		inputStream = assetManager.open("bowdoin-campus-map-2008.pdf");
        		byte [] tempdata = new byte[(int)filesize]; 
        		inputStream.read(tempdata);
        		inputStream.close();
    		    outfile.createNewFile(); 
                FileOutputStream fo = new FileOutputStream(outfile); 
                fo.write(tempdata); 
                fo.close();
    		}
        } catch (IOException e) {
        	Log.e("tag", e.getMessage());
        }
    }
    
    static final String[] OPTIONS = new String[] {"Dining", "Campus Map", "Academic Calendar"};
}
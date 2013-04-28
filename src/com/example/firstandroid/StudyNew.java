/**
 * 
 */
package com.example.firstandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Tom.Tang
 *
 */
public class StudyNew extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.studynew);
		((Button)findViewById(R.id.output)).setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		String input = ((EditText)findViewById(R.id.input_message)).getText().toString();
		if(input==null || input.length()==0) 
			new AlertDialog.Builder(this).setTitle("Oops")
	        .setMessage("please input something")
	        .setPositiveButton(android.R.string.ok, null)
	        .setCancelable(false).create().show();
		((TextView)findViewById(R.id.output_text)).setText(input);
		
	}
}

/**
 * 
 */
package com.example.firstandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListView;


/**
 * @author Tom.Tang
 *
 */
public class ListViewTest extends Activity
	{
	  private static final String[] array =
		  { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday",
		      "saturday" };
		  LinearLayout myLinearLayout;
		  TextView myTextView;
		  ListView myListView;

		  /** Called when the activity is first created. */
		  @Override
		  public void onCreate(Bundle savedInstanceState)
		  {
		    super.onCreate(savedInstanceState);

		    /* �s�WLinearLayout */
		    myLinearLayout = new LinearLayout(this);
		    myLinearLayout.setOrientation(LinearLayout.VERTICAL);
		    myLinearLayout.setBackgroundColor(android.graphics.Color.WHITE);

		    /* �s�WTextView */
		    myTextView = new TextView(this);
		    LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(
		        LinearLayout.LayoutParams.MATCH_PARENT,
		        LinearLayout.LayoutParams.WRAP_CONTENT);
		    myTextView.setText(R.string.title);
		    myTextView.setTextColor(getResources().getColor(R.drawable.blue));
		    /* �NTextView�[��myLinearLayout */
		    myLinearLayout.addView(myTextView, param1);

		    /* �s�WListView */
		    myListView = new ListView(this);
		    LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
		        LinearLayout.LayoutParams.MATCH_PARENT,
		        LinearLayout.LayoutParams.WRAP_CONTENT);
		    myListView.setBackgroundColor(getResources().getColor(R.drawable.ltgray));
		    /* �NListView�[��myLinearLayout */
		    myLinearLayout.addView(myListView, param2);

		    /* �NLinearLayout�[��ContentView */
		    setContentView(myLinearLayout);

		    /* new ArrayAdapter����ñNarray�r��}�C�ǤJ */
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        R.layout.my_simple_list_item, array);
		    /* �NArrayAdapter�[�JListView���� */
		    myListView.setAdapter(adapter);

		    /* myListView�[�JOnItemSelectedListener */
		    myListView
		        .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		        {

		          @Override
		          public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
		              long arg3)
		          {
		            /* �ϥ�getSelectedItem()�N������ȱa�JmyTextView�� */
		            myTextView.setText("�A�諸�O" + arg0.getSelectedItem().toString());
		          }

		          @Override
		          public void onNothingSelected(AdapterView<?> arg0)
		          {
		            // TODO Auto-generated method stub

		          }

		        });

		    /* myListView�[�JOnItemClickListener */
		    myListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		    {

		      @Override
		      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		          long arg3)
		      {
		        /* �ϥ�String[index]�Aarg2���I���ListView��index�A�ñN�ȱa�JmyTextView�� */
		        myTextView.setText("�A�諸�O" + array[arg2]);
		      }

		    });

		  }
}
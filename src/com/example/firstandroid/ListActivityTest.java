/**
 * 
 */
package com.example.firstandroid;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * @author Tom.Tang
 *
 */
public class ListActivityTest extends ListActivity{

	  /* ����ŧi 
	     items�G�s����ܪ��W��
	     paths�G�s���ɮ׸��|
	     rootPath�G�_�l�ؿ�
	  */
	  private List<String> items=null;
	  private List<String> paths=null;
	  private String rootPath="/";
	  private TextView mPath;
	  
	  /** Called when the activity is first created. */
	  @Override
	  protected void onCreate(Bundle icicle)
	  {
	    super.onCreate(icicle);
	    
	    /* ���Jmain.xml Layout */
	    setContentView(R.layout.list_activity);
	    mPath=(TextView)findViewById(R.id.mPath);
	    
	    getFileDir(rootPath);
	  }
	  
	  /* ���o�ɮ׬[�c��method */
	  private void getFileDir(String filePath)
	  {
		/* �]�w�ثe�Ҧb���| */
		mPath.setText(filePath);
		  
		items=new ArrayList<String>();
		paths=new ArrayList<String>();  
		File f=new File(filePath);  
		File[] files=f.listFiles();
		
	    if(!filePath.equals(rootPath))
	    {
	      /* �Ĥ@���]�w��[�^��ڥؿ�] */
	      items.add("Back to "+rootPath);
	      paths.add(rootPath);
	      /* �ĤG���]�w��[�^�W�h] */
	      items.add("Back to ../");
	      paths.add(f.getParent());
	    }
	    /* �N�Ҧ��ɮץ[�JArrayList�� */
	    for(int i=0;i<files.length;i++)
	    {
	      File file=files[i];
	      items.add(file.getName());
	      paths.add(file.getPath());
	    }
	    
	    /* �ŧi�@ArrayAdapter�A�ϥ�file_row�o��Layout�A�ñNAdapter�]�w����ListActivity */
	    ArrayAdapter<String> fileList = new ArrayAdapter<String>(this,R.layout.file_row, items);
	    setListAdapter(fileList);
	  }
	  
	  /* �]�wListItem�Q���U�ɭn�����ʧ@ */
	  @Override
	  protected void onListItemClick(ListView l, View v, int position, long id)
	  {
	    File file = new File(paths.get(position));
	    if (file.isDirectory())
	    {
	      /* �p�G�O��Ƨ��N�A�i�h���@�� */
	      getFileDir(paths.get(position));
	    }
	    else
	    {
	      /* �p�G�O�ɮסA�h���XAlertDialog */
	    	new AlertDialog.Builder(this).setIcon(R.drawable.icon)
	                       .setTitle("["+file.getName()+"] is File!")
	                       .setPositiveButton("OK",
	                       new DialogInterface.OnClickListener()
	                       {
	                         public void onClick(DialogInterface dialog,int whichButton)
	                         {
	                         }
	                       }).show();         
	    }
	  }

}

package com.example.todoexample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class ToDoActivity extends Activity {


	private static final String TO_DO_FILENAME = "to_do.txt";
	private ArrayList<String> mItems = null;
	private ArrayAdapter<String> mItemsAdptr = null ;
	private EditText inTxt = null;
	private Button btn_newItem = null;
	private ListView lv = null ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_do);

		inTxt = (EditText) findViewById(R.id.newItem);
		btn_newItem = (Button) findViewById(R.id.btn_new);
		lv = (ListView) findViewById(R.id.itemList);
		
		mItems = new ArrayList<String>();
		populateTodoItems();
		mItemsAdptr = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, mItems);
	    lv.setAdapter(mItemsAdptr);
	    
	    
		btn_newItem.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addItem();
				inTxt.setText("");
			}
		});
		
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adptr, View v,
					int pos, long id) {
				// TODO Auto-generated method stub	
				removeItems(pos);
				return false;
			}
		});
	}

	private void addItem() {
		// TODO Auto-generated method stub
		String newItem = inTxt.getText().toString();
		mItemsAdptr.add(newItem);
		Toast.makeText(getApplicationContext(), "Added " + newItem, Toast.LENGTH_SHORT).show();
	}
	
	
	private void removeItems(int pos) {
		// TODO Auto-generated method stub
		String temp = mItemsAdptr.getItem(pos);
		mItemsAdptr.remove(temp);
		Toast.makeText(getApplicationContext(), "Removed " + temp, Toast.LENGTH_SHORT).show();
	}

	
	private void populateTodoItems() {
		// TODO Auto-generated method stub
		
		File path_name = getFilesDir();
		File file = new File(path_name, TO_DO_FILENAME);
		try {
			mItems = new ArrayList<String>(FileUtils.readLines(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		writeTodoItems();
	}

	private void writeTodoItems() {
		// TODO Auto-generated method stub
		
		File path_name = getFilesDir();
		File file = new File(path_name, TO_DO_FILENAME);
		try {
			FileUtils.writeLines(file, mItems);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

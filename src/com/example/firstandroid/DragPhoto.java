package com.example.firstandroid;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class DragPhoto extends Activity implements OnDragListener,
		OnItemLongClickListener {

	ArrayList drawables;

	private BaseAdapter adapter;
	private int draggedIndex = -1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drag_photo);
		drawables = new ArrayList();
		drawables.add(R.drawable.chips10);
		GridView gridView = (GridView) findViewById(R.id.grid_view);
		gridView.setOnItemLongClickListener(DragPhoto.this);
		gridView.setAdapter(adapter = new BaseAdapter() {

			@Override
			// Get a View that displays the data at the specified position in
			// the data set.
			public View getView(int position, View convertView,
					ViewGroup gridView) {
				// try to reuse the views.
				ImageView view = (ImageView) convertView;
				// if convert view is null then create a new instance else reuse
				// it
				if (view == null) {
					view = new ImageView(DragPhoto.this);
				}
				view.setImageResource((Integer) drawables.get(position));
				view.setTag(String.valueOf(position));
				return view;
			}

			@Override
			// Get the row id associated with the specified position in the
			// list.
			public long getItemId(int position) {
				return position;
			}

			@Override
			// Get the data item associated with the specified position in the
			// data set.
			public Object getItem(int position) {
				return drawables.get(position);
			}

			@Override
			// How many items are in the data set represented by this Adapter.
			public int getCount() {
				return drawables.size();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onDrag(View view, DragEvent dragEvent) {
		switch (dragEvent.getAction()) {
		case DragEvent.ACTION_DRAG_STARTED:
			// Drag has started
			// If called for trash resize the view and return true
			if (view.getId() == R.id.trash_can) {
				view.animate().scaleX(1.0f);
				view.animate().scaleY(1.0f);
				return true;
			} else // else check the mime type and set the view visibility
			if (dragEvent.getClipDescription().hasMimeType(
					ClipDescription.MIMETYPE_TEXT_PLAIN)) {
				view.setVisibility(View.GONE);
				return true;

			} else {
				return false;
			}
		case DragEvent.ACTION_DRAG_ENTERED:
			// Drag has entered view bounds
			// If called for trash can then scale it.
			if (view.getId() == R.id.trash_can) {
				view.animate().scaleX(1.5f);
				view.animate().scaleY(1.5f);
			}
			return true;
		case DragEvent.ACTION_DRAG_EXITED:
			// Drag exited view bounds
			// If called for trash can then reset it.
			if (view.getId() == R.id.trash_can) {
				view.animate().scaleX(1.0f);
				view.animate().scaleY(1.0f);
			}
			view.invalidate();
			return true;
		case DragEvent.ACTION_DRAG_LOCATION:
			// Ignore this event
			return true;
		case DragEvent.ACTION_DROP:
			// Dropped inside view bounds
			// If called for trash can then delete the item and reload the grid
			// view
			if (view.getId() == R.id.trash_can) {
				drawables.remove(draggedIndex);
				draggedIndex = -1;
			}
			adapter.notifyDataSetChanged();
		case DragEvent.ACTION_DRAG_ENDED:
			// Hide the trash can
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					findViewById(R.id.trash_can).setVisibility(View.GONE);
				}
			}, 1000l);
			if (view.getId() == R.id.trash_can) {
				view.animate().scaleX(1.0f);
				view.animate().scaleY(1.0f);
			} else {
				view.setVisibility(View.VISIBLE);
			}
			// remove drag listeners
			view.setOnDragListener(null);
			return true;

		}
		return false;
	}

	@Override
	public boolean onItemLongClick(AdapterView gridView, View view,
			int position, long row) {
		ClipData.Item item = new ClipData.Item((String) view.getTag());
		ClipData clipData = new ClipData((CharSequence) view.getTag(),
				new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN }, item);
		view.startDrag(clipData, new View.DragShadowBuilder(view), null, 0);
		View trashCan = findViewById(R.id.trash_can);
		trashCan.setVisibility(View.VISIBLE);

		trashCan.setOnDragListener(DragPhoto.this);
		draggedIndex = position;
		return true;
	}
}

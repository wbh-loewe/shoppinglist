package com.wbh.loewe.shoppinglist;

import android.app.ExpandableListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;

import com.wbh.loewe.shoppinglist.database.ShoppingListDatabase;


/**
 * Expandable lists backed by a Simple Map-based adapter
 */
public class ExpandableArticleListActivity extends ExpandableListActivity 
{
	
	protected Cursor mGroupCursor;
	protected Cursor mChildCursor;
	private ExpandableListView mListView;
	protected CustomCursorTreeAdapter mAdapter;
	protected ShoppingListApplication mShoppinglistapp;
	protected int mGroupItemLayout;
	protected int mChildItemLayout;
	protected String[] mGroupFrom;
	protected int[] mGroupTo;
	protected String[] mChildFrom;
	protected int[] mChildTo;
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        int lLayout = 0;
        Bundle lExtras = getIntent().getExtras();
		if (lExtras != null) {
			lLayout = lExtras.getInt("LAYOUT");
		}
        
        setContentView(lLayout);
        
        mShoppinglistapp = (ShoppingListApplication)getApplication();
        mListView = getExpandableListView();
	}
    
    protected Cursor getGroupCursor() {
    	return null;
    }
    
    protected Cursor getChildCursor(int aGroupID) {
    	return null;
    }
    
    public void fillData() {
    	mGroupCursor = getGroupCursor();
    	startManagingCursor(mGroupCursor);
    	       
    	// Adapter f�r ExpandableList erstellen
    	mAdapter = new CustomCursorTreeAdapter(
    			this, 
    			mGroupCursor, 
    			mGroupItemLayout, 
    			mGroupFrom, 
    			mGroupTo, 
    	        mChildItemLayout, 
    	        mChildFrom, 
    	        mChildTo) {
    	          
    	        @Override
    	        protected Cursor getChildrenCursor(Cursor groupCursor) {
    	        	// DB-Abfrage um die Kindelemente darzustellen
    	            mChildCursor = getChildCursor(mGroupCursor.getInt(mGroupCursor.getColumnIndex(ShoppingListDatabase.FIELD_NAME_ID)));
    	            startManagingCursor(mChildCursor);
    	            return mChildCursor;
    	          }
    	        };
    	        
    	        // Der ExpandableListView den Adapter zuweisen
    	        mListView.setAdapter(mAdapter);
    }
}

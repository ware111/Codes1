package com.example.drawereffecttest;




import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	int[] res = {R.drawable.earth, R.drawable.jupiter, R.drawable.mars, R.drawable.mercury,
			R.drawable.neptune, R.drawable.saturn, R.drawable.uranus,R.drawable.venus};
	ListView list;
	DrawerLayout draw;
	ActionBar bar;
	ArrayAdapter<String> adapter;
	String[] title;
	ActionBarDrawerToggle toggle;
	String s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bar = getActionBar();
		bar.setLogo(getResources().getDrawable(R.drawable.ilauncher55));
		//bar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_drawer));
		draw = (DrawerLayout)findViewById(R.id.drawer);
		 draw.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		list = (ListView)findViewById(R.id.list);
		title = getResources().getStringArray(R.array.planet);
		adapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item , title);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				selectItem(res[position%res.length], position);
			}
		});
		//draw.setDrawerListener(new MyListener(this, draw, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close));
	   	 getActionBar().setDisplayHomeAsUpEnabled(true);
	     getActionBar().setHomeButtonEnabled(true);
	       
	        if (savedInstanceState == null){
	        	selectItem(R.drawable.earth, 0);
	        }
	        toggle = new ActionBarDrawerToggle(
	                this,                  /* host Activity */
	                draw,         /* DrawerLayout object */
	                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
	                R.string.drawer_open,  /* "open drawer" description for accessibility */
	                R.string.drawer_close  /* "close drawer" description for accessibility */
	                ) {
	        	@Override
	    		public void onDrawerClosed(View drawerView) {
	    			// TODO Auto-generated method stub
	    			super.onDrawerClosed(drawerView);
	    			bar.setTitle(s);
	    			
	    			invalidateOptionsMenu();
	    		}
	    		
	    		@Override
	    		public void onDrawerOpened(View drawerView) {
	    			// TODO Auto-generated method stub
	    			super.onDrawerOpened(drawerView);
	    			bar.setTitle("222");
	    			invalidateOptionsMenu();
	    		}
	        };
	        draw.setDrawerListener(toggle);
	        bar.setDisplayHomeAsUpEnabled(true);
	        bar.setHomeButtonEnabled(true);
	        
	}
	
	private void selectItem(int resId, int position) {
		// TODO Auto-generated method stub
		Fragment fragment = new PlanetFragment(resId);
		Bundle args = new Bundle();
		args.putInt("planet_number", position);
		getFragmentManager().beginTransaction()
							.replace(R.id.content, fragment)
							.commit();
		list.setItemChecked(position, true);
		draw.closeDrawer(list);
		bar.setTitle(title[position]);
		s = title[position];
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		 if (toggle.onOptionsItemSelected(item)) {  //点击向上按钮，从左弹出listview
	            return true;
	        }
		return super.onOptionsItemSelected(item);
	}
	
	class MyListener extends ActionBarDrawerToggle{

		public MyListener(Activity activity, DrawerLayout drawerLayout,
			 int drawerImageRes,
				int openDrawerContentDescRes, int closeDrawerContentDescRes) {
			super(activity, drawerLayout, drawerImageRes,
					openDrawerContentDescRes, closeDrawerContentDescRes);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void onDrawerClosed(View drawerView) {
			// TODO Auto-generated method stub
			super.onDrawerClosed(drawerView);
			bar.setTitle(s);
			System.out.println("======================================");
			Toast.makeText(getApplicationContext(), "点击了home键！！！", Toast.LENGTH_LONG).show();
			//invalidateOptionsMenu();
		}
		
		@Override
		public void onDrawerOpened(View drawerView) {
			// TODO Auto-generated method stub
			super.onDrawerOpened(drawerView);
			Toast.makeText(getApplicationContext(), "点击了home键！！！", Toast.LENGTH_LONG).show();
			bar.setTitle("222");
			invalidateOptionsMenu();
		}
	}
	  @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	        // If the nav drawer is open, hide action items related to the content view
	        boolean drawerOpen = draw.isDrawerOpen(list);
	        menu.findItem(com.example.drawereffecttest.R.id.item1).setVisible(!drawerOpen);
	        return super.onPrepareOptionsMenu(menu);
	    }
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        toggle.onConfigurationChanged(newConfig);
    }
}

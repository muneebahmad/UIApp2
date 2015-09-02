package dringg.com.uiapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Random;

import pk.muneebahmad.ui.MyAdapter;
import pk.muneebahmad.ui.MyPagerAdapter;
import pk.muneebahmad.ui.MyTwoButtDialogListener;
import pk.muneebahmad.ui.UIManager;
import pk.muneebahmad.util.Log;

/**
 * @author muneebahmad
 */
public class MainActivity extends AppCompatActivity implements MyTwoButtDialogListener {

    private ViewPager viewPager;
    private MyPagerAdapter pagerAdapter;
    private PagerTitleStrip pagerTitleStrip;
    private ActionBar actionBar;

    public static MainActivity activity;

    private String[] posNames = {
            "CALL HISTORY",
            "DIALER",
            "FAVORITES",
            "CONTACTS"
    };

    private String[] titles = {
            "Profile",
            "About",
            "Share",
            "Contacts",
            "Favorites",
            "Settings",
            "License"
    };

    private int[] icons = {
            R.drawable.ic_contacts,
            R.drawable.ic_about,
            R.drawable.ic_share,
            R.drawable.ic_profile,
            R.drawable.ic_favorites,
            R.drawable.ic_settings,
            R.drawable.ic_license
    };

    private String names[] = {
            "Abdul Bari",
            "Mumtaz Lone",
            "Ahmad Kamal",
            "Jalal Khan",
            "Nazim ud Din"
    };

    private String emal[] = {
            "abdulbari@gmail.com",
            "mumtazlone@yahoo.com",
            "ahmad_kamal@hotmail.com",
            "jalal_khan@gmail.com",
            "nazim_123@yahoo.com"
    };

    private int profile = R.drawable.ic_contacts;
    private String n = names[0];
    private String e = emal[0];

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mDrawerToggle;

    private Toolbar toolbar;

    private boolean isOpened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = MainActivity.this;
        //--------- DEBUG, REMOVE LATER -------------------
              setEmailAndName();                   //\\
        //-------------------------------------------------
        UIManager.getInstance().setActivatedView(UIManager.ActivatedView.VIEW_MAIN);
        UIManager.getInstance().addTwoButtonDialogClickListener(this);
        setToolbar();
        setDrawer();
        setViewPager();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void setDrawer() {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mRecyclerView.setHasFixedSize(true);
        this.mAdapter = new MyAdapter(titles, icons, n, e, profile);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mLayoutManager = new LinearLayoutManager(this);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);

        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                isOpened = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                isOpened = false;
            }



        }; // Drawer Toggle Object Made
        drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void setViewPager() {
        viewPager = (ViewPager) findViewById(R.id.main_pager);
        pagerTitleStrip = (PagerTitleStrip) findViewById(R.id.main_pager_title_strip);
        actionBar = getSupportActionBar();
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pagerAdapter.setCtx(getApplication());
        viewPager.setAdapter(pagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.log(Log.LOG_ERROR, "PAGE SELECTED >>> " + position + " >>> " + posNames[position]);
                switch (position) {
                    case 0:
                        UIManager.getInstance().setActivatedView(UIManager.ActivatedView.VIEW_CALL_LOG);
                        break;
                    case 1:
                        UIManager.getInstance().setActivatedView(UIManager.ActivatedView.VIEW_DIALER);
                        break;
                    case 2:
                        UIManager.getInstance().setActivatedView(UIManager.ActivatedView.VIEW_FAVORITES);
                        break;
                    case 3:
                        UIManager.getInstance().setActivatedView(UIManager.ActivatedView.VIEW_CONTACTS);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setEmailAndName() {
        int r = new Random().nextInt(5);
        this.n = names[r];
        this.e = emal[r];
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewPager.setCurrentItem(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_about) {

        } else if (id == R.id.action_disconnect) {

        } else if (id == R.id.action_quit) {
            makeExitDialog();
        } else if (id == R.id.action_share) {

        } else if (id == R.id.action_search) {

        }

        return super.onOptionsItemSelected(item);
    }

    private void makeExitDialog() {
        UIManager.getInstance().makeTwoButtonDialog(this, "Exit Dialog",
                "Do you really want to shutdown and quit?",
                "Yes", "No");
    }

    public static Activity getActivity() {
        return activity;
    }

    @Override
    public void onFirstButtClicked(DialogInterface dialog, int which) {
        System.exit(0);
    }

    @Override
    public void onSecondButtClicked(DialogInterface dialog, int which) {
        dialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        if (isOpened) {
            this.drawer.closeDrawers();
        } else {
            makeExitDialog();
        }
    }
}/** end class. */

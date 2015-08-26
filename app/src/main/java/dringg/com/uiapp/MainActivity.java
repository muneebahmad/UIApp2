package dringg.com.uiapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import pk.muneebahmad.ui.MyOneButtonDialogListener;
import pk.muneebahmad.ui.MyPagerAdapter;
import pk.muneebahmad.ui.MyTwoButtDialogListener;
import pk.muneebahmad.ui.UIManager;
import pk.muneebahmad.util.Log;

/**
 * @author muneebahmad
 */
public class MainActivity extends AppCompatActivity implements MyOneButtonDialogListener {

    private ViewPager viewPager;
    private MyPagerAdapter pagerAdapter;
    private PagerTitleStrip pagerTitleStrip;
    private ActionBar actionBar;

    private String[] posNames = {
            "CALL HISTORY",
            "DIALER",
            "FAVORITES",
            "CONTACTS"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UIManager.getInstance().setActivatedView(UIManager.ActivatedView.VIEW_MAIN);
        UIManager.getInstance().addOneButtonDialogClickListner(this);
        setToolbar();
        setViewPager();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
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
            UIManager.getInstance().makeOneButtonDialog(this, "Dialog", "This is a material dialog", "OK");
        } else if (id == R.id.action_share) {

        } else if (id == R.id.action_search) {

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onButtClicked(DialogInterface dialog, int which) {
        dialog.dismiss();
    }
}/** end class. */

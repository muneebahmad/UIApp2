package dringg.com.uiapp;

import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import pk.muneebahmad.ui.MyPagerAdapter;
import pk.muneebahmad.ui.UIManager;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyPagerAdapter pagerAdapter;
    private PagerTitleStrip pagerTitleStrip;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UIManager.getsInstance().setActivatedView(UIManager.ActivatedView.VIEW_MAIN);
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
            finish();
        } else if (id == R.id.action_share) {

        } else if (id == R.id.action_search) {

        }

        return super.onOptionsItemSelected(item);
    }
}/** end class. */

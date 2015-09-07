package dringg.com.uiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import pk.muneebahmad.ui.UIManager;

/**
 * Created by muneebahmad  on 9/6/2015.
 */
public class ThemesActivity extends AppCompatActivity {
    private Toolbar toolbar;

    String themes[] = {
            "Dark Gray",
            "Blue",
            "Orange",
            "Red",
            "LT Gray",
            "Green",
            "PL Green",
            "WA Green"
    };

    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Select Theme");
        setComponents();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.themes_toolbar);
        setSupportActionBar(toolbar);
    }

    private void setComponents() {
        mainLayout = (LinearLayout) findViewById(R.id.settings_main_layout);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, themes);
        ListView lv = (ListView) findViewById(R.id.listView2);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        UIManager.getInstance().setSelectedTheme(UIManager.SelectedTheme.THEME_DARK_GRAY);
                        break;
                    case 1:
                        UIManager.getInstance().setSelectedTheme(UIManager.SelectedTheme.THEME_BLUE);
                        break;
                    case 2:
                        UIManager.getInstance().setSelectedTheme(UIManager.SelectedTheme.THEME_ORANGE);
                        break;
                    case 3:
                        UIManager.getInstance().setSelectedTheme(UIManager.SelectedTheme.THEME_RED);
                        break;
                    case 4:
                        UIManager.getInstance().setSelectedTheme(UIManager.SelectedTheme.THEME_LT_GRAY);
                        break;
                    case 5:
                        UIManager.getInstance().setSelectedTheme(UIManager.SelectedTheme.THEME_GREEN);
                        break;
                    case 6:
                        UIManager.getInstance().setSelectedTheme(UIManager.SelectedTheme.THEME_PL_GREEN);
                        break;
                    case 7:
                        UIManager.getInstance().setSelectedTheme(UIManager.SelectedTheme.THEME_WA);
                        break;
                }
                sendRefresher();
            }
        });
    }

    private void sendRefresher() {
        MainActivity.activity.finish();
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

}/** end class. */

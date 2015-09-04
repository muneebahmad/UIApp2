package dringg.com.uiapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import pk.muneebahmad.data.SharedData;

/**
 * Created by ay on 9/4/2015.
 */
public class CallDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ImageButton callButt;
    private ImageButton smsButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_details);
        setToolbar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(SharedData.getInstance().getCallName() + "  " +
                SharedData.getInstance().getCallNo());
        initComponents();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.call_details_toolbar);
        setSupportActionBar(toolbar);
    }

    private final void initComponents() {
        this.callButt = (ImageButton) findViewById(R.id.details_call_butt);
        this.smsButt = (ImageButton) findViewById(R.id.details_message_butt);
        this.callButt.setOnClickListener(this);
        this.smsButt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}/** end class. */

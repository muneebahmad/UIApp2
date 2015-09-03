package dringg.com.uiapp;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Random;

import pk.muneebahmad.data.SharedData;
import pk.muneebahmad.ui.MessengerLayout;

/**
 * Created by muneebahmad on 9/3/2015.
 */
public class Messenger extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private LinearLayout mainLayout;
    private ScrollView scrollView;
    private TextView numView;
    private TextView timeView;
    private ImageButton contactButton;

    String msgs[] = {
            "Hello i am working, what are you doing?",
            "Hum i am totally fine.",
            "Where are you going today? I am leaving for Karachi.",
            "Hey kolkk klajdlkj lkajdlkj ;kljadj",
            "dkj;akljdlk; kdjlk dlkjd ;ldkj ;lakjd l",
            "oiyuhepoijdk opidsj voakjdp osaijpao dksijvoiad",
            "oiajdsk oadiu aoisduja oidljka oidhja ;klaj ai",
            "doijd a;oidj oidjdoikja[o iodjd iodj odij doij",
            "podian a;oidj aoijdoia modija spoidj apoijd oaidj"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        setToolbar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(SharedData.getInstance().getMessengerName());

        initComponents();
        populate();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.messenger_toolbar);
        setSupportActionBar(toolbar);
    }

    private void initComponents() {
        this.mainLayout = (LinearLayout) findViewById(R.id.messenger_mail_layout);
        this.scrollView = (ScrollView) findViewById(R.id.messenger_scroll_view);
        this.numView = (TextView) findViewById(R.id.messenger_num_tv);
        this.timeView = (TextView) findViewById(R.id.messenger_time_view_tv);
        this.contactButton = (ImageButton) findViewById(R.id.contact_view);
        this.contactButton.setOnClickListener(this);

        this.numView.setText(SharedData.getInstance().getMessengerNo() + "");
        this.timeView.setText("Last seen today \n" + "@08:00");
    }

    private void populate() {
        MessengerLayout.MsgType msgType = MessengerLayout.MsgType.MSG_TYPE_INCOMING;
        for (int i = 0; i < msgs.length; i++) {
            int r = new Random().nextInt(2);
            if (r == 0) {
                msgType = MessengerLayout.MsgType.MSG_TYPE_INCOMING;
            } else if (r == 1) {
                msgType = MessengerLayout.MsgType.MSG_TYPE_OUTGOING;
            }
            this.mainLayout.addView(new MessengerLayout(this, msgs[i], msgType));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_messenger, menu);
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
        } else if (id == R.id.action_search) {

        } else if (id == R.id.action_disconnect) {

        } else if (id == R.id.action_call) {

        } else if (id == R.id.action_share) {

        } else if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        } else if (id == R.id.action_quit) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

}/** end class. */

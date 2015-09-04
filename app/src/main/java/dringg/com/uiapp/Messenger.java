package dringg.com.uiapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Random;

import pk.muneebahmad.data.SharedData;
import pk.muneebahmad.ui.MessengerLayout;
import pk.muneebahmad.util.Dater;
import pk.muneebahmad.util.Log;

/**
 * Created by muneebahmad on 9/3/2015.
 */
public class Messenger extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    public LinearLayout mainLayout;
    private ScrollView scrollView;
    private TextView numView;
    private TextView timeView;
    private ImageButton contactButton;

    private ImageButton smsButton;
    private ImageButton settingsButton;
    private ImageButton newEditButton;
    private ImageButton shareButton;

    private EditText smsEt;

    public static Messenger messenger;

    String msgs[] = {
            "Hello i am working, what are you doing?",
            "Hum i am totally fine.",
            "Where are you going today? I am leaving for Karachi.",
            "Hey i am going to school. Don't disturb me!",
            "This is a long message, This is a long message, I think this is a long message....",
            "This is a very long message, This really is a very long message, I do believe that this is a very long message, " +
                    "and i think you agree too",
            "This is relatively a short message, But this is not a very short message",
            "I can't say anything more, i am done.",
            "Hello, anyone there?"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        messenger = this;
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
        Log.log(Log.LOG_ERROR, Dater.getInstance().getDate());
        this.mainLayout = (LinearLayout) findViewById(R.id.messenger_mail_layout);
        this.scrollView = (ScrollView) findViewById(R.id.messenger_scroll_view);
        this.scrollView.setNextFocusDownId(R.id.messenger_mail_layout);
        runScroller();
        this.numView = (TextView) findViewById(R.id.messenger_num_tv);
        this.timeView = (TextView) findViewById(R.id.messenger_time_view_tv);
        this.contactButton = (ImageButton) findViewById(R.id.contact_view);
        this.contactButton.setOnClickListener(this);

        this.shareButton = (ImageButton) findViewById(R.id.butt_share);
        this.smsButton = (ImageButton) findViewById(R.id.send_button);
        this.settingsButton = (ImageButton) findViewById(R.id.butt_settings);
        this.newEditButton = (ImageButton) findViewById(R.id.butt_edit);
        this.shareButton.setOnClickListener(this);
        this.smsButton.setOnClickListener(this);
        this.settingsButton.setOnClickListener(this);
        this.newEditButton.setOnClickListener(this);

        this.smsEt = (EditText) findViewById(R.id.send_msg_et);

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
            this.mainLayout.addView(new MessengerLayout(this, this, msgs[i], msgType));
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
        if (v == this.newEditButton) {

        } else if (v == this.contactButton) {

        } else if (v == this.settingsButton) {

        } else if (v == this.shareButton) {

        } else if (v == this.smsButton) {
            final String str = this.smsEt.getText().toString();
            View outView = new MessengerLayout(this, this, str, MessengerLayout.MsgType.MSG_TYPE_OUTGOING);
            this.mainLayout.addView(outView);
            runScroller();

            ////-----------> FAKE MSG
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    View inView = new MessengerLayout(Messenger.this, Messenger.this, str.toUpperCase(),
                            MessengerLayout.MsgType.MSG_TYPE_INCOMING);
                    mainLayout.addView(inView);
                    runScroller();
                }
            }, 5000);
        }
        this.smsEt.setText("");
    }

    public final void runScroller() {
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    /**
     *
     * @param color
     */
    public void changeTheme(int color) {
        LinearLayout item1 = (LinearLayout) findViewById(R.id.color_item_1);
        LinearLayout item2 = (LinearLayout) findViewById(R.id.color_item_2);

        this.toolbar.setBackgroundColor(color);
        item1.setBackgroundColor(color);
        item2.setBackgroundColor(color);
        this.smsButton.setBackgroundColor(color);
        this.newEditButton.setBackgroundColor(color);
        this.contactButton.setBackgroundColor(color);
        this.settingsButton.setBackgroundColor(color);
        this.shareButton.setBackgroundColor(color);
    }

}/** end class. */

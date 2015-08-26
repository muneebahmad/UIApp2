package pk.muneebahmad.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.util.Random;

import dringg.com.uiapp.R;

/**
 * Created by muneebahmad on 8/25/2015.
 */
public class CallLogFragment extends Fragment implements View.OnClickListener {

    private LinearLayout mainLayout;
    private ToggleButton callButt;
    private ToggleButton smsButt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_call_log, container, false);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 2, 0);
        this.callButt = (ToggleButton) rootView.findViewById(R.id.tab_butt_left);
        this.smsButt = (ToggleButton) rootView.findViewById(R.id.tab_butt_right);
        this.callButt.setOnClickListener(this);
        this.smsButt.setOnClickListener(this);
        this.mainLayout = (LinearLayout) rootView.findViewById(R.id.call_log_linear_layout);

        for (int i = 0; i < 15; i++) {
            int r = new Random().nextInt(3);
            CallHistoryListLayout.AttendTypes a = CallHistoryListLayout.AttendTypes.CALL_INCOMING;

            int t = new Random().nextInt(2);
            String time = "";
            if (t == 0) {
                time = "Today";
            } else if (t == 1) {
                time = "Yesterday";
            }

            switch (r) {
                case 0:
                    a = CallHistoryListLayout.AttendTypes.CALL_INCOMING;
                    break;
                case 1:
                    a = CallHistoryListLayout.AttendTypes.CALL_MISSED;
                    break;
                case 2:
                    a = CallHistoryListLayout.AttendTypes.CALL_OUTGOING;
                    break;
                default:
                    break;
            }

            this.mainLayout.addView(new CallHistoryListLayout(rootView.getContext(), "HK Baba", "90909090", time,
                            a),
                    params);
        }

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v == this.callButt) {

        } else if (v == this.smsButt) {

        }
    }
}/** end class. */

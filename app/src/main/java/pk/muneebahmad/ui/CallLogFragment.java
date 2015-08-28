package pk.muneebahmad.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private LinearLayout.LayoutParams params;
    private CallHistoryListLayout.AttendTypes a = CallHistoryListLayout.AttendTypes.CALL_INCOMING;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_call_log, container, false);

        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 2, 0);
        this.callButt = (ToggleButton) rootView.findViewById(R.id.tab_butt_left);
        this.smsButt = (ToggleButton) rootView.findViewById(R.id.tab_butt_right);
        this.callButt.setOnClickListener(this);
        this.smsButt.setOnClickListener(this);
        this.mainLayout = (LinearLayout) rootView.findViewById(R.id.call_log_linear_layout);
        UIManager.getInstance().setActivatedView(UIManager.ActivatedView.VIEW_CALL_LOG);
        this.callButt.setChecked(true);
        check(rootView);
        return rootView;
    }

    /**
     *
     * @param view
     */
    private void check(View rootView) {
        if (UIManager.getInstance().getActivatedView() == UIManager.ActivatedView.VIEW_CALL_LOG) {
            addCallLog(rootView);
        } else if (UIManager.getInstance().getActivatedView() == UIManager.ActivatedView.VIEW_SMS) {
            addSmsLog(rootView);
        }
    }

    /**
     *
     * @param rootView
     */
    private void addCallLog(View rootView) {
        for (int i = 0; i < 15; i++) {
            int r = new Random().nextInt(3);


            String names[] = {
                    "HK Baba",
                    "Jahanzeb",
                    "Rfiq",
                    "Shehzad",
                    "Rizwan",
                    "Mukhtar",
                    "Waqar",
                    "Haneef",
                    "Zulfiqar"
            };

            int phNo[] = {
                    032315454,
                    034566664,
                    251345643,
                    1243533535,
                    254343433,
                    12121212,
                    12132424,
                    56565656,
                    01230002,
            };

            int z = new Random().nextInt(9);

            int t = new Random().nextInt(2);
            String time = "";
            if (t == 0) {
                time = "Today";
            } else if (t == 1) {
                time = "Yesterday";
            }

            checkForCallType(r);

            this.mainLayout.addView(new CallHistoryListLayout(rootView.getContext(), names[z], phNo[z] + "", time,
                            a, i),
                    params);
        }
    }

    /**
     *
     * @param rootView
     */
    private void addSmsLog(View rootView) {
        String names[] = {
                "Hammad",
                "Shahid",
                "Jawad",
                "Nizam",
                "Sabir",
                "Waseem",
                "Imran",
                "Afzal",
                "Tanveer",
                "Hassan",
                "Zahid"
        };


        int phNums[] = {828282828, 78787878, 12894949, 89898989, 89898989,
                        89898988,  90909090, 90909090, 13435466, 14353555,
                        98989090
        };
        for (int i = 0; i < 24; i++) {
            int r = new Random().nextInt(3);
            int n = new Random().nextInt(11);
            checkForCallType(r);
            this.mainLayout.addView(new
                    CallHistoryListLayout(rootView.getContext(), names[n],
                    phNums[n] + "", "" + (n + 1), a, i), params);
        }
    }

    /**
     *
     * @param r
     */
    private void checkForCallType(int r) {
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
    }

    /**
     *
     * @param rootView
     */
    private void updateView(View rootView) {
        this.mainLayout.removeAllViews();
        check(rootView);
    }

    @Override
    public void onClick(View v) {
        if (v == this.callButt) {
            this.callButt.setChecked(true);
            this.smsButt.setChecked(false);
            UIManager.getInstance().setActivatedView(UIManager.ActivatedView.VIEW_CALL_LOG);
            updateView(rootView);
        } else if (v == this.smsButt) {
            this.smsButt.setChecked(true);
            this.callButt.setChecked(false);
            UIManager.getInstance().setActivatedView(UIManager.ActivatedView.VIEW_SMS);
            updateView(rootView);
        }
    }
}/** end class. */

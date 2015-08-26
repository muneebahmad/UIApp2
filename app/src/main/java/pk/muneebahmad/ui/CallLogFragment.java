package pk.muneebahmad.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import dringg.com.uiapp.R;

/**
 * Created by muneebahmad on 8/25/2015.
 */
public class CallLogFragment extends Fragment {

    private LinearLayout mainLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_call_log, container, false);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 2, 0);
        this.mainLayout = (LinearLayout) rootView.findViewById(R.id.call_log_linear_layout);

        for (int i = 0; i < 15; i++) {
            this.mainLayout.addView(new CallHistoryListLayout(rootView.getContext(), "HK Baba", "90909090", "yesterday"),
                    params);
        }

        return rootView;
    }
}/** end class. */

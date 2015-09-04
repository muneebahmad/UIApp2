package pk.muneebahmad.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import dringg.com.uiapp.R;
import pk.muneebahmad.data.SharedData;

/**
 * Created by muneebahmad on 8/25/2015.
 */
public class FavoritesFragment extends Fragment {

    private LinearLayout mainLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);

        this.mainLayout = (LinearLayout) rootView.findViewById(R.id.fav_main_layout);

        if (SharedData.favList.size() > 0) {
            for (int i = 0; i < SharedData.favList.size(); i++) {
                mainLayout.addView(new MessengerLayout(getContext(), getActivity(), SharedData.favList.get(i) + "\n\n" + "from: " +
                        SharedData.favNameList.get(i),
                        MessengerLayout.MsgType.MSG_TYPE_INCOMING));
            }
        }

        return rootView;
    }

}/** end class. */

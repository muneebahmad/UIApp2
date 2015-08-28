package pk.muneebahmad.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Random;

import dringg.com.uiapp.R;

/**
 * Created by muneebahmad on 8/25/2015.
 */
public class ContactsFragment extends Fragment {

    private LinearLayout mainLayout;
    private LinearLayout.LayoutParams params;

    private String[] names = {
            "Shahzad Anwar",
            "Bano Qudsia",
            "Zaheer ud Din",
            "Kamran Ashraf",
            "Hamid Mir",
            "Kashif Abbasi",
            "Alam Channa",
            "Saleem Safi",
            "Kamran Saleem",
            "Irshad Khan Jadoon",
            "Waqar Younis",
            "Waseem Akram",
            "Shoaib Akhtar",
            "Kamran Khan",
            "Akhunzada Chittan",
            "Naveed Baloch"
    };

    private long pN[] = {
            232323223,
            23232323,
            23232323,
            23232323,
            23232323,
            23232323,
            35435434,
            57656545,
            12322323,
            21433454,
            13325324,
            24321212,
            12323434,
            43665665,
            238765667,
            3434543
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);

        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 2, 0);
        this.mainLayout = (LinearLayout) rootView.findViewById(R.id.contacts_linear_layout);
        for (int i = 0; i < 15; i++) {
            int r = new Random().nextInt(16);
            this.mainLayout.addView(new ContactsLayout(getContext(), names[r], "" + pN[r], null, i), params);
        }

        return rootView;
    }

}/** end class. */

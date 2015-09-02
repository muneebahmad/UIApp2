package pk.muneebahmad.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import java.util.Random;

import dringg.com.uiapp.R;

/**
 * Created by muneebahmad on 8/25/2015.
 */
public class ContactsFragment extends Fragment implements SearchView.OnQueryTextListener {

    private LinearLayout mainLayout;
    private LinearLayout.LayoutParams params;
    private SearchView searchView;
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

        addSearchView(rootView);
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

    /**
     *
     * @param rootView
     */
    private void addSearchView(View rootView) {
        this.searchView = (SearchView) rootView.findViewById(R.id.contacts_search_view);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(query)) {
                this.searchView.setQueryHint(query);
            }
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].contains(newText)) {
                this.searchView.setQueryHint(names[i]);
            }
        }
        return false;
    }
}/** end class. */

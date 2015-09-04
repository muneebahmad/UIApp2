package pk.muneebahmad.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import dringg.com.uiapp.R;

/**
 * Created by muneebahmad on 8/25/2015.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    private int imgResIds[] = {
            R.drawable.history_tab,
            R.drawable.dialer_tab,
            R.drawable.fav_tab,
            R.drawable.contacts_tab
    };

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setCtx(Context ctx) {
        this.context = ctx;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CallLogFragment();
            case 1:
                return new DialerFragment();
            case 2:
                return new FavoritesFragment();
            case 3:
                return new ContactsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        SpannableString val = new SpannableString(" ");
        String str = "";
        switch (position) {
            case 0:
                //Drawable img = context.getResources().getDrawable(imgResIds[0]);
                //img.setBounds(0, 0, img.getIntrinsicWidth(), img.getIntrinsicHeight());
                //ImageSpan imageSpan = new ImageSpan(img, ImageSpan.ALIGN_BOTTOM);
                //val.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                str = "Call Log";
                break;
            case 1:
                //Drawable img2 = context.getResources().getDrawable(imgResIds[1]);
                //img2.setBounds(0, 0, img2.getIntrinsicWidth(), img2.getIntrinsicHeight());
                //ImageSpan imageSpan2 = new ImageSpan(img2, ImageSpan.ALIGN_BOTTOM);
                //val.setSpan(imageSpan2, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                str = "Dialer";
                break;
            case 2:
                //Drawable img3 = context.getResources().getDrawable(imgResIds[2]);
                //img3.setBounds(0, 0, img3.getIntrinsicWidth(), img3.getIntrinsicHeight());
                //ImageSpan imageSpan3 = new ImageSpan(img3, ImageSpan.ALIGN_BOTTOM);
                //val.setSpan(imageSpan3, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                str = "Favorites";
                break;
            case 3:
                //Drawable img4 = context.getResources().getDrawable(imgResIds[3]);
                //img4.setBounds(0, 0, img4.getIntrinsicWidth(), img4.getIntrinsicHeight());
                //ImageSpan imageSpan4 = new ImageSpan(img4, ImageSpan.ALIGN_BOTTOM);
                //val.setSpan(imageSpan4, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                str = "Contacts";
                break;
            default:
                break;
        }
        return str.toUpperCase();
    }
}/** end class. */

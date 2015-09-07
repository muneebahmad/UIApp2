package pk.muneebahmad.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import dringg.com.uiapp.MainActivity;
import dringg.com.uiapp.R;
import dringg.com.uiapp.ThemesActivity;
import pk.muneebahmad.util.Log;
import pk.muneebahmad.util.NetStats;

/**
 * Created by ay on 8/28/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements MyOneButtonDialogListener {

    private static final int TYPE_HEADER = 0x00;
    private static final int TYPE_ITEM = 0x01;

    private String navTitles[];
    private int icons[];

    private String name;
    private int profile;
    private String email;

    private String about = "LA ALAL PALALA ALAL ALA LA LA ALLAAL AL AL LALA LA LA LLA" +
            "ALJDSKFJK:LLLKJD:LKDJKDJKDJDKJKDDJKDL:KD:DLKD:LKDDLKD:DDKD:DLKD:DLKD:DKLD:K" +
            "K:D:LKDD:KLD:LDK:LKD:DLKD:LDKDKD:LKD:LKD:DLKD:LDKD:LDKD:LKD:DLKD:DKLD:LDKDKL:" +
            "DK:DLUFIOJDKJDLK KJD:LK D: KDKJ KLJ :KLJ :KL J:KLJ  KJKJ  Jklj kj kk jkj k kl j" +
            "fkl;jg;lskfj;glksjf; oifjngvkfl sifugj l;fkdjg flkjg fkl sfjg sflkjgs ;fklj sfk" +
            "lfkj;lsjf gjf gj klj sfl;kjgsfklj gsklfjg sflkjg sklfj gklsfjg ksf;j gs;fkj g;slk fj" +
            "fklj fs;lkj ;l";

    /**
     *
     * @param titles
     * @param icons
     * @param name
     * @param email
     * @param profile
     */
    public MyAdapter(String titles[], int icons[], String name, String email, int profile) {
        this.navTitles = titles;
        this.icons = icons;
        this.name = name;
        this.email = email;
        this.profile = profile;
        UIManager.getInstance().addOneButtonDialogClickListner(this);
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
            ViewHolder vhItem = new ViewHolder(v, viewType);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String t = "CLICKED";
                    if (v == parent.getChildAt(1)) {
                        t = "Clicked Profile";
                    } else if (v == parent.getChildAt(2)) {
                        t = "Clicked About";
                        UIManager.getInstance().makeOneButtonDialog(MainActivity.getActivity(), "About", about, "OK");
                    } else if (v == parent.getChildAt(3)) {
                        t = "Clicked Share";
                    } else if (v == parent.getChildAt(4)) {
                        t = "Clicked Contacts";
                    } else if (v == parent.getChildAt(5)) {
                        t = "Clicked Favorites";
                    } else if (v == parent.getChildAt(6)) {
                        t = "Clicked Settings";
                    } else if (v == parent.getChildAt(7)) {
                        t = "Clicked License";
                    } else if (v == parent.getChildAt(8)) {
                        v.getContext().startActivity(new Intent(MainActivity.activity, ThemesActivity.class));
                    }
                    Log.log(Log.LOG_ERROR, t);
                }
            });
            return vhItem;
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
            RelativeLayout r = (RelativeLayout) v.findViewById(R.id.header_relative_layout);
            TextView t = (TextView) v.findViewById(R.id.connection_index);
            if (NetStats.isConnected(v.getContext())) {
                r.setBackgroundResource(R.drawable.bg_banner2);
                t.setTextColor(Color.parseColor("#009900"));
                t.setText("Online");
            } else if (!(NetStats.isConnected(v.getContext()))) {
                r.setBackgroundResource(R.drawable.bg_banner);
                t.setTextColor(Color.parseColor("#ff0000"));
                t.setText("Offline");
            }
            ViewHolder vhHeader = new ViewHolder(v, viewType);
            return vhHeader;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        if(holder.holderId ==1) {
            holder.textView.setText(navTitles[position - 1]);
            holder.imageView.setImageResource(icons[position -1]);
        } else{
            holder.profile.setImageResource(profile);
            holder.name.setText(name);
            holder.email.setText(email);
        }
    }

    @Override
    public int getItemCount() {
        return this.navTitles.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    /**
     *
     * @param position
     * @return
     */
    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public void onButtClicked(DialogInterface dialog, int which) {
        dialog.dismiss();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        int holderId;
        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView name;
        TextView email;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);

            if (viewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                holderId = 1;
            } else {
                name = (TextView) itemView.findViewById(R.id.name);
                email = (TextView) itemView.findViewById(R.id.email);
                profile = (ImageView) itemView.findViewById(R.id.circleView);
                holderId = 0;
            }
        }
    }

}/** end class. */

package pk.muneebahmad.ui;

import android.content.DialogInterface;

/**
 * Created by ay on 8/26/2015.
 */
public interface MyTwoButtDialogListener {

    public abstract void onFirstButtClicked(DialogInterface dialog, int which);
    public abstract void onSecondButtClicked(DialogInterface dialog, int which);
}

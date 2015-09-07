package pk.muneebahmad.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import dringg.com.uiapp.R;
import pk.muneebahmad.util.Log;
import pk.muneebahmad.util.NetStats;

/**
 * Created by muneebahmad on 8/25/2015.
 */
public class DialerFragment extends Fragment implements View.OnClickListener,
        View.OnLongClickListener {

    private TextView tv;
    private ImageButton dialpadButt;
    private ImageButton callButt;
    private LinearLayout dpadLayout;
    private LinearLayout tvLayout;
    private View openLayout;

    private Animation downAnim;
    private Animation downAnimRev;
    private ImageButton butts[];
    private ImageButton bckSpace;
    private LinearLayout colorItem1;
    private Vibrator vibrator;

    private enum DpadState {
        DPAD_UP,
        DPAD_DOWN,
    }

    public enum State {
        STATE_NUMBER,
        STATE_HINT,
    }

    private State state;
    private DpadState dpad;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialer, container, false);

        this.tv = (TextView) rootView.findViewById(R.id.dialer_field);

        this.state = State.STATE_HINT;

        if (this.state == State.STATE_HINT) {
            this.tv.setTextColor(Color.parseColor("#cccccc"));
        } else if (this.state == State.STATE_NUMBER) {
            this.tv.setTextColor(Color.parseColor("#ffffff"));
        }
        this.tv.setMaxLines(1);
        this.tv.setText("Offline");

        initComponents(rootView);
        this.dpad = DpadState.DPAD_DOWN;
        this.dpadLayout.startAnimation(new TranslateToHide(1.0f, 1.0f, 1.0f, 0, 500, dpadLayout, true));
        this.tvLayout.startAnimation(new TranslateToHide(1, 1, 1, -1, 500, tvLayout, true));
        this.callButt.setEnabled(false);
        this.dialpadButt.setRotation(180.0f);
        checkNetwork(getContext());
        setTheme();
        return rootView;
    }

    /**
     *
     * @param rootView
     */
    private void initComponents(View rootView) {
        vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        this.colorItem1 = (LinearLayout) rootView.findViewById(R.id.color_item_1);
        this.dpadLayout = (LinearLayout) rootView.findViewById(R.id.dpad_layout);
        this.tvLayout = (LinearLayout) rootView.findViewById(R.id.tv_layout);
        this.dialpadButt = (ImageButton) rootView.findViewById(R.id.butt_dialpad);
        this.dialpadButt.setOnClickListener(this);
        this.bckSpace = (ImageButton) rootView.findViewById(R.id.butt_bckspace);
        this.bckSpace.setOnClickListener(this);
        this.bckSpace.setOnLongClickListener(this);
        this.callButt = (ImageButton) rootView.findViewById(R.id.call_butt);
        this.callButt.setOnClickListener(this);
        this.downAnim = AnimationUtils.loadAnimation(getContext(), R.anim.key_butt_rotate);
        this.downAnimRev = AnimationUtils.loadAnimation(getContext(), R.anim.key_butt_rotate_reverse);

        this.butts = new ImageButton[12];
        butts[0] = (ImageButton) rootView.findViewById(R.id.butt_one);
        butts[1] = (ImageButton) rootView.findViewById(R.id.butt_two);
        butts[2] = (ImageButton) rootView.findViewById(R.id.butt_three);
        butts[3] = (ImageButton) rootView.findViewById(R.id.butt_four);
        butts[4] = (ImageButton) rootView.findViewById(R.id.butt_five);
        butts[5] = (ImageButton) rootView.findViewById(R.id.butt_six);
        butts[6] = (ImageButton) rootView.findViewById(R.id.butt_seven);
        butts[7] = (ImageButton) rootView.findViewById(R.id.butt_eight);
        butts[8] = (ImageButton) rootView.findViewById(R.id.butt_nine);
        butts[9] = (ImageButton) rootView.findViewById(R.id.butt_sharp);
        butts[10] = (ImageButton) rootView.findViewById(R.id.butt_zero);
        butts[11] = (ImageButton) rootView.findViewById(R.id.butt_star);

        for (int i = 0; i < butts.length; i++) {
            butts[i].setOnClickListener(this);
            butts[i].setOnLongClickListener(this);
        }
    }

    private void checkNetwork(Context context) {
        if (NetStats.isConnected(getContext())) {
            this.tv.setText("Online");
            Log.log(Log.LOG_ERROR, "CONNECTED TO WIFI....");
        }else if (!(NetStats.isConnected(getContext()))) {
            this.tv.setText("Offline");
            Log.log(Log.LOG_ERROR, "NOT CONNECTED TO WIFI....");
        }
    }

    private void setTheme() {
        this.tvLayout.setBackgroundColor(Color.parseColor(UIManager.getInstance().getThemeColor()));
        this.colorItem1.setBackgroundColor(Color.parseColor(UIManager.getInstance().getThemeColor()));
    }

    /**
     *
     * @param millis
     */
    private void vibrateFor(long millis) {
        vibrator.vibrate(millis);
    }

    /**
     *
     * @param phNum
     */
    public static void recieveNo(final String phNum) {

    }

    @Override
    public void onClick(View v) {
        if (v == this.dialpadButt) {
            Log.log(Log.LOG_ERROR, "DPAD BUTTON CLICKED...");
            if (this.dpad == DpadState.DPAD_UP) {
                this.dpad = DpadState.DPAD_DOWN;
                this.dpadLayout.startAnimation(new TranslateToHide(1.0f, 1.0f, 1.0f, 0, 500, dpadLayout, true));
                this.tvLayout.startAnimation(new TranslateToHide(1, 1, 1, -1, 500, tvLayout, true));
                this.dialpadButt.clearAnimation();
                this.dialpadButt.startAnimation(this.downAnimRev);
                this.callButt.setEnabled(false);
            } else if (this.dpad == DpadState.DPAD_DOWN) {
                this.dpad = DpadState.DPAD_UP;
                this.tv.setText("");
                this.dpadLayout.startAnimation(new TranslateToShow(1.0f, 1.0f, 0.0f, 1.0f, 500, dpadLayout, true));
                this.tvLayout.startAnimation(new TranslateToShow(1, 1, -1, 1, 500, tvLayout, true));
                this.dialpadButt.clearAnimation();
                this.dialpadButt.startAnimation(this.downAnim);
                this.callButt.setEnabled(true);
            }
        } else if (v == this.bckSpace) {
            String str = this.tv.getText().toString();
            if (str.length() > 1) {
                this.tv.setText(str.substring(0, str.length() - 1));
            } else {
                this.tv.setText("");
            }
        } else if (v == this.callButt && this.callButt.isEnabled()) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + this.tv.getText().toString()));
            startActivity(intent);
        }

        for (int i = 0; i < 9; i++) {
            if (v == butts[i]) {
                tv.append((i + 1) + "");
                playButtSounds(i);
            }
        }
        if (v == butts[9]) {
            tv.append("#");
            playButtSounds(9);
        } else if (v == butts[10]) {
            tv.append("0");
            playButtSounds(10);
        } else if (v == butts[11]) {
            tv.append("*");
            playButtSounds(11);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        playButtSounds(10);
        if (v == this.bckSpace) {
            this.tv.setText("");
            vibrateFor(100);
        } else if (v == butts[9]) {
            this.tv.append("#");
        } else if (v == butts[10]) {
            this.tv.append("+");
        } else if (v == butts[11]) {
            this.tv.append("*");
        }

        if (v != this.bckSpace) {
            vibrateFor(50);
        }
        return false;
    }

    private void playButtSounds(int i) {
        MediaPlayer md = MediaPlayer.create(getContext(), R.raw.dtmf1);
        MediaPlayer one = MediaPlayer.create(getContext(), R.raw.dtmf2);
        MediaPlayer two = MediaPlayer.create(getContext(), R.raw.dtmf2);
        MediaPlayer three = MediaPlayer.create(getContext(), R.raw.dtmf2);
        MediaPlayer four = MediaPlayer.create(getContext(), R.raw.dtmf2);
        MediaPlayer five = MediaPlayer.create(getContext(), R.raw.dtmf2);
        MediaPlayer six = MediaPlayer.create(getContext(), R.raw.dtmf2);
        MediaPlayer seven = MediaPlayer.create(getContext(), R.raw.dtmf2);
        MediaPlayer eight = MediaPlayer.create(getContext(), R.raw.dtmf2);
        MediaPlayer nine = MediaPlayer.create(getContext(), R.raw.dtmf2);

        switch (i) {
            case 0:
                one.start();
                break;
            case 1:
                two.start();
                break;
            case 2:
                three.start();
                break;
            case 3:
                four.start();
                break;
            case 4:
                five.start();
                break;
            case 5:
                six.start();
                break;
            case 6:
                seven.start();
                break;
            case 7:
                eight.start();
                break;
            case 8:
                nine.start();
                break;
            case 9:
                md.start();
                break;
            case 10:
                md.start();
                break;
            case 11:
                md.start();
                break;
        }
    }

    /**
     * Created by muneebahmad on 06/04/15.
     */
    public class ScaleAnimToShow extends ScaleAnimation {

        private View mView;
        private LinearLayout.LayoutParams mLayoutParams;
        private int mMarginBottomFromY;
        private int mMarginBottomToY;
        private boolean mVanishAfter = false;

        /**
         *
         * @param fromX
         * @param toX
         * @param fromY
         * @param toY
         * @param duration
         * @param view
         * @param vanishAfter
         */
        public ScaleAnimToShow(float fromX, float toX, float fromY, float toY, int duration,
                               View view, boolean vanishAfter) {
            super(fromX, toX, fromY, toY);
            openLayout = view;
            setDuration(duration);
            mView = view;
            mVanishAfter = vanishAfter;
            mLayoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            mView.setVisibility(View.VISIBLE);
            int height = mView.getHeight();
            //mMarginBottomFromY = (int) (height * fromY) + mLayoutParams.bottomMargin + height;
            //mMarginBottomToY = (int) (0 - ((height * toY) + mLayoutParams.bottomMargin)) + height;

            mMarginBottomFromY = 0;
            mMarginBottomToY = height;

            Log.log(Log.LOG_ERROR,"CZ .................height..." + height +
                    " , mMarginBottomFromY...." + mMarginBottomFromY + " , mMarginBottomToY.." + mMarginBottomToY);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                int newMarginBottom = (int) ((mMarginBottomToY - mMarginBottomFromY) * interpolatedTime) - mMarginBottomToY;
                mLayoutParams.setMargins(mLayoutParams.leftMargin, mLayoutParams.topMargin, mLayoutParams.rightMargin, newMarginBottom);
                mView.getParent().requestLayout();
                //Log.v("CZ","newMarginBottom..." + newMarginBottom + " , mLayoutParams.topMargin..." + mLayoutParams.topMargin);
            }
        }

    }/** end class. */

    /**
     * Created by muneebahmad on 06/04/15.
     */
    public class ScaleAnimToHide extends ScaleAnimation {

        private View view;
        private LinearLayout.LayoutParams layoutParams;
        private int marginBottomFromY;
        private int marginBottomToY;
        private boolean vanishAfter = false;

        /**
         *
         * @param fromX float
         * @param toX float
         * @param fromY float
         * @param toY float
         * @param view {@link #View}
         * @param vanishAfter boolean
         */
        public ScaleAnimToHide(float fromX, float toX, float fromY, float toY, int duration,
                               View view, boolean vanishAfter) {
            super(fromX, toX, fromY, toY);
            setDuration(duration);
            openLayout =  null;
            this.view = view;
            this.vanishAfter = vanishAfter;
            this.layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            int height = this.view.getHeight();
            marginBottomFromY = (int) (height * fromY) + layoutParams.bottomMargin - height;
            marginBottomToY = (int) (0 - ((height * toY) + layoutParams.bottomMargin)) - height;

            Log.log(Log.LOG_ERROR, "SCALE TO HIDE ANIM -> " + "CZ" + "height..." + height + " , " +
                    "marginBottomFromY...." + marginBottomFromY  + " , marginBottomToY.." + marginBottomToY);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                int newMarginBottom = marginBottomFromY + (int) ((marginBottomToY - marginBottomFromY) * interpolatedTime);
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin,layoutParams.rightMargin, newMarginBottom);
                view.getParent().requestLayout();
                //Log.v("CZ","newMarginBottom..." + newMarginBottom + " , mLayoutParams.topMargin..." + mLayoutParams.topMargin);
            } else if (vanishAfter) {
                view.setVisibility(View.GONE);
            }
        }
    }/** end class. */

    public class TranslateToShow extends TranslateAnimation {
        private View view;
        private LinearLayout.LayoutParams layoutParams;
        private boolean vanishAfter = false;
        private int marginBottomToY;
        private int marginBottomFromY;

        /**
         *
         * @param fromXDelta
         * @param toXDelta
         * @param fromYDelta
         * @param toYDelta
         * @param duration
         * @param view
         * @param vanishAfter
         */
        public TranslateToShow(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                               int duration, View view, boolean vanishAfter) {
            super(fromXDelta, toXDelta, fromYDelta, toYDelta);
            openLayout = view;
            setDuration(duration);
            this.view = view;
            this.vanishAfter = vanishAfter;
            layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            view.setVisibility(View.VISIBLE);
            int height = view.getHeight();
            //mMarginBottomFromY = (int) (height * fromY) + mLayoutParams.bottomMargin + height;
            //mMarginBottomToY = (int) (0 - ((height * toY) + mLayoutParams.bottomMargin)) + height;

            marginBottomFromY = 0;
            marginBottomToY = height;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                int newMarginBottom = (int) ((marginBottomToY - marginBottomFromY) * interpolatedTime) - marginBottomToY;
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, newMarginBottom);
                view.getParent().requestLayout();
                //Log.v("CZ","newMarginBottom..." + newMarginBottom + " , mLayoutParams.topMargin..." + mLayoutParams.topMargin);
            }
        }

    }/** end class. */

    public class TranslateToHide extends TranslateAnimation {

        private View view;
        private LinearLayout.LayoutParams layoutParams;
        private boolean vansishAfter = false;
        private int marginBottomToY;
        private int marginBottomFromY;

        /**
         *
         * @param fromXDelta
         * @param toXDelta
         * @param fromYDelta
         * @param toYDelta
         * @param duration
         * @param view
         * @param vanishAfter
         */
        public TranslateToHide(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                               int duration, View view, boolean vanishAfter) {
            super(fromXDelta, toXDelta, fromYDelta, toYDelta);
            setDuration(duration);
            openLayout = null;
            this.view = view;
            this.vansishAfter = vanishAfter;
            this.layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            int height = this.view.getHeight();
            marginBottomFromY = (int) (height * fromYDelta) + layoutParams.bottomMargin - height;
            marginBottomToY = (int) (0 - ((height * toYDelta) + layoutParams.bottomMargin)) - height;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                int newMarginBottom = marginBottomFromY + (int) ((marginBottomToY - marginBottomFromY) * interpolatedTime);
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin,layoutParams.rightMargin, newMarginBottom);
                view.getParent().requestLayout();
            } else if (vansishAfter) {
                view.setVisibility(View.GONE);
            }
        }
    }/** end class. */

}/** end class. */

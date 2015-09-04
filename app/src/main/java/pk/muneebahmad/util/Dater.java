package pk.muneebahmad.util;

import java.util.Calendar;

/**
 * Created by ay on 9/4/2015.
 */
public class Dater {
    private int day;
    private int month;
    private int finalMonth;
    private int year;

    private final Calendar calendar = Calendar.getInstance();

    private static final String JAN = "January";
    private static final String FEB = "February";
    private static final String MAR = "March";
    private static final String APR = "April";
    private static final String MAY = "May";
    private static final String JUN = "June";
    private static final String JUL = "July";
    private static final String AUG = "August";
    private static final String SEP = "September";
    private static final String OCT = "October";
    private static final String NOV = "November";
    private static final String DEC = "December";

    private static Dater sInstance = null;

    public Dater() {}

    public String getDate() {
        this.day = getDateFromCalendar();
        this.month = getMonthFromCalendar();
        this.year = calendar.get(Calendar.YEAR);

        this.finalMonth = month++;

        String finalYear = year + "";
        String retYear = finalYear.substring(2);

        return "" + day + "-" + month + "-" + retYear;
    }

    /**
     *  Year in format YYYY
     * @return int
     */
    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Year in format YY
     *
     * @return java.lang.String
     */
    public String getYearLastTwo() {
        int y = calendar.get(Calendar.YEAR);
        String finalYr = y + "";
        String retY = finalYr.substring(2);
        return retY;
    }

    private int getDateFromCalendar() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    private int getMonthFromCalendar() {
        return calendar.get(Calendar.MONTH);
    }

    public String getMonthInStringLowerCase() {
        int m = getMonthFromCalendar();
        String mth = "";
        switch (m) {
            case 0:
                mth = JAN;
                break;
            case 1:
                mth = FEB;
                break;
            case 2:
                mth = MAR;
                break;
            case 3:
                mth = APR;
                break;
            case 4:
                mth = MAY;
                break;
            case 5:
                mth = JUN;
                break;
            case 6:
                mth = JUL;
                break;
            case 7:
                mth = AUG;
                break;
            case 8:
                mth = SEP;
                break;
            case 9:
                mth = OCT;
                break;
            case 10:
                mth = NOV;
                break;
            case 11:
                mth = DEC;
                break;
            default:
                break;
        }

        return mth.toLowerCase();
    }

    /**
     *
     * @return
     */
    public String getNamedDate() {
        String d = getDateFromCalendar() + "";
        String m = getMonthInStringLowerCase();
        String y = getYear() + "";

        return m.toUpperCase() + ", " + d + " " + y;
    }

    /**
     *
     * @return Singleton instance.
     */
    public static Dater getInstance() {
        synchronized (Dater.class) {
            if (sInstance == null) {
                sInstance = new Dater();
            }
            return sInstance;
        }
    }

}/** end class. */

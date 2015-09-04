package pk.muneebahmad.data;

import java.util.ArrayList;

/**
 * Created by ay on 9/3/2015.
 */
public class SharedData {

    public static SharedData sInstance = null;

    private String messengerName;
    private int messengerNo;

    private String callName;
    private String callNo;

    public static ArrayList<String> favList = new ArrayList<>();
    public static ArrayList<String> favNameList = new ArrayList<>();

    public SharedData() {}

    /**
     *
     * @return
     */
    public String getMessengerName() {
        return messengerName;
    }

    /**
     *
     * @param messengerName
     */
    public void setMessengerName(String messengerName) {
        this.messengerName = messengerName;
    }

    /**
     *
     * @return
     */
    public int getMessengerNo() {
        return messengerNo;
    }

    /**
     *
     * @param messengerNo
     */
    public void setMessengerNo(int messengerNo) {
        this.messengerNo = messengerNo;
    }

    /**
     *
     * @return
     */
    public String getCallName() {
        return callName;
    }

    /**
     *
     * @param item
     */
    public void addToFav(String item) {
        favList.add(item);
    }

    /**
     *
     * @param callName
     */
    public void setCallName(String callName) {
        this.callName = callName;
    }

    /**
     *
     * @return
     */
    public String getCallNo() {
        return callNo;
    }

    /**
     *
     * @param callNo
     */
    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }

    /**
     *
     * @return Singleton instance of {@link #SharedData}
     */
    public static SharedData getInstance() {
        synchronized (SharedData.class) {
            if (sInstance == null) {
                sInstance = new SharedData();
            }
            return sInstance;
        }
    }

}/** end class. */

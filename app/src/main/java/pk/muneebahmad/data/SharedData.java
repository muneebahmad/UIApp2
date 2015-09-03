package pk.muneebahmad.data;

/**
 * Created by ay on 9/3/2015.
 */
public class SharedData {

    public static SharedData sInstance = null;

    private String messengerName;
    private int messengerNo;

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

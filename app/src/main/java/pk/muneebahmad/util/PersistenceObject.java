package pk.muneebahmad.util;


/**
 * Created by muneebahmad on 8/25/2015.
 */
public abstract class PersistenceObject {

    private String filename;

    /**
     *
     * @param filename
     */
    protected PersistenceObject(final String filename) {
        this.filename = filename;
    }

    protected abstract void writeBytes();

    protected abstract void writeBuffer();

    protected abstract void writeObject(Object obj);

    protected abstract void writeText();

    protected abstract void loadObject(Object obj);

    protected abstract void loadText();

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return this.filename;
    }

}/** end class. */

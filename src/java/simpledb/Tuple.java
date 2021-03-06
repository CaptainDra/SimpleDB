package simpledb;

import java.io.Serializable;
import java.util.Iterator;
import java.util.*;

/**
 * Tuple maintains information about the contents of a tuple. Tuples have a
 * specified schema specified by a TupleDesc object and contain Field objects
 * with the data for each field.
 */
public class Tuple implements Serializable {

    private static final long serialVersionUID = 1L;

    private TupleDesc tupleDesc;
    private Field[] fields;
    private RecordId recordId;
    /**
     * Create a new tuple with the specified schema (type).
     *
     * @param td
     *            the schema of this tuple. It must be a valid TupleDesc
     *            instance with at least one field.
     */
    public Tuple(TupleDesc td) {
        // Done
        this.tupleDesc = td;
        this.fields = new Field[td.numFields()];
    }

    /**
     * @return The TupleDesc representing the schema of this tuple.
     */
    public TupleDesc getTupleDesc() {
        // Done
        return tupleDesc;
    }

    /**
     * @return The RecordId representing the location of this tuple on disk. May
     *         be null.
     */
    public RecordId getRecordId() {
        // Done
        return recordId;
    }

    /**
     * Set the RecordId information for this tuple.
     *
     * @param rid
     *            the new RecordId for this tuple.
     */
    public void setRecordId(RecordId rid) {
        // Done
        this.recordId = rid;
    }

    /**
     * Change the value of the ith field of this tuple.
     *
     * @param i
     *            index of the field to change. It must be a valid index.
     * @param f
     *            new value for the field.
     */
    public void setField(int i, Field f) {
        // Done
        if (i >= 0 && i < fields.length) {
            fields[i] = f;
        } else {
            throw new IllegalArgumentException("out of limit");
        }
    }

    /**
     * @return the value of the ith field, or null if it has not been set.
     *
     * @param i
     *            field index to return. Must be a valid index.
     */
    public Field getField(int i) {
        // Done
        if (i >= 0 && i < fields.length) {
            return fields[i];
        } else {
            throw new IllegalArgumentException("out of limit");
        }
    }

    /**
     * Returns the contents of this Tuple as a string. Note that to pass the
     * system tests, the format needs to be as follows:
     *
     * column1\tcolumn2\tcolumn3\t...\tcolumnN
     *
     * where \t is any whitespace (except a newline)
     */

    public String toString() {
        // Done
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < fields.length; i++){
            if(i == fields.length - 1){
                strBuffer.append(fields[i].toString());
            } else {
                strBuffer.append(fields[i].toString()).append("\t");
            }
        }
        return strBuffer.toString();
    }

    /**
     * @return
     *        An iterator which iterates over all the fields of this tuple
     * */

    public Iterator<Field> fields() {
        // Done
        return new Iterator<Field>() {

            private int loc = 0;
            @Override
            public boolean hasNext() {
                return loc < fields.length;
            }

            @Override
            public Field next() {
                if (hasNext()) {
                    loc++;
                    return fields[loc];
                }
                throw new NoSuchElementException();
            }
        };
    }


    /**
     * reset the TupleDesc of thi tuple
     * */
    public void resetTupleDesc(TupleDesc td) {
        // Done
        tupleDesc = td;
        fields = new Field[td.numFields()];
    }
}

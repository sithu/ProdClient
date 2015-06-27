package com.deodarpark.updater.prodclient.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by saung on 4/26/15.
 */
public class ProdEntry implements Parcelable {
    private String employeeName;
    private Integer numRemaining;
    private Integer numGood;
    private Integer numBad;
    private Integer hourOfDay;

    public ProdEntry(String employeeName, Integer numRemaining) {
        this.employeeName = employeeName;
        this.numRemaining = numRemaining;
        this.numGood = 0;
        this.numBad = 0;
        Calendar rightNow = Calendar.getInstance();
        this.hourOfDay = rightNow.get(Calendar.HOUR_OF_DAY);
    }

    public Integer incrementNumGood() {
        return ++this.numGood;
    }

    public Integer decrementNumGood() {
        return --this.numGood;
    }

    public Integer incrementNumBad() {
        return ++this.numBad;
    }

    public Integer decrementNumBad() {
        return --this.numBad;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Integer getNumGood() {
        return numGood;
    }

    public Integer getNumBad() {
        return numBad;
    }

    public Integer getHourOfDay() {
        return hourOfDay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(employeeName);
    }

    private ProdEntry(Parcel in) {
        employeeName = in.readString();
        numRemaining = 2000;
        this.numGood = 0;
        this.numBad = 0;
        Calendar rightNow = Calendar.getInstance();
        this.hourOfDay = rightNow.get(Calendar.HOUR_OF_DAY);
    }

    public static final Creator<ProdEntry> CREATOR = new Creator<ProdEntry>() {

        @Override
        public ProdEntry createFromParcel(Parcel source) {
            return new ProdEntry(source);
        }

        @Override
        public ProdEntry[] newArray(int size) {
            return new ProdEntry[size];
        }

    };

    public static ArrayList<ProdEntry> getProdEntryList() {
        ArrayList<ProdEntry> list = new ArrayList<>(3);
        list.add(new ProdEntry("Ni Ni", 500));

        return list;
    }

}

package ben.com.linklauncher.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class RealmStatus extends RealmObject implements Parcelable {
    private int enumValue;

    public RealmStatus() {
    }

    protected RealmStatus(Parcel in) {
        enumValue = in.readInt();
    }

    public static final Creator<RealmStatus> CREATOR = new Creator<RealmStatus>() {
        @Override
        public RealmStatus createFromParcel(Parcel in) {
            return new RealmStatus(in);
        }

        @Override
        public RealmStatus[] newArray(int size) {
            return new RealmStatus[size];
        }
    };

    public void saveEnum(Status val) {
        this.enumValue = val.getValue();
    }

    public Status getEnum() {
        return (enumValue != 0) ? Status.values()[enumValue] : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(enumValue);
    }
}

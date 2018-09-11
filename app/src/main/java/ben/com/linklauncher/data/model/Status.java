package ben.com.linklauncher.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import ben.com.linklauncher.R;
import io.realm.RealmObject;

public enum Status implements Parcelable {

    ACTIVE(R.color.linkActive), ERROR(R.color.linkError), UNKNOWN(R.color.linkUnknown);

    private int value;

    Status(int value) {
        this.value = value;
    }

    Status(Parcel in) {
        value = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Status> CREATOR = new Creator<Status>() {
        @Override
        public Status createFromParcel(Parcel in) {
            return Status.values()[in.readInt()];
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };

    public int getValue() {
        return value;
    }
}


package redfest.myapplication.network.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * EPG response model
 * Created by usman on 3/20/18.
 */

public class EPGResponse implements Parcelable {
    private String today;
    private ArrayList<EPGChannel> channels;


    public EPGResponse (){
        channels = new ArrayList<>();
    }


    public ArrayList<EPGChannel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<EPGChannel> channels) {
        this.channels = channels;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.today);
        dest.writeList(this.channels);
    }

    protected EPGResponse(Parcel in) {
        this.today = in.readString();
        this.channels = new ArrayList<EPGChannel>();
        in.readList(this.channels, EPGChannel.class.getClassLoader());
    }

    public static final Parcelable.Creator<EPGResponse> CREATOR = new Parcelable.Creator<EPGResponse>() {
        @Override
        public EPGResponse createFromParcel(Parcel source) {
            return new EPGResponse(source);
        }

        @Override
        public EPGResponse[] newArray(int size) {
            return new EPGResponse[size];
        }
    };

    @Override
    public String toString() {
        return "EPGResponse{" +
                "today='" + today + '\'' +
                ", channels=" + channels +
                '}';
    }
}

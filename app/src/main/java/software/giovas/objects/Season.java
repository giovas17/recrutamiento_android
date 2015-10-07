package software.giovas.objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by darkgeat on 10/6/15.
 */
public class Season implements Parcelable{
    int number;
    String thumbnail;
    String poster;
    int episodes,votes;
    double rating;

    public Season(){
        number = 0;
        thumbnail = "";
        poster = "";
        episodes = 0;
        votes = 0;
        rating = 0;
    }

    public Season(int number, String thumbnail, String poster, int episodes, int votes, double rating) {
        this.number = number;
        this.thumbnail = thumbnail;
        this.poster = poster;
        this.episodes = episodes;
        this.votes = votes;
        this.rating = rating;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Season> CREATOR = new Creator<Season>() {
        @Override
        public Season createFromParcel(Parcel source) {
            Season season = new Season();
            season.setNumber(source.readInt());
            season.setVotes(source.readInt());
            season.setEpisodes(source.readInt());
            season.setRating(source.readDouble());
            season.setPoster(source.readString());
            season.setThumbnail(source.readString());
            return season;
        }

        @Override
        public Season[] newArray(int size) {
            return new Season[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeInt(votes);
        dest.writeInt(episodes);
        dest.writeDouble(rating);
        dest.writeString(poster);
        dest.writeString(thumbnail);
    }
}

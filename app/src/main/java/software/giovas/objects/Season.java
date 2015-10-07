package software.giovas.objects;

/**
 * Created by darkgeat on 10/6/15.
 */
public class Season {
    int number;
    String thumbnail;
    String poster;

    public Season(){
        number = 0;
        thumbnail = "";
        poster = "";
    }

    public Season(int number, String thumbnail, String poster) {
        this.number = number;
        this.thumbnail = thumbnail;
        this.poster = poster;
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
}

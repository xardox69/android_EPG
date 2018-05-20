package redfest.myapplication.network.models;

import java.util.ArrayList;

/**
 * Created by usman on 3/20/18.
 */

public class EPGChannel {

    private String id;
    private String name;
    private String imageUrl;

    public ArrayList<EPGProgram> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<EPGProgram> programs) {
        this.programs = programs;
    }

    private ArrayList<EPGProgram> programs;

    public EPGChannel() {
        programs = new ArrayList<>();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

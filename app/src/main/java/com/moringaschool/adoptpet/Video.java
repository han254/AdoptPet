
package com.moringaschool.adoptpet;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("embed")
    @Expose
    private String embed;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Video() {
    }

    /**
     * 
     * @param embed
     */
    public Video(String embed) {
        super();
        this.embed = embed;
    }

    public String getEmbed() {
        return embed;
    }

    public void setEmbed(String embed) {
        this.embed = embed;
    }

}

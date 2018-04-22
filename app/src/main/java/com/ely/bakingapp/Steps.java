package com.ely.bakingapp;

/**
 * Created by lior on 4/22/18.
 */

public class Steps {

    public String shortDescription;
    public String shortDesdescriptioncription;
    public String videoURL;
    public String thumbnailURL;

    public Steps(String shortDescription, String shortDesdescriptioncription, String videoURL, String thumbnailURL) {
        this.shortDescription = shortDescription;
        this.shortDesdescriptioncription = shortDesdescriptioncription;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getShortDesdescriptioncription() {
        return shortDesdescriptioncription;
    }

    public void setShortDesdescriptioncription(String shortDesdescriptioncription) {
        this.shortDesdescriptioncription = shortDesdescriptioncription;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }




}

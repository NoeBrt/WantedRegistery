package com.example.wantedregistery;

import android.media.Image;
import android.net.Uri;

public class WantedPerson {

    private Uri photo;
    private String name;

    public WantedPerson(Uri photo, String name) {
        this.photo = photo;
        this.name = name;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

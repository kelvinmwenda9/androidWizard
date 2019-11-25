package com.kelvin.farm;


public class DataAdapter
{
    public String ImageURL;
    public String ImageTitle;
    public String seen;
    public String descTheAnimal;



    public String getImageUrl() {

        return ImageURL;
    }

    public void setImageUrl(String imageServerUrl) {

        this.ImageURL = imageServerUrl;
    }

    public String getImageTitle() {

        return ImageTitle;
    }

    public void setImageTitle(String Imagetitlename) {

        this.ImageTitle = Imagetitlename;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public String getDescTheAnimal() {
        return descTheAnimal;
    }

    public void setDescTheAnimal(String descTheAnimal) {
        this.descTheAnimal = descTheAnimal;
    }
}
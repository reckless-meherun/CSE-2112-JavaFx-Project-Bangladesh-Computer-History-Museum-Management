package Departments;

import java.util.ArrayList;

public class Photo_gallery extends Non_Curatorial_Dept{
    private ArrayList<Photo> Photos;
    private ArrayList<ArrayList<Photo>> Gallery;

    public Photo_gallery()
    {
        Photos = null;
    }
    public Photo_gallery(ArrayList<Photo> PhotoList)
    {
        this.Photos = PhotoList;
    }
    public void setPhotos(ArrayList<Photo> PhotoList)
    {
        this.Photos = PhotoList;
    }
    public ArrayList<Photo> getPhotos()
    {
        return this.Photos;
    }
    public void setGallery(ArrayList<ArrayList<Photo>> gallery)
    {
        this.Gallery = gallery;
    }
    public ArrayList<ArrayList<Photo>> getGallery()
    {
        return this.Gallery;
    }
}

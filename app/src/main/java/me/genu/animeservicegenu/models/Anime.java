package me.genu.animeservicegenu.models;

public class Anime {
    private int anime_id;

    private String anime_name;
    private String anime_img;

    public int getAnime_id() {
        return anime_id;
    }

    public void setAnime_id(int anime_id) {
        this.anime_id = anime_id;
    }

    public String getAnime_name() {
        return anime_name;
    }

    public void setAnime_name(String anime_name) {
        this.anime_name = anime_name;
    }

    public String getAnime_img() {
        String urlImagen[] = anime_img.split("/");
        System.out.println(urlImagen[urlImagen.length - 1]);
        return urlImagen[urlImagen.length - 1];
    }

    public void setAnime_img(String anime_img) {
        this.anime_img = anime_img;
    }
}


package com.ciprianursulean.javalab8;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        ArtistController artistController = new ArtistController();
        AlbumController albumController = new AlbumController();
        ChartController chartController = new ChartController();

//        artistController.generateRandomArtists(256);
//        albumController.generateRandomAlbum(256);

        try {
            chartController.printArtistsRankTheFancyWay(100);
        } catch (IOException | SQLException ex) {
            System.out.println(ex.getMessage());
        }

        Database.closeConnection();
    }
}

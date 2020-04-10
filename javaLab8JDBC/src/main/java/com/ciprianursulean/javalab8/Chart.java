package com.ciprianursulean.javalab8;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Chart {
    private int albumId;
    private int rank;
    List<Album> albumList;
    private Connection connection = Database.getInstance().getConnection();

    public Chart(int albumId, int rank) {
        this.albumId = albumId;
        this.rank = rank;
        this.albumList = new ArrayList<>();
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

//    public List<Album> fetchStatistics() {
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT ID, ALBUM_ID, RANK FROM CHARTS");
//            while (resultSet.next()) {
//
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//    }

    public void generateRandomCart(int noOfAlbums) {
        for (int i = 1; i <= noOfAlbums; ++i) {

        }
    }
}

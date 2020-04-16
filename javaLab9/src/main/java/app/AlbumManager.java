package app;

import entity.Chart;
import repo.AlbumRepository;
import repo.ArtistRepository;
import util.ChartUtil;

import java.io.*;

public class AlbumManager {
    private static String getConfigOption() throws IOException {
        File configFile = new File("config.txt");
        if (configFile.exists()) {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(configFile));
            return bufferedReader.readLine();
        }
        else {
            throw new FileNotFoundException();
        }
    }

    public static void main(String[] args) {
//        ArtistRepository artistRepository = new ArtistRepository();
//        AlbumRepository  albumRepository  = new AlbumRepository();
//        String option;
//        try {
//            option = getConfigOption();
//            System.out.println(option);
//            if (option.equals("JPA")) {
//                System.out.println(artistRepository.findByIdWithJPA(1));
//            }
//            else {
//                System.out.println(artistRepository.findByIdWithJDBC(1));
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
        ChartUtil.printTopWithJPA(10);
        ChartUtil.printTopWithJDBC(10);
    }
}

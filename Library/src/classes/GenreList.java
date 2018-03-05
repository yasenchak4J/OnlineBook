package classes;

import dataBase.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenreList {

    private ArrayList<Genre> genreList = new ArrayList<>();

    private ArrayList<Genre> getGenres(){
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DataBase.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM library.genre");
            while (resultSet.next()){
                Genre genre = new Genre();
                genre.setName(resultSet.getString("name"));
                genre.setId(resultSet.getLong("id"));
                genreList.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (resultSet != null) resultSet.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AuthorList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return  genreList;
    }
    public ArrayList<Genre> getGenreList() {
        if (!genreList.isEmpty()) {
            return genreList;
        } else {
            return getGenres();
        }
    }

}

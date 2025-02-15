package org.example.model;

import java.sql.*;
import java.util.ArrayList;

/**
 * Manages highscore records, including adding and retrieving scores from the database.
 */
public class HighscoreManager {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/yogidb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    /**
     * Constructs a new HighscoreManager and ensures the highscores table exists.
     */
    public HighscoreManager() {
        createTableIfNotExists();
    }

    /**
     * Creates the highscores table in the database if it does not already exist.
     */
    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS highscores (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "player_name VARCHAR(100), " +
                "baskets_collected INT NOT NULL, " +
                "levels_cleared INT NOT NULL)";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new highscore record to the database.
     *
     * @param playerName       the name of the player
     * @param basketsCollected the number of baskets collected
     * @param levelsCleared    the number of levels cleared
     */
    public void addScore(String playerName, int basketsCollected, int levelsCleared) {
        String sql = "INSERT INTO highscores (player_name, baskets_collected, levels_cleared) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, playerName);
            pstmt.setInt(2, basketsCollected);
            pstmt.setInt(3, levelsCleared);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all highscore records from the database, sorted by baskets collected and levels cleared.
     *
     * @return an ArrayList of GameRecord instances
     */
    public ArrayList<GameRecord> getAllScores() {
        ArrayList<GameRecord> records = new ArrayList<>();
        String sql = "SELECT id, player_name, baskets_collected, levels_cleared " +
                "FROM highscores ORDER BY baskets_collected DESC, levels_cleared DESC";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                records.add(new GameRecord(
                        rs.getInt("id"),
                        rs.getString("player_name"),
                        rs.getInt("baskets_collected"),
                        rs.getInt("levels_cleared")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}

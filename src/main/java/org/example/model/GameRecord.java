/**
 * Provides the core classes that represent the game's data and logic.
 * This includes classes for game levels, enemies, items, and score management.
 */
package org.example.model;

/**
 * Represents a highscore record for a player.
 */
public class GameRecord {
    private int playerId;
    private String playerName;
    private int basketsCollected;
    private int levelsCleared;

    /**
     * Constructs a new GameRecord with the specified details.
     *
     * @param playerId         the unique identifier for the player
     * @param playerName       the name of the player
     * @param basketsCollected the number of baskets collected by the player
     * @param levelsCleared    the number of levels cleared by the player
     */
    public GameRecord(int playerId, String playerName, int basketsCollected, int levelsCleared) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.basketsCollected = basketsCollected;
        this.levelsCleared = levelsCleared;
    }

    /**
     * Retrieves the player's unique identifier.
     *
     * @return the playerId
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Retrieves the player's name.
     *
     * @return the playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Retrieves the number of baskets collected by the player.
     *
     * @return the basketsCollected
     */
    public int getBasketsCollected() {
        return basketsCollected;
    }

    /**
     * Retrieves the number of levels cleared by the player.
     *
     * @return the levelsCleared
     */
    public int getLevelsCleared() {
        return levelsCleared;
    }

    /**
     * Returns a string representation of the GameRecord.
     *
     * @return a formatted string with player details
     */
    @Override
    public String toString() {
        return String.format("Player ID: %d | Player: %s | Baskets: %d | Levels: %d",
                playerId, playerName, basketsCollected, levelsCleared);
    }
}

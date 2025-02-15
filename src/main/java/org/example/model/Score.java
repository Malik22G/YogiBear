package org.example.model;

/**
 * Represents a player's score, including their name and the number of baskets collected.
 */
public class Score implements Comparable<Score> {
    private String playerName;
    private int score;

    /**
     * Constructs a new Score with the specified player name and score.
     *
     * @param playerName the name of the player
     * @param score      the number of baskets collected
     */
    public Score(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
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
     * Retrieves the player's score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Compares this score with another for sorting purposes.
     * Sorting is done in descending order of score.
     *
     * @param o the other Score to compare against
     * @return a negative integer, zero, or a positive integer as this score
     *         is greater than, equal to, or less than the specified score
     */
    @Override
    public int compareTo(Score o) {
        return Integer.compare(o.score, this.score);
    }
}

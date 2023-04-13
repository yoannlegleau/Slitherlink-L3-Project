package fr.slitherlink.game;

public class Score {
    private String userId;
    private int nbPoints;

    public Score(String userId, int nbPoints) {
        this.userId = userId;
        this.nbPoints = nbPoints;
    }

    public String getUserId() {
        return userId;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    @Override
    public String toString() {
        return "Score{" +
                "userId='" + userId + '\'' +
                ", nbPoints=" + nbPoints +
                '}';
    }
}

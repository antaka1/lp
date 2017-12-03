package ro.lsacbucuresti.lanpartyquiz.dto;

/**
 * Created by cristi on 05 - October - 2017
 */
public class UserPointsDTO {

    private String username;

    private long totalPoints;

    public UserPointsDTO() {
    }

    public UserPointsDTO(String username, long totalPoints) {
        this.username = username;
        this.totalPoints = totalPoints;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(long totalPoints) {
        this.totalPoints = totalPoints;
    }

}

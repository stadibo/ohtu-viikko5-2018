package ohtu;

public class TennisGame {
    
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private String playerOneName;
    private String playerTwoName;

    public TennisGame(String player1Name, String player2Name) {
        this.playerOneName = player1Name;
        this.playerTwoName = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            playerOneScore++;
        else
            playerTwoScore++;
    }

    public String getScore() {
        if (scoreIsEven()) { 
            return printEvenScore(); 
        } else if (playerHasAdvantageOrWinsPoint()) {
            if (playerHasAdvantage()) {
                return printAdvantage();
            } else {
                return printWinnerForPoint();
            }
        }
        else {
            return printCurrentScore();
        }
        
    }

    private int scoreDifference() {
        return playerOneScore - playerTwoScore;
    }
    
    private String printCurrentScore() {
        return getScoreName(playerOneScore) + "-" + getScoreName(playerTwoScore);
    }

    private String getScoreName(int score) {
        switch(score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "";
        }
    }

    private String printWinnerForPoint() {
        if (playerOneWins()) return "Win for player1";
        else return "Win for player2";
    }

    private boolean playerOneWins() {
        return scoreDifference() >= 2;
    }

    private String printAdvantage() {
        if (playerOneHasAdvantage()) return "Advantage player1";
        else return "Advantage player2";
    }

    private boolean playerHasAdvantageOrWinsPoint() {
        return playerOneScore >= 4 || playerTwoScore >= 4;
    }
    
    private boolean playerHasAdvantage() {
        return Math.abs(scoreDifference()) == 1;
    }
    
    private boolean playerOneHasAdvantage() {
        return scoreDifference() == 1;
    }

    private boolean scoreIsEven() {
        return playerOneScore == playerTwoScore;
    }

    private String printEvenScore() {
        switch (playerOneScore) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";       
        }
    }
}
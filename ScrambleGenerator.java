import java.util.Random;

/**
 * Creates a random algorithm to scramble the Rubik's Cube (3x3) with a number of moves
 * defined by the user.
 *
 * Created by mrxuso on 13/07/16.
 */

public class ScrambleGenerator {

    /**
    * MOVE -> 1 - U, 2 - L, 3 - F, 4 - D, 5 - R, 6 - B
    * ROTATION -> 1 - X, 2 - 2X, 3 - X', 4 - 2X
    */
    int mLastFace = 0, mTwoFacesAgo = 0;
    private String mScramble = "";

    /**
    * Constructor: create a scramble algorithm with "algorithmLength" number of moves
    * @param algorithmLength Define the length of the algorithm
    */
    public ScrambleGenerator(int algorithmLength) {
        for (int i = 1; i <= algorithmLength; i++) {
            mScramble += getNextMove();
        }
    }

    /**
    * Next face we move in the algorithm, it is different from the last move and from the face moved
    * two times ago if the opposite face was moved in the last move (i.e. R L 2R is incorrect, it should be R' L instead).
    * @return random A number between 1 and 6 (both included) --> 1 - U, 2 - L, 3 - F, 4 - D, 5 - R, 6 - B <--
    */
    private int getNextFace(){
        int random = (new Random().nextInt(6) + 1);
        if (random == mTwoFacesAgo && random%3 == mLastFace%3){
            while (random == mTwoFacesAgo && random%3 == mLastFace%3) {
                random = (new Random().nextInt(6) + 1);
            }
        } else {
            if (random == mLastFace){
                while (random == mLastFace) {
                    random = (new Random().nextInt(6) + 1);
                }
            }
        }

        mTwoFacesAgo = mLastFace;
        mLastFace = random;

        return random;
    }
    /**
    * Next rotation of the face (there are no constraints) --> 1 - X, 2 - 2X, 3 - X', 4 - 2X <-- 
    */
    private int getNextRotation(){

        return (new Random().nextInt(4) + 1);
    }

    /**
    * Concatenates the rotation and the movement (X, X', 2X)
    */
    private String getNextMove(){
        String nextMove = "";
        switch (getNextFace()){
            case 1:
                nextMove = "U";
                break;
            case 2:
                nextMove = "L";
                break;
            case 3:
                nextMove = "F";
                break;
            case 4:
                nextMove = "D";
                break;
            case 5:
                nextMove = "R";
                break;
            case 6:
                nextMove = "B";
                break;
        }

        switch (getNextRotation()){
            case 1:
                break;
            case 2:
                nextMove = "2" + nextMove;
                break;
            case 3:
                nextMove = nextMove + "'";
                break;
            case 4:
                nextMove = "2" + nextMove;
                break;
        }

        return nextMove + " ";
    }

    /**
    * @return mScramble The full algorithm for scrambling the cube
     */
    public String getScramble(){
        return mScramble;
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private int index;
    private int noOfTokens;
    List<Token> tokenList;

    public Board(int noOfTokens) {
        this.index = 0;
        this.noOfTokens = noOfTokens;
        this.tokenList = new ArrayList<>(noOfTokens);
    }

    public int getNoOfTokens() {
        return noOfTokens;
    }

    public void setNoOfTokens(int noOfTokens) {
        this.noOfTokens = noOfTokens;
    }

    public void generateTokenList() {
        for (int i = 1; i <= noOfTokens; ++i) {
            tokenList.add(new Token(i));
        }
        Collections.shuffle(tokenList);
        this.tokenList = Collections.synchronizedList(tokenList);
    }
}

package server.algorithms;

import java.util.Map;

public class LCSS<T> {
    private T[] sequenceX;
    private T[] sequenceY;
    private int[][] map;
    private int[] indexMapY;
    private int[] indexMapX;

    public LCSS(T[] utterance, T[] variation) {
        this.sequenceX = variation;
        this.sequenceY = utterance;

        this.map = new int[this.sequenceX.length + 1][this.sequenceY.length + 1];
        this.indexMapY = new int[this.sequenceY.length];
        this.indexMapX = new int[this.sequenceX.length];
    }

    public float computeMatchScore() {
        float score = 0.0f;

        //invoke the LCSS algorithm and get the longest common sub sequence
        int matchedKeywords = this.runLCSS();

        //compute the best path to mark the slot
        this.fillIndexMap();

        //keywords matched in utterance
        float p = (float)matchedKeywords / (float)this.sequenceX.length;
        float q = (float)matchedKeywords / (float)this.sequenceY.length;

        score = 2 * p * q / (p + q);

        return score;
    }

    public float reScore(Map<T, boolean[]> presenceCache) {
        int m = this.sequenceX.length;
        int n = this.sequenceY.length;
        boolean[] indexMap = new boolean[this.indexMapY.length];

        float score = 0;
        int matchedKeywords = this.map[m][n];


        for (int i = 0; i < this.indexMapY.length; i++) {
            if(indexMapY[i] == -1)
                indexMap[i] = true;
            else
                indexMap[i] = false;
        }

        //for each of the non tagged slot, check if there is a match in the presence cache




        return score;
    }

    private void fillIndexMap() {
        int m = this.sequenceX.length;
        int n = this.sequenceY.length;

        if(this.map[m][n] == 0)
            return;

        int i = m;
        int j = n;

        while(i != 0 && j != 0) {
            if(this.sequenceX[i-1].equals(this.sequenceY[j-1])) {
                i--;
                j--;

                //set the index map
                this.indexMapY[j] = i;
                this.indexMapX[i] = j;
            }
            else {
                if (this.map[i - 1][j] > this.map[i][j - 1]) {
                    i--;
                    this.indexMapX[i] = -1;
                }
                else {
                    j--;
                    this.indexMapY[j] = -1;
                }
            }
        }
    }


    private int runLCSS()
    {
        int m = this.sequenceX.length;
        int n = this.sequenceY.length;
        int i, j;
  
        for (i=0; i<=m; i++)
        {
            for (j=0; j<=n; j++)
            {
                if (i == 0 || j == 0)
                    this.map[i][j] = 0;

                else if (this.sequenceX[i-1].equals(this.sequenceY[j-1]))
                    this.map[i][j] = this.map[i-1][j-1] + 1;

                else
                    this.map[i][j] = max(this.map[i-1][j], this.map[i][j-1]);
            }
        }

        /* L[m][n] contains length of LCS for X[0..n-1] and Y[0..m-1] */
        return this.map[m][n];
    }

    private int max(int a, int b) {
        return (a > b)? a : b;
    }
}

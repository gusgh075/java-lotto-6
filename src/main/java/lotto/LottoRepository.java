package lotto;

import java.util.Arrays;
import java.util.List;

public class LottoRepository {
    private List<Lotto> lottos;
    private LottoRank lottoRank;
    private int[] ranks=new int[6];
    private Lotto winningLotto;
    private int bonusNum;
    private final int[] PRIZE={20000000,3000000,1500000,50000,5000};
    private static final String RANK_ERROR_MESSAGE="[ERROR] 로또의 번호 개수는 6개여야 합니다.";

    public void setWinningLotto(Lotto lotto){
        this.winningLotto=lotto;
    }
    public void setBonusNum(int bonusNum){
        this.bonusNum=bonusNum;
    }
    public int[] getRanks() {
        return ranks;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public void addLotto(Lotto lotto){
        if(calculateRank(lotto))
            return;
        lottos.add(lotto);
    }
    private boolean calculateRank(Lotto lotto){
        int rank = lottoRank.isRank(lotto, winningLotto, bonusNum);
        if(rank==-1){
            System.out.println(RANK_ERROR_MESSAGE);
            return false;
        }
        ranks[rank-1]++;
        return true;
    }


    public double getProfitRate(){
        int money=lottos.size()*1000;
        int prize=0;
        for(int i=0;i<5;i++){
            prize=prize+ranks[i]*PRIZE[i];
        }
        double rate=Math.round((double)prize*10/money)/10.0;
        return rate;
    }

}

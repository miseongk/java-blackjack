package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Score;
import blackjack.domain.result.Result;
import java.util.List;

public abstract class Participant {

    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 5;

    private Hand hand;
    private final String name;

    public Participant(final Hand hand, final String name) {
        validateNameLength(name);
        this.hand = hand;
        this.name = name;
    }

    private void validateNameLength(final String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "[ERROR] 이름 길이는 최소 " + MIN_NAME_LENGTH + "글자에서 최대 "
                            + MAX_NAME_LENGTH + "글자 입니다.");
        }
    }

    public abstract List<Card> showInitCards();

    public abstract boolean canDraw();

    public void receiveCard(final Card card) {
        hand = hand.add(card);
    }

    public Score calculateTotalScore() {
        return this.hand.calculateScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public Result decideResultAgainst(final Participant participant) {
        Score score = this.calculateTotalScore();
        Score otherScore = participant.calculateTotalScore();

        if (score.isWin(otherScore)) {
            return Result.WIN;
        }
        if (score.isLose(otherScore)) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    public List<Card> getHand() {
        return hand.getHand();
    }

    public String getName() {
        return name;
    }
}

package expression.generic;

public interface ParenthesesTrackingInfo {

    void includeInParenthesesLessGroup(ParenthesesTrackingInfo other);

    void performParenthesesApplicationDecision(boolean toApplyOrNotToApply);
    boolean parenthesesApplied();
    int getConsideredPriority();
    boolean getConsideredNonAssociativity();
}

package expression;

import java.util.Objects;

public class OperatorTraits {
    public final int priority;
    public final boolean commutativityAmongPriorityClass;
    public final boolean associativityAmongPriorityClass;
    public final String operatorSymbol;


    public OperatorTraits(int priority, boolean commutativityAmongPriorityClass, boolean associativityAmongPriorityClass, String operatorSymbol) {
        this.priority = priority;
        this.commutativityAmongPriorityClass = commutativityAmongPriorityClass;
        this.associativityAmongPriorityClass = associativityAmongPriorityClass;
        this.operatorSymbol = operatorSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperatorTraits)) {
            return false;
        }
        OperatorTraits that = (OperatorTraits) o;
        return priority == that.priority &&
            commutativityAmongPriorityClass == that.commutativityAmongPriorityClass &&
            associativityAmongPriorityClass == that.associativityAmongPriorityClass &&
            operatorSymbol.equals(that.operatorSymbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            priority,
            commutativityAmongPriorityClass,
            associativityAmongPriorityClass,
            operatorSymbol
        );
    }
}

package domainModel;

import java.util.Collections;
import java.util.Comparator;

public class Dolphin extends Creature {

    public Dolphin() {
        inventions = Collections.emptyList();
        preferred = Action.HAVE_FUN;
    }

    @Override
    public Comparator<Creature> think() {
        return new Comparator<Creature>() {
            @Override
            public int compare(Creature o1, Creature o2) {
                int actionValue1 = o1.getPreferred() == null ? 0 : o1.getPreferred().getValue();
                int actionValue2 = o2.getPreferred() == null ? 0 : o2.getPreferred().getValue();
                return actionValue2 - actionValue1;
            }
        };
    }


}

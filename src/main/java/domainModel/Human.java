package domainModel;

import java.util.Arrays;
import java.util.Comparator;

public class Human extends Creature {

    public Human() {
        super(Arrays.asList("Wheel", "New York", "War"), Action.INVENT);
    }

    @Override
    public Comparator<Creature> think() {
        return new Comparator<Creature>() {
            @Override
            public int compare(Creature o1, Creature o2) {
                int inventionsSize1 = o1.getInventions() == null ? 0 : o1.getInventions().size();
                int inventionsSize2 = o2.getInventions() == null ? 0 : o2.getInventions().size();
                return inventionsSize1 - inventionsSize2;
            }
        };
    }
}

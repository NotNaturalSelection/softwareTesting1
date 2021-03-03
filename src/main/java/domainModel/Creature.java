package domainModel;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

abstract public class Creature {
    protected List<String> inventions;
    protected Action preferred;

    public abstract Comparator<Creature> think();

    public List<String> getInventions() {
        return inventions;
    }

    public Action getPreferred() {
        return preferred;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Creature creature = (Creature) o;
        return Objects.equals(inventions, creature.inventions) && preferred == creature.preferred;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventions, preferred);
    }
}

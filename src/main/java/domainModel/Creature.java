package domainModel;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

abstract public class Creature {
    private final List<String> inventions;
    private final Action preferred;

    public abstract Comparator<Creature> think();

    public List<String> getInventions() {
        return inventions;
    }

    public Action getPreferred() {
        return preferred;
    }

    public Creature(List<String> inventions, Action preferred) {
        this.inventions = inventions;
        this.preferred = preferred;
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

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domainModel.Creature;
import domainModel.Dolphin;
import domainModel.Human;

public class DomainModelTest {

    @Test
    public void testFieldValues(){
        Human human = new Human();
        assert human.getInventions().size() > 0;
        Dolphin dolphin = new Dolphin();
        assert dolphin.getInventions() != null;
        assert dolphin.getInventions().size() == 0;
    }

    @Test
    public void testThoughts() {
        Human human = new Human();
        Dolphin dolphin = new Dolphin();
        List<Creature> creatures = Arrays.asList(human, dolphin);
        List<Creature> humanOrder = Arrays.asList(new Dolphin(), new Human());
        List<Creature> dolphinOrder = Arrays.asList(new Human(), new Dolphin());

        creatures.sort(human.think());
        assert human.think().compare(human, dolphin) > 0;
        Assertions.assertArrayEquals(humanOrder.toArray(), creatures.toArray());

        creatures.sort(dolphin.think());
        assert dolphin.think().compare(human, dolphin) < 0;
        Assertions.assertArrayEquals(dolphinOrder.toArray(), creatures.toArray());
    }
}


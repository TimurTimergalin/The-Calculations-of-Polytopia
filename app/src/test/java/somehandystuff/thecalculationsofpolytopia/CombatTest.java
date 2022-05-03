package somehandystuff.thecalculationsofpolytopia;


import org.junit.Test;

import somehandystuff.thecalculationsofpolytopia.core.Combat;
import somehandystuff.thecalculationsofpolytopia.core.CombatActors;
import somehandystuff.thecalculationsofpolytopia.core.Unit;
import static org.junit.Assert.*;

public class CombatTest {
    @Test
    public void simpleCombat() {
        Unit wa1 = new Unit(10, 10, 2, 2, true, true, 1.0f, true, false);
        Unit wa2 = new Unit(10, 10, 2, 2, true, true, 1.0f, true, false);
        Unit res = new Unit(5, 10, 2, 2, true, true, 1.0f, true, false);

        CombatActors cRes = Combat.singleCombat(wa1, wa2);
        assertEquals(res, cRes.getDefender());
        assertEquals(res, cRes.getAttackers()[0]);
    }

    @Test
    public void complicatedCombat() {
        Unit giant = new Unit(33, 40, 5, 4, true, true, 1.5f, true, false);
        Unit boostedKnight = new Unit(13, 15, 4.0f, 1, true, true, 1.0f, true, true);

        Unit giantRes = new Unit(26, 40, 5, 4, true, true, 0.4f, true, false);
        Unit knightRes = new Unit(2, 15, 4.0f, 1, true, true, 1.0f, true, true);

        CombatActors cRes = Combat.singleCombat(boostedKnight, giant);
        assertEquals(giantRes, cRes.getDefender());
        assertEquals(knightRes, cRes.getAttackers()[0]);

    }
}

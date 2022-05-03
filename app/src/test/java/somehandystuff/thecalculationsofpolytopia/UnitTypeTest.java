package somehandystuff.thecalculationsofpolytopia;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.HashSet;

import somehandystuff.thecalculationsofpolytopia.core.Unit;
import somehandystuff.thecalculationsofpolytopia.units.BasicUnitType;
import somehandystuff.thecalculationsofpolytopia.units.ContainerUnitType;
import somehandystuff.thecalculationsofpolytopia.units.DefenceModifier;

public class UnitTypeTest {
    @Test
    public void testBasicUnitType() {
        BasicUnitType waType = BasicUnitType.TypeBuilder.basicUnit().stats(10, 2, 2).melee().build();
        Unit wa = waType.createUnit(8, true, DefenceModifier.No, false);
        Unit res = new Unit(8, 15, 2, 2, true, true, 1.0f, true, false);
        assertEquals(res, wa);
    }

    @Test
    public void testErrors() {
        BasicUnitType giantType = BasicUnitType.TypeBuilder.superUnit().stats(40, 5, 4).melee().build();

        try {
            Unit giant = giantType.createUnit(45, false, DefenceModifier.No, false);
            throw new Exception();
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }

        try {
            Unit giant = giantType.createUnit(30, true, DefenceModifier.No, false);
            throw new Exception();
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }

        try {
            Unit giant = giantType.createUnit(30, false, DefenceModifier.Wall, false);
            throw new Exception();
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }

        try {
            Unit giant = giantType.createUnit(30, false, DefenceModifier.No, false);
            throw new Exception();
        } catch (Exception e) {
            assertEquals(Exception.class, e.getClass());
        }
    }

    @Test
    public void testContainer() {
        BasicUnitType waType = BasicUnitType.TypeBuilder.basicUnit().stats(10, 2, 2).melee().build();
        HashSet<BasicUnitType> possibleContains = new HashSet<>();
        possibleContains.add(waType);
        ContainerUnitType bst = ContainerUnitType.TypeBuilder.basicContainer().stats(4, 3).possibleContains(possibleContains).build();
        Unit wa = waType.createUnit(8, true, DefenceModifier.No, false);
        Unit bs = bst.createUnit(wa, false, DefenceModifier.Basic);
        Unit act = new Unit(8, 15, 4, 3, true, false, DefenceModifier.Basic.multiplier(), true, false);
        assertEquals(act, bs);
    }
}

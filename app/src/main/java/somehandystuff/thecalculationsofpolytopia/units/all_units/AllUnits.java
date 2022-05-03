package somehandystuff.thecalculationsofpolytopia.units.all_units;

import java.util.Map;

import somehandystuff.thecalculationsofpolytopia.units.BasicUnitType;
import somehandystuff.thecalculationsofpolytopia.units.ContainerUnitType;
import somehandystuff.thecalculationsofpolytopia.units.UnitType;

abstract class AllUnits {
    abstract UnitType getBasic(String key);
    abstract UnitType getContainer(String key);
    abstract String version();
    abstract Map<String, BasicUnitType> copyBasic();
    abstract Map<String, ContainerUnitType> copyCont();
}

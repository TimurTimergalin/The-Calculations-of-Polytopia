package somehandystuff.thecalculationsofpolytopia.units.all_units;

import java.util.Map;

import somehandystuff.thecalculationsofpolytopia.units.BasicUnitType;
import somehandystuff.thecalculationsofpolytopia.units.ContainerUnitType;
import somehandystuff.thecalculationsofpolytopia.units.UnitType;

abstract public class AllUnits {
    abstract public UnitType getBasic(String key);
    abstract public UnitType getContainer(String key);
    abstract public String version();
    abstract public Map<String, BasicUnitType> copyBasic();
    abstract public Map<String, ContainerUnitType> copyCont();
    abstract public String[] unitArray();
}

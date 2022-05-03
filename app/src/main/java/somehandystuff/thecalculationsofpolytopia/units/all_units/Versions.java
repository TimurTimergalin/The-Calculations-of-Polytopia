package somehandystuff.thecalculationsofpolytopia.units.all_units;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import somehandystuff.thecalculationsofpolytopia.units.BasicUnitType;
import somehandystuff.thecalculationsofpolytopia.units.ContainerUnitType;
import somehandystuff.thecalculationsofpolytopia.units.UnitType;

class Ver53 extends AllUnits {
    private final Map<String, BasicUnitType> basicDct = new HashMap<>();
    private final Map<String, ContainerUnitType> contDct = new HashMap<>();
    private final Set<BasicUnitType> canSwim = new HashSet<>();

    @Override
    String version() {
        return "Version 53";
    }

    private static final Ver53 inst = new Ver53();

    public static Ver53 getInstance() {
        return inst;
    }

    private Ver53() {
        basicDct.put("Warrior", BasicUnitType.TypeBuilder.basicUnit().melee().stats(10, 2, 2).build());
        basicDct.put("Archer", BasicUnitType.TypeBuilder.basicUnit().ranged().stats(10, 2, 1).build());
        basicDct.put("Defender", BasicUnitType.TypeBuilder.basicUnit().melee().stats(15, 1, 3).build());
        basicDct.put("Rider", BasicUnitType.TypeBuilder.basicUnit().melee().stats(10, 2, 1).build());
        basicDct.put("Mind Bender", BasicUnitType.TypeBuilder.peacefulUnit().stats(10, 0, 2).build());
        basicDct.put("Catapult", BasicUnitType.TypeBuilder.basicUnit().fightsBack(false).canFortify(false).ranged().stats(10, 4, 0).build());
        basicDct.put("Knight", BasicUnitType.TypeBuilder.basicUnit().melee().stats(15, 3.5f, 1).build());
        basicDct.put("Giant", BasicUnitType.TypeBuilder.superUnit().melee().stats(40, 5, 4).build());

        basicDct.put("Nature Bunny", BasicUnitType.TypeBuilder.superUnit().stats(20, 5, 1).build());

        basicDct.put("Amphibian", BasicUnitType.TypeBuilder.basicUnit().melee().stats(10, 2, 1).build());
        basicDct.put("Tridention", BasicUnitType.TypeBuilder.basicUnit().ranged().stats(15, 3, 1).build());
        basicDct.put("Crab", BasicUnitType.TypeBuilder.superUnit().melee().stats(40, 4, 4).build());

        basicDct.put("Polytaur", BasicUnitType.TypeBuilder.basicUnit().isPromotable(false).melee().stats(15, 3, 1).build());
        basicDct.put("Navalon", BasicUnitType.TypeBuilder.basicUnit().isPromotable(false).canFortify(false).melee().stats(30, 4, 4).build());
        basicDct.put("Dragon Egg", BasicUnitType.TypeBuilder.superUnit().canFortify(true).canAttack(false).fightsBack(false).melee().stats(10, 0, 2).build());
        basicDct.put("Baby Dragon", BasicUnitType.TypeBuilder.superUnit().ranged().stats(15, 3, 3).build());
        basicDct.put("Fire Dragon", BasicUnitType.TypeBuilder.superUnit().ranged().stats(20, 4, 3).build());

        basicDct.put("Ice Archer", BasicUnitType.TypeBuilder.basicUnit().ranged().stats(10, 0.1f, 1).build());
        basicDct.put("Battle Sled", BasicUnitType.TypeBuilder.basicUnit().canFortify(false).melee().stats(15, 3, 2).build());
        basicDct.put("Mooni", BasicUnitType.TypeBuilder.peacefulUnit().stats(10, 0, 2).build());
        basicDct.put("Ice Fortress", BasicUnitType.TypeBuilder.basicUnit().canFortify(false).ranged().stats(20, 4, 3).build());
        basicDct.put("Gaami", BasicUnitType.TypeBuilder.superUnit().melee().stats(30, 4, 4).build());

        basicDct.put("Hexapod", BasicUnitType.TypeBuilder.cymantiUnit().melee().stats(5, 3, 1).build());
        basicDct.put("Kiton", BasicUnitType.TypeBuilder.cymantiUnit().melee().poisons(true).stats(20, 1, 3).build());
        basicDct.put("Phychi", BasicUnitType.TypeBuilder.cymantiUnit().ranged().stats(5, 1, 1).poisons(true).build());
        basicDct.put("Shaman", BasicUnitType.TypeBuilder.cymantiUnit().melee().isPromotable(false).stats(10, 1, 1).build());
        basicDct.put("Raychi", BasicUnitType.TypeBuilder.cymantiUnit().melee().stats(15, 3, 2).build());
        basicDct.put("Exida", BasicUnitType.TypeBuilder.cymantiUnit().ranged().poisons(true).stats(10, 3, 1).build());
        basicDct.put("Doomax", BasicUnitType.TypeBuilder.cymantiUnit().melee().stats(20, 4, 2).build());
        basicDct.put("Centipede", BasicUnitType.TypeBuilder.superUnit().melee().stats(20, 4, 3).build());
        basicDct.put("Segment", BasicUnitType.TypeBuilder.superUnit().canAttack(false).melee().stats(10, 2, 2).build());

        canSwim.add(basicDct.get("Warrior"));
        canSwim.add(basicDct.get("Archer"));
        canSwim.add(basicDct.get("Defender"));
        canSwim.add(basicDct.get("Rider"));
        canSwim.add(basicDct.get("Mind Bender"));
        canSwim.add(basicDct.get("Catapult"));
        canSwim.add(basicDct.get("Knight"));
        canSwim.add(basicDct.get("Giant"));
//        canSwim.add(basicDct.get("Polytaur"));  ???
        canSwim.add(basicDct.get("Ice Archer"));

        contDct.put("Boat", ContainerUnitType.TypeBuilder.basicContainer().possibleContains(canSwim).stats(1, 1).build());
        contDct.put("Ship", ContainerUnitType.TypeBuilder.basicContainer().possibleContains(canSwim).stats(2, 2).build());
        contDct.put("Battleship", ContainerUnitType.TypeBuilder.basicContainer().possibleContains(canSwim).stats(4, 3).build());
    }

    public UnitType getBasic(String key) {
        return basicDct.get(key);
    }

    UnitType getContainer(String key) {
        return contDct.get(key);
    }

    public Map<String, BasicUnitType> copyBasic() {
        return new HashMap<>(basicDct);
    }

    public Map<String, ContainerUnitType> copyCont() {
        return new HashMap<>(contDct);
    }

    public Set<BasicUnitType> copyCanSwim() {return new HashSet<>(canSwim);}
}

class Ver81 extends AllUnits {
    private final Map<String, BasicUnitType> basicDct;
    private final Map<String, ContainerUnitType> contDct;

    Map<String, BasicUnitType> copyBasic() {
        return new HashMap<>(basicDct);
    }

    Map<String, ContainerUnitType> copyCont() {
        return new HashMap<>(contDct);
    }

    UnitType getBasic(String key) {
        return basicDct.get(key);
    }

    UnitType getContainer(String key) {
        return contDct.get(key);
    }

    String version() {
        return "Version 81 (Beta)";
    }

    private static final Ver81 inst = new Ver81();

    public static Ver81 getInstance() {
        return inst;
    }

    private Ver81() {
        basicDct = Ver53.getInstance().copyBasic();
        basicDct.put("Knight", BasicUnitType.TypeBuilder.basicUnit().melee().stats(10, 3.5f, 1).build());
        basicDct.put("Cloak", BasicUnitType.TypeBuilder.peacefulUnit().stats(5, 0, 0.5f).build());
        basicDct.put("Dagger", BasicUnitType.TypeBuilder.basicUnit().enemyFightsBack(false).melee().stats(10, 2, 2).build());

        contDct = Ver53.getInstance().copyCont();

        Set<BasicUnitType> dinghySet = new HashSet<>();
        dinghySet.add(basicDct.get("Cloak"));

        contDct.put("Dinghy", ContainerUnitType.TypeBuilder.peacefulContainer().possibleContains(dinghySet).stats(0, 0.5f).build());
    }
}


package somehandystuff.thecalculationsofpolytopia.units;


import java.util.Set;

import somehandystuff.thecalculationsofpolytopia.core.Unit;

public class ContainerUnitType extends UnitType {
    public static Set<BasicUnitType> mayBeContained;

    public static class TypeBuilder {
        private static final TypeBuilder inst = new TypeBuilder();

        private float attack;
        private float defence;
        private boolean isMelee;
        private boolean _fightsBack;
        private boolean _canAttack;

        boolean hasStats;
        boolean hasPossibleContains;
        private void refresh() {
            hasStats = false;
            hasPossibleContains = false;
        }

        private boolean isReady() {return hasStats && hasPossibleContains;}
        private TypeBuilder() {refresh();}

        public static TypeBuilder basicContainer() {
            inst.refresh();
            inst._fightsBack = true;
            inst._canAttack = true;
            inst.isMelee = false;
            return inst;
        }

        public static TypeBuilder peacefulContainer() {
            inst.refresh();
            inst._fightsBack = false;
            inst._canAttack = false;
            inst.isMelee = true;
            return inst;
        }

        public TypeBuilder stats(float attack, float defence) {
            if (attack < 0 || defence < 0) throw new IllegalArgumentException();
            this.attack = attack;
            this.defence = defence;
            hasStats = true;
            return this;
        }

        public TypeBuilder possibleContains(Set<BasicUnitType> mbc) {
            mayBeContained = mbc;
            hasPossibleContains = true;
            return this;
        }

        public ContainerUnitType build() {
            if (!isReady()) throw new RuntimeException();
            ContainerUnitType res = new ContainerUnitType();
            res.attack = attack;
            res.defence = defence;
            res.isMelee = isMelee;
            res.fightsBack = _fightsBack;
            res.canAttack = _canAttack;
            res.enemyFightsBack = true;
            res.poisons = false;
            return res;
        }
    }

    public Unit createUnit(Unit containedUnit, boolean standsBy, DefenceModifier defMod) {
        if (defMod == DefenceModifier.Wall) throw new IllegalArgumentException();
        return new Unit(containedUnit.health, containedUnit.maxHealth, attack, defence, fightsBack, isMelee, defMod.multiplier(), enemyFightsBack, poisons);
    }
}

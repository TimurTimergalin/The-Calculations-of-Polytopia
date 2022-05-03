package somehandystuff.thecalculationsofpolytopia.units;

import somehandystuff.thecalculationsofpolytopia.core.Unit;

public class BasicUnitType extends UnitType{
    public static class TypeBuilder {
        private int maxHealth;
        private float attack;
        private float defence;
        private boolean isMelee;
        private boolean _fightsBack;
        private boolean _isPromotable;
        private boolean _canFortify;
        private boolean _enemyFightsBack;
        private boolean _canAttack;
        private boolean _poisons;

        private boolean hasStats = false;
        private boolean hasRange = false;

        private boolean isReady() {
            return hasRange && hasStats;
        }

        private void refresh() {
            hasStats = false;
            hasRange = false;
        }

        private TypeBuilder() {}
        private static final TypeBuilder inst = new TypeBuilder();

        public TypeBuilder fightsBack(boolean _fightsBack) {
            this._fightsBack = _fightsBack;
            return this;
        }

        public TypeBuilder isPromotable(boolean _isPromotable) {
            this._isPromotable = _isPromotable;
            return this;
        }

        public TypeBuilder canFortify(boolean _canFortify) {
            this._canFortify = _canFortify;
            return this;
        }

        public TypeBuilder enemyFightsBack(boolean _enemyFightsBack) {
            this._enemyFightsBack = _enemyFightsBack;
            return this;
        }

        public TypeBuilder canAttack(boolean _canAttack) {
            this._canAttack = _canAttack;
            return this;
        }

        public TypeBuilder poisons(boolean _poisons) {
            this._poisons = _poisons;
            return this;
        }

        public static TypeBuilder basicUnit() {
            inst.refresh();
            inst._fightsBack = true;
            inst._isPromotable = true;
            inst._canFortify = true;
            inst._enemyFightsBack = true;
            inst._canAttack = true;
            inst._poisons = false;
            return inst;
        }

        public static TypeBuilder cymantiUnit() {
            inst.refresh();
            inst._fightsBack = true;
            inst._isPromotable = true;
            inst._canFortify = false;
            inst._enemyFightsBack = true;
            inst._canAttack = true;
            inst._poisons = false;
            return inst;
        }

        public static TypeBuilder superUnit() {
            inst.refresh();
            inst._fightsBack = true;
            inst._isPromotable = false;
            inst._canFortify = false;
            inst._enemyFightsBack = true;
            inst._canAttack = true;
            inst._poisons = false;
            return inst;
        }

        public static TypeBuilder peacefulUnit() {
            inst.refresh();
            inst._fightsBack = false;
            inst._isPromotable = false;
            inst._canFortify = false;
            inst._canAttack = false;
            inst._poisons = false;
            inst.isMelee = true;
            inst.hasRange = true;
            return inst;
        }

        public TypeBuilder melee() {
            isMelee = true;
            hasRange = true;
            return this;
        }

        public TypeBuilder ranged() {
            isMelee = false;
            hasRange = true;
            return this;
        }

        public TypeBuilder stats(int maxHealth, float attack, float defence) {
            if (maxHealth <= 0 || attack < 0 || defence < 0) throw new IllegalArgumentException();
            this.maxHealth = maxHealth;
            this.attack = attack;
            this.defence = defence;
            this.hasStats = true;
            return this;
        }

        public BasicUnitType build() {
            if (!isReady()) throw new RuntimeException();

            BasicUnitType ut = new BasicUnitType();
            ut.maxHealth = maxHealth;
            ut.attack = attack;
            ut.defence = defence;
            ut.canAttack = _canAttack;
            ut.enemyFightsBack = _enemyFightsBack;
            ut.isMelee = isMelee;
            ut.fightsBack = _fightsBack;
            ut.poisons = _poisons;
            ut.isPromotable = _isPromotable;
            ut.canFortify = _canFortify;
            refresh();
            return ut;
        }
    }

    public Unit createUnit(int health, boolean veteran, DefenceModifier defMod, boolean boost) {
        if (!isPromotable && veteran) throw new IllegalArgumentException();
        if (defMod == DefenceModifier.Wall && !canFortify) throw new IllegalArgumentException();
        int maxHealth = this.maxHealth; if (veteran) maxHealth += 5;
        if (health <= 0 || health > maxHealth) throw new IllegalArgumentException();
        float attack = this.attack; if (boost) attack += 0.5f;

        return new Unit(health, maxHealth, attack, defence, fightsBack, isMelee, defMod.multiplier(), enemyFightsBack, poisons);
    }
}

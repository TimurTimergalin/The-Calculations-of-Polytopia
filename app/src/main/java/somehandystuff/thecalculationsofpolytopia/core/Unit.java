package somehandystuff.thecalculationsofpolytopia.core;

import java.util.Objects;

public class Unit {
    @Override
    public String toString() {
        return "Unit{" +
                "health=" + health +
                ", max_health=" + maxHealth +
                ", attack=" + attack +
                ", defence=" + defence +
                ", fightsBack=" + fightsBack +
                ", isMelee=" + isMelee +
                ", defenceBonus=" + defenceBonus +
                ", enemyFightsBack=" + enemyFightsBack +
                ", poisons=" + poisons +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return health == unit.health && maxHealth == unit.maxHealth && Float.compare(unit.attack, attack) == 0 && Float.compare(unit.defence, defence) == 0 && fightsBack == unit.fightsBack && isMelee == unit.isMelee && Float.compare(unit.defenceBonus, defenceBonus) == 0 && enemyFightsBack == unit.enemyFightsBack && poisons == unit.poisons;
    }

    @Override
    public int hashCode() {
        return Objects.hash(health, maxHealth, attack, defence, fightsBack, isMelee, defenceBonus, enemyFightsBack, poisons);
    }

    public int health;
    public int maxHealth;
    public float attack;
    public float defence;
    public boolean fightsBack;
    public boolean isMelee;
    public float defenceBonus;
    public boolean enemyFightsBack;
    public boolean poisons;


    public Unit(int health, int maxHealth, float attack, float defence, boolean fightsBack, boolean isMelee, float defenceBonus, boolean enemyFightsBack, boolean poisons) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.defence = defence;
        this.fightsBack = fightsBack;
        this.isMelee = isMelee;
        this.defenceBonus = defenceBonus;
        this.enemyFightsBack = enemyFightsBack;
        this.poisons = poisons;
    }

    public float attackPower() {
        return attack * health / maxHealth;
    }

    public float defencePower() {
        return defence * health / maxHealth * defenceBonus;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public Unit dealDamage(int damage) {
        return new Unit(health - damage, maxHealth, attack, defence, fightsBack, isMelee, defenceBonus, enemyFightsBack, poisons);
    }
}

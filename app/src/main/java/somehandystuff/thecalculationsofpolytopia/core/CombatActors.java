package somehandystuff.thecalculationsofpolytopia.core;

public class CombatActors {
    public CombatActors(Unit[] attackers, Unit defender) {
        this.attackers = attackers;
        this.defender = defender;
    }

    private final Unit[] attackers;
    private final Unit defender;

    public Unit[] getAttackers() {
        return attackers;
    }

    public Unit getDefender() {
        return defender;
    }
}
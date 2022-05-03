package somehandystuff.thecalculationsofpolytopia.core;

public class Combat {
    public static CombatActors singleCombat(Unit attacker, Unit defender) {
        float atPower = attacker.attackPower();
        float dfPower = defender.defencePower();
        float total = atPower + dfPower;

        int atResult = Math.round(atPower / total * attacker.attack * 4.5f + 0.01f);
        int dfResult = Math.round(dfPower / total * defender.defence * 4.5f + 0.01f);
        if (!attacker.enemyFightsBack || !defender.fightsBack || (!attacker.isMelee)) {
            dfResult = 0;
        }

        Unit newDefender = defender.dealDamage(atResult);
        if (attacker.poisons) {
            newDefender.defenceBonus = 0.4f;
        }
        Unit newAttacker;
        if (newDefender.isDead()) {
            newAttacker = attacker;
        } else {
            newAttacker = attacker.dealDamage(dfResult);
        }
        return new CombatActors(new Unit[]{newAttacker}, newDefender);
    }

    public static CombatActors combatChain(Unit[] attackers, Unit defender) {
        CombatActors res;
        Unit[] newAttackers = new Unit[attackers.length];

        Unit at;

        for (int i = 0;i< newAttackers.length;i++) {
            at = attackers[i];
            if (defender.isDead()) {
                newAttackers[i] = at;
            } else {
                res = singleCombat(at, defender);
                defender = res.getDefender();
                attackers[i] = res.getAttackers()[0];
            }
        }
        return new CombatActors(newAttackers, defender);
    }

    public static int howManyCombat(Unit attacker, Unit defender) {
        int c = 0;

        while (!defender.isDead()) {
            defender = singleCombat(attacker, defender).getDefender();
            c++;
        }
        return c;
    }
}

package com.dt180g.project.abilities;

/**
 * Container class, used for passing along ability information.
 * @author Erik Ström
 */
public final class AbilityInfo {
    private final String information;
    private final int amountOfTargets;
    private final int damage;
    private final boolean targetEnemies;
    private final boolean isMagic;
    private final boolean isHeal;

    /**
     * Support class to contain ability information to be passed to game engine.
     * @param info log information.
     * @param amountOfTargets number of targets for the ability.
     * @param damage amount of attack damage.
     * @param targetEnemies whether enemies or heroes should be targeted.
     * @param isMagic whether the ability is magical or physical.
     * @param isHeal whether the ability is healing or damaging.
     */
    public AbilityInfo(final String info, final int amountOfTargets, final int damage,
                       final boolean targetEnemies, final boolean isMagic, final boolean isHeal) {
        this.information = info;
        this.amountOfTargets = amountOfTargets;
        this.damage = damage;
        this.targetEnemies = targetEnemies;
        this.isMagic = isMagic;
        this.isHeal = isHeal;
    }

    /**
     * Accessor for information.
     * @return ability information.
     */
    public String getInformation() { return information; }

    /**
     * Accessor for amount of targets.
     * @return the amount of targets.
     */
    public int getAmountOfTargets() { return amountOfTargets; }

    /**
     * Accessor for damage of ability.
     * @return the ability's damage value.
     */
    public int getDamage() { return damage; }

    /**
     * Accessor for target enemies.
     * @return whether enemies should be targeted.
     */
    public boolean getTargetEnemies() { return targetEnemies; }

    /**
     * Whether the ability is magical.
     * @return true if magic, false otherwise.
     */
    public boolean isMagic() { return isMagic; }

    /**
     * Whether the ability regards healing.
     * @return true if healing, false otherwise.
     */
    public boolean isHeal() { return isHeal; }

}

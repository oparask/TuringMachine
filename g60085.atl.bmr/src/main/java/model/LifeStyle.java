package model;
/**
 * This enum represents different levels of lifestyle activity and their corresponding factors for BMR calculation.
 * Each enum constant includes a lifestyle factor that is used in BMR calculation.
 */
public enum LifeStyle {
    SEDENTARY(1.2),
    LIGHTLY_ACTIVE(1.375),
    MODERATELY_ACTIVE(1.55),
    VERY_ACTIVE(1.725),
    EXTREMELY_ACTIVE(1.9);

    private final double factor;

    /**
     * Constructor for LifeStyle enum constants.
     *
     * @param factor The lifestyle factor used for BMR calculation.
     */
    LifeStyle(double factor) {
        this.factor = factor;
    }

    /**
     * Get the lifestyle factor associated with the enum constant.
     *
     * @return The lifestyle factor.
     */
    public double getFactor() {
        return factor;
    }

    /**
     * Returns a human-readable representation of the enum constant.
     *
     * @return A string representation of the enum constant, in French.
     */
    @Override
    public String toString() {
        switch (this) {
            case SEDENTARY:
                return "Sédentaire";
            case LIGHTLY_ACTIVE:
                return "Peu actif";
            case MODERATELY_ACTIVE:
                return "Modérément Actif";
            case VERY_ACTIVE:
                return "Fort actif";
            case EXTREMELY_ACTIVE:
                return "Extrêmement actif";
            default:
                return super.toString();
        }
    }
}


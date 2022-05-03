package somehandystuff.thecalculationsofpolytopia.units;

public enum DefenceModifier {
    No,
    Basic,
    Wall,
    Poison;

    public float multiplier() {
        float res = 1.0f;
        switch (this) {
            case No: res = 1;break;
            case Basic: res = 1.5f;break;
            case Wall: res = 4;break;
            case Poison: res = 0.7f;break;
        }
        return res;
    }
}

package somehandystuff.thecalculationsofpolytopia.units.all_units;

public class VersionManager {
    private static final AllUnits[] versions = new AllUnits[]{Ver53.getInstance(), Ver81.getInstance()};


    public static AllUnits getByName(String name) {
        for (AllUnits ver: versions) {
            if (ver.version().equals(name)) return ver;
        }
        throw new RuntimeException("No such version");
    }

    public static String[] allVersionNames() {
        String[] res = new String[versions.length];

        for (int i = 0; i < versions.length;i++) {
            res[i] = versions[i].version();
        }
        return res;
    }

}

package somehandystuff.thecalculationsofpolytopia;

import org.junit.Test;

import somehandystuff.thecalculationsofpolytopia.units.all_units.VersionManager;

public class VersionsTest {
    @Test
    public void basic() {
        String[] allVersions = VersionManager.allVersionNames();
    }
}

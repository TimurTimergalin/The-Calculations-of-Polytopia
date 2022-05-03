package somehandystuff.thecalculationsofpolytopia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import somehandystuff.thecalculationsofpolytopia.debug.DebugClass;
import somehandystuff.thecalculationsofpolytopia.units.all_units.VersionManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Spinner spinner = findViewById(R.id.version);

        TextView txt = findViewById(R.id.name);

        String[] allVersions = VersionManager.allVersionNames();

        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allVersions);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        } catch (Exception e) {
            txt.setText(String.valueOf(e.getClass()));
        }

    }
}
package somehandystuff.thecalculationsofpolytopia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import somehandystuff.thecalculationsofpolytopia.ui.CardLayout;
import somehandystuff.thecalculationsofpolytopia.units.UnitType;
import somehandystuff.thecalculationsofpolytopia.units.all_units.AllUnits;
import somehandystuff.thecalculationsofpolytopia.units.all_units.VersionManager;

public class MainActivity extends AppCompatActivity {
    private int dpToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private AllUnits version() {
        return VersionManager.getByName(((Spinner) findViewById(R.id.version)).getSelectedItem().toString());
    }

    private String[] getVersionUnits() {
        return version().unitArray();
    }

    private class CardBuilder {
        private ConstraintLayout outer;
        private ConstraintLayout res;
        private LinearLayout col;
        private int unitId;
        private int lastID;
        boolean first = true;
        boolean firstCheckbox = true;

        public CardBuilder(LinearLayout col) {
            this.col = col;
            outer = new ConstraintLayout(MainActivity.this);
            col.addView(outer);
            outer.setId(View.generateViewId());

            outer.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
            outer.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;

            res = new CardLayout(MainActivity.this);
            res.setId(View.generateViewId());
            outer.addView(res);

            res.setBackground(getDrawable(R.drawable.card_background));
            res.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
            res.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
            res.setMinHeight(dpToPx(130));

            ConstraintSet cs = new ConstraintSet();
            cs.clone(outer);
            int[] dir = new int[]{ConstraintSet.TOP, ConstraintSet.BOTTOM, ConstraintSet.START, ConstraintSet.END, ConstraintSet.LEFT, ConstraintSet.RIGHT};

            for (int i: dir) {
                cs.connect(res.getId(), i, outer.getId(), i, dpToPx(10));
            }
            cs.applyTo(outer);

            addBar();
        }

        private UnitType getCurrentUnitType() {
            String name = ((Spinner)findViewById(unitId)).getSelectedItem().toString();

            try {
                return version().getBasic(name);
            } catch (Exception e) {
                return version().getContainer(name);
            }
        }

        private void addBar() {
            FrameLayout bar = new FrameLayout(MainActivity.this);
            bar.setId(View.generateViewId());
            res.addView(bar);

            bar.setBackground(getDrawable(R.drawable.card_bar));
            bar.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            bar.getLayoutParams().height = res.getLayoutParams().height * 3 / 10;
            bar.setPadding(dpToPx(10), 0, dpToPx(10), 0);

            ConstraintSet cs = new ConstraintSet();
            cs.clone(res);
            cs.connect(bar.getId(), ConstraintSet.TOP, res.getId(), ConstraintSet.TOP, 0);
            cs.connect(bar.getId(), ConstraintSet.START, res.getId(), ConstraintSet.START, 0);
            cs.connect(bar.getId(), ConstraintSet.END, res.getId(), ConstraintSet.END, 0);
            cs.applyTo(res);

            Spinner sp = new Spinner(MainActivity.this);
            sp.setId(View.generateViewId());

            String[] uar = getVersionUnits();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, uar);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(adapter);
            sp.setSelection(adapter.getPosition("Warrior"));

            bar.addView(sp);
            lastID = bar.getId();
            unitId = sp.getId();
        }

        public CardBuilder addHP() {
            LinearLayout lay = new LinearLayout(MainActivity.this);
            lay.setId(View.generateViewId());
            res.addView(lay);

            ConstraintSet cs = new ConstraintSet();
            cs.clone(res);
            cs.connect(lay.getId(), ConstraintSet.TOP, lastID, ConstraintSet.BOTTOM, dpToPx(5));
            cs.connect(lay.getId(), ConstraintSet.START, lastID, ConstraintSet.START);
            cs.connect(lay.getId(), ConstraintSet.LEFT, lastID, ConstraintSet.LEFT);

            cs.applyTo(res);

            if (first) {
                first = false;
                ((ViewGroup.MarginLayoutParams) lay.getLayoutParams()).setMarginStart(dpToPx(5));
            }



            TextView label = new TextView(MainActivity.this);
            label.setId(View.generateViewId());
            lay.addView(label);

            label.setText("HP: ");
            label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            label.setGravity(Gravity.CENTER);
            label.setTypeface(null, Typeface.BOLD);
            label.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
            label.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;

            TextView hp = new TextView(MainActivity.this);
            hp.setId(View.generateViewId());
            lay.addView(hp);

            hp.setText(String.valueOf(getCurrentUnitType().maxHealth));
            hp.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            hp.setGravity(Gravity.CENTER);
            hp.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
            hp.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            ((ViewGroup.MarginLayoutParams)hp.getLayoutParams()).setMargins(0, 0, 0, 0);
            hp.setPadding(0, 0, 0, 0);

            Button dwn = new Button(MainActivity.this);
            dwn.setId(View.generateViewId());
            lay.addView(dwn);

            dwn.setText("<");
            dwn.getLayoutParams().height = dpToPx(35);
            dwn.getLayoutParams().width = dpToPx(35);
            dwn.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ((ViewGroup.MarginLayoutParams) dwn.getLayoutParams()).setMargins(dpToPx(3),0, dpToPx(3), 0);
            dwn.setBackground(getDrawable(R.drawable.button_shape));

            dwn.setOnClickListener(view -> {
                int val = Integer.parseInt(hp.getText().toString());
                if (val > 0) {
                    --val;
                }
                String res = String.valueOf(val);
                if (res.length() == 1) {
                    res = "0" + res;
                }
                hp.setText(res);
            });

            Button up = new Button(MainActivity.this);
            up.setId(View.generateViewId());
            lay.addView(up);

            up.setText(">");
            up.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            up.getLayoutParams().height = dpToPx(35);
            up.getLayoutParams().width = dpToPx(35);
            up.setBackground(getDrawable(R.drawable.button_shape));

            up.setOnClickListener(view -> {
                int val = Integer.parseInt(hp.getText().toString());
                if (val < getCurrentUnitType().maxHealth) {
                    ++val;
                }
                String res = String.valueOf(val);
                if (res.length() == 1) {
                    res = "0" + res;
                }
                hp.setText(res);
            });

            lastID = lay.getId();
            return this;
        }

        public CardBuilder addDelete() {
            FrameLayout btn_out = new FrameLayout(MainActivity.this);
            btn_out.setId(View.generateViewId());
            outer.addView(btn_out);

            ConstraintSet cs = new ConstraintSet();
            cs.clone(outer);
            cs.connect(btn_out.getId(), ConstraintSet.TOP, outer.getId(), ConstraintSet.TOP, dpToPx(5));
            cs.connect(btn_out.getId(), ConstraintSet.END, outer.getId(), ConstraintSet.END, dpToPx(5));
            cs.applyTo(outer);

            btn_out.getLayoutParams().width = dpToPx(20);
            btn_out.getLayoutParams().height = dpToPx(20);

            btn_out.setBackground(getDrawable(R.drawable.close_button_background));

            Button btn = new Button(MainActivity.this);
            btn.setId(View.generateViewId());
            btn_out.addView(btn);

            btn.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            btn.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            btn.setBackground(getDrawable(android.R.drawable.ic_menu_close_clear_cancel));

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    col.removeView(outer);
                }
            });

            return this;
        }

        public CardBuilder addDefenceBonus() {
            LinearLayout lay = new LinearLayout(MainActivity.this);
            lay.setId(View.generateViewId());
            res.addView(lay);

            lay.setOrientation(LinearLayout.HORIZONTAL);
            lay.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
            lay.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;

            ConstraintSet cs = new ConstraintSet();
            cs.clone(res);
            cs.connect(lay.getId(), ConstraintSet.START, lastID, ConstraintSet.START);
            cs.connect(lay.getId(), ConstraintSet.TOP, lastID, ConstraintSet.BOTTOM, dpToPx(3));
            cs.applyTo(res);

            if (first) {
                first = false;
                ((ViewGroup.MarginLayoutParams) lay.getLayoutParams()).setMarginStart(dpToPx(5));
            }

            TextView label = new TextView(MainActivity.this);
            label.setId(View.generateViewId());
            lay.addView(label);

            label.setText("Bonus: ");
            label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            label.setGravity(Gravity.CENTER);
            label.setTypeface(null, Typeface.BOLD);
            label.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
            label.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;

            Spinner sp = new Spinner(MainActivity.this);
            sp.setId(View.generateViewId());
            lay.addView(sp);

            String[] choices;

            boolean canFortify = getCurrentUnitType().canFortify;

            if (canFortify) {
                choices = new String[]{"1.0 None", "1.5 Standard", "4.0 Wall", "0.7 Poison"};
            } else {
                choices = new String[]{"1.0 None", "1.5 Standard", "0.7 Poison"};
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, choices);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(adapter);

            ((ViewGroup.MarginLayoutParams) sp.getLayoutParams()).setMargins(dpToPx(5), 0, 0 ,0);

            lastID = lay.getId();

            return this;
        }

        public CardBuilder addPromotedCheckbox() {
            if (!getCurrentUnitType().isPromotable) throw new RuntimeException("No veteran!");

            CheckBox c = new CheckBox(MainActivity.this);
            c.setId(View.generateViewId());
            res.addView(c);

            int m;
            if (firstCheckbox) {
                m = -dpToPx(6);
                firstCheckbox = false;
            } else {
                m = 0;
            }

            ConstraintSet cs = new ConstraintSet();
            cs.clone(res);
            cs.connect(c.getId(), ConstraintSet.TOP, lastID, ConstraintSet.BOTTOM, dpToPx(3));
            cs.connect(c.getId(), ConstraintSet.START, lastID, ConstraintSet.START, m);
            cs.applyTo(res);

            c.setText("Veteran");
            c.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

            lastID = c.getId();
            return this;
        }

        public CardBuilder addBoostCheckbox() {
            CheckBox c = new CheckBox(MainActivity.this);
            c.setId(View.generateViewId());
            res.addView(c);

            int m;
            if (firstCheckbox) {
                m = -dpToPx(6);
                firstCheckbox = false;
            } else {
                m = 0;
            }

            ConstraintSet cs = new ConstraintSet();
            cs.clone(res);
            cs.connect(c.getId(), ConstraintSet.TOP, lastID, ConstraintSet.BOTTOM, dpToPx(3));
            cs.connect(c.getId(), ConstraintSet.START, lastID, ConstraintSet.START, m);
            cs.applyTo(res);

            c.setText("Boost");
            c.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

            lastID = c.getId();

            return this;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Spinner spinner = findViewById(R.id.version);

        TextView txt = findViewById(R.id.name);

        String[] allVersions = VersionManager.allVersionNames();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allVersions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        try {
            CardBuilder b = new CardBuilder(findViewById(R.id.defender_column)).addHP().addDelete().addDefenceBonus().addPromotedCheckbox().addBoostCheckbox();

        } catch (Throwable e) {
            txt.setText(String.valueOf(e.getMessage()));
        }
    }
}
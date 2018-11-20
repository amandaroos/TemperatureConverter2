package com.amandafarrell.www.temperatureconverter2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private int mDecimalPlaces = 2;
    private String mDecimalPattern = "#0.";

    private double mKelvin = 273.15;
    private double mCelsius = 0;
    private double mFahrenheit = 32;
    private double mRankine = 491.67;
    private double mDelisle = 150;
    private double mNewton = 0;
    private double mReaumur = 0;
    private double mRomer = 7.5;

    private EditText mKelvinEditText;
    private EditText mCelsiusEditText;
    private EditText mFahrenheitEditText;
    private EditText mRankineEditText;
    private EditText mDelisleEditText;
    private EditText mNewtonEditText;
    private EditText mReaumurEditText;
    private EditText mRomerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Access the user's preference for decimal places
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mDecimalPlaces = Integer.parseInt(
                sharedPreferences.getString(getString(R.string.settings_decimal_places_key),
                        getString(R.string.settings_decimal_places_default)));

        //Create decimal pattern string based on the decimal places int
        for (int i = 0; i < mDecimalPlaces; i++) {
            mDecimalPattern = mDecimalPattern + "0";
        }

        //Find Views
        mKelvinEditText = (EditText) findViewById(R.id.edit_text_kelvin);
        mCelsiusEditText = (EditText) findViewById(R.id.edit_text_celsius);
        mFahrenheitEditText = (EditText) findViewById(R.id.edit_text_fahrenheit);
        mRankineEditText = (EditText) findViewById(R.id.edit_text_rankine);
        mDelisleEditText = (EditText) findViewById(R.id.edit_text_delisle);
        mNewtonEditText = (EditText) findViewById(R.id.edit_text_newton);
        mReaumurEditText = (EditText) findViewById(R.id.edit_text_reaumur);
        mRomerEditText = (EditText) findViewById(R.id.edit_text_romer);

        //Set views to default values
        mKelvinEditText.setText(formatUnit(mKelvin));
        mCelsiusEditText.setText(formatUnit(mCelsius));
        mFahrenheitEditText.setText(formatUnit(mFahrenheit));
        mRankineEditText.setText(formatUnit(mRankine));
        mDelisleEditText.setText(formatUnit(mDelisle));
        mNewtonEditText.setText(formatUnit(mNewton));
        mReaumurEditText.setText(formatUnit(mReaumur));
        mRomerEditText.setText(formatUnit(mRomer));

        //Select all text
        mKelvinEditText.setSelectAllOnFocus(true);
        mCelsiusEditText.setSelectAllOnFocus(true);
        mFahrenheitEditText.setSelectAllOnFocus(true);
        mRankineEditText.setSelectAllOnFocus(true);
        mDelisleEditText.setSelectAllOnFocus(true);
        mNewtonEditText.setSelectAllOnFocus(true);
        mReaumurEditText.setSelectAllOnFocus(true);
        mRomerEditText.setSelectAllOnFocus(true);

        //Set TextWatchers
        mKelvinEditText.addTextChangedListener(kelvinTextWatcher);
        mCelsiusEditText.addTextChangedListener(celsiusTextWatcher);
        mFahrenheitEditText.addTextChangedListener(fahrenheitTextWatcher);
        mRankineEditText.addTextChangedListener(rankineTextWatcher);
        mDelisleEditText.addTextChangedListener(delisleTextWatcher);
        mNewtonEditText.addTextChangedListener(newtonTextWatcher);
        mReaumurEditText.addTextChangedListener(reaumurTextWatcher);
        mRomerEditText.addTextChangedListener(romerTextWatcher);

        //Format the EditTexts when they lose focus
        mKelvinEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    mKelvinEditText.setText(formatUnit(mKelvin));
                }
            }
        });

        mCelsiusEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    mCelsiusEditText.setText(formatUnit(mCelsius));
                }
            }
        });

        mFahrenheitEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    mFahrenheitEditText.setText(formatUnit(mFahrenheit));
                }
            }
        });

        mRankineEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    mRankineEditText.setText(formatUnit(mRankine));
                }
            }
        });

        mDelisleEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    mDelisleEditText.setText(formatUnit(mDelisle));
                }
            }
        });

        mNewtonEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    mNewtonEditText.setText(formatUnit(mNewton));
                }
            }
        });

        mReaumurEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    mReaumurEditText.setText(formatUnit(mReaumur));
                }
            }
        });

        mRomerEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    mRomerEditText.setText(formatUnit(mRomer));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.actions_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private TextWatcher kelvinTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String editTextString = mKelvinEditText.getText().toString();
            //don't parse if the string is empty
            if (editTextString.isEmpty()) {
                mKelvin = 0;
            } else if (!editTextString.equals("-")&&!editTextString.equals(".")&&!editTextString.equals("-.")) {
                mKelvin = Double.valueOf(editTextString);
            }
            mKelvinEditText.setSelection(mKelvinEditText.getText().length());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //don't do anything if the editText is not in focus
            if (getCurrentFocus() == mKelvinEditText) {
                //convert to all other units from Kelvin
                convertAllFromKelvin();
                //reset views with the new values
                mCelsiusEditText.setText(formatUnit(mCelsius));
                mFahrenheitEditText.setText(formatUnit(mFahrenheit));
                mRankineEditText.setText(formatUnit(mRankine));
                mDelisleEditText.setText(formatUnit(mDelisle));
                mNewtonEditText.setText(formatUnit(mNewton));
                mReaumurEditText.setText(formatUnit(mReaumur));
                mRomerEditText.setText(formatUnit(mRomer));
            }
        }
    };

    private TextWatcher celsiusTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String editTextString = mCelsiusEditText.getText().toString();
            //don't parse if the string is empty
            if (editTextString.isEmpty()) {
                mCelsius = 0;
            } else if (!editTextString.equals("-")&&!editTextString.equals(".")&&!editTextString.equals("-.")) {
                mCelsius = Double.valueOf(editTextString);
            }
            mCelsiusEditText.setSelection(mCelsiusEditText.getText().length());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //don't do anything if the editText is not in focus
            if (getCurrentFocus() == mCelsiusEditText) {
                //convert to Kelvin and convert to all other units from Kelvin
                celsiusToKelvin();
                convertAllFromKelvin();
                //reset views with the new values
                mKelvinEditText.setText(formatUnit(mKelvin));
                mFahrenheitEditText.setText(formatUnit(mFahrenheit));
                mRankineEditText.setText(formatUnit(mRankine));
                mDelisleEditText.setText(formatUnit(mDelisle));
                mNewtonEditText.setText(formatUnit(mNewton));
                mReaumurEditText.setText(formatUnit(mReaumur));
                mRomerEditText.setText(formatUnit(mRomer));
            }
        }
    };

    private TextWatcher fahrenheitTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String editTextString = mFahrenheitEditText.getText().toString();
            //don't parse if the string is empty or a negative sign
            if (editTextString.isEmpty()) {
                mFahrenheit = 0;
            } else if (!editTextString.equals("-")&&!editTextString.equals(".")&&!editTextString.equals("-.")) {
                mFahrenheit = Double.valueOf(editTextString);
            }
            mFahrenheitEditText.setSelection(mFahrenheitEditText.getText().length());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //don't do anything if the editText is not in focus
            if (getCurrentFocus() == mFahrenheitEditText) {
                //convert to Kelvin and convert to all other units from Kelvin
                fahrenheitToKelvin();
                convertAllFromKelvin();
                //reset views with the new values
                mKelvinEditText.setText(formatUnit(mKelvin));
                mCelsiusEditText.setText(formatUnit(mCelsius));
                mRankineEditText.setText(formatUnit(mRankine));
                mDelisleEditText.setText(formatUnit(mDelisle));
                mNewtonEditText.setText(formatUnit(mNewton));
                mReaumurEditText.setText(formatUnit(mReaumur));
                mRomerEditText.setText(formatUnit(mRomer));
            }
        }
    };

    private TextWatcher rankineTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String editTextString = mRankineEditText.getText().toString();
            //don't parse if the string is empty or a negative sign
            if (editTextString.isEmpty()) {
                mRankine = 0;
            } else if (!editTextString.equals("-")&&!editTextString.equals(".")&&!editTextString.equals("-.")) {
                mRankine = Double.valueOf(editTextString);
            }
            mRankineEditText.setSelection(mRankineEditText.getText().length());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //don't do anything if the editText is not in focus
            if (getCurrentFocus() == mRankineEditText) {
                //convert to Kelvin and convert to all other units from Kelvin
                rankineToKelvin();
                convertAllFromKelvin();
                //reset views with the new values
                mKelvinEditText.setText(formatUnit(mKelvin));
                mCelsiusEditText.setText(formatUnit(mCelsius));
                mFahrenheitEditText.setText(formatUnit(mFahrenheit));
                mDelisleEditText.setText(formatUnit(mDelisle));
                mNewtonEditText.setText(formatUnit(mNewton));
                mReaumurEditText.setText(formatUnit(mReaumur));
                mRomerEditText.setText(formatUnit(mRomer));
            }
        }
    };

    private TextWatcher delisleTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String editTextString = mDelisleEditText.getText().toString();
            //don't parse if the string is empty or a negative sign
            if (editTextString.isEmpty()) {
                mDelisle = 0;
            } else if (!editTextString.equals("-")&&!editTextString.equals(".")&&!editTextString.equals("-.")) {
                mDelisle = Double.valueOf(editTextString);
            }
            mDelisleEditText.setSelection(mDelisleEditText.getText().length());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //don't do anything if the editText is not in focus
            if (getCurrentFocus() == mDelisleEditText) {
                //convert to Kelvin and convert to all other units from Kelvin
                delisleToKelvin();
                convertAllFromKelvin();
                //reset views with the new values
                mKelvinEditText.setText(formatUnit(mKelvin));
                mCelsiusEditText.setText(formatUnit(mCelsius));
                mFahrenheitEditText.setText(formatUnit(mFahrenheit));
                mRankineEditText.setText(formatUnit(mRankine));
                mNewtonEditText.setText(formatUnit(mNewton));
                mReaumurEditText.setText(formatUnit(mReaumur));
                mRomerEditText.setText(formatUnit(mRomer));
            }
        }
    };

    private TextWatcher newtonTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String editTextString = mNewtonEditText.getText().toString();
            //don't parse if the string is empty or a negative sign
            if (editTextString.isEmpty()) {
                mNewton = 0;
            } else if (!editTextString.equals("-")&&!editTextString.equals(".")&&!editTextString.equals("-.")) {
                mNewton = Double.valueOf(editTextString);
            }
            mNewtonEditText.setSelection(mNewtonEditText.getText().length());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //don't do anything if the editText is not in focus
            if (getCurrentFocus() == mNewtonEditText) {
                //convert to Kelvin and convert to all other units from Kelvin
                newtonToKelvin();
                convertAllFromKelvin();
                //reset views with the new values
                mKelvinEditText.setText(formatUnit(mKelvin));
                mCelsiusEditText.setText(formatUnit(mCelsius));
                mFahrenheitEditText.setText(formatUnit(mFahrenheit));
                mRankineEditText.setText(formatUnit(mRankine));
                mDelisleEditText.setText(formatUnit(mDelisle));
                mReaumurEditText.setText(formatUnit(mReaumur));
                mRomerEditText.setText(formatUnit(mRomer));
            }
        }
    };

    private TextWatcher reaumurTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String editTextString = mReaumurEditText.getText().toString();
            //don't parse if the string is empty or a negative sign
            if (editTextString.isEmpty()) {
                mReaumur = 0;
            } else if (!editTextString.equals("-")&&!editTextString.equals(".")&&!editTextString.equals("-.")) {
                mReaumur = Double.valueOf(editTextString);
            }
            mReaumurEditText.setSelection(mReaumurEditText.getText().length());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //don't do anything if the editText is not in focus
            if (getCurrentFocus() == mReaumurEditText) {
                //convert to Kelvin and convert to all other units from Kelvin
                reaumurToKelvin();
                convertAllFromKelvin();
                //reset views with the new values
                mKelvinEditText.setText(formatUnit(mKelvin));
                mCelsiusEditText.setText(formatUnit(mCelsius));
                mFahrenheitEditText.setText(formatUnit(mFahrenheit));
                mRankineEditText.setText(formatUnit(mRankine));
                mDelisleEditText.setText(formatUnit(mDelisle));
                mNewtonEditText.setText(formatUnit(mNewton));
                mRomerEditText.setText(formatUnit(mRomer));
            }
        }
    };

    private TextWatcher romerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String editTextString = mRomerEditText.getText().toString();
            //don't parse if the string is empty or a negative sign
            if (editTextString.isEmpty()) {
                mRomer = 0;
            } else if (!editTextString.equals("-")&&!editTextString.equals(".")&&!editTextString.equals("-.")) {
                mRomer = Double.valueOf(editTextString);
            }
            mRomerEditText.setSelection(mRomerEditText.getText().length());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //don't do anything if the editText is not in focus
            if (getCurrentFocus() == mRomerEditText) {
                //convert to Kelvin and convert to all other units from Kelvin
                romerToKelvin();
                convertAllFromKelvin();
                //reset views with the new values
                mKelvinEditText.setText(formatUnit(mKelvin));
                mCelsiusEditText.setText(formatUnit(mCelsius));
                mFahrenheitEditText.setText(formatUnit(mFahrenheit));
                mRankineEditText.setText(formatUnit(mRankine));
                mDelisleEditText.setText(formatUnit(mDelisle));
                mNewtonEditText.setText(formatUnit(mNewton));
                mReaumurEditText.setText(formatUnit(mReaumur));
            }
        }
    };

    //return the unit as a string formatted as a decimal with two significant digits
    private String formatUnit(double unit) {
        NumberFormat formatter = new DecimalFormat(mDecimalPattern);
        return String.valueOf(formatter.format(unit));
    }

    private void convertAllFromKelvin() {
        if (mKelvin < 0){
            Toast.makeText(getBaseContext(), R.string.negative_kelvin_error_toast,
                    Toast.LENGTH_SHORT).show();
            mKelvin = 0;
            convertAllFromKelvin();
            return;
        }

        mCelsius = mKelvin - 273.15;
        mFahrenheit = (mKelvin * 9 / 5.0) - 459.67;
        mRankine = mKelvin * 9/5.0;
        mDelisle = (373.15 - mKelvin) * 3/2.0;
        mNewton = (mKelvin - 273.15)*33/100.0;
        mReaumur = (mKelvin - 273.15) * 4/5.0;
        mRomer = (mKelvin-273.15) * 21/40.0 +7.5;
    }

    private void celsiusToKelvin() {
        mKelvin = mCelsius + 273.15;
    }

    private void fahrenheitToKelvin() {
        mKelvin = (mFahrenheit + 459.67) * 5 / 9.0;
    }

    private void rankineToKelvin() {
        mKelvin = (mRankine)*5/9.0;
    }

    private void delisleToKelvin() {
        mKelvin = 373.15 - (mDelisle * 2/3.0);
    }

    private void newtonToKelvin() {
        mKelvin = (mNewton *100/33.0) +273.15;
    }

    private void reaumurToKelvin() {
        mKelvin = (mReaumur*5/4.0) + 273.15;
    }

    private void romerToKelvin() {
        mKelvin = (mRomer -7.5)*40/21.0 +273.15;
    }
}

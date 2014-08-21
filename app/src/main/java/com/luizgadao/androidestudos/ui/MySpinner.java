package com.luizgadao.androidestudos.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.luizgadao.androidestudos.R;

public class MySpinner extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spinner);

        final int imgs[] = { R.drawable.ferrari_laferrari, R.drawable.bugatti_veyron, R.drawable.lamborghini_veneno,
                R.drawable.maserati_gran_turismo, R.drawable.mclaren, R.drawable.pagani_zonda,
                R.drawable.porsche_911};

        final ImageView imageView = (ImageView) findViewById( R.id.imageview1 );

        String[] carros = { "La Ferrari", "Bugatti Veyron", "Lamborghini Veneno", "Maserati GT", "McLaren", "Pagani Zonda", "Porche 911" };
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_dropdown_item, carros );
        //adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        //Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        //spinner.setAdapter( adapter );

        //spinner.getSelectedItem();
        //spinner.getSelectedItemId();
        //spinner.getSelectedItemPosition();

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource( this, R.array.carros, android.R.layout.simple_spinner_dropdown_item );
        final Spinner spinner = new Spinner( this );
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT );
        spinner.setLayoutParams( layoutParams );
        spinner.setAdapter( arrayAdapter );
        //spinner.setAdapter( new AdapterSpinner( getBaseContext(), carros ) );


        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageView.setImageResource( imgs[ position ] );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        LinearLayout parent = (LinearLayout) findViewById( R.id.linearlayout_spinner );
        parent.addView( spinner, 1 );

        Button button = new Button( this );
        //layoutParams.width = 200;
        button.setLayoutParams( layoutParams );
        button.setText( "INFO" );

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = (String) spinner.getSelectedItem();
                long itemId = (long) spinner.getSelectedItemId();
                int itemPosition = spinner.getSelectedItemPosition();

                Toast.makeText( getBaseContext(), String.format("name: %s item id: %d position %d", name, itemId, itemPosition), Toast.LENGTH_LONG ).show();
            }
        });

        parent.addView( button );

    }

}

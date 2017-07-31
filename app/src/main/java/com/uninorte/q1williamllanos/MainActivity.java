package com.uninorte.q1williamllanos;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    TextView salida;
    EditText[] notas = new EditText[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salida = (TextView) findViewById(R.id.tvSalida);
        notas[0] = (EditText) findViewById(R.id.etNota1);
        notas[1] = (EditText) findViewById(R.id.etNota2);
        notas[2] = (EditText) findViewById(R.id.etNota3);
        notas[3] = (EditText) findViewById(R.id.etNota4);
    }

    /**
     *
     * @param view
     */
    public void onCalc(View view) {
        boolean flag = false;
        boolean[] farray = new boolean[notas.length];
        float[] ns = new float[notas.length];

        for (int i = 0; i < notas.length; i++) {
            if (TextUtils.isEmpty(notas[i].getText().toString())) {
                farray[i] = true;
                flag |= true;
                ns[i] = 0;
            } else {
                ns[i] = Float.parseFloat(notas[i].getText().toString());
                flag |= false;
            }
            notas[i].setTextColor(Color.BLACK);
        }

        if (!flag) {
            float def = 0;
            for (int i = 0; i < ns.length; i++) {
                def += ns[i] * 0.25f;
            }
            salida.setText( String.valueOf(def) );
        } else {
            float def = 0;
            int count = ns.length;

            for (int i = 0; i < ns.length; i++) {
                if (!farray[i]) {
                    def += ns[i] * 0.25f;
                    count--;
                }
            }

            //Calcular el valor requerido en las otras notas para pasar
            float r = 3.8f - def;
            r = (r/0.25f)/count;

            for (int i = 0; i < ns.length; i++) {
                if (farray[i]) {
                    def += r * 0.25f;
                    notas[i].setText( String.valueOf(r) );
                    notas[i].setTextColor(Color.RED);
                }
            }

            salida.setText( String.valueOf(def) );
        }

    }
}

package com.example.rodrigo.neyntegrator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    TextView txtFuncao;
    TextView txtA;
    TextView txtB;
    TextView txtResultado;

    Integrador integrador = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtFuncao = (TextView) findViewById(R.id.txtFuncao);
        txtA = (TextView) findViewById(R.id.txtA);
        txtB = (TextView) findViewById(R.id.txtB);
        txtResultado = (TextView) findViewById(R.id.txtResultado);

    }

    public void btnIntegrarClick(View view) {

        Log.d("tecnichal","Click de Integrar");


        if (validarImputs()) {

        Integrador integrador = new Integrador();
            integrador.setA(Float.parseFloat(txtA.getText().toString()));
            integrador.setB(Float.parseFloat(txtB.getText().toString()));
            integrador.setFuncao(txtFuncao.getText().toString());

            Float resultado = null;
            try {

                resultado = new Float(integrador.integrar());
                Log.d("result",resultado+"");
                txtResultado.setText(String.format("%.4f", resultado));
                this.integrador = integrador;

            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Your parameters are not ok... Check them before you try again :)");


            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    }
            );

            // Create the AlertDialog object and show it
            builder.create().show();
        }


    }

    // Valida função e limites de integração
    private boolean validarImputs() {

        try{

            Float.parseFloat(txtA.getText().toString());
            Float.parseFloat(txtB.getText().toString());

            String f = txtFuncao.getText().toString();

            Expression e = new ExpressionBuilder(f)
                    .variables("x")
                    .build()
                    .setVariable("x",10);

            e.evaluate();

            return  true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void lblResultadoClick(View view){


        if(!txtResultado.getText().toString().equals("---")){
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(integrador.getDetalhes());


            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    }
            );

            // Create the AlertDialog object and show it
            builder.create().show();
        }

    }



}
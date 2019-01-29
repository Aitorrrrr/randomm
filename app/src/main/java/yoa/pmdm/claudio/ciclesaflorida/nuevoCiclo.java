package yoa.pmdm.claudio.ciclesaflorida;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.w3c.dom.Text;

public class nuevoCiclo extends AppCompatActivity {

    private EditText titulo;
    private EditText descrip;
    private Spinner tipo;
    private Spinner familia;

    private Button crear;
    private Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_ciclo);

        titulo = (EditText) findViewById(R.id.nuevo_titulo);
        descrip = (EditText) findViewById(R.id.nuevo_descrip);

        tipo = (Spinner) findViewById(R.id.nuevo_tipo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(adapter);

        familia = (Spinner) findViewById(R.id.nuevo_familia);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.familia, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        familia.setAdapter(adapter2);

        crear = (Button) findViewById(R.id.nuevo_crear);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comprobarCampos())
                {
                    Ciclo c = new Ciclo(familia.getSelectedItem().toString(), tipo.getSelectedItem().toString(), titulo.getText().toString(), descrip.getText().toString());
                    Log.d("MIO",familia.getSelectedItem().toString()+tipo.getSelectedItem().toString()+titulo.getText().toString()+descrip.getText().toString());
                    Log.d("MIO", c.getDescripcio()+c.getTitol()+c.getFamiliaProfessional()+c.getTipus());

                    Intent i = new Intent();
                    Bundle parametros = new Bundle();
                    parametros.putParcelable("ciclo", c);
                    i.putExtras(parametros);

                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });

        cancelar = (Button) findViewById(R.id.nuevo_cancelar);
        cancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private boolean comprobarCampos()
    {
        if (tipo.getSelectedItem().toString()=="" || familia.getSelectedItem().toString()=="" || descrip.getText().toString()=="" || titulo.getText().toString()=="")
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}

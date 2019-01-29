package yoa.pmdm.claudio.ciclesaflorida;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerTipoCiclo extends RecyclerView.Adapter<RecyclerTipoCiclo.TiposViewHolder> {

    private ArrayList<String> tipos;
    private interfazRCTipos interfaz;

    public static class TiposViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements AdapterView.OnClickListener {

        public TextView nombre;

        public Context context;
        public View v;

        public TiposViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre=(TextView)itemView.findViewById(R.id.rc_tipo_nombre);

            v = itemView;
            context = itemView.getContext();
        }

        @Override
        public void onClick(View v) {

        }
    }

    public RecyclerTipoCiclo(ArrayList<String> array, TipusCicle context)
    {
        this.tipos = array;
        try{
            interfaz = (interfazRCTipos) context;
        }catch(ClassCastException ex){
            Log.d("MIO","interfaz mal"+ex.getMessage());
            //.. should log the error or throw and exception
        }
    }



    public void recibirTitulacion(String tit)
    {
        tipos.add(tit);
        notifyItemInserted(tipos.size()-1);
    }

    @NonNull
    @Override
    public TiposViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycler_tipo_ciclo,viewGroup, false);

        return new TiposViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TiposViewHolder tiposViewHolder, int i) {
        final int aux = i;

        tiposViewHolder.nombre.setText(tipos.get(i));
        tiposViewHolder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipos.get(aux)=="Mitjà")
                {
                    interfaz.titPulsada(1);
                }
                else
                {
                    interfaz.titPulsada(2);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tipos.size();
    }

    public interface interfazRCTipos{

        void titPulsada(int id);
    }
}

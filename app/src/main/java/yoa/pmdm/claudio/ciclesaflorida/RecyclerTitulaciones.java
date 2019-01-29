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
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerTitulaciones extends RecyclerView.Adapter<RecyclerTitulaciones.TitulacionesViewHolder> {

    private ArrayList<String> titulaciones;
    private interfazRCTitulaciones interfaz;

    public static class TitulacionesViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements AdapterView.OnClickListener {

        public TextView nombre;

        public Context context;
        public View v;

        public TitulacionesViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre=(TextView)itemView.findViewById(R.id.rc_tit_nombre);

            v = itemView;
            context = itemView.getContext();
        }

        @Override
        public void onClick(View v) {

        }
    }

    public RecyclerTitulaciones(ArrayList<String> array, Titulaciones context)
    {
        this.titulaciones = array;
        try{
            interfaz = (interfazRCTitulaciones) context;
        }catch(ClassCastException ex){
            Log.d("MIO","interfaz mal"+ex.getMessage());
            //.. should log the error or throw and exception
        }
    }

    public void recibirTitulacion(String tit)
    {
        titulaciones.add(tit);
        notifyItemInserted(titulaciones.size()-1);
    }

    @NonNull
    @Override
    public TitulacionesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycler_titulaciones,viewGroup, false);

        return new TitulacionesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TitulacionesViewHolder titulacionesViewHolder, int i) {
        final int aux = i;

        titulacionesViewHolder.nombre.setText(titulaciones.get(i));
        titulacionesViewHolder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MIO","posicion sin +1 "+aux);
                interfaz.titPulsada(aux+1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titulaciones.size();
    }

    public interface interfazRCTitulaciones{

        void titPulsada(int id);
    }
}

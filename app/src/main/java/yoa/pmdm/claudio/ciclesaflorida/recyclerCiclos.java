package yoa.pmdm.claudio.ciclesaflorida;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class recyclerCiclos extends RecyclerView.Adapter<recyclerCiclos.CicleViewHolder> {

    private ArrayList<Ciclo> arrayCiclos;
    private interfazAdaptador interfaz;

    public static class CicleViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements AdapterView.OnClickListener {

        public TextView familia;
        public TextView tipo;
        public TextView titulo;
        public ImageButton delete;
        public ImageButton add;

        public Context context;
        public View v;

        public CicleViewHolder(@NonNull View itemView) {
            super(itemView);

            familia=(TextView)itemView.findViewById(R.id.rc_familia);
            tipo=(TextView)itemView.findViewById(R.id.rc_tipo);
            titulo=(TextView)itemView.findViewById(R.id.rc_titol);
            delete = (ImageButton) itemView.findViewById(R.id.rc_delete);
            add = (ImageButton) itemView.findViewById(R.id.rc_add);

            v = itemView;
            context = itemView.getContext();
        }

        @Override
        public void onClick(View v) {

        }
    }

    public recyclerCiclos(ArrayList<Ciclo> array, Llistat context)
    {
        this.arrayCiclos = array;
        try{
            interfaz = (interfazAdaptador) context;
        }catch(ClassCastException ex){
            Log.d("MIO","interfaz mal"+ex.getMessage());
            //.. should log the error or throw and exception
        }
    }

    @NonNull
    @Override
    public CicleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycler_ciclos,viewGroup, false);

        return new CicleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CicleViewHolder cicleViewHolder, int i) {
        final int aux = i;

        cicleViewHolder.familia.setText(arrayCiclos.get(i).getFamiliaProfessional());
        cicleViewHolder.titulo.setText(arrayCiclos.get(i).getTitol());
        cicleViewHolder.tipo.setText(arrayCiclos.get(i).getTipus());

        cicleViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayCiclos.remove(aux);
                notifyItemRemoved(aux);
                notifyItemRangeChanged(aux, getItemCount());
            }
        });

        cicleViewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaz.anyadir();
            }
        });
    }

    public void recibirCiclo(Ciclo c)
    {
        arrayCiclos.add(c);
        notifyItemInserted(arrayCiclos.size()-1);
    }

    @Override
    public int getItemCount() {
        return arrayCiclos.size();
    }

    public interface interfazAdaptador{

        void anyadir();
    }
}

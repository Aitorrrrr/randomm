package yoa.pmdm.claudio.ciclesaflorida;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class Llistat extends Fragment implements recyclerCiclos.interfazAdaptador{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    final String TAG="MIO";
    final int resultado=1;

    private RecyclerView rc;
    private recyclerCiclos adaptadorRecycler;
    private RecyclerView.LayoutManager rvLM;

    // TODO: Rename and change types of parameters
    private int mParam1;
    private ArrayList<Ciclo> mParam2;
    private ArrayList<Ciclo> arrayFiltrado;

    private interfazListado mListener;
    private Context contextMain;

    public Llistat() {
        // Required empty public constructor
    }

    public static Llistat newInstance(int param1, ArrayList<Ciclo>param2) {
        Llistat fragment = new Llistat();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putParcelableArrayList(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getParcelableArrayList(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_llistat, container, false);
        contextMain = v.getContext();
        arrayFiltrado = new ArrayList<Ciclo>();
        Leer();

        rc = (RecyclerView)v.findViewById(R.id.fragment_reciclerView);
        rvLM = new LinearLayoutManager(v.getContext());
        rc.setLayoutManager(rvLM);

        adaptadorRecycler = new recyclerCiclos(arrayFiltrado, this);
        rc.setAdapter(adaptadorRecycler);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof interfazListado) {
            mListener = (interfazListado) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void Leer(){
        Log.d("MIO", "Antes For");

        if (mParam2!=null)
        {
            for(int i=0;i<mParam2.size();i++) {
                if (mParam1==1 && mParam2.get(i).getTipus()=="Mitjà")
                {
                    arrayFiltrado.add(mParam2.get(i));
                }
                else if(mParam1==2 && mParam2.get(i).getTipus()=="Superior")
                {
                    arrayFiltrado.add(mParam2.get(i));
                }
            }
        }
        else
        {
            Log.d("MIO","Era null");
        }
    }

    @Override
    public void anyadir() {
        Intent i = new Intent(contextMain, nuevoCiclo.class);

        startActivityForResult(i, resultado);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "Ciclo ok?"+resultCode);
        switch(resultCode){
            case RESULT_OK:
                if(requestCode == resultado){
                    Bundle bundleAux =  data.getExtras();

                    Ciclo nuevoCiclo = bundleAux.getParcelable("ciclo");
                    Log.d(TAG, "Ciclo ok?"+nuevoCiclo.getTitol());

                    adaptadorRecycler.recibirCiclo(nuevoCiclo);
                }

                break;

            case RESULT_CANCELED:
                Log.d(TAG, "Cancelado");
        }
    }

    public interface interfazListado {
        // TODO: Update argument type and name
        void anyadirAlListado();
    }
}

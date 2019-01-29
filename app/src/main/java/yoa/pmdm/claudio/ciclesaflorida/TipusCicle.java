package yoa.pmdm.claudio.ciclesaflorida;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class TipusCicle extends Fragment implements View.OnClickListener {
    final String TAG="MIO";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int id;
    private ArrayList<Ciclo> arrayCiclos;
    private ArrayList<Ciclo> arrayAux;

    private boolean mijtaEnabled;
    private boolean supEnabled;

    private Button mitja;
    private  Button superior;


    private comunicaTipoCiclo mListener;

    public TipusCicle() {
        // Required empty public constructor
    }

    public static TipusCicle newInstance(int id, ArrayList<Ciclo> array) {
        TipusCicle fragment = new TipusCicle();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, id);
        args.putParcelableArrayList(ARG_PARAM2, array);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_PARAM1);
            arrayCiclos = getArguments().getParcelableArrayList(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_tipus_cicle, container, false);
        mitja= v.findViewById(R.id.btnMitja);
        superior= v.findViewById(R.id.btnSup);
        arrayAux = new ArrayList<Ciclo>();
        mijtaEnabled = false;
        supEnabled = false;

        recorrerArray();

        mitja.setOnClickListener(this);
        superior.setOnClickListener(this);

        return v;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof comunicaTipoCiclo) {
            mListener = (comunicaTipoCiclo) context;
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

    @Override
    public void onClick(View v) {
        if (v.getId()==mitja.getId())
        {
            mListener.tipoCicloPulsado(1, arrayAux);
        }
        else if(v.getId()==superior.getId())
        {
            mListener.tipoCicloPulsado(2, arrayAux);
        }
    }

    public void recorrerArray()
    {
        Log.d(TAG, ""+id);
        switch (id){
            case 1:
                for (Ciclo aux: arrayCiclos)
                {
                    if (aux.getFamiliaProfessional() == "EMPRESA")
                    {
                        Log.d(TAG, aux.getFamiliaProfessional());
                        if (aux.getTipus()=="Mitjà")
                        {
                            Log.d(TAG, "Mitja");
                            mijtaEnabled = true;
                        }
                        if (aux.getTipus()=="Superior")
                        {
                            Log.d(TAG, "superior");
                            supEnabled = true;
                        }
                        arrayAux.add(aux);
                    }
                }
                break;

            case 2:
                for (Ciclo aux: arrayCiclos)
                {
                    if (aux.getFamiliaProfessional() == "ESPORT")
                    {
                        if (aux.getTipus()=="Mitjà")
                        {
                            mijtaEnabled = true;
                        }
                        if (aux.getTipus()=="Superior")
                        {
                            supEnabled = true;
                        }
                        arrayAux.add(aux);
                    }
                }
                break;
            case 3:
                for (Ciclo aux: arrayCiclos)
                {
                    if (aux.getFamiliaProfessional() == "INFORMÀTICA")
                    {
                        if (aux.getTipus()=="Mitjà")
                        {
                            mijtaEnabled = true;
                        }
                        if (aux.getTipus()=="Superior")
                        {
                            supEnabled = true;
                        }
                        arrayAux.add(aux);
                    }
                }
                break;
        }

        if (!mijtaEnabled)
        {
            mitja.setEnabled(false);
        }
        if (!supEnabled)
        {
            superior.setEnabled(false);
        }
    }

    public interface comunicaTipoCiclo {
        // TODO: Update argument type and name
        void tipoCicloPulsado(int id, ArrayList<Ciclo> arrayAux);
    }
}

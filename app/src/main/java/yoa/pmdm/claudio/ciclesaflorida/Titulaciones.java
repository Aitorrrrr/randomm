package yoa.pmdm.claudio.ciclesaflorida;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class Titulaciones extends Fragment implements RecyclerTitulaciones.interfazRCTitulaciones{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rc;
    private RecyclerTitulaciones adaptadorRecycler;
    private RecyclerView.LayoutManager rvLM;

    private comunicaTitulacion mListener;

    public Titulaciones() {

    }

    public static Titulaciones newInstance(String param1, String param2) {
        Titulaciones fragment = new Titulaciones();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_titulaciones, container, false);

        rc = (RecyclerView)v.findViewById(R.id.recycler_Titulaciones);
        rvLM = new LinearLayoutManager(v.getContext());
        rc.setLayoutManager(rvLM);

        ArrayList<String> titBase= new ArrayList<String>();
        titBase.add("EMPRESA");
        titBase.add("ESPORTS");
        titBase.add("INFORMÁTICA");
        adaptadorRecycler = new RecyclerTitulaciones(titBase, this);

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof comunicaTitulacion) {
            mListener = (comunicaTitulacion) context;
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
    public void titPulsada(int id) {
        mListener.titulacionPulsada(id);
    }


    public interface comunicaTitulacion {
        // TODO: Update argument type and name
        void titulacionPulsada(int id);
    }
}

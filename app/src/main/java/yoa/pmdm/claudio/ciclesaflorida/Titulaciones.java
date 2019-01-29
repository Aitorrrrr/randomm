package yoa.pmdm.claudio.ciclesaflorida;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class Titulaciones extends Fragment implements RecyclerTitulaciones.interfazRCTitulaciones {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context contextMain;
    private String nuevaTit;

    private RecyclerView rc;
    private RecyclerTitulaciones adaptadorRecycler;
    private RecyclerView.LayoutManager rvLM;

    private Button anyadir;

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
        contextMain = v.getContext();
        anyadir = (Button) v.findViewById(R.id.btn_anyadir_Titulaciones);
        anyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MIO", "hola hehehe");
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(contextMain);
                    alertBuilder.setTitle("Nueva titulación");

                    //Creamos un edittext y lo introducimos en el AlertDialog
                    final EditText input = new EditText(contextMain);
                    input.setHint("Titulación");
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    alertBuilder.setView(input);

                    // Botón guardar
                    alertBuilder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            nuevaTit = input.getText().toString();
                            Log.d("MIO", "Nueva titulacion lenght "+nuevaTit.length());

                            if (comprobarTitulacion(nuevaTit))
                            {
                                adaptadorRecycler.recibirTitulacion(nuevaTit);
                            }

                            dialog.dismiss();
                        }
                    });
                    // Botón cancelar
                    alertBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alertBuilder.show();
            }
        });

        rc = (RecyclerView)v.findViewById(R.id.recycler_Titulaciones);
        rvLM = new LinearLayoutManager(v.getContext());
        rc.setLayoutManager(rvLM);

        ArrayList<String> titBase= new ArrayList<String>();
        titBase.add("EMPRESA");
        titBase.add("ESPORTS");
        titBase.add("INFORMÀTICA");
        adaptadorRecycler = new RecyclerTitulaciones(titBase, this);
        rc.setAdapter(adaptadorRecycler);

        return v;
    }

    public boolean comprobarTitulacion(String nuevaTit)
    {
        if (nuevaTit.trim().length()==0)
        {
            return false;
        }
        else
        {
            return true;
        }
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

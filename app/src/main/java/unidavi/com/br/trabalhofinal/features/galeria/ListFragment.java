package unidavi.com.br.trabalhofinal.features.galeria;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import unidavi.com.br.trabalhofinal.R;
import unidavi.com.br.trabalhofinal.model.Foto;

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private FotoAdapter adapter;

    public ListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

            recyclerView = view.findViewById(R.id.recycler_fotos);
            recyclerView.setLayoutManager(
                    new LinearLayoutManager(getActivity()));
            recyclerView.setHasFixedSize(true);

            adapter = new FotoAdapter(new ArrayList<Foto>(), getActivity());

            recyclerView.setAdapter(adapter);
            return view;
    }
}

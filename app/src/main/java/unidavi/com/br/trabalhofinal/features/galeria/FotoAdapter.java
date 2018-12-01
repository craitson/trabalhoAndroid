package unidavi.com.br.trabalhofinal.features.galeria;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import unidavi.com.br.trabalhofinal.R;
import unidavi.com.br.trabalhofinal.model.Foto;

public class FotoAdapter extends RecyclerView.Adapter<FotoViewHolder> {

    List<Foto> fotoList;
    Context context;

    public FotoAdapter(List<Foto> fotoList, Context context) {
        this.fotoList = fotoList;
        this.context = context;
    }

    @NonNull
    @Override
    public FotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_foto,
                        parent,
                        false);

        FotoViewHolder myViewHolder = new FotoViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FotoViewHolder holder, int position) {
        final Foto myFoto = fotoList.get(position);

        holder.labelUf.setText(myFoto.getUf());
        holder.labelCidade.setText(myFoto.getCidade());
        holder.labelBairro.setText(myFoto.getBairro());

        Picasso.with(context)
                .load(myFoto.getCaminho())
                .into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return fotoList.size();
    }
}

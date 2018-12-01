package unidavi.com.br.trabalhofinal.features.galeria;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FotoViewHolder extends RecyclerView.ViewHolder {
    ImageView foto;
    TextView labelUf;
    TextView labelCidade;
    TextView labelBairro;

    public FotoViewHolder(View itemView) {
        super(itemView);
    }
}

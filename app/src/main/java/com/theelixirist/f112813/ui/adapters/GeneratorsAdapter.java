package com.theelixirist.f112813.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theelixirist.f112813.R;
import com.theelixirist.f112813.game.Chronicle;
import com.theelixirist.f112813.game.GameState;
import com.theelixirist.f112813.game.definitions.DefinitionRegistry;
import com.theelixirist.f112813.game.definitions.GeneratorDefinition;
import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.game.math.BigDoubleFormatter;
import com.theelixirist.f112813.game.runtime.Generator;
import com.theelixirist.f112813.game.runtime.Requirement;
import com.theelixirist.f112813.game.utils.ResourceResolver;

import java.util.ArrayList;
import java.util.List;

public class GeneratorsAdapter extends RecyclerView.Adapter<GeneratorsAdapter.ViewHolder> {

    private final List<GeneratorDefinition> definitions;
    private final GameState gameState;
    private final Chronicle chronicle;

    public GeneratorsAdapter(DefinitionRegistry registry, GameState gameState, Chronicle chronicle) {
        this.definitions = new ArrayList<>(registry.getGeneratorDefinitions().values());
        this.gameState = gameState;
        this.chronicle = chronicle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_market, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GeneratorDefinition def = definitions.get(position);

        boolean visible = true;
        for (Requirement r : def.requirements) {
            if (r.getGate() == Requirement.Gate.VISIBILITY && !r.isMet(gameState, chronicle)) {
                visible = false;
                break;
            }
        }

        holder.itemView.setVisibility(visible ? View.VISIBLE : View.GONE);
        holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(
                visible ? RecyclerView.LayoutParams.MATCH_PARENT : 0,
                visible ? RecyclerView.LayoutParams.WRAP_CONTENT : 0
        ));

        if (!visible) return;

        holder.ivIcon.setImageResource(ResourceResolver.drawable(def.icon));
        holder.tvName.setText(def.name);
        holder.tvDescription.setText(def.desc);
        holder.tvPrice.setText(BigDoubleFormatter.format(new BigDouble(def.baseCost)));

        Generator owned = gameState.getGenerator(def.id);
        holder.tvOwned.setText(owned != null ? "x" + owned.getCurrentCount() : "x0");

        holder.itemView.setOnClickListener(v -> {
            boolean canBuy = true;
            for (Requirement r : def.requirements) {
                if (r.getGate() == Requirement.Gate.PURCHASABILITY && !r.isMet(gameState, chronicle)) {
                    canBuy = false;
                    break;
                }
            }
            if (!canBuy) return;
            if (!gameState.spendElixirs(new BigDouble(def.baseCost))) return;

            Generator g = gameState.getGenerator(def.id);
            if (g == null) {
                gameState.putGenerator(new Generator(def.id, 1));
            } else {
                g.setCurrentCount(g.getCurrentCount() + 1);
            }
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return definitions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvName, tvDescription, tvOwned, tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.item_market_iv_icon);
            tvName = itemView.findViewById(R.id.item_market_tv_name);
            tvDescription = itemView.findViewById(R.id.item_market_tv_description);
            tvOwned = itemView.findViewById(R.id.item_market_tv_owned);
            tvPrice = itemView.findViewById(R.id.item_market_tv_price);
        }
    }
}

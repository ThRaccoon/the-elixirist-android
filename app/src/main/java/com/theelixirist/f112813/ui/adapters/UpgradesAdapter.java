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
import com.theelixirist.f112813.game.definitions.UpgradeDefinition;
import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.game.math.BigDoubleFormatter;
import com.theelixirist.f112813.game.runtime.Requirement;
import com.theelixirist.f112813.game.runtime.Upgrade;
import com.theelixirist.f112813.game.utils.ResourceResolver;

import java.util.ArrayList;
import java.util.List;

public class UpgradesAdapter extends RecyclerView.Adapter<UpgradesAdapter.ViewHolder> {

    private final List<UpgradeDefinition> definitions;
    private final GameState gameState;
    private final Chronicle chronicle;

    public UpgradesAdapter(DefinitionRegistry registry, GameState gameState, Chronicle chronicle) {
        this.definitions = new ArrayList<>(registry.getUpgradeDefinitions().values());
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
        UpgradeDefinition def = definitions.get(position);

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
        holder.tvPrice.setText(BigDoubleFormatter.format(new BigDouble(def.cost)));

        boolean alreadyOwned = gameState.getUpgrade(def.id) != null;
        holder.tvOwned.setText(alreadyOwned ? "✓" : "");

        if (alreadyOwned) {
            holder.itemView.setAlpha(0.5f);
            return;
        }

        holder.itemView.setOnClickListener(v -> {
            boolean canBuy = true;
            for (Requirement r : def.requirements) {
                if (r.getGate() == Requirement.Gate.PURCHASABILITY && !r.isMet(gameState, chronicle)) {
                    canBuy = false;
                    break;
                }
            }
            if (!canBuy) return;
            if (!gameState.spendElixirs(new BigDouble(def.cost))) return;

            gameState.putUpgrade(new Upgrade(def.id));
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

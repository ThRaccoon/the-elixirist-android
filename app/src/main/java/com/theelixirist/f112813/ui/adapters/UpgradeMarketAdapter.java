package com.theelixirist.f112813.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theelixirist.f112813.AppContainer;
import com.theelixirist.f112813.R;
import com.theelixirist.f112813.definitions.models.UpgradeDefinition;
import com.theelixirist.f112813.domain.RequirementFilter;
import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.game.math.BigDoubleFormatter;
import com.theelixirist.f112813.ui.utils.ResourceResolver;

import java.util.ArrayList;
import java.util.List;

public class UpgradeMarketAdapter extends RecyclerView.Adapter<UpgradeMarketAdapter.ViewHolder> {
    public interface OnBuyClickListener {
        void onBuy(UpgradeDefinition def);
    }

    private List<UpgradeDefinition> items = new ArrayList<>();
    private final AppContainer appContainer;
    private final OnBuyClickListener listener;

    public UpgradeMarketAdapter(AppContainer appContainer, OnBuyClickListener listener) {
        this.appContainer = appContainer;
        this.listener = listener;
    }

    public void setItems(List<UpgradeDefinition> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_market, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UpgradeDefinition def = items.get(position);

        boolean alreadyOwned = appContainer.getUpgradeRegistry().getUpgrades().containsKey(def.id);

        BigDouble cost = new BigDouble(def.cost);

        boolean purchasable = !alreadyOwned
                && RequirementFilter.isPurchasable(def, def.requirements, appContainer)
                && appContainer.getChronicle().getCurrentElixirs().compareTo(cost) >= 0;

        holder.tvName.setText(def.name);
        holder.tvDesc.setText(def.desc);
        holder.tvPrice.setText(BigDoubleFormatter.format(cost));

        // Upgrades are one-time — show owned status instead of count
        holder.tvOwned.setText(alreadyOwned ? "✓" : "");

        float alpha = (purchasable || alreadyOwned) ? 1.0f : 0.4f;
        holder.itemView.setAlpha(alpha);

        holder.itemView.setOnClickListener(v -> {
            if (purchasable) listener.onBuy(def);
        });

        holder.ivIcon.setImageResource(ResourceResolver.drawable(def.icon));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvName, tvDesc, tvOwned, tvPrice;

        ViewHolder(View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.item_market_iv_icon);
            tvName = itemView.findViewById(R.id.item_market_tv_name);
            tvDesc = itemView.findViewById(R.id.item_market_tv_description);
            tvOwned = itemView.findViewById(R.id.item_market_tv_owned);
            tvPrice = itemView.findViewById(R.id.item_market_tv_price);
        }
    }
}

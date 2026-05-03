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
import com.theelixirist.f112813.definitions.models.GeneratorDefinition;
import com.theelixirist.f112813.domain.RequirementFilter;
import com.theelixirist.f112813.domain.models.Generator;
import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.game.math.BigDoubleFormatter;
import com.theelixirist.f112813.ui.utils.ResourceResolver;

import java.util.ArrayList;
import java.util.List;

public class GeneratorMarketAdapter extends RecyclerView.Adapter<GeneratorMarketAdapter.ViewHolder> {
    public interface OnBuyClickListener {
        void onBuy(GeneratorDefinition def);
    }

    private List<GeneratorDefinition> items = new ArrayList<>();
    private final AppContainer appContainer;
    private final OnBuyClickListener listener;

    public GeneratorMarketAdapter(AppContainer appContainer, OnBuyClickListener listener) {
        this.appContainer = appContainer;
        this.listener = listener;
    }

    public void setItems(List<GeneratorDefinition> items) {
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
        GeneratorDefinition def = items.get(position);
        AppContainer container = appContainer;

        Generator existing = container.getGeneratorRegistry().getGenerator(def.id);
        int owned = existing != null ? existing.getCurrentCount() : 0;

        BigDouble cost = container.getPurchaseSystem().calculateGeneratorCost(def);

        boolean purchasable = RequirementFilter.isPurchasable(def, def.requirements, container)
                && container.getChronicle().getCurrentElixirs().compareTo(cost) >= 0;

        holder.tvName.setText(def.name);
        holder.tvDesc.setText(def.desc);
        holder.tvOwned.setText("x" + owned);
        holder.tvPrice.setText(BigDoubleFormatter.format(cost));

        float alpha = purchasable ? 1.0f : 0.4f;
        holder.itemView.setAlpha(alpha);

        holder.itemView.setOnClickListener(v -> {
            if (purchasable) listener.onBuy(def);
        });

        holder.ivIcon.setImageResource(ResourceResolver.drawable(def.icon));
    }

    @Override
    public int getItemCount() { return items.size(); }

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

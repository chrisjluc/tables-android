package tables.android.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tables.android.R;
import tables.android.models.CustomizationCategory;
import tables.android.models.CustomizationOption;
import tables.android.models.RestaurantMenuItem;
import tables.android.models.RestaurantMenuItemOrder;
import tables.android.ui.restaurant.MenuItemActivity;

public class CustomizationOptionsAdapter extends RecyclerView.Adapter<CustomizationOptionsAdapter.ViewHolder> {
    private final static String TAG = "CustomizationOptionsAdapter";
    private RestaurantMenuItemOrder mOrder;
    private RestaurantMenuItem mMenuItem;
    private MenuItemActivity mActivity;
    private CustomizationOptionsAdapter self;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;

        public ViewHolder(CardView v) {
            super(v);
            mCardView = v;
        }
    }

    public CustomizationOptionsAdapter(RestaurantMenuItemOrder order, Activity activity) {
        mOrder = order;
        mMenuItem = mOrder.getMenuItem();
        mActivity = (MenuItemActivity) activity;
        self = this;
    }

    @Override
    public CustomizationOptionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_customization_option, parent, false);
        ViewHolder vh = new ViewHolder((CardView) v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CustomizationCategory category = mOrder.getCustomizationCategories().get(position);
        ArrayList<CustomizationOption> options = mOrder.getCustomizationSelectedOptionsByIndex(position);

        if (category != null) {
            TextView categoryNameTextView = (TextView) holder.mCardView.findViewById(R.id.customizationCategoryTextView);
            categoryNameTextView.setText(category.getName());
        }

        if (options != null) {
            TextView selectedTextView = (TextView) holder.mCardView.findViewById(R.id.customizationSelectedOptionTextView);
            StringBuilder sb = new StringBuilder();
            if (options.size() == 0) {
                sb.append("None");
            } else {
                for (int i = 0; i < options.size(); i++) {
                    sb.append(options.get(i).getName());
                    if (i != options.size() - 1)
                        sb.append(", ");
                }
            }
            selectedTextView.setText(sb.toString());
        }

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<CustomizationOption> selectedOptions = mOrder.getCustomizationSelectedOptionsByIndex(position);
                final ArrayList<CustomizationOption> options = mMenuItem.getCustomizationOptions(category);
                String[] optionNames = mMenuItem.getCustomizationOptionNames(category);

                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setTitle(category.getName())
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });

                if (category.isMultiSelect()) {
                    boolean[] checkedOptions = new boolean[optionNames.length];

                    if (selectedOptions.size() > 0)
                        for (int i = 0; i < checkedOptions.length; i++)
                            checkedOptions[i] = selectedOptions.contains(options.get(i));

                    builder.setMultiChoiceItems(optionNames, checkedOptions,
                            new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int index,
                                                    boolean isChecked) {
                                    if (isChecked) {
                                        selectedOptions.add(options.get(index));
                                    } else if (selectedOptions.contains(options.get(index))) {
                                        selectedOptions.remove(options.get(index));
                                    }
                                }
                            })
                            .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    mOrder.setSelectedCustomizationOptions(category, selectedOptions);
                                    self.notifyItemChanged(position);
                                    mActivity.recalculatePrice();
                                }
                            });
                } else {
                    int checkedOptionIndex = -1;
                    if (selectedOptions.size() > 0)
                            checkedOptionIndex = options.indexOf(selectedOptions.get(0));

                    builder.setSingleChoiceItems(optionNames, checkedOptionIndex,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int index) {
                                    selectedOptions.clear();
                                    selectedOptions.add(options.get(index));
                                    mOrder.setSelectedCustomizationOptions(category, selectedOptions);
                                    self.notifyItemChanged(position);
                                    mActivity.recalculatePrice();
                                    dialog.dismiss();
                                }
                    });
            }

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    });
}

    @Override
    public int getItemCount() {
        return mOrder.getCustomizationCategories().size();
    }
}

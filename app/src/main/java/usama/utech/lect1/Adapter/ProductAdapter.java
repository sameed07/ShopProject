package usama.utech.lect1.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import usama.utech.lect1.Model.Products;
import usama.utech.lect1.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ExampleViewHolder> implements Filterable {

    private List<Products> exampleList;
    private List<Products> exampleListFull;


    public ProductAdapter(List<Products> exampleList) {
        this.exampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_adapter,
                parent, false);
        return new ExampleViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Products currentItem = exampleList.get(position);

        holder.txtName.setText(currentItem.getName());
        holder.txtQuantity.setText(currentItem.getQuantity()+"");
        holder.txtPrice.setText(currentItem.getSalePrice()+"");
        holder.txtPurchasePrice.setText(currentItem.getPurchasePrice()+"");

    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }



    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Products> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();//to ask from sameed about the trim

                for (Products item : exampleListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    class ExampleViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//        TextView textView1;
//        TextView textView2;
//

        TextView txtName;
        TextView txtQuantity;
        TextView txtPrice;
        TextView txtPurchasePrice;


        ExampleViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_title);
            txtQuantity = itemView.findViewById(R.id.txt_Quantity);
            txtPrice = itemView.findViewById(R.id.txt_price);
            txtPurchasePrice = itemView.findViewById(R.id.txt_saleprice);
        }
    }

}

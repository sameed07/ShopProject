package usama.utech.lect1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import usama.utech.lect1.Add_Update_Delete_Pages.UpdateOrDeleteProducts;
import usama.utech.lect1.Model.Products;
import usama.utech.lect1.R;

public class UpdateAndDeleteAdapter extends RecyclerView.Adapter<UpdateAndDeleteAdapter.UpdateAndDeleteAdapterViewHolder> implements Filterable {

    private List<Products> exampleList;
    private List<Products> exampleListFull;
    private Context mContext;


    public UpdateAndDeleteAdapter(Context context, List<Products> exampleList) {
        this.exampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
        mContext = context;
    }

    @NonNull
    @Override
    public UpdateAndDeleteAdapter.UpdateAndDeleteAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_delete_adapter,
                parent, false);
        return new UpdateAndDeleteAdapter.UpdateAndDeleteAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final UpdateAndDeleteAdapterViewHolder holder, int position) {

        Products currentItem = exampleList.get(position);

        holder.txtId.setText("" + currentItem.getId());
        holder.txtBarcode.setText(""+ currentItem.getBarCode());
        holder.txtName.setText(currentItem.getName());
        holder.txtQuantity.setText("" + currentItem.getQuantity());
        holder.txtSPrice.setText("" + currentItem.getSalePrice());
        holder.txtPurchasePrice.setText("" + currentItem.getPurchasePrice());
        holder.txtWSalePrice.setText(""+currentItem.getWholeSalePrice());

        holder.parentLayoutOfProductAdaptor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //     Products products = exampleListFull.get(position);
                Toast.makeText(mContext, holder.txtName.getText() + " is clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext,UpdateOrDeleteProducts.class);
                intent.putExtra("id",holder.txtId.getText());
                intent.putExtra("barcode",holder.txtBarcode.getText());
                intent.putExtra("name",holder.txtName.getText());
                intent.putExtra("quantity",holder.txtQuantity.getText());
                intent.putExtra("Sprice",holder.txtSPrice.getText());
                intent.putExtra("Pprice",holder.txtPurchasePrice.getText());
                intent.putExtra("Wsprice", holder.txtWSalePrice.getText());

                mContext.startActivity(intent);
            }
        });

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


    class UpdateAndDeleteAdapterViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//        TextView textView1;
//        TextView textView2;
//

        TextView txtId;
        TextView txtBarcode;
        TextView txtName;
        TextView txtQuantity;
        TextView txtSPrice;
        TextView txtPurchasePrice;
        TextView txtWSalePrice;

        CardView parentLayoutOfProductAdaptor;


        UpdateAndDeleteAdapterViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txt_Id);
            txtBarcode = itemView.findViewById(R.id.txt_Barcode);
            txtName = itemView.findViewById(R.id.txt_title);
            txtQuantity = itemView.findViewById(R.id.txt_Quantity);
            txtSPrice = itemView.findViewById(R.id.txt_price);
            txtWSalePrice = itemView.findViewById(R.id.txt_whole_saleprice);
            txtPurchasePrice = itemView.findViewById(R.id.txt_saleprice);
            parentLayoutOfProductAdaptor = itemView.findViewById(R.id.parentLayoutOfProductAdaptor);
        }
    }

}

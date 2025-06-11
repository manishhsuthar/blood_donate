package blood.donate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by admin on 15-10-2016.
 */
public class ApAdapter extends RecyclerView.Adapter<ApAdapter.MyViewHolder> {

    private List<ApList> apList;
    Context context;

    public ApAdapter(Context c, List<ApList> apList) {
        this.context = c;
        this.apList = apList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName,txtAddress,txtCall,txtContact,txtGroup,txtNoBottel;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.custom_ap_recycle_txt_name);
            txtAddress = (TextView) view.findViewById(R.id.custom_ap_recycle_txt_address);
            txtCall = (TextView) view.findViewById(R.id.custom_ap_recycle_txt_cno);
            txtContact = (TextView) view.findViewById(R.id.custom_ap_recycle_txt_msg);
            txtGroup = (TextView) view.findViewById(R.id.custom_ap_recycle_group);
        }
    }


    public ApAdapter(List<ApList> apList) {
        this.apList = apList;
    }

    @Override
    public ApAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_ap, parent, false);

        return new ApAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ApAdapter.MyViewHolder holder, int position) {
        ApList apositive = apList.get(position);
        holder.txtName.setText(apList.get(position).getName_ap());
        holder.txtAddress.setText(apList.get(position).getAddress_ap());
        holder.txtCall.setText(apList.get(position).getCall_ap());
        holder.txtContact.setText(apList.get(position).getMsg_ap());
        holder.txtGroup.setText(apList.get(position).getGrp_ap());
    }

    @Override
    public int getItemCount() {
        Log.d("het", "size : " + apList.size());
        return apList.size();
    }
}
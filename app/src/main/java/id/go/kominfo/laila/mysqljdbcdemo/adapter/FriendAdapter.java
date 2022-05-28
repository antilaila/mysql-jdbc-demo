package id.go.kominfo.laila.mysqljdbcdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import id.go.kominfo.laila.mysqljdbcdemo.R;
import id.go.kominfo.laila.mysqljdbcdemo.model.Friend;

public class FriendAdapter extends BaseAdapter {
    private final Context ctx;
    private final List<Friend> ls;

    public FriendAdapter(Context ctx, List<Friend> ls) {
        this.ctx = ctx;
        this.ls = ls;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public Object getItem(int position) {
        return ls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            assert false;
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        holder.tvName.setText(ls.get(position).getName());
        holder.tvPhone.setText(ls.get(position).getPhone());

        return convertView;
    }

    private static class ViewHolder {
        TextView tvName;
        TextView tvPhone;

        public ViewHolder(View view) {
            tvName = view.findViewById(R.id.tv_name);
            tvPhone = view.findViewById(R.id.tv_phone);
        }
    }
}

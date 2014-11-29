package me.isming.tools.cvfilter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import me.isming.tools.cvfilter.library.*;



public class MainActivity extends Activity {

    private int mCurrentFilterPosition;
    private Bitmap mOriginBitmap, mResultBitmap;
    private ImageData mImageData;
    private ImageView mContentView;
    private HorizontalListView mListView;
    private FilterPreviewAdapter mAdapter;
    private ImageView mRotateView, mHDRView;
    private ICVFilter mHDRFilter;
    private int rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContentView = (ImageView) findViewById(R.id.imageView);
        mOriginBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meizi);
        mImageData = new ImageData(mOriginBitmap);
        mContentView.setImageBitmap(mOriginBitmap);

        mListView = (HorizontalListView) findViewById(R.id.listview);
        mAdapter = new FilterPreviewAdapter(this, FilterFactory.createFilters(this));
        mListView.setAdapter(mAdapter);

        mRotateView = (ImageView) findViewById(R.id.rotate);
        mRotateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotate += 90;
                mImageData.rotate(rotate);
                mImageData.createResult();
                mResultBitmap = mImageData.getResult();
                mContentView.setImageBitmap(mResultBitmap);
            }
        });

        mHDRView = (ImageView) findViewById(R.id.hdr);
        mHDRView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHDRFilter == null) {
                    mHDRFilter = new CvHDR();
                }
                //todo

            }
        });
    }

    public class FilterPreviewAdapter extends BaseAdapter {

        //private List<FilterContent> mFilters;
        private ICVFilter[] mFilters;
        private Context mContext;
        private int mSelectItem;

        public FilterPreviewAdapter(Context context, ICVFilter[] filters) {
            mContext = context;
            mFilters = filters;
        }

        @Override
        public int getCount() {
            return mFilters == null ? 0 : mFilters.length;
        }

        @Override
        public Object getItem(int position) {
            return mFilters == null ? null : mFilters[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.filter_preview, null);
                holder = new ViewHolder();
                holder.imageView = (ImageView) convertView.findViewById(R.id.image);
                holder.titleView = (TextView) convertView.findViewById(R.id.title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final ICVFilter filterContent = (ICVFilter) getItem(position);
            final int nowItem = position;
            holder.imageView.setImageResource(filterContent.getResId());
            holder.titleView.setText(filterContent.getName());
            if (mSelectItem == position) {
                holder.titleView.setTextColor(mContext.getResources().getColor(R.color.join_group_text_color));
            } else {
                holder.titleView.setTextColor(mContext.getResources().getColor(R.color.youdao_background_color));
            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectItem = nowItem;
                    mCurrentFilterPosition = nowItem;
                    ImageData d = filterContent.convert(mImageData);
                    d.createResult();
                    mResultBitmap = d.getResult();
                    mContentView.setImageBitmap(mResultBitmap);
                    notifyDataSetChanged();
                }
            });

            return convertView;
        }

        public void selectItem(int position) {
            final ICVFilter filterContent = (ICVFilter) getItem(position);
            mSelectItem = position;
            mCurrentFilterPosition = position;
            ImageData d = filterContent.convert(mImageData);
            d.createResult();
            mResultBitmap = d.getResult();
            mContentView.setImageBitmap(mResultBitmap);
            notifyDataSetChanged();
        }
    }

    class ViewHolder {
        TextView titleView;
        ImageView imageView;
    }
}

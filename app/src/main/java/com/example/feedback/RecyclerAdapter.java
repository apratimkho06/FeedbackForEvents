package com.example.feedback;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.feedback.database.Responses;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Typeface franocis,hammersmith,inconsolota;
    //private Responses[] ques_list;
    private ArrayList<Responses> responses = new ArrayList<>();
    private Context context;

    private ClickListener clickListener;

    public interface ClickListener{
        public void onRateListener(int position, String rating);
        public void onTextListener(int position, String ans);
        public void onCheckListener(int position, String checked);
        public void radioListener(int position, String radio);
    }

    public class RateViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView question_rate;
        public RatingBar rateBar;


        public RateViewHolder(View v) {
            super(v);
            question_rate = (TextView) v.findViewById(R.id.tv_rate);
            rateBar = (RatingBar) v.findViewById(R.id.rate_bar);

            rateBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    clickListener.onRateListener(getAdapterPosition(), rating+"");
                }
            });
        }
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView question_text;
        public EditText answer_text;


        public TextViewHolder(View v) {
            super(v);
            question_text = (TextView) v.findViewById(R.id.tv_text);
            answer_text = (EditText) v.findViewById(R.id.answer_text);

            answer_text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    clickListener.onTextListener(getAdapterPosition(), s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public class RadioHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView question_radio;
        public RadioGroup answer_radio;
        public RadioButton radioButton,radioYes,radioNo;


        public RadioHolder(final View v) {
            super(v);
            question_radio = (TextView) v.findViewById(R.id.tv_radio);
            answer_radio = (RadioGroup) v.findViewById(R.id.answer_radio);
            radioYes = (RadioButton) v.findViewById(R.id.radio_yes);
            radioNo = (RadioButton) v.findViewById(R.id.radio_no);

            answer_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    radioButton = (RadioButton) v.findViewById(checkedId);
                    clickListener.radioListener(getAdapterPosition(),radioButton.getText().toString());
                }
            });

            v.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        // run scale animation and make it bigger
                        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_in_tv);
                        v.startAnimation(anim);
                        anim.setFillAfter(true);
                    } else {
                        // run scale animation and make it smaller
                        Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_out);
                        v.startAnimation(anim);
                        anim.setFillAfter(true);
                    }
                }
            });
        }
    }

    public class CheckBoxViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView question_cb;
        public CheckBox cb1,cb2,cb3,cb4,cb5,cb6;


        public CheckBoxViewHolder(View v) {
            super(v);
            question_cb = (TextView) v.findViewById(R.id.tv_cb);
            cb1 = (CheckBox) v.findViewById(R.id.cb1);
            cb2 = (CheckBox) v.findViewById(R.id.cb2);
            cb3 = (CheckBox) v.findViewById(R.id.cb3);
            cb4 = (CheckBox) v.findViewById(R.id.cb4);
            cb5 = (CheckBox) v.findViewById(R.id.cb5);
            cb6 = (CheckBox) v.findViewById(R.id.cb6);

            //TODO: logic for checkboxes

        }
    }


    public RecyclerAdapter(Context context, ArrayList<Responses> responses, ClickListener clickListener) {
        //this.inconsolota=Typeface.createFromAsset(context.getAssets(), "fonts/inconsolatabold.ttf");
        //this.hammersmith = Typeface.createFromAsset(context.getAssets(), "fonts/hammersmithoneregular.ttf");
        //this.franocis = Typeface.createFromAsset(context.getAssets(), "fonts/francoisoneregular.ttf");
        this.responses = responses;
        this.context = context;
        this.clickListener = clickListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());

        if(viewType == 1){
            View v =
                    inflater.inflate(R.layout.rate_xml, parent, false);
            // set the view's size, margins, paddings and layout parameters
            RateViewHolder vh = new RateViewHolder(v);

            return vh;
        }else if(viewType == 2){
            View v =
                    inflater.inflate(R.layout.text_xml, parent, false);
            // set the view's size, margins, paddings and layout parameters
            TextViewHolder vh = new TextViewHolder(v);

            return vh;
        }else if(viewType == 3){
            View v =
                    inflater.inflate(R.layout.checkbox_xml, parent, false);
            // set the view's size, margins, paddings and layout parameters
            CheckBoxViewHolder vh = new CheckBoxViewHolder(v);

            return vh;
        } else {
            View v =
                    inflater.inflate(R.layout.radio_xml, parent, false);
            // set the view's size, margins, paddings and layout parameters
            RadioHolder vh = new RadioHolder(v);

            return vh;
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Log.d("abcd","inside view");
        if(responses.get(position).catg.equalsIgnoreCase("type_rate")){
            ((RateViewHolder)holder).question_rate.setText(responses.get(position).ques);
            //((RateViewHolder)holder).question_rate.setTypeface(hammersmith);
            if(responses.get(position).ans==null) {
                ((RateViewHolder)holder).rateBar.setRating(0);
            } else {
                ((RateViewHolder)holder).rateBar.setRating(Float.parseFloat(responses.get(position).ans));
            }
        } else if(responses.get(position).catg.equalsIgnoreCase("type_text")) {
            ((TextViewHolder)holder).question_text.setText(responses.get(position).ques);
            //((TextViewHolder)holder).question_text.setTypeface(hammersmith);
            if(responses.get(position).ans==null) {
                ((TextViewHolder)holder).answer_text.setText("");
            } else {
                ((TextViewHolder)holder).answer_text.setText(responses.get(position).ans);
            }
        } else if(responses.get(position).catg.equalsIgnoreCase("type_checkbox")){
            ((CheckBoxViewHolder)holder).question_cb.setText(responses.get(position).ques);
        } else if(responses.get(position).catg.equalsIgnoreCase("type_radio")) {
            ((RadioHolder)holder).question_radio.setText(responses.get(position).ques);
            //((RadioHolder)holder).question_radio.setTypeface(hammersmith);
            if(responses.get(position).ans==null) {
                //((RadioHolder)holder).answer_radio.clearCheck();
                ((RadioHolder)holder).radioYes.setChecked(false);
                ((RadioHolder)holder).radioNo.setChecked(false);
            } else {
                if(responses.get(position).ans.equalsIgnoreCase("Yes")) {
                    ((RadioHolder)holder).radioYes.setChecked(true);
                    ((RadioHolder)holder).radioNo.setChecked(false);
                } else {
                    ((RadioHolder)holder).radioYes.setChecked(false);
                    ((RadioHolder)holder).radioNo.setChecked(true);
                }
            }
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return responses.size();
    }

    @Override
    public int getItemViewType(int position){
        if(responses.get(position).catg.equalsIgnoreCase("type_rate")){
            return 1;
        }else if(responses.get(position).catg.equalsIgnoreCase("type_text")){
            return 2;
        }else if(responses.get(position).catg.equalsIgnoreCase("type_checkbox")){
            return 3;
        } else if(responses.get(position).catg.equalsIgnoreCase("type_radio")) {
            return 4;
        }
        return 1;
    }

}


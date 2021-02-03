package com.example.mexcomapp.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mexcomapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*

class MexItemAdapter(
    private val mexItemList: List<MexItem>,
    private val listener: ButtonClickedListener
):RecyclerView.Adapter<MexItemAdapter.MexHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MexHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MexHolder(layoutInflater.inflate(R.layout.item_product,parent,false))
    }

    override fun getItemCount(): Int = mexItemList.size

    override fun onBindViewHolder(holder: MexHolder, position: Int) {
        holder.render(mexItemList[position])
    }

    inner class MexHolder(val view: View):RecyclerView.ViewHolder(view), View.OnClickListener{

        init {
            view.cart_add_button.setOnClickListener(this)
        }

        fun render(item: MexItem){
            view.prod_name.text = item.name
            view.prod_price.text = String.format("%.2f",item.lastPrice)
            Picasso.get().load(item.icon).into(view.prod_icon)
        }

        override fun onClick(v: View?) {
           val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) listener.onButtonClick(position)
        }
    }

    interface ButtonClickedListener {
        fun onButtonClick(position: Int)
    }
}

package com.example.mexcomapp.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mexcomapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*

class CartAdapter(val cartList: MutableList<MexItem>):RecyclerView.Adapter<CartAdapter.CartHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CartHolder(layoutInflater.inflate(R.layout.item_cart,parent,false))
    }

    override fun getItemCount(): Int = cartList.size

    override fun onBindViewHolder(holder: CartAdapter.CartHolder, position: Int) {
        holder.render(cartList[position])
    }

    inner class CartHolder(val view: View):RecyclerView.ViewHolder(view){
        fun render(item: MexItem){
            view.prod_name.text = item.name
            view.prod_price.text = String.format("%.2f",item.lastPrice)
            Picasso.get().load(item.icon).into(view.prod_icon)
        }
    }
}
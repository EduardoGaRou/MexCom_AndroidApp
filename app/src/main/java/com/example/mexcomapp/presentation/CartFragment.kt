package com.example.mexcomapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mexcomapp.R
import com.example.mexcomapp.data.CartAdapter
import com.example.mexcomapp.data.MexItem
import com.example.mexcomapp.domain.AppFunctions
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.view.*
import org.threeten.bp.LocalDateTime

class CartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart,container,false)
    }

    private val args: CartFragmentArgs by navArgs()
    private val appFunctions = AppFunctions()
    private val dow = LocalDateTime.now().dayOfWeek.toString()
    private val cartList: MutableList<MexItem> = mutableListOf()

    private fun initCartRecycler() {
        val str = args.myArg
        for (i in str.indices){
            when(str[i]){
                'a' -> cartList.add(MexItem("Muñeca otomí",appFunctions.applyPromos(50.0,dow,"Decorativo"),"https://tipsparatuviaje.com/wp-content/uploads/2019/07/muneca-mxs.jpg"))
                'b' -> cartList.add(MexItem("Alebrije mache",appFunctions.applyPromos(1800.0,dow,"Decorativo"),"https://mymodernmet.com/wp/wp-content/uploads/2019/05/Alebrijes-1-e1558455347541.jpg"))
                'c' -> cartList.add(MexItem("Catrina Capula",appFunctions.applyPromos(450.0,dow,"Decorativo"),"https://i.pinimg.com/originals/82/a3/1c/82a31c2683bc68b590d6eb5fab6644ef.jpg"))
                'd' -> cartList.add(MexItem("Pulsera tejida",appFunctions.applyPromos(40.0,dow,"Prenda"),"https://http2.mlstatic.com/D_NQ_NP_750745-MLM43489408519_092020-W.jpg"))
                'e' -> cartList.add(MexItem("Sarape/Rebozo",appFunctions.applyPromos(600.0,dow,"Prenda"),"https://tipsparatuviaje.com/wp-content/uploads/2019/07/sarapes-mx.jpg"))
                'f' -> cartList.add(MexItem("Huipil yucateco",appFunctions.applyPromos(1400.0,dow,"Prenda"),"https://i.pinimg.com/originals/1c/bc/e5/1cbce5630affbb3b5920b6920524d562.jpg"))
                'g' -> cartList.add(MexItem("Sombrero charro",appFunctions.applyPromos(1500.0,dow,"Prenda"),"https://tipsparatuviaje.com/wp-content/uploads/2019/07/sombreros-de-charro.jpg"))
                'h' -> cartList.add(MexItem("Jarrón oaxaqueño",appFunctions.applyPromos(750.0,dow,"Util"),"https://tipsparatuviaje.com/wp-content/uploads/2019/07/ceramica-negra-de-oaxaca.jpg"))
                'i' -> cartList.add(MexItem("Talavera poblana",appFunctions.applyPromos(940.0,dow,"Util"),"https://radiotexmex.com/wp-content/uploads/2020/12/Certifican-a-la-Talavera-poblana-como-Patrimonio-Intangible-de-la-Humanidad.jpg"))
                'j' -> cartList.add(MexItem("Caja de Olinalá",appFunctions.applyPromos(350.0,dow,"Util"),"https://tipsparatuviaje.com/wp-content/uploads/2019/07/cajitas-de-olinala.jpg"))
                else -> {}
            }
        }
        cart_list.layoutManager = LinearLayoutManager(this.context)
        cart_list.adapter = CartAdapter(cartList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val str = args.myArg
        if(str.equals(" ")) {
            cart_list.isEnabled = false
        } else {
            cart_list.isEnabled = true
            initCartRecycler()
        }
        view.button_confirm.isEnabled = !str.equals(" ")
        view.button_clear.isEnabled = !str.equals(" ")
        val totalPrice = appFunctions.getTotal(cartList)
        view.tv_pricenum.text = String.format("%.2f",totalPrice)

        view.shopback_button.setOnClickListener {
            val action = CartFragmentDirections.actionCartToShop(str)
            findNavController().navigate(action)
        }

        view.button_clear.setOnClickListener {
            val action = CartFragmentDirections.actionCartToShop(" ")
            Toast.makeText(context,"Tu carrito ha sido limpiado",Toast.LENGTH_SHORT).show()
            findNavController().navigate(action)
        }

        view.button_confirm.setOnClickListener {
            val action = CartFragmentDirections.actionCartToConfirm(totalPrice.toFloat(),str)
            findNavController().navigate(action)
        }
    }
}
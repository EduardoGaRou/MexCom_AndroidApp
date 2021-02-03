package com.example.mexcomapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mexcomapp.R
import com.example.mexcomapp.data.MexItem
import com.example.mexcomapp.data.MexItemAdapter
import com.example.mexcomapp.domain.AppFunctions
import kotlinx.android.synthetic.main.fragment_shop.*
import kotlinx.android.synthetic.main.fragment_shop.view.*
import org.threeten.bp.LocalDateTime

class ShopFragment : Fragment(), MexItemAdapter.ButtonClickedListener {

    private val appFunctions = AppFunctions()
    private val dow = LocalDateTime.now().dayOfWeek.toString()
    private var strids = " "

    private var productList: List<MexItem> = listOf(
        MexItem("Muñeca otomí",appFunctions.applyPromos(50.0,dow,"Decorativo"),"https://tipsparatuviaje.com/wp-content/uploads/2019/07/muneca-mxs.jpg"),
        MexItem("Alebrije mache",appFunctions.applyPromos(1800.0,dow,"Decorativo"),"https://mymodernmet.com/wp/wp-content/uploads/2019/05/Alebrijes-1-e1558455347541.jpg"),
        MexItem("Catrina Capula",appFunctions.applyPromos(450.0,dow,"Decorativo"),"https://i.pinimg.com/originals/82/a3/1c/82a31c2683bc68b590d6eb5fab6644ef.jpg"),
        MexItem("Pulsera tejida",appFunctions.applyPromos(40.0,dow,"Prenda"),"https://http2.mlstatic.com/D_NQ_NP_750745-MLM43489408519_092020-W.jpg"),
        MexItem("Sarape/Rebozo",appFunctions.applyPromos(600.0,dow,"Prenda"),"https://tipsparatuviaje.com/wp-content/uploads/2019/07/sarapes-mx.jpg"),
        MexItem("Huipil yucateco",appFunctions.applyPromos(1400.0,dow,"Prenda"),"https://i.pinimg.com/originals/1c/bc/e5/1cbce5630affbb3b5920b6920524d562.jpg"),
        MexItem("Sombrero charro",appFunctions.applyPromos(1500.0,dow,"Prenda"),"https://tipsparatuviaje.com/wp-content/uploads/2019/07/sombreros-de-charro.jpg"),
        MexItem("Jarrón oaxaqueño",appFunctions.applyPromos(750.0,dow,"Util"),"https://tipsparatuviaje.com/wp-content/uploads/2019/07/ceramica-negra-de-oaxaca.jpg"),
        MexItem("Talavera poblana",appFunctions.applyPromos(940.0,dow,"Util"),"https://radiotexmex.com/wp-content/uploads/2020/12/Certifican-a-la-Talavera-poblana-como-Patrimonio-Intangible-de-la-Humanidad.jpg"),
        MexItem("Caja de Olinalá",appFunctions.applyPromos(350.0,dow,"Util"),"https://tipsparatuviaje.com/wp-content/uploads/2019/07/cajitas-de-olinala.jpg")
    )

    private val args: ShopFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    private fun initRecycler(){
        product_list.layoutManager = LinearLayoutManager(this.context)
        product_list.adapter =  MexItemAdapter(productList,this)
    }

    override fun onButtonClick(position: Int) {
        val clickedItem = productList[position]
        Toast.makeText(context,"${clickedItem.name} agregado al carrito",Toast.LENGTH_LONG).show()
        strids = when(clickedItem.name){
            "Muñeca otomí" -> "${strids}a"
            "Alebrije mache" -> "${strids}b"
            "Catrina Capula" -> "${strids}c"
            "Pulsera tejida" -> "${strids}d"
            "Sarape/Rebozo" -> "${strids}e"
            "Huipil yucateco" -> "${strids}f"
            "Sombrero charro" -> "${strids}g"
            "Jarrón oaxaqueño" -> "${strids}h"
            "Talavera poblana" -> "${strids}i"
            else -> "${strids}j"
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        strids = args.myArg

        initRecycler()

        view.cart_button.setOnClickListener {
            val action = ShopFragmentDirections.actionShopToCart(strids)
            findNavController().navigate(action)
        }

        view.button_calendar.setOnClickListener {
            val action = ShopFragmentDirections.actionShopToCalendar(strids)
            findNavController().navigate(action)
        }
    }
}

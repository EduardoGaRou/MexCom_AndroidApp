package com.example.mexcomapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mexcomapp.R
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_calendar.view.*
import org.threeten.bp.LocalDateTime
import java.util.*

class CalendarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    private val args: CalendarFragmentArgs by navArgs()

    private fun setPromoBanner(auxview: View, dow: String) {
        if(dow == "MONDAY" || dow == "THURSDAY")
            auxview.promo_banner.setText(R.string.decor_promo)
        else if(dow == "TUESDAY" || dow == "FRIDAY")
            auxview.promo_banner.setText(R.string.prenda_promo)
        else if(dow == "WEDNESDAY")
            auxview.promo_banner.setText(R.string.util_promo)
        else if(dow == "SATURDAY")
            auxview.promo_banner.setText(R.string.total_promo)
        else
            auxview.promo_banner.setText(R.string.no_promo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = LocalDateTime.now()
        val dow = date.dayOfWeek.toString()
        setPromoBanner(view,dow)

        promo_calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year,month+1,dayOfMonth)
            val seldow = calendar.get(Calendar.DAY_OF_WEEK)
            var dowstr = ""
            when(seldow){
                1 -> dowstr = "SUNDAY"
                2 -> dowstr = "MONDAY"
                3 -> dowstr = "TUESDAY"
                4 -> dowstr = "WEDNESDAY"
                5 -> dowstr = "THURSDAY"
                6 -> dowstr = "FRIDAY"
                7 -> dowstr = "SATURDAY"
                else -> {}
            }
            setPromoBanner(view,dowstr)
        }

        view.shopback_button.setOnClickListener {
            val str = args.myArg
            val action = CalendarFragmentDirections.actionCalendarToShop(str)
            findNavController().navigate(action)
        }
    }
}
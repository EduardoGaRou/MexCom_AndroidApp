package com.example.mexcomapp.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mexcomapp.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_confirm.*
import kotlinx.android.synthetic.main.fragment_confirm.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConfirmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm, container,false)
    }

    private val args: ConfirmFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var strname = ""

        view.button_send.isEnabled = !strname.equals("")

        et_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                strname = s.toString()
                view.button_send.isEnabled = !strname.equals("")
            }
        })

        view.button_send.setOnClickListener {
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a")
            val date = LocalDateTime.now().format(formatter)
            val doc = "$strname $date"
            addCFSInfo(doc,args.myArgSec.toDouble())
            Toast.makeText(context,"Solicitud de Compra Enviada",Toast.LENGTH_LONG).show()
            val action = ConfirmFragmentDirections.actionConfirmToShop(" ")
            findNavController().navigate(action)
        }
    }

    private fun addCFSInfo(docs: String, price: Double) {
        val datastr = args.myArg
        val firebaseDB = FirebaseFirestore.getInstance()
        val pricestr = String.format("%.2f",price)
        val productArray = mutableListOf<String>()
        for(i in datastr.indices){
            if(i != 0) {
                when (datastr[i]) {
                    'a' -> productArray.add("Muñeca otomí")
                    'b' -> productArray.add("Alebrije mache")
                    'c' -> productArray.add("Catrina Capula")
                    'd' -> productArray.add("Pulsera tejida")
                    'e' -> productArray.add("Sarape/Rebozo")
                    'f' -> productArray.add("Huipil yucateco")
                    'g' -> productArray.add("Sombrero charro")
                    'h' -> productArray.add("Jarrón oaxaqueño")
                    'i' -> productArray.add("Talavera poblana")
                    'j' -> productArray.add("Caja de Olinalá")
                    else -> {}
                }
            }
        }
        val request = hashMapOf("products" to productArray,"totalPrice" to pricestr)
        firebaseDB.collection("Shop Requests").document(docs).set(request).addOnSuccessListener {
            Log.i("Testing","Successful sent request")
        }.addOnFailureListener {
            Log.i("Testing","Error ${it.message}")
        }
    }
}
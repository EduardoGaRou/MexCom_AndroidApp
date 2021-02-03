package com.example.mexcomapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.mexcomapp.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class StartFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.start_button).setOnClickListener {
            val str = " "
            val action = StartFragmentDirections.actionStartToShop(str)
            findNavController().navigate(action)
        }
    }
}

/*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    view.findViewById<Button>(R.id.random_button).setOnClickListener {
        val cntTextView = view.findViewById<TextView>(R.id.textview_first)
        val curCount = cntTextView.text.toString().toInt()
        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(curCount)
        findNavController().navigate(action)
    }
    view.findViewById<Button>(R.id.toast_button).setOnClickListener {
        val myToast = Toast.makeText(context, "Hello Toast!", Toast.LENGTH_SHORT)
        myToast.show()
        addFirstInfo()
    }
    view.findViewById<Button>(R.id.count_button).setOnClickListener {
        countMe(view)
    }
}

private fun countMe(view: View) {
    val countView = view.findViewById<TextView>(R.id.textview_first)
    val countStr = countView.text.toString()
    var count = countStr.toInt()
    count += 1
    countView.text = count.toString()
}

private fun addFirstInfo() {
    val firebaseDB = FirebaseFirestore.getInstance()
    val user = hashMapOf("first" to "Eduardo","last" to "Garcia Rougon","born" to 1997)
    firebaseDB.collection("Users").add(user).addOnSuccessListener {
        Log.i("Testing","Success ${it.id} path ${it.path}")
    }.addOnFailureListener {
        Log.i("Testing","Error ${it.message}")
    }
}*/
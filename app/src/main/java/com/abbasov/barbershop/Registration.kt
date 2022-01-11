package com.abbasov.barbershop

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abbasov.barbershop.databinding.FragmentMenuBinding
import com.abbasov.barbershop.databinding.FragmentRegistrationBinding
import androidx.core.content.ContextCompat.getSystemService

import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat
import java.lang.Exception
import java.lang.reflect.Method
import java.util.*
import android.net.NetworkInfo

import androidx.core.content.ContextCompat.getSystemService

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.TimePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.abbasov.barbershop.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Registration : Fragment() {

    lateinit var binding:FragmentRegistrationBinding
    lateinit var firebaseDatabase:FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var list:ArrayList<User>
    lateinit var auth: FirebaseAuth
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(LayoutInflater.from(context))
        auth = FirebaseAuth.getInstance()
        val aut = auth.currentUser
        val person= aut?.email.toString()
        firebaseDatabase= FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference("users")
        binding.time.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                context,
                { view, hourOfDay, minute ->
                    binding.time.text="$hourOfDay:$minute"
                },
                24,
                60,
                true
            )
            timePickerDialog.updateTime(12, 60)
            timePickerDialog.show()

        }

        binding.date.setOnClickListener {
            val datePickerDialog = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                DatePickerDialog(requireContext())
            } else {
                TODO("VERSION.SDK_INT < N")
            }

            datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                binding.date.text="${dayOfMonth}.${month+1}.$year"
            }

            datePickerDialog.show()
        }
        binding.save.setOnClickListener {
            val name=binding.name.text.toString().trim()
            val number=binding.number.text.toString().trim()
            val data=binding.date.text.toString().trim()
            val time=binding.time.text.toString().trim()
            if (number!=""&&name!=""&&data!=""&&time!=""&&isOnline(requireContext())){
                val key = reference.push().key
                val user= User(key.toString(),name,number,data,time)
                reference.child(key!!).setValue(user)

            }
            if (number==""||name==""||data==""||time==""){
                Toast.makeText(context, "заполните все поля", Toast.LENGTH_SHORT).show()
            }
            if (!isOnline(requireContext())){
                Toast.makeText(context, "включите интернет", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {

                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {

                    return true
                }
            }
        }
        return false
    }

}
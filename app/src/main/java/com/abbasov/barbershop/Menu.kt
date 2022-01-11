package com.abbasov.barbershop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.abbasov.barbershop.databinding.FragmentMainBinding
import com.abbasov.barbershop.databinding.FragmentMenuBinding
import kotlinx.android.synthetic.main.fragment_menu.*
import uz.umarxon.barbershopui.adapter.RvAdapter
import uz.umarxon.barbershopui.models.Barber
import android.graphics.Typeface



import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import android.net.NetworkCapabilities

import android.net.ConnectivityManager





class Menu : Fragment() {


    lateinit var binding: FragmentMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val name = arguments?.get("name").toString()

        binding = FragmentMenuBinding.inflate(LayoutInflater.from(context))
        val list = ArrayList<Barber>()
        list.add(Barber("Admin","",R.drawable.scisor_image))
        list.add(Barber("Registration","",R.drawable.shaving_image))
        list.add(Barber("Queue","",R.drawable.bread_image))
        binding.rv.adapter= RvAdapter(list,object : RvAdapter.Click{
            override fun click(barber: Barber, position: Int) {
                if (position==0){
                    Toast.makeText(context, "admin", Toast.LENGTH_SHORT).show()
                }
                if (position==1 && isOnline(context!!)){
                    findNavController().navigate(R.id.registration)
                }else{
                    Toast.makeText(context, "включите свой интернет", Toast.LENGTH_SHORT).show()
                }
                if (position==2){
                    Toast.makeText(context, "all", Toast.LENGTH_SHORT).show()
                }
            }

        })
        binding.name.text=name
        val typeface=ResourcesCompat.getFont(requireContext(),R.font.font1)
        binding.name.typeface = typeface

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
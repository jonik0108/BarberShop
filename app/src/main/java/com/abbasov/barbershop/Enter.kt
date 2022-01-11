package com.abbasov.barbershop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.abbasov.barbershop.databinding.FragmentEnterBinding


class Enter : Fragment() {

    lateinit var binding: FragmentEnterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEnterBinding.inflate(LayoutInflater.from(context))
        //"954028707"
        binding.next.setOnClickListener {
            val name=binding.name.text.toString()
            findNavController().navigate(R.id.menu, bundleOf("name" to name))
        }

        return binding.root
    }

}
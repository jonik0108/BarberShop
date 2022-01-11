package com.abbasov.barbershop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abbasov.barbershop.adapter.ReelsAdapter
import com.abbasov.barbershop.databinding.FragmentReelsBinding
import com.abbasov.barbershop.models.User2


class Reels : Fragment() {

    lateinit var reelsAdapter: ReelsAdapter
    lateinit var binding: FragmentReelsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentReelsBinding.inflate(LayoutInflater.from(context))
        val array=ArrayList<User2>()
        for (i in 0..10) {
            array.add(User2(R.drawable.circle))
            array.add(User2(R.drawable.strange))
            array.add(User2(R.drawable.strange2))
            array.add(User2(R.drawable.svetofor))
            array.add(User2(R.drawable.miwka))
        }
        //code
        reelsAdapter = ReelsAdapter(requireContext(),array)
        binding.rv1.adapter=reelsAdapter
        return binding.root
    }

}
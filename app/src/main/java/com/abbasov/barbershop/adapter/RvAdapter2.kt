package uz.umarxon.barbershopui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abbasov.barbershop.databinding.ItemRv2Binding
import uz.umarxon.barbershopui.models.Barber

class RvAdapter2(private val list: List<Barber>, var click:Click) :
    RecyclerView.Adapter<RvAdapter2.Vh>() {
    inner class Vh(var itemRv: ItemRv2Binding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(barber: Barber) {
            itemRv.name3.text = barber.name
            itemRv.image3.setImageResource(barber.image!!)

            itemRv.root.setOnClickListener{
                click.click(barber)
            }
        }
    }

    interface Click{
        fun click(barber: Barber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRv2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
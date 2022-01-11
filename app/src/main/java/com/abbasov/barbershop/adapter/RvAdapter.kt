package uz.umarxon.barbershopui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abbasov.barbershop.databinding.ItemRvBinding
import uz.umarxon.barbershopui.models.Barber

class RvAdapter(private val list: List<Barber>,var click:Click) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(barber: Barber,position: Int) {
            itemRv.name.text = barber.name
            itemRv.about.text = barber.about
            itemRv.image2.setImageResource(barber.image!!)
            itemRv.root.setOnClickListener{
                click.click(barber,position)
            }
        }
    }

    interface Click{
        fun click(barber: Barber,position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size
}
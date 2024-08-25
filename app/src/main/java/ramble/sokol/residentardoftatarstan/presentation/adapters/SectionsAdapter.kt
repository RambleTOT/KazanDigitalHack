package ramble.sokol.residentardoftatarstan.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ramble.sokol.residentardoftatarstan.R
import ramble.sokol.residentardoftatarstan.data.model.GetEventsResponse
import ramble.sokol.residentardoftatarstan.data.model.GetSectionsResponse
import ramble.sokol.residentardoftatarstan.databinding.ItemEventBinding
import ramble.sokol.residentardoftatarstan.databinding.ItemSectionBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SectionsAdapter (
    private val eventsList: List<GetSectionsResponse>
): RecyclerView.Adapter<SectionsAdapter.ViewHolder>() {

    var onItemClick : ((GetSectionsResponse) -> Unit)? = null
    private lateinit var context: Context

    inner class ViewHolder(val binding: ItemSectionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            ItemSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = eventsList[position]
        holder.binding.apply {
            nameSection.text = currentItem.name
            val beginningDateTime = parseDateTime(currentItem.beginningAt.toString())
            dateAddressSection.text = "${currentItem.address} · ${formatDateTime(beginningDateTime)}"
            priceSectionText.text = if (currentItem.price == "0") "Бесплатно" else "от ${currentItem.price} ₽"
            if (currentItem.image == null){
                imageSectionCard.setBackgroundResource(R.drawable.image_card)
            }else {
                Picasso.with(context).load(currentItem.image).into(imageSectionCard)
            }
            holder.itemView.setOnClickListener{
                onItemClick?.invoke(currentItem)
            }
        }
    }

    fun formatDateTime(dateTime: LocalDateTime): String {
        val date = dateTime.format(DateTimeFormatter.ofPattern("dd MMMM"))
        return "$date"
    }

    fun parseDateTime(dateTimeString: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return LocalDateTime.parse(dateTimeString, formatter)
    }

}
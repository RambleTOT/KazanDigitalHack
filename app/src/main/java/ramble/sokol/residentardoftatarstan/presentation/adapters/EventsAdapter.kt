package ramble.sokol.residentardoftatarstan.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ramble.sokol.residentardoftatarstan.data.model.GetEventsResponse
import ramble.sokol.residentardoftatarstan.databinding.ItemEventBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EventsAdapter (
    private val eventsList: List<GetEventsResponse>
): RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    var onItemClick : ((GetEventsResponse) -> Unit)? = null

    inner class ViewHolder(val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = eventsList[position]
        holder.binding.apply {
            nameEvent.text = currentItem.name
            val beginningDateTime = parseDateTime(currentItem.beginningAt.toString())
            dateAddressEvent.text = "${currentItem.address} · ${formatDateTime(beginningDateTime)}"
            priceEventText.text = if (currentItem.price == "0") "Бесплатно" else "от ${currentItem.price} ₽"
//            holder.itemView.setOnClickListener{
//                onItemClick?.invoke(currentItem)
//            }
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
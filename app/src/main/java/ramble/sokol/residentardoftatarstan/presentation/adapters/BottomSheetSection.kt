package ramble.sokol.residentardoftatarstan.presentation.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import com.yandex.mapkit.geometry.Point
import ramble.sokol.residentardoftatarstan.presentation.fragments.CreateRoutesToPointFragment
import ramble.sokol.residentardoftatarstan.R
import ramble.sokol.residentardoftatarstan.data.model.GetGroundsResponse
import ramble.sokol.residentardoftatarstan.data.model.GetSectionsResponse
import ramble.sokol.residentardoftatarstan.databinding.BottomSheetGroundBinding
import ramble.sokol.residentardoftatarstan.databinding.BottomSheetSectionBinding
import ramble.sokol.residentardoftatarstan.presentation.fragments.MapFragment
import ramble.sokol.residentardoftatarstan.presentation.fragments.PayFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BottomSheetSection(val i: GetSectionsResponse) : BottomSheetDialogFragment() {

    private var binding: BottomSheetSectionBinding? = null

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetSectionBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyLog", i.name.toString())
        binding!!.nameSection.text = i.name
        binding!!.descriptionSection.text = i.description
        binding!!.priceSectionText.text = if (i.price == "0") "Бесплатно" else "от ${i.price} ₽"
        val beginningDateTime = parseDateTime(i.beginningAt.toString())
        binding!!.dateAddressSection.text = "${i.address} · ${formatDateTime(beginningDateTime)}"
        binding!!.daysSection.text = convertDays(i.days.toString())
        if (binding!!.imageSection == null){
            binding!!.imageSection.setBackgroundResource(R.drawable.image_card)
        }else {
            Picasso.with(context).load(i.image).into(binding!!.imageSection)
        }

    }

    companion object {
        const val TAG = "AddBottomSheetGround"
    }

    fun formatDateTime(dateTime: LocalDateTime): String {
        val date = dateTime.format(DateTimeFormatter.ofPattern("dd MMMM"))
        return "$date"
    }

    fun parseDateTime(dateTimeString: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return LocalDateTime.parse(dateTimeString, formatter)
    }

    fun convertDays(input: String): String {
        // Создаем отображение сокращений на полные названия
        val daysMap = mapOf(
            "Пн" to "Понедельник",
            "Вт" to "Вторник",
            "Ср" to "Среда",
            "Чт" to "Четверг",
            "Пт" to "Пятница",
            "Сб" to "Суббота",
            "Вс" to "Воскресенье"
        )

        // Разбиваем входную строку по запятой и пробелу, затем преобразуем в полные названия
        return input.split(", ")
            .joinToString("\n") { daysMap[it.trim()] ?: it }
    }

}
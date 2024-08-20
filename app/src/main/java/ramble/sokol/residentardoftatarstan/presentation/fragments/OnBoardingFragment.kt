package ramble.sokol.residentardoftatarstan.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ramble.sokol.residentardoftatarstan.R
import ramble.sokol.residentardoftatarstan.databinding.FragmentOnBoardingBinding

class OnBoardingFragment : Fragment() {

    private var binding: FragmentOnBoardingBinding? = null
    private var currentQuestions = 1
    private var currentClick = 0
    private val questions: Array<String> = arrayOf("Относитесь ли вы к одной из следующих категорий?", "Занимаетесь ли вы спортом? ", "Ходите ли вы на городские мероприятия?", "Посещаете ли вы какие-то секции? ", "Есть ли у вас машина?", "Что вам наиболее интересно?", "Как часто вы пользуетесь общественным траснпортом?", "Какие виды транспорта вы используете чаще всего?", "Как часто вы путешествуете по другим городам Татарстана", "Какие факторы влияют на ваш выбор места для посещения?")
    private val variant1: Array<String> = arrayOf("Пенсионер", "Часто заниамюсь спортом", "Да, часто хожу", "Да, посещаю", "Да, часто езжу", "Концерты", "Ежедневно", "Автобус", "Часто", "Цена")
    private val variant2: Array<String> = arrayOf("Студент", "Редко, но занимаюсь", "Редко хожу", "Ребенок посещает", "Да, но езжу редко", "Концерты", "Несколько раз в неделю", "Метро", "Только во время отпуска", "Отзывы")
    private val variant3: Array<String> = arrayOf("Инвалид", "Только смотрю эфиры", "Смотрю по телевизору", "Нет, но хочу", "Нет, но собираюсь купить", "Лекции и митапы", "Раз в месяц", "трамвай", "Очень редко", "Близость к дому")
    private val variant4: Array<String> = arrayOf("Не отношусь", "Нет", "Вообще не хожу", "Не посещаю и не хочу", "Нет", "Спорт", "Не пользуюсь", "Не пользуюсь", "Никогда", "Популярность")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding!!.nextQuestions.setOnClickListener {
            if (currentClick != 0) {
                if (currentQuestions == 10){
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.layout_fragment, EndTestFragment())
                    transaction.disallowAddToBackStack()
                    transaction.commit()
                }else {
                    currentQuestions += 1
                    currentClick = 0
                    logicTest()
                }
            }
        }
        binding!!.cardAnswer1.setOnClickListener{
            if (currentClick != 1){
                currentClick = 1
                binding!!.cardAnswer1.setBackgroundResource(R.drawable.questions_background_true)
                binding!!.textCard1.setTextColor(resources.getColor(R.color.color_main, null))
                binding!!.cardAnswer4.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard4.setTextColor(resources.getColor(R.color.text_questions, null))
                binding!!.cardAnswer3.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard3.setTextColor(resources.getColor(R.color.text_questions, null))
                binding!!.cardAnswer2.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard2.setTextColor(resources.getColor(R.color.text_questions, null))
            }else{
                currentClick = 0
                binding!!.cardAnswer1.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard1.setTextColor(resources.getColor(R.color.text_questions, null))
            }
        }
        binding!!.cardAnswer2.setOnClickListener{
            if (currentClick != 2){
                currentClick = 2
                binding!!.cardAnswer2.setBackgroundResource(R.drawable.questions_background_true)
                binding!!.textCard2.setTextColor(resources.getColor(R.color.color_main, null))
                binding!!.cardAnswer4.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard4.setTextColor(resources.getColor(R.color.text_questions, null))
                binding!!.cardAnswer3.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard3.setTextColor(resources.getColor(R.color.text_questions, null))
                binding!!.cardAnswer1.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard1.setTextColor(resources.getColor(R.color.text_questions, null))
            }else{
                currentClick = 0
                binding!!.cardAnswer2.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard2.setTextColor(resources.getColor(R.color.text_questions, null))
            }
        }
        binding!!.cardAnswer3.setOnClickListener{
            if (currentClick != 3){
                currentClick = 3
                binding!!.cardAnswer3.setBackgroundResource(R.drawable.questions_background_true)
                binding!!.textCard3.setTextColor(resources.getColor(R.color.color_main, null))
                binding!!.cardAnswer4.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard4.setTextColor(resources.getColor(R.color.text_questions, null))
                binding!!.cardAnswer2.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard2.setTextColor(resources.getColor(R.color.text_questions, null))
                binding!!.cardAnswer1.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard1.setTextColor(resources.getColor(R.color.text_questions, null))
            }else{
                currentClick = 0
                binding!!.cardAnswer3.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard3.setTextColor(resources.getColor(R.color.text_questions, null))
            }
        }

        binding!!.cardAnswer4.setOnClickListener{
            if (currentClick != 4){
                currentClick = 4
                binding!!.cardAnswer4.setBackgroundResource(R.drawable.questions_background_true)
                binding!!.textCard4.setTextColor(resources.getColor(R.color.color_main, null))
                binding!!.cardAnswer3.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard3.setTextColor(resources.getColor(R.color.text_questions, null))
                binding!!.cardAnswer2.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard2.setTextColor(resources.getColor(R.color.text_questions, null))
                binding!!.cardAnswer1.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard1.setTextColor(resources.getColor(R.color.text_questions, null))
            }else{
                currentClick = 0
                binding!!.cardAnswer4.setBackgroundResource(R.drawable.questions_background)
                binding!!.textCard4.setTextColor(resources.getColor(R.color.text_questions, null))
            }
        }
        logicTest()
    }

    private fun logicTest(){
        binding!!.cardAnswer4.setBackgroundResource(R.drawable.questions_background)
        binding!!.textCard4.setTextColor(resources.getColor(R.color.text_questions, null))
        binding!!.cardAnswer3.setBackgroundResource(R.drawable.questions_background)
        binding!!.textCard3.setTextColor(resources.getColor(R.color.text_questions, null))
        binding!!.cardAnswer2.setBackgroundResource(R.drawable.questions_background)
        binding!!.textCard2.setTextColor(resources.getColor(R.color.text_questions, null))
        binding!!.cardAnswer1.setBackgroundResource(R.drawable.questions_background)
        binding!!.textCard1.setTextColor(resources.getColor(R.color.text_questions, null))
        binding!!.textNumberQuestions.text = "${currentQuestions} из 10 вопросов"
        binding!!.questionsText.text = questions[currentQuestions-1]
        binding!!.textCard1.text = variant1[currentQuestions-1]
        binding!!.textCard2.text = variant2[currentQuestions-1]
        binding!!.textCard3.text = variant3[currentQuestions-1]
        binding!!.textCard4.text = variant4[currentQuestions-1]
        viewBack()
    }

    private fun viewBack(){
        when (currentQuestions){
            1 -> binding!!.view1.setBackgroundResource(R.drawable.background_test_progress_true)
            2 -> binding!!.view2.setBackgroundResource(R.drawable.background_test_progress_true)
            3 -> binding!!.view3.setBackgroundResource(R.drawable.background_test_progress_true)
            4 -> binding!!.view4.setBackgroundResource(R.drawable.background_test_progress_true)
            5 -> binding!!.view5.setBackgroundResource(R.drawable.background_test_progress_true)
            6 -> binding!!.view6.setBackgroundResource(R.drawable.background_test_progress_true)
            7 -> binding!!.view7.setBackgroundResource(R.drawable.background_test_progress_true)
            8 -> binding!!.view8.setBackgroundResource(R.drawable.background_test_progress_true)
            9 -> binding!!.view9.setBackgroundResource(R.drawable.background_test_progress_true)
            10 -> binding!!.view10.setBackgroundResource(R.drawable.background_test_progress_true)
        }
    }

}
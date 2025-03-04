package com.rsschool.quiz.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.core.view.get
import com.rsschool.quiz.databinding.FragmentQuizBinding
import com.rsschool.quiz.interfaces.OnClosePressedListener
import com.rsschool.quiz.interfaces.OnNextPressedListener
import com.rsschool.quiz.managers.QuestionManager

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding: FragmentQuizBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionId = arguments?.getInt(QUESTION_ID) ?: 0
        val questionData = QuestionManager.getQuestion(questionId)

        with(binding) {
            toolbar.setNavigationOnClickListener {
                if (parentFragmentManager.backStackEntryCount == 0) {
                    val onClosePressedListener = context as OnClosePressedListener
                    onClosePressedListener.onClosePressed()
                } else {
                    parentFragmentManager.popBackStack()
                }
            }

            if (questionId == 0) {
                previousButton.isEnabled = false
            }

            toolbar.title = "Question ${questionId + 1}"
            question.text = questionData.question
            for (answer in questionData.answers) {
                radioGroup.addView(
                    RadioButton(context).apply {
                        text = answer
                    }
                )
            }
            radioGroup.setOnCheckedChangeListener { radioGroup, i ->
                nextButton.isEnabled = true
                questionData.selectedAnswerId = i - radioGroup[0].id
            }
            if (questionData.selectedAnswerId != null) {
                radioGroup.check(radioGroup[0].id + questionData.selectedAnswerId!!)
            }

            previousButton.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            nextButton.setOnClickListener {
                val onNextPressedListener = context as OnNextPressedListener
                onNextPressedListener.onNextPressed(questionId + 1)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val QUESTION_ID = "QUESTION_ID"

        fun newInstance(
            questionId: Int
        ): QuizFragment {
            return QuizFragment().apply {
                arguments = bundleOf(
                    QUESTION_ID to questionId
                )
            }
        }
    }
}
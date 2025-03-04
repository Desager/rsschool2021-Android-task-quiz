package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.fragments.QuizFragment
import com.rsschool.quiz.fragments.ResultFragment
import com.rsschool.quiz.interfaces.OnClosePressedListener
import com.rsschool.quiz.interfaces.OnNextPressedListener
import com.rsschool.quiz.interfaces.OnRestartPressedListener
import com.rsschool.quiz.managers.QuestionManager

class MainActivity : AppCompatActivity(), OnNextPressedListener,
    OnRestartPressedListener, OnClosePressedListener {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        startQuiz()
    }

    override fun onNextPressed(nextQuestionId: Int) {
        if (nextQuestionId > QuestionManager.getQuestionMaxId()) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(binding.container.id, ResultFragment())
                .addToBackStack(null)
                .commit()
        } else {
            setQuestionTheme(nextQuestionId)

            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(binding.container.id, QuizFragment.newInstance(nextQuestionId))
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onRestartPressed() = startQuiz()

    override fun onClosePressed() {
        this.finish()
    }

    private fun startQuiz() {
        QuestionManager.resetQuestionList()
        setQuestionTheme(0)

        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(binding.container.id, QuizFragment.newInstance(0))
            .commit()
    }

    private fun setQuestionTheme(questionId: Int) {
        when(questionId) {
            0 -> setTheme(R.style.Theme_Quiz_First)
            1 -> setTheme(R.style.Theme_Quiz_Second)
            2 -> setTheme(R.style.Theme_Quiz_Third)
            3 -> setTheme(R.style.Theme_Quiz_Fourth)
            4 -> setTheme(R.style.Theme_Quiz_Fifth)
        }
    }
}
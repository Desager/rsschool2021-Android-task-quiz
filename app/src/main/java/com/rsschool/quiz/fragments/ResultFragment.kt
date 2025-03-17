package com.rsschool.quiz.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentResultBinding
import com.rsschool.quiz.interfaces.OnClosePressedListener
import com.rsschool.quiz.interfaces.OnRestartPressedListener
import com.rsschool.quiz.managers.QuestionManager

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding: FragmentResultBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val resultPercent = QuestionManager.getResultPercent()
            result.text = resources.getString(R.string.result, resultPercent)

            share.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtras(
                    bundleOf(
                        Intent.EXTRA_TEXT to resources.getString(R.string.result_share, resultPercent)
                    )
                )
                startActivity(intent)
            }
            back.setOnClickListener {
                val onRestartPressedListener = context as OnRestartPressedListener
                onRestartPressedListener.onRestartPressed()
            }
            close.setOnClickListener {
                val onClosePressedListener = context as OnClosePressedListener
                onClosePressedListener.onClosePressed()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
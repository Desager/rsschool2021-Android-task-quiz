package com.rsschool.quiz.managers

object QuestionManager {

    private var questionList: List<Question> = listOf(
        Question(
            question = "What is the right answer??",
            answers = listOf(
                "This one",
                "Or this one?",
                "Or maybe that one?",
                "There's no right answers",
                "The fog is coming"
            ),
            correctAnswer = "There's no right answers"
        ),
        Question(
            question = "Who is in your walls?",
            answers = listOf(
                "Me",
                "The thing",
                "Nothing",
                "Nobody",
                "I have no walls"
            ),
            correctAnswer = "The thing"
        ),
        Question(
            question = "When?",
            answers = listOf(
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                "today.",
                ),
            correctAnswer = "today."
        ),
        Question(
            question = "What is your favourite animal?",
            answers = listOf(
                "Dog",
                "Cat",
                "Elephant",
                "Capybara",
                "Cow"
            ),
            correctAnswer = "Cat"
        ),
        Question(
            question = "Android?",
            answers = listOf(
                "Android"
            ),
            correctAnswer = "Android"
        )
    )

    fun getQuestion(questionId: Int) = questionList[questionId]

    fun getQuestionMaxId() = questionList.size - 1

    fun getResultPercent(): Float {
        val rightAnswersCount: Float = questionList.filter { it.selectedAnswerId == it.answers.indexOf(it.correctAnswer) }.size.toFloat()

        return rightAnswersCount / questionList.size * 100
    }

    fun resetQuestionList() {
        questionList.forEach {
            it.selectedAnswerId = null
            it.answers = it.answers.shuffled()
        }
        questionList = questionList.shuffled()
    }
}

data class Question(
    val question: String,
    var answers: List<String>,
    val correctAnswer: String,
    var selectedAnswerId: Int? = null
)
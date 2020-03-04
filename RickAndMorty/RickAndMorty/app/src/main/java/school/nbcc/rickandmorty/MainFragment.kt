package school.nbcc.rickandmorty


import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment

import android.widget.Toast
import androidx.core.os.bundleOf

import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.fragment_main.*

import school.nbcc.rickandmorty.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            navController
        ) || super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener{nextQuestion(it)}
        binding.btnBack.setOnClickListener{lastQuestion(it)}
        binding.btnTrue.setOnClickListener{isTrue()}
        binding.btnFalse.setOnClickListener{isFalse()}
        binding.btnCheat.setOnClickListener{toCheat()}

    }

//    class Question(var question: Int, var answer: Boolean)

    var counter = 0;
    var firstTime = true;

    private val questionBank = listOf(
        Question(R.string.question_1, false),
        Question(R.string.question_2, true),
        Question(R.string.question_3, true),
        Question(R.string.question_4, false),
        Question(R.string.question_5, false),
        Question(R.string.question_6, true),
        Question(R.string.question_7, false),
        Question(R.string.question_8, true),
        Question(R.string.question_9, false),
        Question(R.string.question_10, false),
        Question(R.string.question_11, false),
        Question(R.string.question_12, true),
        Question(R.string.question_13, false),
        Question(R.string.question_14, true),
        Question(R.string.question_15, false),
        Question(R.string.question_16, false),
        Question(R.string.question_17, true),
        Question(R.string.question_18, false),
        Question(R.string.question_19, false),
        Question(R.string.question_20, true)
    )

    private fun nextQuestion(view: View) {
        if (firstTime) {
            var questionNumber = questionBank[counter].question
            binding.apply {
                tvQuestions.text = resources.getString(questionNumber)
            }
            firstTime = false;

        } else {
            counter++;
            if (counter >= questionBank.size){
                counter = 0;
            }

            var questionNumber = questionBank[counter].question
            binding.apply {
                tvQuestions.text = resources.getString(questionNumber)
            }
        }
    }

    private fun lastQuestion(view: View) {
        if (firstTime){
            firstTime = false;
        }
        if (counter == 0){
            counter = 19;
            var questionNumber = questionBank[counter].question
            binding.apply {
                tvQuestions.text = resources.getString(questionNumber)
            }
        } else {
            counter--;
            var questionNumber = questionBank[counter].question
            binding.apply {
                tvQuestions.text = resources.getString(questionNumber)
            }
        }

    }

    private fun isTrue() {
        var questionNumber = questionBank[counter].answer
        if (questionNumber == true) {
            Toast.makeText(this.context, "You are correct!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                this.context,
                "You really should watch this show.... it's awesome!",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    private fun isFalse() {
        var questionNumber = questionBank[counter].answer
        if (questionNumber == false) {
            Toast.makeText(this.context, "You are correct!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                this.context,
                "You really should watch this show.... it's awesome!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun toCheat(){
        if (tvQuestions.text != "Welcome to the Rick and Morty Quiz!"){

            var questionHolder = tvQuestions.text;
            var answerHolder = questionBank[counter].answer

            var bundle = Bundle()
            bundle.putString("question", questionHolder.toString())
            bundle.putBoolean("answer", answerHolder)

            navController = view!!.findNavController()
            navController.navigate(R.id.action_mainFragment_to_cheatFragment, bundle)
        } else {
            Toast.makeText(
                this.context,"You need to pick a question first",Toast.LENGTH_LONG
            ).show()
        }

    }



}

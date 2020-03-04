package school.nbcc.rickandmorty


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.fragment_cheat.*
import school.nbcc.rickandmorty.databinding.FragmentCheatBinding

/**
 * A simple [Fragment] subclass.
 */
class CheatFragment : Fragment() {

    private lateinit var binding: FragmentCheatBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cheat,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var theQuestion = arguments!!.getString("question") ?: "No Question Selected"
        var theAnswer = arguments!!.getBoolean("answer")

        txtQuestion.text = theQuestion
        binding.btnCancel.setOnClickListener{goBack()}
        binding.btnCheat.setOnClickListener{goAheadAndCheatYouCoward(theAnswer)}
    }


    private fun goBack(){
        activity!!.onBackPressed()
    }

    private fun goAheadAndCheatYouCoward(answer: Boolean){
        txtAnswer.text = answer.toString().capitalize()
    }



}

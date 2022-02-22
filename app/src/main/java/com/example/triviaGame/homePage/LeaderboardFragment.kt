package com.example.triviaGame.homePage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.triviaGame.R
import com.example.triviaGame.database.TriviaDao
import com.example.triviaGame.database.TriviaDatabase
import com.example.triviaGame.database.TriviaViewModel
import com.example.triviaGame.entities.PlayerEntity
import kotlinx.android.synthetic.main.activity_view_leaderboard.*
import kotlinx.android.synthetic.main.fragment_leaderboard.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [LeaderboardFragmentOne.newInstance] factory method to
 * create an instance of this fragment.
 */
class LeaderboardFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (param1 != null) {
            tvLeaderboardCategory.setText("")
            var category_to_show = "Leaders for $param1"
            tvLeaderboardCategory.setText(category_to_show)
        }


        val dataSource = TriviaDatabase.getInstance(this.requireContext().applicationContext).TriviaDao
        var triviaViewModel = TriviaViewModel(dataSource, this.requireActivity().application)
        if (param1 == null) {
            param1 = "CS"
        }

        var leaders = listOf<LeaderboardEntry>()
        if (param1.equals("CS")) {
            leaders = triviaViewModel.get_cs_leaders()
            Log.d("Leaders", "Switched to CS")
        } else if (param1 .equals("Healthcare")) {
            leaders = triviaViewModel.get_healthcare_leaders()
            Log.d("Leaders", "Switched to healthcare")
        } else {
            leaders = triviaViewModel.get_financial_leaders()
            Log.d("Leaders", "Switched to finances")
        }

        leaders = leaders.sortedByDescending { it.score }
        leaderboardRecyclerView.adapter = LeaderboardAdapter(leaders)
        leaderboardRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        leaderboardRecyclerView.setHasFixedSize(true)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LeaderboardFragmentOne.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            LeaderboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
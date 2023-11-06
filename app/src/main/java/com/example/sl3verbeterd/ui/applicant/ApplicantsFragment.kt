package com.example.sl3verbeterd.ui.applicant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sl3verbeterd.ProfileListAdapter
import com.example.sl3verbeterd.databinding.FragmentApplicantBinding

class ApplicantsFragment : Fragment() {

    private lateinit var applicantsViewModel: ApplicantsViewModel
    private lateinit var profileAdapter: ProfileListAdapter
    private var _binding: FragmentApplicantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApplicantBinding.inflate(inflater, container, false)
        val root: View = binding.root

        applicantsViewModel = ViewModelProvider(this).get(ApplicantsViewModel::class.java)
        profileAdapter = ProfileListAdapter()

        val recyclerView: RecyclerView = binding.import androidx.recyclerview.widget.RecyclerViewrecyclerview
        recyclerView.adapter = profileAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe LiveData from the ViewModel
        applicantsViewModel.profiles.observe(viewLifecycleOwner, { profiles ->
            // Update the UI when the profiles data changes
            profileAdapter.submitList(profiles)
        })

        binding.filterButton.setOnClickListener {
            // Handle filter button click if needed
        }

        binding.sortButton.setOnClickListener {
            // Handle sort button click if needed
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

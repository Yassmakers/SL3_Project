package com.example.sl3verbeterd.ui.applicant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sl3verbeterd.HireHubDao
import com.example.sl3verbeterd.HireHubDatabase
import com.example.sl3verbeterd.databinding.FragmentApplicantBinding


class ApplicantsFragment : Fragment() {

    private var _binding: FragmentApplicantBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val applicantsViewModel =
            ViewModelProvider(this).get(ApplicantsViewModel::class.java)

        _binding = FragmentApplicantBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.searchText
        applicantsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

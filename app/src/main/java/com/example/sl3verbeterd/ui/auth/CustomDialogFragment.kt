package com.example.sl3verbeterd.ui.auth

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.sl3verbeterd.R

class CustomDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_custom_dialog, container, false)
        val okButton = view.findViewById<Button>(R.id.ok_button)
        okButton.setOnClickListener {
            // Dismiss the dialog when OK button is clicked
            dismiss()
        }
        return view
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        // Handle any additional cleanup if needed
    }
}

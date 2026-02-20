package com.cargram.app.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cargram.app.R

class CreatePostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnShare = view.findViewById<android.widget.TextView>(R.id.btn_share)
        val btnCancel = view.findViewById<android.widget.TextView>(R.id.btn_cancel)
        val btnGallery = view.findViewById<Button>(R.id.btn_gallery)
        val btnCamera = view.findViewById<Button>(R.id.btn_camera)
        val etCaption = view.findViewById<EditText>(R.id.et_caption)

        btnShare.setOnClickListener {
            val caption = etCaption.text.toString().trim()
            if (caption.isEmpty()) {
                Toast.makeText(requireContext(), "Add a caption for your ride!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Post shared! 🏎️ #GearGram", Toast.LENGTH_LONG).show()
                etCaption.text?.clear()
            }
        }

        btnCancel.setOnClickListener {
            requireActivity().onBackPressed()
        }

        btnGallery.setOnClickListener {
            Toast.makeText(requireContext(), "Gallery picker coming soon!", Toast.LENGTH_SHORT).show()
        }

        btnCamera.setOnClickListener {
            Toast.makeText(requireContext(), "Camera coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
}

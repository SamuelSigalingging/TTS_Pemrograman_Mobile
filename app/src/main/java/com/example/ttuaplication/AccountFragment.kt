package com.example.ttuaplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.ttuaplication.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pre-fill fields if data exists in ViewModel
        viewModel.nama.value?.let { binding.etNama.setText(it) }
        viewModel.nim.value?.let { binding.etNim.setText(it) }
        viewModel.ipk.value?.let { binding.etIpk.setText(it) }

        binding.tvLang.setOnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.loadFragment(SettingFragment())
        }

        binding.btnSimpan.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val nim = binding.etNim.text.toString().trim()
            val ipkStr = binding.etIpk.text.toString().trim()

            var isValid = true

            if (nama.isEmpty()) {
                binding.etNama.error = "Nama tidak boleh kosong"
                isValid = false
            }
            if (nim.isEmpty()) {
                binding.etNim.error = "NIM tidak boleh kosong"
                isValid = false
            }
            if (ipkStr.isEmpty()) {
                binding.etIpk.error = "IPK tidak boleh kosong"
                isValid = false
            } else {
                val ipk = ipkStr.toDoubleOrNull()
                if (ipk == null || ipk < 0.0 || ipk > 4.0) {
                    binding.etIpk.error = "IPK harus antara 0.00 dan 4.00"
                    isValid = false
                }
            }

            if (isValid) {
                // Update Shared ViewModel
                viewModel.setProfile(nama, nim, ipkStr)
                
                // Navigate to DetailAccountActivity
                val intent = Intent(requireContext(), DetailAccountActivity::class.java).apply {
                    putExtra("EXTRA_NAMA", nama)
                    putExtra("EXTRA_NIM", nim)
                    putExtra("EXTRA_IPK", ipkStr)
                }
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.demoapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.demoapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val action = Runnable {
            nav2NextFragment()
        }
        val mainHandler = Handler(Looper.getMainLooper())

        binding.buttonFirst.setOnClickListener {
            nav2NextFragment()
            mainHandler.removeCallbacks(action) //如果跳过，就直接展现主界面
        }

        mainHandler.postDelayed(action, 3000) // 展现广告
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun nav2NextFragment() {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

}
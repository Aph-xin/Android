package com.example.demoapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.demoapp.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val uploadHandler by lazy {
        // 下载完成发message8
        Handler(Looper.getMainLooper()) { msg ->
            when (msg.what) {
                UploadManager.UPLOAD_START -> toast("Upload Start")
                UploadManager.UPLOAD_FAIL -> toast("Upload Failed")
                UploadManager.UPLOAD_SUCCESS -> toast("Upload Success")
                UploadManager.UPLOAD_PROGRESS -> {
                    val progress = msg.obj as Float
                    binding.progressView.progress = progress
                }
            }
            true
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAnr.setOnClickListener { v ->
            demoANR()
        }

        binding.btnWrongThread.setOnClickListener { v ->
            demoChangeUIonNonUIThread()
        }

        binding.btnProgress.setOnClickListener { v ->
            UploadManager.startUpload(uploadHandler)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /** 主线程进行耗时操作 **/
    private fun demoANR() {
        while (true) {
            println()
        }
    }

    private fun demoChangeUIonNonUIThread() {
        Thread {
            binding.btnWrongThread.text = "try change text"
        }.start()
    }

    private fun toast(text: String) {
        val toast = Toast(context)
        toast.setText(text)
        toast.show()
    }

}
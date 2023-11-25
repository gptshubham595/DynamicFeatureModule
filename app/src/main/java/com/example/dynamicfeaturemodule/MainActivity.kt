package com.example.dynamicfeaturemodule

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dynamicfeaturemodule.databinding.ActivityMainBinding
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var progressDialog: ProgressDialog
    private lateinit var splitInstallManager: SplitInstallManager

    private var mySessionId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splitInstallManager = SplitInstallManagerFactory.create(this)

        val intent = Intent()
        binding.btnOnInstall.setOnClickListener {
            intent.setClassName(
                BuildConfig.APPLICATION_ID,
                "com.example.oninstallmodule.MainOnInstallActivity"
            )
            startActivity(intent)
        }

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("onDemand")


        binding.btnOnDemand.setOnClickListener {
            startDownload()
        }
    }

    private fun startDownload() {
        val request = SplitInstallRequest.newBuilder()
            .addModule("onDemandModule")
            .build()
        val listener = SplitInstallStateUpdatedListener {
            if (it.sessionId() == mySessionId) {
                when (it.status()) {
                    SplitInstallSessionStatus.DOWNLOADING -> {
                        progressDialog.setMessage("Downloading")
                        progressDialog.show()
                    }

                    SplitInstallSessionStatus.INSTALLED -> {
                        progressDialog.dismiss()
                        val intent = Intent()
                        intent.setClassName(
                            BuildConfig.APPLICATION_ID,
                            "com.example.ondemand.MainOnDemandActivity"
                        )
                        startActivity(intent)
                    }

                    SplitInstallSessionStatus.CANCELED -> {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
                    }

                    SplitInstallSessionStatus.CANCELING -> {
                        progressDialog.setMessage("Canceling")
                        progressDialog.show()
                    }

                    SplitInstallSessionStatus.DOWNLOADED -> {
                        Toast.makeText(this, "Downloaded", Toast.LENGTH_SHORT).show()
                    }

                    SplitInstallSessionStatus.FAILED -> {
                    }

                    SplitInstallSessionStatus.INSTALLING -> {
                        progressDialog.setMessage("Installing")
                        progressDialog.show()
                    }

                    SplitInstallSessionStatus.PENDING -> {
                        progressDialog.setMessage("Pending")
                        progressDialog.show()
                    }

                    SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                    }

                    SplitInstallSessionStatus.UNKNOWN -> {
                    }
                }

            }
        }
        splitInstallManager?.registerListener(listener)

        splitInstallManager?.startInstall(request)
            ?.addOnSuccessListener { sessionId ->
                mySessionId = sessionId
            }
            ?.addOnFailureListener {
            }
    }
}
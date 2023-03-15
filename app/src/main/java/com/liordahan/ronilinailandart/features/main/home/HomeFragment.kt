package com.liordahan.ronilinailandart.features.main.home


import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.liordahan.ronilinailandart.R
import com.liordahan.ronilinailandart.base.BaseFragment
import com.liordahan.ronilinailandart.databinding.FragmentHomeBinding
import com.liordahan.ronilinailandart.extenstions.showErrorToast
import com.liordahan.ronilinailandart.features.main.home.adapters.WorkingHoursAdapter
import com.liordahan.ronilinailandart.features.main.home.models.WorkingHours
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeFragmentViewModel, FragmentHomeBinding, HomeFragmentState>(R.layout.fragment_home) {

    override val viewModel: HomeFragmentViewModel by viewModels()
    override val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var workingHoursAdapter: WorkingHoursAdapter

    override fun initUi() {
        setWorkingHoursAdapter()
        binding.apply {
            homeFragmentWazeLogo.setOnClickListener {
                launchWaze()
            }

            homeFragmentWhatsappLogo.setOnClickListener {
                launchWhatApp()
            }

            homeFragmentInstagramLogo.setOnClickListener {
                openInstagram()
            }

            homeFragmentFacebookLogo.setOnClickListener {
                openFacebook()
            }

            homeFragmentBtn.setOnClickListener {
                val action = HomeFragmentDirections.toServiceAndPricing()
                findNavController().navigate(action)
            }
        }
    }

    private fun setWorkingHoursAdapter(){
        binding.fragmentHomeWorkingHoursRv.apply {
            workingHoursAdapter = WorkingHoursAdapter()
            adapter = workingHoursAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun updateWorkingHours(data: List<WorkingHours>?){
        workingHoursAdapter.submitList(data)
    }

    override fun observeViewModelState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collect { state ->
                        updateInputsWithState(state)
                    }
                }
            }
        }
    }

    override fun observeViewModelEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect { event ->
                    when (event) {
                        is HomeFragmentEvents.Error -> showErrorToast(event.message)
                    }
                }
            }
        }
    }

    override fun updateInputsWithState(state: HomeFragmentState?) {
        binding.apply {
            homeFragImageSlider.setImageList(state?.images ?: emptyList(), scaleType = ScaleTypes.CENTER_CROP)
        }
        updateWorkingHours(state?.workingHours)
    }

    private fun openInstagram() {
        val uriForApp: Uri = Uri.parse("https://www.instagram.com/ronilidahan/?igshid=YmMyMTA2M2Y%3D")
        val forApp = Intent(Intent.ACTION_VIEW, uriForApp)

        val uriForBrowser: Uri = Uri.parse("https://www.instagram.com/ronilidahan/?igshid=YmMyMTA2M2Y%3D")
        val forBrowser = Intent(Intent.ACTION_VIEW, uriForBrowser)

        forApp.component =
            ComponentName(
                "com.instagram.android",
                "com.instagram.android.activity.UrlHandlerActivity"
            )

        try {
            startActivity(forApp)
        } catch (e: ActivityNotFoundException) {
            startActivity(forBrowser)
        }
    }

    private fun openFacebook() {
        val uriForApp: Uri = Uri.parse("https://www.facebook.com/ronili.twito?mibextid=ZbWKwL")
        val forApp = Intent(Intent.ACTION_VIEW, uriForApp)

        val uriForBrowser: Uri = Uri.parse("https://www.facebook.com/ronili.twito?mibextid=ZbWKwL")
        val forBrowser = Intent(Intent.ACTION_VIEW, uriForBrowser)

        forApp.component =
            ComponentName(
                "com.facebook.katana",
                "com.facebook.katana.ProfileTabHostActivity"
            )

        try {
            startActivity(forApp)
        } catch (e: ActivityNotFoundException) {
            startActivity(forBrowser)
        }
    }

    private fun launchWhatApp() {
        try {
            val url = Uri.parse("https://api.whatsapp.com/send?phone=$+972508721533")
            val i = Intent(Intent.ACTION_VIEW, url)
            i.setPackage("com.whatsapp")
            startActivity(i)
        } catch (ex: Exception) {
            showErrorToast("No Whatsapp app detected on the device")
        }
    }

    private fun launchWaze(){
        try {
            val url = "https://waze.com/ul?ll=32.505735,34.924699&navigate=yes"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            showErrorToast("No Waze app detected on the device")
        }

    }

}
package com.example.myapplication1


import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import org.json.JSONException
import org.json.JSONObject


/**
 * A simple [Fragment] subclass.
 */
class Default_mainpageFragment : Fragment() {
    ////
    internal var qrScanIntegrator: IntentIntegrator? = null
    internal var btnScan: Button? = null
    ////
    lateinit var sliderView: SliderView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.activity_default_mainpage,
            container, false)
        sliderView = view.findViewById(R.id.imageSlider)
        val adapter = SliderAdapterExample(this.activity!!.applicationContext)
        adapter.count = 3
        sliderView.setSliderAdapter(adapter)
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION)
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH)
        sliderView.setIndicatorSelectedColor(Color.WHITE)
        sliderView.setIndicatorUnselectedColor(Color.GRAY)
        sliderView.scrollTimeInSec = 3
        sliderView.startAutoCycle()
        sliderView.setOnIndicatorClickListener(DrawController.ClickListener { position ->
            sliderView.setCurrentPagePosition(
                position
            )
        })

        val button1: Button = view.findViewById<View>(R.id.buttonFB) as Button
        button1.setOnClickListener {
            val intent = Intent(this.activity, FBContent::class.java)
            startActivity(intent)
        }
        val button2: Button = view.findViewById<View>(R.id.buttonPay) as Button
        button2.setOnClickListener {
            val intent = Intent(this.activity, DisplayQRCode::class.java)
            startActivity(intent)
        }

        val buttonPayment: Button = view.findViewById<View>(R.id.buttonPay) as Button
        buttonPayment.setOnClickListener {
            val intent = Intent(this.activity, PayPage::class.java)
            startActivity(intent)



        }
        /////
        btnScan = view.findViewById<View>(R.id.buttonScan) as Button
        btnScan!!.setOnClickListener { performAction()}
        qrScanIntegrator = IntentIntegrator(this.context as Activity?)
        qrScanIntegrator?.setOrientationLocked(false)

        // Different Customization option...
        qrScanIntegrator?.setPrompt(getString(R.string.scan_qr_code))
        qrScanIntegrator?.setCameraId(0)  // Use a specific camera of the device
        qrScanIntegrator?.setBeepEnabled(true)
        qrScanIntegrator?.setBarcodeImageEnabled(true)
        ///////

        return view

    }

    private fun performAction() {
        qrScanIntegrator?.initiateScan()
    }

}

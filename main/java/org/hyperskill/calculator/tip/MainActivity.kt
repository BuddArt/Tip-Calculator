package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tipPercentTV = findViewById<TextView>(R.id.tip_percent_tv)
        val seekBar = findViewById<SeekBar>(R.id.seek_bar)
        val editText = findViewById<EditText>(R.id.edit_text)
        val billValueTV = findViewById<TextView>(R.id.bill_value_tv)
        val tipAmountTV = findViewById<TextView>(R.id.tip_amount_tv)

        var prgs = 15
        var count = 0
        var sCount: Double = 0.00
        var bigDec: BigDecimal
        var countAmount: Double

        editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty() && s.toString().toDouble() > 0) {
                    count = 1
                    sCount = s.toString().toDouble()
                    countAmount = (prgs.toDouble() / 100) * sCount
                    bigDec = countAmount.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                    tipPercentTV.text = "Tip: $prgs%"
                    billValueTV.text = "Bill Value: $" + String.format("%.2f", sCount)
                    tipAmountTV.text = "Tip Amount: $$bigDec"
                }
                if (s.isNullOrEmpty() || s.toString().toDouble() <= 0 || editText.text.isEmpty()) {
                    count = 0
                    tipPercentTV.text = ""
                    billValueTV.text = ""
                    tipAmountTV.text = ""
                }
            }
        })

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                prgs = progress
                if (count > 0) {
                    countAmount = (prgs.toDouble() / 100) * sCount
                    bigDec = countAmount.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
                    tipPercentTV.text = "Tip: $progress%"
                    tipAmountTV.text = "Tip Amount: $$bigDec"
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }
}
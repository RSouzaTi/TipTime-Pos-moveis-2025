package br.edu.utfpr.tiptime_pos_moveis_2025

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.utfpr.tiptime_pos_moveis_2025.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {//Fim do MainActivity

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
        if (savedInstanceState != null) {
            binding.tipResult.text = savedInstanceState.getString("cost_of_tip")
        } else {
            val formattedTip = NumberFormat.getCurrencyInstance().format(0)
            binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
                    }
        println("onCreate() executado")

        Log.d("MainActivity", "onCreate() executado")
        Log.w("MainActivity", "onCreate() executado")
        Log.i("MainActivity", "onCreate() executado")
        Log.v("MainActivity", "onCreate() executado")
        Log.wtf("MainActivity", "onCreate() executado")
        Log.e("MainActivity", "onCreate() executado")





    } //fim do onCreate()


    override fun onStart() {
        super.onStart()
        println("onStart() executado")
    }

    override fun onResume() {
        super.onResume()
        println("onResume() executado")
    }

    override fun onPause() {
        super.onPause()
        println("onPause() executado")
    }

    override fun onStop() {
        super.onStop()
        println("onStop() executado")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy() executado")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart() executado")
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("cost_of_tip", binding.tipResult.text.toString())

    }

    private fun calculateTip() {

        //entrada
        val stringInTextField = binding.costOfService.text.toString()

        val cost = stringInTextField.toDoubleOrNull() ?: return

        val selectedId = binding.tipOption.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //processamento
        var tip = cost * tipPercentage
        val roundUp = binding.roundUpSwitch.isChecked

        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        //saído
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }


}
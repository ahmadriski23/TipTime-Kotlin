package com.example.tiptime
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var costOfServiceEditText: EditText
    private lateinit var tipOptionsRadioGroup: RadioGroup
    private lateinit var roundUpSwitch: Switch
    private lateinit var tipResultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        costOfServiceEditText = findViewById(R.id.cost_of_service)
        tipOptionsRadioGroup = findViewById(R.id.tip_options)
        roundUpSwitch = findViewById(R.id.round_up_switch)
        tipResultTextView = findViewById(R.id.tip_result)
        val calculateButton: TextView = findViewById(R.id.calculate_button)
        calculateButton.setOnClickListener { calculateTip() }
    }

    // memanggil halaman kedua
    fun halamanKedua(view: View){
        val intent = Intent(this,HitungMakanan::class.java)
        startActivity(intent)
    }

    // function calculate tip
    private fun calculateTip() {
       try {
           // cost of service dari EditText
           val costOfServiceText = costOfServiceEditText.text.toString()

           // kondisi jika cost of service kosong
           if(costOfServiceText.isEmpty()){
               // menampilkan pesan jika cost of service belum diisi
               Toast.makeText(this, "Please enter the cost of service, friends!",Toast.LENGTH_SHORT).show()
               return
           }
           // memformat variabel cost of service dari string ke double
           val costOfService = costOfServiceText.toDouble()

           // persentase tip dari radio button yang dipilih
           val selectedRadioButtonId = tipOptionsRadioGroup.checkedRadioButtonId
           var tipPercentage = 0.0

           when(selectedRadioButtonId){
               R.id.option_twenty_percent -> tipPercentage = 0.20
               R.id.option_eighteen_percent -> tipPercentage = 0.18
               R.id.option_fifteen_percent -> tipPercentage = 0.15
           }

           // menghitung jumlah tip
           var tipAmount = costOfService * tipPercentage

           // dibulatkan ke atas jika switch aktif
           if (roundUpSwitch.isChecked){
               tipAmount = Math.ceil(tipAmount)
           }

           // menampilkan jumlah tip yang telah dihitung
           val decimalFormat = DecimalFormat("#.##")
           val formattedTipAmount = decimalFormat.format(tipAmount)
           tipResultTextView.text = getString(R.string.tip_amount,formattedTipAmount)
       } catch (e: NumberFormatException){
           // menampilkan pesan jika format angka tidak valid
           Toast.makeText(this,"Invalid input. Please enter a valid number for cost of service friends!",Toast.LENGTH_SHORT).show()
       } catch (e: Exception){
           e.printStackTrace()
       }
}}
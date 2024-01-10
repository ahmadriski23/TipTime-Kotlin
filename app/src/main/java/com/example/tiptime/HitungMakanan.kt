package com.example.tiptime
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import android.widget.CheckBox
import android.widget.Button
import android.widget.Toast


class HitungMakanan : AppCompatActivity() {
    // membuat variabel untuk melacak jumlah item dari berbagai makanan
    private var jumlahSeblak = 0
    private var jumlahCoklat = 0
    private var jumlahBasreng = 0

    // menginisialisasi sebelum digunakan pertama kali
    private lateinit var checkBoxSeblak: CheckBox
    private lateinit var checkBoxCoklat: CheckBox
    private lateinit var checkBoxBasreng: CheckBox
    private lateinit var textViewJumlahSeblak: TextView
    private lateinit var textViewJumlahCoklat:TextView
    private lateinit var textViewJumlahBasreng:TextView
    private lateinit var textViewTotalHargaProduk:TextView
    private lateinit var buttonBuy: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hitung_makanan)

        // menginisialisasi komponen UI yang ada
        checkBoxSeblak = findViewById(R.id.check_box_seblak)
        checkBoxCoklat = findViewById(R.id.check_box_coklat)
        checkBoxBasreng = findViewById(R.id.check_box_basreng)
        textViewJumlahSeblak = findViewById(R.id.text_view_jumlah_seblak)
        textViewJumlahCoklat = findViewById(R.id.text_view_jumlah_coklat)
        textViewJumlahBasreng = findViewById(R.id.text_view_jumlah_basreng)
        textViewTotalHargaProduk = findViewById(R.id.text_view_total_harga_produk)
        buttonBuy = findViewById(R.id.button_buy)
        buttonBuy.setOnClickListener {
            // Call a function to handle the Buy button click
            showToast()
        }
    }

    // function untuk button tambah jumlah seblak
    fun tambahJumlahSeblak(view: View) {
        jumlahSeblak++
        updateJumlahTextView(textViewJumlahSeblak, jumlahSeblak)
    }

    // function untuk button kurang jumlah seblak
    fun kurangJumlahSeblak(view: View) {
        if (jumlahSeblak > 0) {
            jumlahSeblak--
            updateJumlahTextView(textViewJumlahSeblak, jumlahSeblak)
        }
    }

    // function untuk button tambah jumlah coklat
    fun tambahJumlahCoklat(view: View) {
        jumlahCoklat++
        updateJumlahTextView(textViewJumlahCoklat, jumlahCoklat)
    }

    // function untuk button kurang jumlah seblak
    fun kurangJumlahCoklat(view: View) {
        if (jumlahCoklat > 0) {
            jumlahCoklat--
            updateJumlahTextView(textViewJumlahCoklat, jumlahCoklat)
        }
    }

    // function untuk button tambah jumlah basreng
    fun tambahJumlahBasreng(view: View) {
        jumlahBasreng++
        updateJumlahTextView(textViewJumlahBasreng, jumlahBasreng)
    }

    // function untuk button kurang jumlah seblak
    fun kurangJumlahBasreng(view: View) {
        if (jumlahBasreng > 0) {
            jumlahBasreng--
            updateJumlahTextView(textViewJumlahBasreng, jumlahBasreng)
        }
    }

    // function untuk button menghitung total produk
    fun hitungTotalProduk(view: View) {
        val hargaNasi = jumlahSeblak * 6000
        val hargaCoklat = jumlahCoklat * 25000
        val hargaSayur = jumlahBasreng * 12000

        val totalHargaProduk = hargaNasi + hargaCoklat + hargaSayur
        textViewTotalHargaProduk.text = "Total Price: Rp $totalHargaProduk"
    }

    // function untuk menampilkan jumlah dari berbagai item secara update
    private fun updateJumlahTextView(textView: TextView, jumlah: Int) {
        textView.text = "Amount: $jumlah"
    }

    // function untuk memunculkan pesan jika kita klik button buy
    private fun showToast() {
        val totalHarga = textViewTotalHargaProduk.text.toString()

        if (totalHarga == "Total Price: Rp 0" || totalHarga.isEmpty()) {
            val toast = Toast.makeText(this, "Sorry, please choose a product and calculate it first", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
            return
        }
        val message = "Thank you for buying my products.\n\n$totalHarga"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
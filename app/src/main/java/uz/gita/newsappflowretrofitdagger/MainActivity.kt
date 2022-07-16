package uz.gita.newsappflowretrofitdagger

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // AndroidEntryPoint qo'yamiz chunki activity tarkibidagi fragmenlar dependencyga muhtoj
class MainActivity : AppCompatActivity(R.layout.activity_main)

// MainActivity ning tanasida ortiqcha code yoq bolganligi uchun tanasini o'chirib tasshlaasak boladi.
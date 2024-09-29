package com.example.androidmaster.superheroapp

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.R
import com.example.androidmaster.databinding.ActivityDetailSuperheroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSuperheroBinding

    companion object {
        const val EXTRA_ID = "extra_id"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)

    }


    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superheroDetailt =
                getRetrofit().create(ApiService::class.java).getSuperheroDetail(id)
            if (superheroDetailt.body() != null) {
                runOnUiThread{
                    createUI(superheroDetailt.body()!!)
                }
            }
        }

    }

    private fun createUI(superhero: SuperHeroDetailResponse){
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
        binding.tvSuperHeroName.text=superhero.name
        prepareStats(superhero.powerStats)
        binding.tvSuperHeroRealName.text=superhero.biography.fullName
        binding.tvSuperHeroPublisher.text=superhero.biography.publisher

    }



    private fun prepareStats(powerstats: PowerStatsResponse){


        updateHeight(binding.viewCombat, powerstats.combat)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewStrength, powerstats.strength)
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewPower, powerstats.power)

    }
    private fun updateHeight(view: View, stat: String){
        val params=view.layoutParams
        params.height= pxToDp(stat.toFloat())
        view.layoutParams=params
    }

    private fun pxToDp(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

}
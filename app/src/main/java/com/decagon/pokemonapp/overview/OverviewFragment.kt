package com.decagon.pokemonapp.overview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.*
import com.decagon.pokemon.util.getName
import com.decagon.pokemonapp.R
import com.decagon.pokemonapp.detail.DetailFragment
import com.decagon.pokemonapp.network.NetworkConnection
import com.decagon.pokemonapp.network.PokemonProperty
import com.decagon.pokemonapp.network.Result
import kotlinx.android.synthetic.main.overview_fragment.*

class OverviewFragment : Fragment() {
    val title: String by lazy { this.getName() }


//    companion object {
//        fun newInstance() = OverviewFragment()
//    }

    private lateinit var viewModel: OverviewViewModel

    private val adapter = PokemonGridAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)
        return inflater.inflate(R.layout.overview_fragment, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val liveDataSuccess = MutableLiveData<PokemonProperty>()
        val liveDataError = MediatorLiveData<String>()

        // on activity
        val networkConnection = NetworkConnection(activity?.applicationContext!!)
        var loadTime = 0
        networkConnection.observe(requireActivity(), Observer {
            if (it){
                if (loadTime>0){
                tvOfflineMessage.visibility = View.GONE
                tvOnlineMessage.visibility = View.VISIBLE
                tvOnlineMessage.postDelayed({ tvOnlineMessage.visibility = View.GONE }, 3000)
//                    loadTime=0
                }

                firePokemonServices()
            }else{
                //back online
//                tvMessage.text = "You are offline"
                loadTime++
                tvOnlineMessage.visibility = View.GONE
                tvOfflineMessage.visibility = View.VISIBLE
//                tvMessage.setBackgroundColor((R.color.colorError))
//                tvMessage.bringToFront()
                Toast.makeText(requireContext(), "No network", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun firePokemonServices(){
        viewModel.getPokemonProperties(object : LiveDataCallBack, RecyclerViewClickListener {
            override fun <T> getResponse(lifeData: LiveData<T>) {
                lifeData.observe(viewLifecycleOwner, Observer { poke ->
                    Log.i(title, "A $poke")
//                    if (poke is PokemonProperty)
                    val res = poke as PokemonProperty
                    Log.i(title, "Count ${res.results}")
//                    if(res is String)
                    adapter.setResult(res.results)
                    adapter.listener = this
                    rvGrid.adapter = adapter
                })
            }

            override fun onRecyclerViewClickListener(view: View, pokemon: Result) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.pokemonFrag, DetailFragment(pokemon))?.addToBackStack(null)?.commit()
            }
        })
    }
}
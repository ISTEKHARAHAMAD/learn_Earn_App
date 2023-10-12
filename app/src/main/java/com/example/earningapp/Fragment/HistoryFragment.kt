package com.example.earningapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earningapp.R
import com.example.earningapp.Withdrawal
import com.example.earningapp.adaptor.HistoryAdaptor
import com.example.earningapp.adaptor.categoryadaptor
import com.example.earningapp.databinding.FragmentHistoryBinding
import com.example.earningapp.modle.HistoryModelClass
import com.example.earningapp.modle.User
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.Collections

class HistoryFragment : Fragment() {
  val binding by lazy {
      FragmentHistoryBinding.inflate(layoutInflater)
  }
    lateinit var adaptor:HistoryAdaptor
private var ListHistory= ArrayList<HistoryModelClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.database.reference.child("playerCoinHistory").child(Firebase.auth.currentUser!!.uid)
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    ListHistory.clear()
                     var ListHistory1= ArrayList<HistoryModelClass>()
                   for (datasnapshot in snapshot.children){
                       var data=datasnapshot.getValue(HistoryModelClass::class.java)
                       ListHistory1.add(data!!)
                   }
                    Collections.reverse(ListHistory1)
                    ListHistory.addAll(ListHistory1)
                    adaptor.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                  //  TODO("Not yet implemented")
                }

            })

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.CoinWithdrawal.setOnClickListener {
            val bottomSheetDialog: BottomSheetDialogFragment = Withdrawal()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"TEST")
            bottomSheetDialog.enterTransition
        }
        binding.CoinWithdrawal1.setOnClickListener {
            val bottomSheetDialog:BottomSheetDialogFragment = Withdrawal()
            bottomSheetDialog.show(requireActivity().supportFragmentManager,"TEST")
            bottomSheetDialog.enterTransition
        }
        binding.HistoryRecyclerView.layoutManager=LinearLayoutManager(requireContext())
         adaptor = HistoryAdaptor(ListHistory)
        binding.HistoryRecyclerView.adapter= adaptor
        binding.HistoryRecyclerView.setHasFixedSize(true)
        // Inflate the layout for this fragment
        Firebase.database.reference.child("Users")
            .child(Firebase.auth.currentUser!!.uid)
            .addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var user=snapshot.getValue<User>()
                        binding.Name.text=user?.name

                    }

                    override fun onCancelled(error: DatabaseError) {
                        // TODO("Not yet implemented")
                    }

                }
            )
        Firebase.database.reference.child("playerCoin").child(Firebase.auth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                      var  currentCoin = snapshot.getValue() as Long
                        binding.CoinWithdrawal.text = currentCoin.toString()

                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        return binding.root
    }
    companion object {
    }
}
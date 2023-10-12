package com.example.earningapp.adaptor

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.earningapp.QuizActivity
import com.example.earningapp.databinding.CategoryitemBinding
import com.example.earningapp.modle.categoryModelClass

class categoryadaptor(
    var categoryList: ArrayList<categoryModelClass>,
    var requireActivity: FragmentActivity
) : RecyclerView.Adapter<categoryadaptor.MycategoryViewHolder>() {
    class MycategoryViewHolder(var binding: CategoryitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MycategoryViewHolder {
        return MycategoryViewHolder(
            CategoryitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: MycategoryViewHolder, position: Int) {
        val datalist = categoryList[position]
        holder.binding.categoryImage.setImageResource(datalist.catImage)
        holder.binding.category.text = datalist.catText
        holder.binding.categorybtn.setOnClickListener {

            var intent = Intent(requireActivity, QuizActivity::class.java)

            intent.putExtra("categoryimg", datalist.catImage)
            intent.putExtra("questionType",datalist.catText)
            requireActivity.startActivity(intent)


        }
    }

}
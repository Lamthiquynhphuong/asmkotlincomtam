package edu.phuong.comtam.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.phuong.comtam.Model.FoodAdmin

class ReviewFood(
    private val foods: MutableList<FoodAdmin>,
    private val onFoodClick: (FoodAdmin) -> Unit
) : RecyclerView.Adapter<ReviewFood.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foods[position]
        holder.nameTextView.text = food.name
        holder.itemView.setOnClickListener { onFoodClick(food) }
    }

    override fun getItemCount(): Int = foods.size

    fun setFoods(newFoods: MutableList<FoodAdmin>) {
        foods.clear()
        foods.addAll(newFoods)
        notifyDataSetChanged()
    }
}

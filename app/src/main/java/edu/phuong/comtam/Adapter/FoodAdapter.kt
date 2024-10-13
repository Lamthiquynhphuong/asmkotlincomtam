import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.phuong.comtam.Model.Food
import edu.phuong.comtam.R

class FoodAdapter(private var foodList: List<Food>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    // Danh sách đầy đủ để lọc
    private var foodListFull: List<Food> = foodList.toList()

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName: TextView = itemView.findViewById(R.id.food_name)
        val foodDescription: TextView = itemView.findViewById(R.id.food_description)
        val foodImage: ImageView = itemView.findViewById(R.id.food_image) // Thêm ImageView cho hình ảnh
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentFood = foodList[position]
        holder.foodName.text = currentFood.name
        holder.foodDescription.text = currentFood.description

        // Tải hình ảnh từ thumbnail URL
        Glide.with(holder.itemView.context)
            .load(currentFood.thumbnail) // Sử dụng trường imageUrl trong Food
//            .placeholder(R.drawable.placeholder) // Hình ảnh mặc định khi đang tải
//            .error(R.drawable.error) // Hình ảnh hiển thị khi có lỗi
            .into(holder.foodImage)
    }

    override fun getItemCount(): Int = foodList.size

    // Phương thức filter để tìm kiếm
    fun filter(query: String?) {
        foodList = if (query.isNullOrEmpty()) {
            foodListFull // Nếu không có từ khóa, trả về danh sách đầy đủ
        } else {
            foodListFull.filter {
                it.name.contains(query, ignoreCase = true) || // Tìm theo tên món ăn
                        it.description.contains(query, ignoreCase = true) // Tìm theo mô tả
            }
        }
        notifyDataSetChanged() // Cập nhật giao diện
    }
}

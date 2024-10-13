import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.phuong.comtam.Model.Food
import edu.phuong.comtam.R
import edu.phuong.comtam.RetrofitAll.FoodRetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    private var foodList: List<Food> = emptyList() // Dữ liệu món ăn sẽ được lấy từ API

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val searchView: SearchView = view.findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                foodAdapter.filter(newText)
                return true
            }
        })

        fetchFoods() // Gọi hàm để lấy dữ liệu món ăn

        return view
    }

    private fun fetchFoods() {
        FoodRetrofitClient.instance.getFoods().enqueue(object : Callback<List<Food>> {
            override fun onResponse(call: Call<List<Food>>, response: Response<List<Food>>) {
                if (response.isSuccessful) {
                    foodList = response.body() ?: emptyList()
                    foodAdapter = FoodAdapter(foodList)
                    recyclerView.adapter = foodAdapter // Gán adapter sau khi có dữ liệu
                } else {
                    // Xử lý lỗi phản hồi không thành công
                }
            }

            override fun onFailure(call: Call<List<Food>>, t: Throwable) {
                // Xử lý lỗi mạng
            }
        })
    }
}

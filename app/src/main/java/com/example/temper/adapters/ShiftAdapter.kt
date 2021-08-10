package com.example.temper.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.temper.R
import com.example.temper.models.Shift
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_item_main_work.view.*

class ShiftAdapter : RecyclerView.Adapter<ShiftAdapter.ShiftViewHolder>() {

    var items = ArrayList<Shift>()

    fun setUpdatedData(items: ArrayList<Shift>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShiftAdapter.ShiftViewHolder {
        return ShiftViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.single_item_main_work, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShiftAdapter.ShiftViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ShiftViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val heading: TextView = view.txtHeading
        private val distance: TextView = view.txtWorkDistance
        private val title: TextView = view.txtTitle
        private val time: TextView = view.txtTime
        private val poster: RoundedImageView = view.imgPoster
        private val rate: TextView = view.txtRate

        fun bind(data : Shift){
            heading.text = if(data.job?.category?.name != null) data.job.category.name else "N/A"
            distance.text = "10 KM"
            title.text = if(data.job?.title != null) data.job.title else "N/A"

            val startTime = if(data.recurringShiftSchedule?.startsAt != null) data.recurringShiftSchedule.startsAt.substring(0,5) else "N/A"
            val endTime = if(data.recurringShiftSchedule?.endsAt != null) data.recurringShiftSchedule.endsAt.substring(0,5) else "N/A"
            time.text = "$startTime - $endTime"

            rate.text = if(data.earningsPerHour?.amount != null) String.format("%.2f", data.earningsPerHour.amount) else "N/A"

            Picasso.get()
                .load(data.job?.links?.hero380Image)
                .placeholder(R.drawable.placeholder)
                .into(poster)
        }
    }
}
package com.app.assessment.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.assessment.R
import com.app.assessment.data.model.Cast
import com.app.assessment.data.model.ProductionCompanies
import com.app.assessment.utils.Constants.IMAGE_BASE_URL
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class CastAdapter(private val castList: List<Cast>) :
    RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val castMember = castList[position]
        holder.bind(castMember)
    }

    override fun getItemCount() = castList.size

    class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val castProfile: CircleImageView = itemView.findViewById(R.id.castProfile)
        private val castName: TextView = itemView.findViewById(R.id.castName)
        fun bind(castMember: Cast) {
            castName.text = castMember.name
            Picasso.get().load("$IMAGE_BASE_URL${castMember.profilePath}")
                .into(castProfile)
        }
    }
}

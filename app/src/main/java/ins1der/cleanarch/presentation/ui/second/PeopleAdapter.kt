package ins1der.cleanarch.presentation.ui.second

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ins1der.cleanarch.R
import ins1der.cleanarch.presentation.ui.models.PersonUI
import kotlinx.android.synthetic.main.item_person.view.*

class PeopleAdapter: PagedListAdapter<PersonUI, PeopleAdapter.PersonViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder =
        PersonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false))

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class PersonViewHolder(val root: View): RecyclerView.ViewHolder(root) {

        fun bind(person: PersonUI) {
            root.name_tv.text = person.name
            root.url_tv.text = person.url
        }
    }

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<PersonUI>() {

            override fun areItemsTheSame(oldItem: PersonUI, newItem: PersonUI): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: PersonUI, newItem: PersonUI): Boolean {
                return oldItem == newItem
            }

        }
    }
}
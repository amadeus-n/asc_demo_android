package com.ekoenterprise.socialdemo.custom.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ekoenterprise.socialdemo.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ekoenterprise.socialdemo.custom.adapter.ListListener
import com.ekoenterprise.socialdemo.custom.adapter.ContentAdapter
import com.ekoenterprise.socialdemo.custom.adapter.TrendsAdapter
import com.ekoenterprise.socialdemo.custom.model.ListMemberItem
import com.ekoenterprise.socialdemo.custom.model.Member

class HomePageFragment : Fragment(), ListListener {

    companion object {
        fun newInstance() = HomePageFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val memberList = getDataFromJson()
        val memberAdapter = TrendsAdapter(memberList, this)
        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            isNestedScrollingEnabled =false
            adapter = memberAdapter
            onFlingListener = null
        }
        memberAdapter.notifyDataSetChanged()

        val contentData = memberList[0].data
        val contentAdapter = ContentAdapter(contentData, this)
        view.findViewById<RecyclerView>(R.id.content_recycler).apply{
            layoutManager = LinearLayoutManager(activity)
            adapter =contentAdapter
            onFlingListener=null
        }
        contentAdapter.notifyDataSetChanged()
    }

    private fun getDataFromJson(): ArrayList<Member> {
        val inputStream = requireActivity().assets.open("trends_mock.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, charset("UTF-8"))
        val listType = object : TypeToken<ListMemberItem>() {}.type
        val gson = Gson().fromJson<ListMemberItem>(json, listType)
        return gson.members
    }

    override fun onItemClick() {

    }
}
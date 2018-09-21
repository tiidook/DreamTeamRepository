package me.codezfox.extension

import android.content.res.Resources
import android.support.v7.widget.RecyclerView


inline val RecyclerView.ViewHolder.context
    get() = this.itemView.context!!

inline val RecyclerView.ViewHolder.resources: Resources
    get() = this.itemView.context!!.resources

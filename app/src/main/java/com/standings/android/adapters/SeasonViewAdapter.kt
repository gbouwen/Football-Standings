package com.standings.android.adapters

import androidx.annotation.LayoutRes
import com.standings.android.model.season.Season
import com.standings.android.utils.RecyclerViewBindingInterface

class SeasonViewAdapter(
    list: List<Season>,
    @LayoutRes layoutId: Int,
    bindingInterface: RecyclerViewBindingInterface<Season>,
) : BaseRecyclerViewAdapter<Season>(list, layoutId, bindingInterface)
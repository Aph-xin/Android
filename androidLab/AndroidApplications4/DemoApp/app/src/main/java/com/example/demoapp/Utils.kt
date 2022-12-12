package com.example.demoapp

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToInt

/**
 * Created by huangxin.2020 on 2022/10/30
 * @author huangxin.2020@bytedance.com
 */
inline val Number.dpFloat
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        toFloat(),
        Resources.getSystem().displayMetrics
    )

inline val Number.dp
    get() = dpFloat.roundToInt()

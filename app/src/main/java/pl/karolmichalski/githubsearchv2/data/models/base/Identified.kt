package pl.karolmichalski.githubsearchv2.data.models.base

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Identified(open val id: Int): Parcelable
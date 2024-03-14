package nl.joramkwetters.duogerman.data

import com.google.gson.annotations.SerializedName

data class NewsResponse(

	@field:SerializedName("news")
	val news: List<NewsItem?>? = null,

	@field:SerializedName("regional")
	val regional: List<Any?>? = null,

	@field:SerializedName("nextPage")
	val nextPage: String? = null,

	@field:SerializedName("newStoriesCountLink")
	val newStoriesCountLink: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class TagsItem(

	@field:SerializedName("tag")
	val tag: String? = null
)

data class TeaserImage(

	@field:SerializedName("copyright")
	val copyright: String? = null,

	@field:SerializedName("alttext")
	val alttext: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("imageVariants")
	val imageVariants: ImageVariants? = null
)

data class NewsItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("copyright")
	val copyright: String? = null,

	@field:SerializedName("sophoraId")
	val sophoraId: String? = null,

	@field:SerializedName("alttext")
	val alttext: String? = null,

	@field:SerializedName("streams")
	val streams: Streams? = null,

	@field:SerializedName("updateCheckUrl")
	val updateCheckUrl: String? = null,

	@field:SerializedName("externalId")
	val externalId: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("tracking")
	val tracking: List<TrackingItem?>? = null,

	@field:SerializedName("teaserImage")
	val teaserImage: TeaserImage? = null,

	@field:SerializedName("tags")
	val tags: List<TagsItem?>? = null,

	@field:SerializedName("breakingNews")
	val breakingNews: Boolean? = null,

	@field:SerializedName("geotags")
	val geotags: List<Any?>? = null,

	@field:SerializedName("regionIds")
	val regionIds: List<Int?>? = null,

	@field:SerializedName("topline")
	val topline: String? = null,

	@field:SerializedName("firstSentence")
	val firstSentence: String? = null,

	@field:SerializedName("regionId")
	val regionId: Int? = null,

	@field:SerializedName("details")
	val details: String? = null,

	@field:SerializedName("shareURL")
	val shareURL: String? = null,

	@field:SerializedName("detailsweb")
	val detailsweb: String? = null,

	@field:SerializedName("ressort")
	val ressort: String? = null,

	@field:SerializedName("comments")
	val comments: String? = null
)

data class ImageVariants(

	@field:SerializedName("1x1-144")
	val jsonMember1x1144: String? = null,

	@field:SerializedName("16x9-384")
	val jsonMember16x9384: String? = null,

	@field:SerializedName("1x1-640")
	val jsonMember1x1640: String? = null,

	@field:SerializedName("16x9-640")
	val jsonMember16x9640: String? = null,

	@field:SerializedName("16x9-256")
	val jsonMember16x9256: String? = null,

	@field:SerializedName("16x9-960")
	val jsonMember16x9960: String? = null,

	@field:SerializedName("16x9-1920")
	val jsonMember16x91920: String? = null,

	@field:SerializedName("16x9-512")
	val jsonMember16x9512: String? = null,

	@field:SerializedName("16x9-1280")
	val jsonMember16x91280: String? = null,

	@field:SerializedName("1x1-256")
	val jsonMember1x1256: String? = null,

	@field:SerializedName("1x1-432")
	val jsonMember1x1432: String? = null,

	@field:SerializedName("1x1-840")
	val jsonMember1x1840: String? = null
)

data class Streams(

	@field:SerializedName("h264m")
	val h264m: String? = null,

	@field:SerializedName("h264s")
	val h264s: String? = null,

	@field:SerializedName("h264xl")
	val h264xl: String? = null,

	@field:SerializedName("adaptivestreaming")
	val adaptivestreaming: String? = null
)

data class TrackingItem(

	@field:SerializedName("bcr")
	val bcr: String? = null,

	@field:SerializedName("pdt")
	val pdt: String? = null,

	@field:SerializedName("pti")
	val pti: String? = null,

	@field:SerializedName("src")
	val src: String? = null,

	@field:SerializedName("av_full_show")
	val avFullShow: Boolean? = null,

	@field:SerializedName("otp")
	val otp: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("ctp")
	val ctp: String? = null,

	@field:SerializedName("sid")
	val sid: String? = null,

	@field:SerializedName("cid")
	val cid: String? = null,

	@field:SerializedName("c10")
	val c10: String? = null,

	@field:SerializedName("c12")
	val c12: String? = null,

	@field:SerializedName("length")
	val length: String? = null,

	@field:SerializedName("c16")
	val c16: String? = null,

	@field:SerializedName("program")
	val program: String? = null,

	@field:SerializedName("c18")
	val c18: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("c2")
	val c2: String? = null,

	@field:SerializedName("type_nielsen")
	val typeNielsen: String? = null,

	@field:SerializedName("c5")
	val c5: String? = null,

	@field:SerializedName("c7")
	val c7: String? = null,

	@field:SerializedName("c8")
	val c8: String? = null,

	@field:SerializedName("assetid")
	val assetid: String? = null,

	@field:SerializedName("c9")
	val c9: String? = null,

	@field:SerializedName("av_air_time")
	val avAirTime: String? = null,

	@field:SerializedName("c15")
	val c15: String? = null
)

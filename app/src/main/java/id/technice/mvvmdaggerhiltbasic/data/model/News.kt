package id.technice.mvvmdaggerhiltbasic.data.model

class News:java.io.Serializable {
    var id:String = ""
    var content:String = ""
    var title:String = ""
    var thumb:String = ""
    var place_id:String? = null
    var category_id:String = ""
    var createdAt:String = ""
    var news_category: NewsCategory? = null
    var views:Int = 0
}
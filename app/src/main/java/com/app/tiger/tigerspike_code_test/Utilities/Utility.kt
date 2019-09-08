package com.app.tiger.tigerspike_code_test.Utilities



class Utility {
    companion object {
        fun cleanJsonCode(body: String?): String {
            var removeOpenBrakets = body!!.replace("jsonFlickrFeed(", "")
            var removedLastBracket = removeOpenBrakets.substring(0, removeOpenBrakets.count() - 1)
            return removedLastBracket;
        }
    }
}
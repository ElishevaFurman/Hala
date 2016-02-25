package com.example.faigy.hala;

/**
 * Created by Home on 2/25/2016.
 */
public class DownloadDataEvent {
MainActivity mainActivity;
        private final String tag;

        public DownloadDataEvent(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }
        public void download(){
           mainActivity.dataBaseOperations.makeJsonArrayRequest("news","http://162.243.100.186/news_array.php");
        }
}

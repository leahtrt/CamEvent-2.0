package ca.uwaterloo.camevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import Core.APIResult;
import Core.JSONDownloader;
import Core.UWOpenDataAPI;
import Events.Event;
import Events.EventTime;
import Events.EventsParser;


public class InitializeActivity extends AppCompatActivity implements JSONDownloader.onDownloadListener {

    //String apiKey = null;
    final String LOGCAT_TAG = "InitializeActivity";
    EventDBHandler eventDB= new EventDBHandler(this);

    EventsParser parser1 = new EventsParser();
    EventsParser parser2  =new EventsParser();
    EventsParser parser3 =new EventsParser();
    EventsParser parser4 =new EventsParser();
    EventsParser parser5 =new EventsParser();
    EventsParser parser6 =new EventsParser();
    EventsParser parser7 =new EventsParser();
    EventsParser parser8=new EventsParser();
    EventsParser parser9 =new EventsParser();
    EventsParser parser10 =new EventsParser();
    ArrayList<Event> events=null;
    //new Initilize();
    Initialize initilize = new Initialize();
    String url1;
    String url2;
    String url3;
    String url4;
    String url5;
    String url6;
    String url7;
    String url8;
    String url9;
    String url10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialize);
        //...
        // Step 1, do not redownload on screen rotation
        if(savedInstanceState == null) {
            //initilize.create();
            //clear all the data in the databases
            eventDB.deleteallEvents();
            events =initilize.getEvents();
            System.out.println(events.size());
            parser1.setParseType(EventsParser.ParseType.EVENTS_SITE_ID);
            parser2.setParseType(EventsParser.ParseType.EVENTS_SITE_ID);
            parser3.setParseType(EventsParser.ParseType.EVENTS_SITE_ID);
            parser4.setParseType(EventsParser.ParseType.EVENTS_SITE_ID);
            parser5.setParseType(EventsParser.ParseType.EVENTS_SITE_ID);
            parser6.setParseType(EventsParser.ParseType.EVENTS_SITE_ID);
            parser7.setParseType(EventsParser.ParseType.EVENTS_SITE_ID);
            parser8.setParseType(EventsParser.ParseType.EVENTS_SITE_ID);
            parser9.setParseType(EventsParser.ParseType.EVENTS_SITE_ID);
            parser10.setParseType(EventsParser.ParseType.EVENTS_SITE_ID);
            String apiKey = getString(R.string.api_key); // store your key in strings.xml
            // Step 2  events.get(1).getSite(), String.valueOf(events.get(1).getEventId())
            url1 = UWOpenDataAPI.buildURL(parser1.getEndPoint(events.get(1).getSite(), String.valueOf(events.get(1).getEventId())), apiKey);
            url2 = UWOpenDataAPI.buildURL(parser2.getEndPoint(events.get(2).getSite(), String.valueOf(events.get(2).getEventId())), apiKey);
            url3 = UWOpenDataAPI.buildURL(parser3.getEndPoint(events.get(3).getSite(), String.valueOf(events.get(3).getEventId())), apiKey);
            url4 = UWOpenDataAPI.buildURL(parser4.getEndPoint(events.get(4).getSite(), String.valueOf(events.get(4).getEventId())), apiKey);
            url5 = UWOpenDataAPI.buildURL(parser4.getEndPoint(events.get(5).getSite(), String.valueOf(events.get(5).getEventId())), apiKey);
            url6 = UWOpenDataAPI.buildURL(parser4.getEndPoint(events.get(6).getSite(), String.valueOf(events.get(6).getEventId())), apiKey);
            url7 = UWOpenDataAPI.buildURL(parser4.getEndPoint(events.get(7).getSite(), String.valueOf(events.get(7).getEventId())), apiKey);
            url8 = UWOpenDataAPI.buildURL(parser4.getEndPoint(events.get(8).getSite(), String.valueOf(events.get(8).getEventId())), apiKey);
            url9 = UWOpenDataAPI.buildURL(parser4.getEndPoint(events.get(9).getSite(), String.valueOf(events.get(9).getEventId())), apiKey);
            url10 = UWOpenDataAPI.buildURL(parser4.getEndPoint(events.get(10).getSite(), String.valueOf(events.get(10).getEventId())), apiKey);
            // Step 3
            JSONDownloader downloader = new JSONDownloader(url1,url2,url3,url4,url5,url6,url7,url8,url9,url10);
            downloader.setOnDownloadListener(this);
            downloader.start(); // starts download in seperate thread

        }
    }

    // ...

    @Override
    public void onDownloadFail(String givenURL, int index) {
        // this method is called if the download fails (No internet connection, timeout, bad url, missing permission etc).
        Log.i(LOGCAT_TAG, "Download failed.. url = " + givenURL);
    }

    @Override

    public void onDownloadComplete(APIResult apiResult) {
        // Step 4
        // parseJSON() will do different types of parsing depending on what ParseType you give it.
        // Each Parser has their own ParseTypes
        String givenUrl = apiResult.getUrl();
        if(givenUrl.equals(url1)){
            parser1.setAPIResult(apiResult);
            parser1.parseJSON();
            Event eventdisplay= parser1.getSpecificEvent();
            ArrayList<EventTime> eventTimes= eventdisplay.getTimes();
            Eventinfo eventinfo=new Eventinfo(eventdisplay.getEventTitle(),eventdisplay.getLocationName(),String.valueOf(eventdisplay.getLatitude()),String.valueOf(eventdisplay.getLongitude()),eventdisplay.getEventDescriptionRaw(),eventdisplay.getLink(),eventTimes.get(0).getStartDate());
            eventDB.addEventinfo(eventinfo);

        } else if(givenUrl.equals(url2)){
            parser2.setAPIResult(apiResult);
            parser2.parseJSON();
            Event eventdisplay= parser2.getSpecificEvent();
            ArrayList<EventTime> eventTimes= eventdisplay.getTimes();
            Eventinfo eventinfo=new Eventinfo(eventdisplay.getEventTitle(),eventdisplay.getLocationName(),String.valueOf(eventdisplay.getLatitude()),String.valueOf(eventdisplay.getLongitude()),eventdisplay.getEventDescriptionRaw(),eventdisplay.getLink(),eventTimes.get(0).getStartDate());
            eventDB.addEventinfo(eventinfo);
        }
        else if(givenUrl.equals(url3)){
            parser3.setAPIResult(apiResult);
            parser3.parseJSON();
            Event eventdisplay= parser3.getSpecificEvent();
            ArrayList<EventTime> eventTimes= eventdisplay.getTimes();
            Eventinfo eventinfo=new Eventinfo(eventdisplay.getEventTitle(),eventdisplay.getLocationName(),String.valueOf(eventdisplay.getLatitude()),String.valueOf(eventdisplay.getLongitude()),eventdisplay.getEventDescriptionRaw(),eventdisplay.getLink(),eventTimes.get(0).getStartDate());
            eventDB.addEventinfo(eventinfo);
        }
        else if(givenUrl.equals(url4)){
            parser4.setAPIResult(apiResult);
            parser4.parseJSON();
            Event eventdisplay= parser4.getSpecificEvent();
            ArrayList<EventTime> eventTimes= eventdisplay.getTimes();
            Eventinfo eventinfo=new Eventinfo(eventdisplay.getEventTitle(),eventdisplay.getLocationName(),String.valueOf(eventdisplay.getLatitude()),String.valueOf(eventdisplay.getLongitude()),eventdisplay.getEventDescriptionRaw(),eventdisplay.getLink(),eventTimes.get(0).getStartDate());
            eventDB.addEventinfo(eventinfo);
        }
        else if(givenUrl.equals(url5)){
            parser5.setAPIResult(apiResult);
            parser5.parseJSON();
            Event eventdisplay= parser5.getSpecificEvent();
            ArrayList<EventTime> eventTimes= eventdisplay.getTimes();
            Eventinfo eventinfo=new Eventinfo(eventdisplay.getEventTitle(),eventdisplay.getLocationName(),String.valueOf(eventdisplay.getLatitude()),String.valueOf(eventdisplay.getLongitude()),eventdisplay.getEventDescriptionRaw(),eventdisplay.getLink(),eventTimes.get(0).getStartDate());
            eventDB.addEventinfo(eventinfo);
        }
        else if(givenUrl.equals(url6)){
            parser6.setAPIResult(apiResult);
            parser6.parseJSON();
            Event eventdisplay= parser6.getSpecificEvent();
            ArrayList<EventTime> eventTimes= eventdisplay.getTimes();
            Eventinfo eventinfo=new Eventinfo(eventdisplay.getEventTitle(),eventdisplay.getLocationName(),String.valueOf(eventdisplay.getLatitude()),String.valueOf(eventdisplay.getLongitude()),eventdisplay.getEventDescriptionRaw(),eventdisplay.getLink(),eventTimes.get(0).getStartDate());
            eventDB.addEventinfo(eventinfo);
        }
        else if(givenUrl.equals(url7)){
            parser7.setAPIResult(apiResult);
            parser7.parseJSON();
            Event eventdisplay= parser7.getSpecificEvent();
            ArrayList<EventTime> eventTimes= eventdisplay.getTimes();
            Eventinfo eventinfo=new Eventinfo(eventdisplay.getEventTitle(),eventdisplay.getLocationName(),String.valueOf(eventdisplay.getLatitude()),String.valueOf(eventdisplay.getLongitude()),eventdisplay.getEventDescriptionRaw(),eventdisplay.getLink(),eventTimes.get(0).getStartDate());
            eventDB.addEventinfo(eventinfo);
        }
        else if(givenUrl.equals(url8)){
            parser8.setAPIResult(apiResult);
            parser8.parseJSON();
            Event eventdisplay= parser8.getSpecificEvent();
            ArrayList<EventTime> eventTimes= eventdisplay.getTimes();
            Eventinfo eventinfo=new Eventinfo(eventdisplay.getEventTitle(),eventdisplay.getLocationName(),String.valueOf(eventdisplay.getLatitude()),String.valueOf(eventdisplay.getLongitude()),eventdisplay.getEventDescriptionRaw(),eventdisplay.getLink(),eventTimes.get(0).getStartDate());
            eventDB.addEventinfo(eventinfo);
        }
        else if(givenUrl.equals(url9)){
            parser9.setAPIResult(apiResult);
            parser9.parseJSON();
            Event eventdisplay= parser9.getSpecificEvent();
            ArrayList<EventTime> eventTimes= eventdisplay.getTimes();
            Eventinfo eventinfo=new Eventinfo(eventdisplay.getEventTitle(),eventdisplay.getLocationName(),String.valueOf(eventdisplay.getLatitude()),String.valueOf(eventdisplay.getLongitude()),eventdisplay.getEventDescriptionRaw(),eventdisplay.getLink(),eventTimes.get(0).getStartDate());
            eventDB.addEventinfo(eventinfo);
        }
        else if(givenUrl.equals(url10)){
            parser10.setAPIResult(apiResult);
            parser10.parseJSON();
            Event eventdisplay= parser10.getSpecificEvent();
            ArrayList<EventTime> eventTimes= eventdisplay.getTimes();
            Eventinfo eventinfo=new Eventinfo(eventdisplay.getEventTitle(),eventdisplay.getLocationName(),String.valueOf(eventdisplay.getLatitude()),String.valueOf(eventdisplay.getLongitude()),eventdisplay.getEventDescriptionRaw(),eventdisplay.getLink(),eventTimes.get(0).getStartDate());
            eventDB.addEventinfo(eventinfo);
        }
    }
}


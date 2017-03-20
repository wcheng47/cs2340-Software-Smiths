package main.java.controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
import netscape.javascript.JSObject;
import main.java.model.SourceReport;


public class GMapController implements Initializable,
        MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    private Main myApp;

    public void setMainApp(Main mainApp) {
        myApp = mainApp;
    }

    @Override
    public void mapInitialized() {
        MapOptions mapOptions = new MapOptions();
        ArrayList<SourceReport> myReports = myApp.getSourceReportList();
        SourceReport report = myReports.get(myReports.size() - 1);
        double latDefault = report.get_latitude();
        double longDefault = report.get_longitude();
        LatLong center = new LatLong(latDefault, longDefault);
        mapOptions.center(center).zoom(8)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);
        map = mapView.createMap(mapOptions);
        for (SourceReport dummyReport : myReports) {
            MarkerOptions option = new MarkerOptions();
            double latitude = dummyReport.get_latitude();
            double longitude = dummyReport.get_longitude();
            LatLong posPair = new LatLong(latitude, longitude);
            option.position(posPair)
                    .visible(Boolean.TRUE)
                    .title(dummyReport.getTitle());
            mapOptions.center(posPair);

            Marker myMarker = new Marker(option);
            map.addMarker(myMarker);
            map.addUIEventHandler(myMarker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content(dummyReport.getDescription());

                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, myMarker);
                    });
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapView.addMapInializedListener(this);
    }

    @FXML
    public void onCloseMenu() {
        myApp.closeMapView();
    }
}

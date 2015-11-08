package com.calliemao.gasmeter.bus2;

import com.calliemao.gasmeter.clients.mapsresponse.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by myungoh on 11/8/15.
 */
public class GoogleGodLineEvent extends AbstractGodLineEvent {

    public enum Type {
        COMPLETED,
        STARTED
    }

    private PolylineOptions godLine;

    public GoogleGodLineEvent(Type type, PolylineOptions godLine) {
        super(type);
        this.godLine = godLine;
    }

    public PolylineOptions getGodLine() {
        return godLine;
    }
}

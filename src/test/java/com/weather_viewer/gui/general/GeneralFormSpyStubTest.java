package com.weather_viewer.gui.general;

import com.stubs.GeneralFormStart;
import com.weather_viewer.functional_layer.structs.weather.CurrentDay;
import com.weather_viewer.functional_layer.structs.weather.Workweek;
import com.weather_viewer.functional_layer.weather_connector.ApiConnector;
import com.weather_viewer.functional_layer.weather_connector.IWeatherConnector;
import com.weather_viewer.gui.previews.start.StartPreview;
import com.weather_viewer.gui.settings.Settings;
import com.weather_viewer.main.WeatherViewer;
import org.jetbrains.annotations.Contract;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;

import static helpers.TestData.RU_COUNTRY;
import static helpers.TestData.SAMARA;
import static org.mockito.Mockito.*;

public class GeneralFormSpyStubTest {

    private final static int TIMEOUT;

    static class PreviewFormStub extends StartPreview {
        private boolean isDispose;

        private PreviewFormStub() throws HeadlessException {
        }

        @Override
        public void dispose() {
            super.dispose();
            this.isDispose = true;
        }

        @Contract(pure = true)
        private boolean wasDisposed() {
            return this.isDispose;
        }
    }


    static {
        TIMEOUT = 7;
    }

    @Test
    public void counterCallsPreview() throws Exception {
        IWeatherConnector<CurrentDay> connectorWeatherForDay
                = spy(ApiConnector.build(SAMARA, RU_COUNTRY, CurrentDay.class));
        IWeatherConnector<Workweek> connectorForecastForTheWorkWeek
                = spy(ApiConnector.build(SAMARA, RU_COUNTRY, Workweek.class));
        IWeatherConnector<CurrentDay.SignatureCurrentDay> connectorSignatureDay
                = Mockito.mock(ApiConnector.class);

        final PreviewFormStub previewFormStub = new PreviewFormStub();

        Callable<GeneralFormStart> generalFormStartCallable = () -> new GeneralFormStart(previewFormStub, new Settings());
        WeatherViewer<GeneralFormStart> start = WeatherViewer.getInstance
                (connectorWeatherForDay, connectorForecastForTheWorkWeek, connectorSignatureDay, generalFormStartCallable).start();
        GeneralFormStart general = start.getGeneral();

        LocalDateTime maxTime = LocalDateTime.now().plusSeconds(TIMEOUT);
        while (!general.wasPerform() && LocalDateTime.now().isBefore(maxTime)) ;

        Assert.assertTrue("General form was not disposed", general.wasPerform());
        Assert.assertTrue("StartPreview was not disposed", previewFormStub.wasDisposed());

        verify(connectorWeatherForDay, times(1)).requestAndGetWeatherStruct();
        verify(connectorForecastForTheWorkWeek, times(1)).requestAndGetWeatherStruct();


    }
}

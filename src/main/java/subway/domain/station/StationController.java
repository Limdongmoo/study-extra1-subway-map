package subway.domain.station;

import subway.domain.line.LineFunction;
import subway.ui.input.InputView;
import subway.ui.output.LineOutputView;
import subway.ui.output.StationOutputView;

import java.util.List;
import java.util.stream.Collectors;

import static subway.domain.line.LineFunction.*;
import static subway.domain.station.StationFunction.*;

public class StationController {
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    public void run() {
        StationOutputView.printManagingPage();
        StationFunction function = StationFunction.from(InputView.readMain());
        navigate(function);
    }

    private void navigate(StationFunction function) {
        if (function.equals(CREATE_STATION)) {
            createStation(getStationName());
        }
        if (function.equals(DELETE_STATION)) {
            deleteStation(getStationName());
        }
        if (function.equals(SHOW_STATION)) {
            List<Station> stations = getStations();
            StationOutputView.printStations(stations.stream().map(Station::getName).collect(Collectors.toList()));
        }
    }

    public Station createStation(String stationName) {
        return stationService.createStation(stationName);
    }

    public void deleteStation(String stationName) {
        stationService.deleteStation(stationName);
    }

    public List<Station> getStations() {
        return stationService.getStations();
    }

    public String getStationName() {
        StationOutputView.askStationName();
        return InputView.readName();
    }
}

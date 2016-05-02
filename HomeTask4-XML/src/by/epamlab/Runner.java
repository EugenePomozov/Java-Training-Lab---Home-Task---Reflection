package by.epamlab;

import by.epamlab.beans.FlightSchedule;
import by.epamlab.utils.XmlUtils;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        final String LOAD_FILE = "src/schedule.xml";
        final String SAVE_FILE = "src/saved_shedule.xml";
        List<FlightSchedule> list = XmlUtils.loadScheduleListFromFile(LOAD_FILE);
        XmlUtils.saveScheduleListToFile(SAVE_FILE, list);
    }
}

package by.epamlab.utils;

import by.epamlab.beans.Aircraft;
import by.epamlab.beans.FlightSchedule;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class XmlUtils {

    public static void saveScheduleListToFile(String filename, List<FlightSchedule> list) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element mainElement = document.createElement("flight-schedule"); //create main tag in xml
            document.appendChild(mainElement);

            for (FlightSchedule schedule : list) {
                Element element = document.createElement("schedule"); //create set origin-destination
                mainElement.appendChild(createSchedule(document, element, schedule));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);

            System.out.println("Saving to file  was done");

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static Element createSchedule(Document doc, Element element, FlightSchedule fl) {
        addTextContent(doc, element, "airline", fl.getAirline());

        Element aircraft = doc.createElement("aircraft"); //create aircraft element with attributes
        addAttribute(doc, aircraft, "capacity", fl.getAircraft().getCapacity() + "");
        addAttribute(doc, aircraft, "type", fl.getAircraft().getType());
        addAttribute(doc, aircraft, "number", fl.getAircraft().getNumber());
        element.appendChild(aircraft);

        addTextContent(doc, element, "date-origin", fl.getDateOrigin());
        addTextContent(doc, element, "date-destination", fl.getDateDestination());
        addTextContent(doc, element, "class", fl.getClas());
        addTextContent(doc, element, "cost", fl.getTicketCost() + "");

        return element;
    }

    private static void addTextContent(Document doc, Element root, String param, String value) {
        Element ele = doc.createElement(param);
        ele.setTextContent(value);
        root.appendChild(ele);
    }

    private static void addAttribute(Document doc, Element root, String param, String value) {
        Attr attr = doc.createAttribute(param);
        attr.setValue(value);
        root.setAttributeNode(attr);
    }


    public static List<FlightSchedule> loadScheduleListFromFile(String filename) {
        List<FlightSchedule> list = new ArrayList<>();
        try {
            File xmlFile = new File(filename);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            NodeList scheduleList = document.getElementsByTagName("schedule"); //load all flight schedules

            for (int i = 0; i < scheduleList.getLength(); i++) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("y-MM-dd"); //parse xml date to util.Date
                FlightSchedule schedule = new FlightSchedule();

                Node node = scheduleList.item(i);
                Element element = (Element) node;

                String airline = element.getElementsByTagName("airline").item(0).getTextContent();
                String dateOrigin = element.getElementsByTagName("date-origin").item(0).getTextContent();
                String dateDest = element.getElementsByTagName("date-destination").item(0).getTextContent();
                String clas = element.getElementsByTagName("class").item(0).getTextContent();
                String ticketCost = element.getElementsByTagName("cost").item(0).getTextContent();

                NodeList aircraftList = element.getElementsByTagName("aircraft"); // load aircraft and attributes
                for (int j = 0; j < aircraftList.getLength(); j++) {
                    Node nNode = aircraftList.item(j);
                    Element eElement = (Element) nNode;
                    String number = eElement.getAttribute("number");
                    String type = eElement.getAttribute("type");
                    int capacity = Integer.parseInt(eElement.getAttribute("capacity"));
                    schedule.setAircraft(new Aircraft(number, type, capacity));
                }

                schedule.setAirline(airline);
                schedule.setDateOrigin(dateFormat.parse(dateOrigin));
                schedule.setDateDestination(dateFormat.parse(dateDest));
                schedule.setClas(clas);
                schedule.setTicketCost(Double.parseDouble(ticketCost));

                list.add(schedule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Loading from file was done");

        return list;
    }
}

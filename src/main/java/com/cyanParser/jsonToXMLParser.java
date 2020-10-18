package com.cyanParser;

import com.cyanParser.model.*;
import com.google.gson.Gson;
import org.springframework.core.env.Environment;
import sun.awt.HKSCS;

import javax.validation.constraints.Null;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class jsonToXMLParser {


    List<String> uomList =  Arrays.asList("W","varh","var","Wh");
    List<String> profileObisList = Arrays.asList("1.0.99.3.0.255","1.0.99.1.0.255");
    List<String> subtractiveRegisterObisList = Arrays.asList("1.0.1.8.0.255","1.0.2.8.0.255");

    String[] formatedValues=null;
    public jsonToXMLParser() {
    }

    public String GetUsagesCount(@org.jetbrains.annotations.NotNull StringBuffer jsonBody)
    {
        try {
            Gson gson = new Gson(); // Or use new GsonBuilder().create();
            ListUsage target2 = gson.fromJson(jsonBody.toString(), ListUsage.class);
            int count  = target2.usages.size();
            return String.valueOf(count);
        }catch (NullPointerException ne) {
            return ne.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public String GetEventsCount(@org.jetbrains.annotations.NotNull StringBuffer jsonBody)
    {
        try {
            Gson gson = new Gson(); // Or use new GsonBuilder().create();
            ListEvent target2 = gson.fromJson(jsonBody.toString(), ListEvent.class);
            int count  = target2.events.size();
            return String.valueOf(count);
        }catch (NullPointerException ne) {
            return ne.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public String GetAlarmsCount(@org.jetbrains.annotations.NotNull StringBuffer jsonBody)
    {
        try {
            Gson gson = new Gson(); // Or use new GsonBuilder().create();
            ListAlarm target2 = gson.fromJson(jsonBody.toString(), ListAlarm.class);
            int count  = target2.events.size();
            return String.valueOf(count);
        }catch (NullPointerException ne) {
            return ne.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    //code for parsing Measurements json
    /*public void parseMeasurementJson(@org.jetbrains.annotations.NotNull StringBuffer jsonBody, StringBuilder fileName, Environment env) {

        try {
            Gson gson = new Gson(); // Or use new GsonBuilder().create();
            ListUsage target2 = gson.fromJson(jsonBody.toString(), ListUsage.class);
            D1InitialLoadIMD DIMD;
            preVEE PVSeeder =  new preVEE();
            //new Work
            Devices devices = new Devices();
            Device device;
            mL ml;
            preVEE preVee;
            initialMeasurementData initialMeasurementData;// = new initialMeasurementData();
            InitialMeasurementDataList initialMeasurementDataList = new InitialMeasurementDataList();
            LocalDateTime startDate = null;
            LocalDateTime endDate = null;
            int count;// = 1;

            ArrayList<String> devicesIds = new ArrayList<>();

            //System.out.println(target2.usages);

            for (Usage usage : target2.usages) {

                if (devicesIds.contains(usage.getDeviceId()))
                    continue;

                device = new Device();

                //device.setHeadEnd(env.getProperty("hes.name"));PVSeeder.setServiceProviderExternalId(env.getProperty("hes.name"));
                device.setHeadEndExternalId(env.getProperty("hes.externalid"));
                device.setDeviceIdentifierNumber(usage.getDeviceId());

                List<Usage> sameDevices = target2.usages.stream().filter(i -> i.getDeviceId().equals(usage.getDeviceId())).collect(Collectors.toList());
                startDate=null;
                for (Intervals interval : usage.getRegisterValues()) {

                    count = 1;

                    preVee = new preVEE();


                    if (interval.getFormattedRegisterObisCode().equals("0.0.1.0.0.255") && startDate == null) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
                        startDate = LocalDateTime.parse(interval.getFormattedValue(), formatter);

                        startDate = startDate.minusMinutes(15);

                        Usage lastUsage = sameDevices.get(sameDevices.size() - 1);
                        Intervals sameObisCode = lastUsage.getRegisterValues().get(0);

                        endDate = LocalDateTime.parse(sameObisCode.getFormattedValue(), formatter);
                    } else {
                        for (Usage same : sameDevices) {
                            for (Intervals inter : same.getRegisterValues()) {
                                ml = new mL();

                                if (inter.getFormattedRegisterObisCode().equals(interval.getFormattedRegisterObisCode())) {
                                    ml.setS(count);
                                    ml.setQ(inter.getFormattedValue().split("\\s")[0]);
                                    preVee.getMsrs().get(0).addML(ml);
                                    count++;
                                }
                            }
                        }

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

                        preVee.setMcIdN(interval.getFormattedRegisterObisCode());
                        preVee.setStDt(startDate.format(formatter));
                        preVee.setEnDt(endDate.format(formatter));
                        preVee.setSpi(env.getProperty("hes.spi"));

                        initialMeasurementData = new initialMeasurementData();
                        initialMeasurementData.addpreVEE(preVee);
                        initialMeasurementDataList.addIMDData(initialMeasurementData);
                    }

                    device.setInitialMeasurementDataList(initialMeasurementDataList);
                }

                devices.getDevices().add(device);
                devicesIds.add(usage.getDeviceId());

                //target2.usages.removeIf(i->i.getDeviceId().equals(usage.getDeviceId()));
            }

            //devices.getDevices().forEach(u -> u.getInitialMeasurementDataList().forEach(
            //      i -> i.getPreVEES().forEach(p -> p.getMsrs().forEach(
            //    m -> System.out.println(m.getQ())))));
            JAXBContext context = JAXBContext.newInstance(Devices.class, Device.class, InitialMeasurementDataList.class,
                    preVEE.class, mL.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            OutputStream outputfile = new FileOutputStream(fileName.replace(fileName.length() - 4, fileName.length(), "xml").toString());
            m.marshal(devices, outputfile);
            outputfile.close();

            //target2.usages.forEach(usage -> usage.registerValues.forEach(interval -> System.out.println(interval.getAttributeId())));
        } catch (JAXBException je) {
            je.printStackTrace();
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

     */

    //code for parsing events json
    public void parseEventsJson(@org.jetbrains.annotations.NotNull StringBuffer jsonBody, StringBuilder fileName, Environment env) {
        try {
            Gson gson = new Gson(); // Or use new GsonBuilder().create();
            ListEvent target2 = gson.fromJson(jsonBody.toString(), ListEvent.class);

            SGGIMDsEvents events = new SGGIMDsEvents();
            DeviceEventSeeder deviceEvent;
            String externalSourceIdentifier=null;
            LocalDateTime date;
            externalSourceIdentifier = fileName.replace(fileName.length() - 4, fileName.length(), "xml").toString();

            for(Event event : target2.events)
            {
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
                //DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

                //date = LocalDateTime.parse(event.getEventTime(), formatter);

                deviceEvent = new DeviceEventSeeder();

                deviceEvent.setDeviceIdentifierNumber(event.getMeterId());
                deviceEvent.setEventDateTime(event.getEventTime());
                deviceEvent.setExternalEventName(event.getType());
                deviceEvent.setExternalSenderId(env.getProperty("hes.name"));
                deviceEvent.setExternalSourceIdentifier(externalSourceIdentifier.replace(env.getProperty("file.path"),"").toString());

                events.getEvents().add(deviceEvent);
            }
            JAXBContext context = JAXBContext.newInstance(SGGIMDsEvents.class, DeviceEventSeeder.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            OutputStream outputfile = new FileOutputStream(externalSourceIdentifier);
            m.marshal(events, outputfile);
            outputfile.close();

            //target2.usages.forEach(usage -> usage.registerValues.forEach(interval -> System.out.println(interval.getAttributeId())));
        } catch (JAXBException je) {
            je.printStackTrace();
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //code for parsing alarms  json
    public void parseAlarmsJson(@org.jetbrains.annotations.NotNull StringBuffer jsonBody, StringBuilder fileName, Environment env) {
        try {
            Gson gson = new Gson(); // Or use new GsonBuilder().create();
            ListAlarm target2 = gson.fromJson(jsonBody.toString(), ListAlarm.class);

            SGGIMDsEvents events = new SGGIMDsEvents();
            DeviceEventSeeder deviceEvent;
            String externalSourceIdentifier=null;
            LocalDateTime date;

            externalSourceIdentifier = fileName.replace(fileName.length() - 4, fileName.length(), "xml").toString();

            for(Alarms event : target2.events)
            {
                //DateTimeFormatter.ISO_OFFSET_DATE_TIME

                //DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;//DateTimeFormatter.ofPattern(DateTimeFormatter.ISO_OFFSET_DATE_TIME, Locale.ENGLISH);
                //DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
                for (String alarm: event.getAlarmActive()){
                    //date = LocalDateTime.parse(event.getAlarmTime(), formatter);
                    //System.out.println(event.getAlarmTime());
                    //System.out.println(date);
                    //System.out.println(date.format(stringFormatter));
                    deviceEvent = new DeviceEventSeeder();

                    deviceEvent.setDeviceIdentifierNumber(event.getMeterId());
                    deviceEvent.setEventDateTime(event.getAlarmTime());
                    deviceEvent.setExternalSenderId(env.getProperty("hes.name"));
                    deviceEvent.setExternalSourceIdentifier(externalSourceIdentifier.replace(env.getProperty("file.path"),"").toString());
                    deviceEvent.setExternalEventName(alarm);
                    events.getEvents().add(deviceEvent);
                }

            }
            JAXBContext context = JAXBContext.newInstance(SGGIMDsEvents.class, DeviceEventSeeder.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            OutputStream outputfile = new FileOutputStream(externalSourceIdentifier);
            m.marshal(events, outputfile);
            outputfile.close();

            //target2.usages.forEach(usage -> usage.registerValues.forEach(interval -> System.out.println(interval.getAttributeId())));
        } catch (JAXBException je) {
            je.printStackTrace();
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //code for parsing Measurements json
    public void parseMeasurementJsonV2(@org.jetbrains.annotations.NotNull StringBuffer jsonBody, StringBuilder fileName, Environment env) {

        try {
            Gson gson = new Gson(); // Or use new GsonBuilder().create();
            ListUsage target2 = gson.fromJson(jsonBody.toString(), ListUsage.class);
            Collections.sort(target2.usages, Usage.sampleDateTime);
            subtractiveRegisterObisList = Arrays.asList(env.getProperty("hes.subtractiveRegisterObisList").split(","));
            profileObisList = Arrays.asList(env.getProperty("hes.profileObisList").split(","));
            uomList = Arrays.asList(env.getProperty("hes.uomList").split(","));

            SGGIMDsEvents imds = new SGGIMDsEvents();
            D1InitialLoadIMD DIMD;
            preVEE PVSeeder;
            mL ml;
            preVEE preVee;

            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;//DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            target2.usages.removeIf(x-> !(profileObisList.contains(x.getFormattedProfileObisCode())));
            List<String> devices= target2.usages.stream().map(x->x.getDeviceId()).collect(Collectors.toList());
            for(Usage usage : target2.usages)
            {
                LocalDateTime startDate = LocalDateTime.parse( usage.getSampleTime(), formatter);
                startDate=  startDate.minusMinutes(15);
                LocalDateTime endDate = LocalDateTime.parse( usage.getSampleTime(), formatter);
                for (Intervals interval : usage.getRegisterValues()) {
                    if(interval.getFormattedRegisterObisCode().equals("0.0.1.0.0.255"))//skip creating imd for datetime obis code
                        continue;
                    DIMD=null;PVSeeder=null;
                    PVSeeder= new preVEE();
                    DIMD= new D1InitialLoadIMD(PVSeeder);
                    DIMD.setServiceProviderExternalId(env.getProperty("hes.externalid"));
                    PVSeeder.setDvcIdN(usage.getDeviceId());
                    preVee = new preVEE();
                    ml = new mL();
                    ml.setS(1);
                    formatedValues = interval.getFormattedValue().split("\\s");
                    if(!formatedValues[0].matches("0") && formatedValues.length > 1 &&
                            uomList.contains(formatedValues[1]) ) {
                        double div = 1000.0;
                        double ans= Double.valueOf(formatedValues[0])/div;
                        if(subtractiveRegisterObisList.contains(interval.getFormattedRegisterObisCode()))
                            ml.setR(String.format("%.4f",ans));
                        else
                            ml.setQ(String.format("%.4f",ans));

                    }else {
                        if(subtractiveRegisterObisList.contains(interval.getFormattedRegisterObisCode()))
                            ml.setR(interval.getFormattedValue().split("\\s")[0]);
                        else
                            ml.setQ(interval.getFormattedValue().split("\\s")[0]);
                    }
                    PVSeeder.getMsrs().get(0).addML(ml);

                    formatedValues = null;

                    PVSeeder.setMcIdN(interval.getFormattedRegisterObisCode());
                    if(startDate.toString().length()<18){
                        PVSeeder.setStDt(startDate.toString().concat(":00"));
                        PVSeeder.setEnDt(endDate.toString().concat(":00"));
                    }else{
                        PVSeeder.setStDt(startDate.toString()/*.concat(":00")*/);
                        PVSeeder.setEnDt(endDate.toString()/*.concat(":00")*/);
                    }
                    PVSeeder.setSpi(env.getProperty("hes.spi"));
                    imds.getDIMD().add(DIMD);
                }



            }
            /* Removed 15 sept 2020
           JAXBContext context = JAXBContext.newInstance(Devices.class, Device.class, InitialMeasurementDataList.class,
                    preVEE.class, mL.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            OutputStream outputfile = new FileOutputStream(fileName.replace(fileName.length() - 4, fileName.length(), "xml").toString());
            m.marshal(devices, outputfile);
            outputfile.close();
            */
            JAXBContext context = JAXBContext.newInstance(SGGIMDsEvents.class, D1InitialLoadIMD.class,preVEE.class,mL.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            OutputStream outputfile = new FileOutputStream(fileName.replace(fileName.length() - 4, fileName.length(), "xml").toString());
            m.marshal(imds, outputfile);
            outputfile.close();

            //target2.usages.forEach(usage -> usage.registerValues.forEach(interval -> System.out.println(interval.getAttributeId())));
        } catch (JAXBException je) {
            je.printStackTrace();
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

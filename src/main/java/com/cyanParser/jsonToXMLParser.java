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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class jsonToXMLParser {


    List<String> uomList =  Arrays.asList("W","varh","var","Wh");
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
            SGGIMDsEvents imds = new SGGIMDsEvents();
            D1InitialLoadIMD DIMD;
            preVEE PVSeeder;
            //new Work
            Devices devices = new Devices();
            Device device;
            mL ml;
            preVEE preVee;

            LocalDateTime startDate = null;
            LocalDateTime endDate = null;
            int count;// = 1;

            ArrayList<String> devicesIds = new ArrayList<>();

            //System.out.println(target2.usages);

            for (Usage usage : target2.usages) {
                ///*new*/DIMD=null;PVSeeder=null;
                ///*new*/PVSeeder= new preVEE();
               ///*new*/DIMD= new D1InitialLoadIMD(PVSeeder);
                if (devicesIds.contains(usage.getDeviceId()))
                    continue;

                device = new Device();

                //device.setHeadEnd(env.getProperty("hes.name"));PVSeeder.setServiceProviderExternalId(env.getProperty("hes.name"));
                device.setHeadEndExternalId(env.getProperty("hes.externalid"));
                ///*new*/PVSeeder.setServiceProviderExternalId(env.getProperty("hes.externalid"));
                device.setDeviceIdentifierNumber(usage.getDeviceId());
                ///*new*/PVSeeder.setDvcIdN(usage.getDeviceId());

                List<Usage> sameDevices = target2.usages.stream().filter(i -> i.getDeviceId().equals(usage.getDeviceId())).collect(Collectors.toList());
                startDate=null;
                for (Intervals interval : usage.getRegisterValues()) {
                    /*new*/DIMD=null;PVSeeder=null;
                    /*new*/PVSeeder= new preVEE();
                    /*new*/DIMD= new D1InitialLoadIMD(PVSeeder);
                    /*new*/DIMD.setServiceProviderExternalId(env.getProperty("hes.externalid"));
                    /*new*/PVSeeder.setDvcIdN(usage.getDeviceId());

                    count = 1;
                    preVee = new preVEE();


                    if (interval.getFormattedRegisterObisCode().equals("0.0.1.0.0.255") && startDate == null) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
                        startDate = LocalDateTime.parse(interval.getFormattedValue().substring(0,16), formatter);

                        startDate = startDate.minusMinutes(15);

                        //startDate.minusSeconds(startDate.getSecond())

                        Usage lastUsage = sameDevices.get(sameDevices.size() - 1);
                        Intervals sameObisCode = lastUsage.getRegisterValues().get(0);

                        endDate = LocalDateTime.parse(sameObisCode.getFormattedValue().substring(0,16), formatter);
                    } else {
                        for (Usage same : sameDevices) {
                            for (Intervals inter : same.getRegisterValues()) {
                                ml = new mL();

                                if (inter.getFormattedRegisterObisCode().equals(interval.getFormattedRegisterObisCode())) {
                                    ml.setS(count);
                                    formatedValues = inter.getFormattedValue().split("\\s");
                                    if(!formatedValues[0].matches("0") && formatedValues.length > 1 &&
                                            uomList.contains(formatedValues[1]) ) {
                                        double div = 1000.0;
                                        double ans= Double.valueOf(formatedValues[0])/div;
                                        ml.setQ(String.format("%.4f",ans));

                                    }else {
                                        ml.setQ(inter.getFormattedValue().split("\\s")[0]);
                                    }
                                    //preVee.getMsrs().get(0).addML(ml);
                                    /*new*/PVSeeder.getMsrs().get(0).addML(ml);
                                    count++;
                                    formatedValues = null;
                                }
                            }
                        }

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
                        /*new*/PVSeeder.setMcIdN(interval.getFormattedRegisterObisCode());
                        //preVee.setMcIdN(interval.getFormattedRegisterObisCode());

                        /*new*/PVSeeder.setStDt(startDate.toString().concat(":00"));
                        //preVee.setStDt(startDate.format(formatter));

                        /*new*/PVSeeder.setEnDt(endDate.toString().concat(":00"));
                        //preVee.setEnDt(endDate.format(formatter));

                        /*new*/PVSeeder.setSpi(env.getProperty("hes.spi"));
                        //preVee.setSpi(env.getProperty("hes.spi"));




                        /*new*/imds.getDIMD().add(DIMD);
                    }


                }

                devices.getDevices().add(device);
                devicesIds.add(usage.getDeviceId());

                //target2.usages.removeIf(i->i.getDeviceId().equals(usage.getDeviceId()));

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

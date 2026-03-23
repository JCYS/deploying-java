
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/global/util/DateTimeUtil.java.p.vm
 */
package com.copeinca.apicopeincaprov.global.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtil {

    public static final String DEFAULT_TIMEZONE = "America/Lima";
    public static final String PERU_TIMEZONE = "America/Lima";
    public static final String UTC_TIMEZONE = "Etc/UTC";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_FORMAT_V2 = "dd/MM/yyyy HH:mm";
    public static final String DEFAULT_YEAR_FORMAT = "yyyy";
    public static final String DEFAULT_MONTH_FORMAT = "MM";

    public static final String DEFAULT_DATE_FORMAT_PROJECT = "dd/MM/yyyy";

    public static DateFormat OUT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static DateFormat OUT_TIME_FORMAT = new SimpleDateFormat("H:mm:ss");
    public static DateFormat OUT_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
    public static DateFormat OUT_TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SSS");
    public static DateTimeFormatter OUT_LOCALDATETIME = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateFormat IN_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static DateFormat IN_TIME_FORMAT = new SimpleDateFormat("H:mm:ss");
    public static DateFormat IN_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
    public static DateFormat IN_DATETIME_FORMAT_V2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static DateFormat IN_TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SSS");
    public static Calendar calendar = new GregorianCalendar();

    private DateTimeUtil() {
    }

    public static LocalDateTime getCurrentLocalDateTime() {

        return LocalDateTime.ofInstant(Instant.now(), ZoneId.of(DEFAULT_TIMEZONE));

    }

    public static long getCurrentZoneLocalDateTime() {

        return getCurrentLocalDateTime().atZone(ZoneId.of(DEFAULT_TIMEZONE)).toInstant().toEpochMilli();

    }

    public static LocalDateTime getCurrentLocalDateTimeByZone(ZoneId zoneId) {
        return LocalDateTime.ofInstant(Instant.now(), zoneId);
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String asString(Date date, String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    public static String asString(LocalDate date, String dateFormat) {
        if( date == null )
            return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return date.format(formatter);
    }

    public static String asString(LocalDate date) {
        return asString(date, DEFAULT_DATE_FORMAT_PROJECT);
    }

    public static String asString(Date date) {
        if( date == null )
            return "";
        DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return df.format(date);
    }

    public static String asString(LocalDateTime localDateTime) {
        if( localDateTime == null )
            return "";
        return localDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
    }

    public static String asStringV2(LocalDateTime localDateTime) {
        if( localDateTime == null )
            return "";
        return localDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT_V2));
    }

    public static Date stringPatternToDateAtLocalZone(String stringDate, String pattern) {
        if (stringDate == null || stringDate.isEmpty() || pattern == null || pattern.isEmpty())
            return null;

        return Date.from(LocalDate.parse(stringDate, DateTimeFormatter.ofPattern(pattern)).atStartOfDay(ZoneId.of(DEFAULT_TIMEZONE)).toInstant());
    }

    public static String asLocalDateString(LocalDate localDate, String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat);

        return df.format(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
    }

    public static Date asDateString(Timestamp dateTime) {
        return new Date(dateTime.getTime());
    }

    public static String asDatetimeString(Timestamp dateTime, String dateFormat) {
        if (dateTime == null || dateFormat == null || dateFormat.isEmpty())
            return null;

        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(new Date(dateTime.getTime()));
    }

    public static LocalDate asLocalDate(String dateString, String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        try {
            return df.parse(dateString).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static LocalDateTime asLocalDateTime( String dateString ) {
        try {
            return IN_DATETIME_FORMAT.parse( dateString ).toInstant( )
                    .atZone( ZoneId.systemDefault( ) )
                    .toLocalDateTime( );
        } catch( ParseException e ) {
            e.printStackTrace( );
            return null;
        }
    }

    public static LocalDateTime asLocalDateTimeFromXLSX( String dateString ) {
        return LocalDate.parse(dateString, OUT_LOCALDATETIME).atStartOfDay();
    }

    public static LocalDateTime asLocalDateTimev2( String dateString ) {
        try {
            return IN_DATETIME_FORMAT_V2.parse( dateString ).toInstant( )
                    .atZone( ZoneId.systemDefault( ) )
                    .toLocalDateTime( );
        } catch( ParseException e ) {
            e.printStackTrace( );
            return null;
        }
    }

    public static Date asDate(String dateString, String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Timestamp asDateTimeFrom(Date date) {
        return Timestamp.valueOf(DateTimeUtil.asString(date) + " 00:00:00");
    }

    public static Timestamp asDateTimeTo(Date date) {
        return Timestamp.valueOf(DateTimeUtil.asString(date) + " 23:59:59");
    }

    public static Timestamp asDateTime(String dateString, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        try {
            Date parsedDate = df.parse(dateString);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Timestamp now() {
        return Timestamp.valueOf(LocalDateTime.ofInstant(Instant.now(), ZoneId.of(DEFAULT_TIMEZONE)));
    }

    public static Timestamp nowPlusMiliseconds(long miliseconds) {
        return Timestamp.valueOf(LocalDateTime.ofInstant(Instant.now().plusMillis(miliseconds), ZoneId.of(DEFAULT_TIMEZONE)));
    }

    public static int getYear(Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getYear(Timestamp date) {
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static int getMonth(Timestamp date) {
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    /**
     * Convert an Object to a DateTime, without an Exception
     */
    public static Date getDateTime(Object value) {
        try {
            return toDateTime(value);
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }
    }

    /**
     * Convert an Object to a DateTime.
     */
    public static Date toDateTime(Object value) throws ParseException {
        if (value == null)
            return null;
        if (value instanceof Date)
            return (Date) value;
        if (value instanceof String) {
            if ("".equals(value))
                return null;
            return IN_DATETIME_FORMAT.parse((String) value);
        }

        return IN_DATETIME_FORMAT.parse(value.toString());
    }

    /**
     * Convert an Object to a Date, without an Exception
     */
    public static java.sql.Date getDate(Object value) {
        try {
            return toDate(value);
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }
    }

    /**
     * Convert an Object to a Date.
     */
    public static java.sql.Date toDate(Object value) throws ParseException {
        if (value == null)
            return null;
        if (value instanceof java.sql.Date)
            return (java.sql.Date) value;
        if (value instanceof String) {
            if ("".equals(value))
                return null;
            return new java.sql.Date(IN_DATE_FORMAT.parse((String) value).getTime());
        }

        return new java.sql.Date(IN_DATE_FORMAT.parse(value.toString()).getTime());
    }

    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    public static LocalDate toLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Timestamp dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime dateToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTimeZoned(Timestamp dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.of(DEFAULT_TIMEZONE)).toLocalDateTime();
    }

    public static String getCurrentLocalDateTimeAsStringPattern(String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(LocalDateTime.ofInstant(Instant.now(), ZoneId.of(DEFAULT_TIMEZONE)));
    }

    public static Date toDatePlusHours(Date date, int hours) {
        return Date.from(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusHours(6).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String toStringDayByZoneAndPattern(Timestamp timestamp, ZoneId zoneId, String pattern) {
        if (timestamp == null)
            return null;

        return timestamp.toInstant().atZone(zoneId).toLocalDate().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static Date localDateTimeToDatePlusHours(LocalDateTime localDatetimedate, int hours) {
        return Date.from(localDatetimedate.plusHours(6).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date stringToDatePlusHours(String dateString, String pattern, int hours) {
        if (dateString == null || dateString.isBlank())
            return null;

        LocalDateTime localDateTime = asLocalDate(dateString, pattern).atStartOfDay();

        return localDateTimeToDatePlusHours(localDateTime, hours);
    }

    public static LocalDate dateToLocalDate(Date dateToConvert) {
        if (dateToConvert == null)
            return null;

        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public XMLGregorianCalendar getDate() {

        try {

            GregorianCalendar gregorianCalendar = new GregorianCalendar();

            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

            XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);

            return xmlGregorianCalendar;

        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException();

        }

    }

    public static XMLGregorianCalendar convertToXMLGregorianCalendar(LocalDate localDate) {

        try {

            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            GregorianCalendar gregorianCalendar = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
            xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED); // Unset timezone
            xmlGregorianCalendar.setTime(DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED); // Unset time
            return xmlGregorianCalendar;

        } catch (DatatypeConfigurationException e) {

            e.printStackTrace();
            return null;

        }

    }

}
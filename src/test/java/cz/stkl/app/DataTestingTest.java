package cz.stkl.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DataTestingTest {
    @Test
    public void AlwaysTrue() {
        assertEquals(true, true);
    }

    private static final TimeZone AMERICA_LOS_ANGELES = TimeZone.getTimeZone("America/Los_Angeles");
    private static final TimeZone AUSTRALIA_LORD_HOWE = TimeZone.getTimeZone("Australia/Lord_Howe");

    // The RI fails this test.
    /*
     * @Test
     * public void test2DigitYearStartIsCloned() throws Exception {
     * // Test that get2DigitYearStart returns a clone.
     * SimpleDateFormat sdf = new SimpleDateFormat();
     * Date originalDate = sdf.get2DigitYearStart();
     * assertNotSame(sdf.get2DigitYearStart(), originalDate);
     * assertEquals(sdf.get2DigitYearStart(), originalDate);
     * originalDate.setTime(0);
     * assertFalse(sdf.get2DigitYearStart().equals(originalDate));
     * // Test that set2DigitYearStart takes a clone.
     * Date newDate = new Date();
     * sdf.set2DigitYearStart(newDate);
     * assertNotSame(sdf.get2DigitYearStart(), newDate);
     * assertEquals(sdf.get2DigitYearStart(), newDate);
     * newDate.setTime(0);
     * assertFalse(sdf.get2DigitYearStart().equals(newDate));
     * }
     */

    // The RI fails this test because this is an ICU-compatible Android extension.
    // Necessary for correct localization in various languages (http://b/2633414).
    /*
     * @Test
     * public void testStandAloneNames() throws Exception {
     * Locale en = Locale.ENGLISH;
     * Locale pl = new Locale("pl");
     * Locale ru = new Locale("ru");
     * assertEquals("January", formatDate(en, "MMMM"));
     * assertEquals("January", formatDate(en, "LLLL"));
     * assertEquals("stycznia", formatDate(pl, "MMMM"));
     * assertEquals("stycze\u0144", formatDate(pl, "LLLL"));
     * assertEquals("Thursday", formatDate(en, "EEEE"));
     * assertEquals("Thursday", formatDate(en, "cccc"));
     * assertEquals("\u0447\u0435\u0442\u0432\u0435\u0440\u0433", formatDate(ru,
     * "EEEE"));
     * assertEquals("\u0427\u0435\u0442\u0432\u0435\u0440\u0433", formatDate(ru,
     * "cccc"));
     * assertEquals(Calendar.JUNE, parseDate(en, "yyyy-MMMM-dd",
     * "1980-June-12").get(Calendar.MONTH));
     * assertEquals(Calendar.JUNE, parseDate(en, "yyyy-LLLL-dd",
     * "1980-June-12").get(Calendar.MONTH));
     * assertEquals(Calendar.JUNE, parseDate(pl, "yyyy-MMMM-dd",
     * "1980-czerwca-12").get(Calendar.MONTH));
     * assertEquals(Calendar.JUNE, parseDate(pl, "yyyy-LLLL-dd",
     * "1980-czerwiec-12").get(Calendar.MONTH));
     * assertEquals(Calendar.TUESDAY, parseDate(en, "EEEE",
     * "Tuesday").get(Calendar.DAY_OF_WEEK));
     * assertEquals(Calendar.TUESDAY, parseDate(en, "cccc",
     * "Tuesday").get(Calendar.DAY_OF_WEEK));
     * assertEquals(Calendar.TUESDAY,
     * parseDate(ru, "EEEE",
     * "\u0432\u0442\u043e\u0440\u043d\u0438\u043a").get(Calendar.DAY_OF_WEEK));
     * assertEquals(Calendar.TUESDAY,
     * parseDate(ru, "cccc",
     * "\u0412\u0442\u043e\u0440\u043d\u0438\u043a").get(Calendar.DAY_OF_WEEK));
     * }
     */

    @Test
    public void test2038() {
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        assertEquals("Sun Nov 24 17:31:44 1833",
                format.format(new Date(((long) Integer.MIN_VALUE + Integer.MIN_VALUE) * 1000L)));
        assertEquals("Fri Dec 13 20:45:52 1901",
                format.format(new Date(Integer.MIN_VALUE * 1000L)));
        assertEquals("Thu Jan 01 00:00:00 1970",
                format.format(new Date(0L)));
        assertEquals("Tue Jan 19 03:14:07 2038",
                format.format(new Date(Integer.MAX_VALUE * 1000L)));
        assertEquals("Sun Feb 07 06:28:16 2106",
                format.format(new Date((2L + Integer.MAX_VALUE + Integer.MAX_VALUE) * 1000L)));
    }

    /*
     * @Test
     * private String formatDate(Locale l, String fmt) {
     * DateFormat dateFormat = new SimpleDateFormat(fmt, l);
     * dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
     * return dateFormat.format(new Date(0));
     * }
     */

    /*
     * @Test
     * private Calendar parseDate(Locale l, String fmt, String value) {
     * SimpleDateFormat sdf = new SimpleDateFormat(fmt, l);
     * ParsePosition pp = new ParsePosition(0);
     * Date d = sdf.parse(value, pp);
     * if (d == null) {
     * fail(pp.toString());
     * }
     * Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
     * c.setTime(d);
     * return c;
     * }
     */

    // http://code.google.com/p/android/issues/detail?id=13420
    /*
     * @Test
     * public void testParsingUncommonTimeZoneAbbreviations() {
     * String fmt = "yyyy-MM-dd HH:mm:ss.SSS z";
     * String date = "2010-12-23 12:44:57.0 CET";
     * // ICU considers "CET" (Central European Time) to be common in Britain...
     * assertEquals(1293104697000L, parseDate(Locale.UK, fmt,
     * date).getTimeInMillis());
     * // ...but not in the US. Check we can parse such a date anyway.
     * assertEquals(1293104697000L, parseDate(Locale.US, fmt,
     * date).getTimeInMillis());
     * }
     */

    @Test
    public void testFormattingUncommonTimeZoneAbbreviations() {
        // In Honeycomb, only one Olson id was associated with CET (or any
        // other "uncommon" abbreviation).
        String fmt = "yyyy-MM-dd HH:mm:ss.SSS z";
        String date = "1970-01-01 01:00:00.000 CET";
        SimpleDateFormat sdf = new SimpleDateFormat(fmt, Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        assertEquals(date, sdf.format(new Date(0)));
        sdf = new SimpleDateFormat(fmt, Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Zurich"));
        assertEquals(date, sdf.format(new Date(0)));
    }

    @Test
    // http://code.google.com/p/android/issues/detail?id=8258
    public void testTimeZoneFormatting() throws Exception {
        Date epoch = new Date(0);
        // Create a SimpleDateFormat that defaults to America/Chicago...
        TimeZone.setDefault(TimeZone.getTimeZone("America/Chicago"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        // We should see something appropriate to America/Chicago...
        assertEquals("1969-12-31 18:00:00 -0600", sdf.format(epoch));
        // We can set any TimeZone we want:
        sdf.setTimeZone(AMERICA_LOS_ANGELES);
        assertEquals("1969-12-31 16:00:00 -0800", sdf.format(epoch));
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        assertEquals("1970-01-01 00:00:00 +0000", sdf.format(epoch));
        // A new SimpleDateFormat will default to America/Chicago...
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        // ...and parsing an America/Los_Angeles time will *not* change that...
        sdf.parse("2010-12-03 00:00:00 -0800");
        // ...so our time zone here is "America/Chicago":
        assertEquals("1969-12-31 18:00:00 -0600", sdf.format(epoch));
        // We can set any TimeZone we want:
        sdf.setTimeZone(AMERICA_LOS_ANGELES);
        assertEquals("1969-12-31 16:00:00 -0800", sdf.format(epoch));
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        assertEquals("1970-01-01 00:00:00 +0000", sdf.format(epoch));
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = sdf.parse("2010-07-08 02:44:48");
        assertEquals(1278557088000L, date.getTime());
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        sdf.setTimeZone(AMERICA_LOS_ANGELES);
        assertEquals("2010-07-07T19:44:48-0700", sdf.format(date));
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        assertEquals("2010-07-08T02:44:48+0000", sdf.format(date));
    }

    /**
     * Africa/Cairo standard time is EET and daylight time is EEST. They no
     * longer use their DST zone but we should continue to parse it properly.
     */
    @Test
    public void testObsoleteDstZoneName() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm zzzz");
        Date normal = format.parse("1970-01-01T00:00 EET");
        Date dst = format.parse("1970-01-01T00:00 EEST");
        assertEquals(60 * 60 * 1000, normal.getTime() - dst.getTime());
    }

    @Test
    public void testDstZoneNameWithNonDstTimestamp() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm zzzz");
        Calendar calendar = new GregorianCalendar(AMERICA_LOS_ANGELES);
        calendar.setTime(format.parse("2011-06-21T10:00 Pacific Standard Time")); // 18:00 GMT-8
        assertEquals(11, calendar.get(Calendar.HOUR_OF_DAY)); // 18:00 GMT-7
        assertEquals(0, calendar.get(Calendar.MINUTE));
    }

    @Test
    public void testNonDstZoneNameWithDstTimestamp() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm zzzz");
        Calendar calendar = new GregorianCalendar(AMERICA_LOS_ANGELES);
        calendar.setTime(format.parse("2010-12-21T10:00 Pacific Daylight Time")); // 17:00 GMT-7
        assertEquals(9, calendar.get(Calendar.HOUR_OF_DAY)); // 17:00 GMT-8
        assertEquals(0, calendar.get(Calendar.MINUTE));
    }

    // http://b/4723412
    @Test
    public void testDstZoneWithNonDstTimestampForNonHourDstZone() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm zzzz");
        Calendar calendar = new GregorianCalendar(AUSTRALIA_LORD_HOWE);
        calendar.setTime(format.parse("2011-06-21T20:00 Lord Howe Daylight Time")); // 9:00 GMT+11
        assertEquals(19, calendar.get(Calendar.HOUR_OF_DAY)); // 9:00 GMT+10:30
        assertEquals(30, calendar.get(Calendar.MINUTE));
    }

    @Test
    public void testNonDstZoneWithDstTimestampForNonHourDstZone() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm zzzz");
        Calendar calendar = new GregorianCalendar(AUSTRALIA_LORD_HOWE);
        calendar.setTime(format.parse("2010-12-21T19:30 Lord Howe Standard Time")); // 9:00 GMT+10:30
        assertEquals(20, calendar.get(Calendar.HOUR_OF_DAY)); // 9:00 GMT+11:00
        assertEquals(0, calendar.get(Calendar.MINUTE));
    }

    @Test
    public void testLocales() throws Exception {
        // Just run through them all. Handy as a poor man's benchmark, and a sanity
        // check.
        for (Locale l : Locale.getAvailableLocales()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzzz", l);
            sdf.format(new Date(0));
        }
    }

    // http://code.google.com/p/android/issues/detail?id=14963
    @Test
    public void testParseTimezoneOnly() throws Exception {
        new SimpleDateFormat("z", Locale.FRANCE).parse("UTC");
        new SimpleDateFormat("z", Locale.US).parse("UTC");
    }
}

package com.davideleonino.palestra.dto.response;

public class BookingInfoDto {
    private Long bookingId;
    private String courseName;
    private String dayOfWeek;
    private String timeRange;
    private String coachName;

    public BookingInfoDto() {}

    public BookingInfoDto(Long bookingId, String courseName, String dayOfWeek, String timeRange, String coachName) {
        this.bookingId = bookingId;
        this.courseName = courseName;
        this.dayOfWeek = dayOfWeek;
        this.timeRange = timeRange;
        this.coachName = coachName;
    }

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public String getTimeRange() { return timeRange; }
    public void setTimeRange(String timeRange) { this.timeRange = timeRange; }

    public String getCoachName() { return coachName; }
    public void setCoachName(String coachName) { this.coachName = coachName; }
}
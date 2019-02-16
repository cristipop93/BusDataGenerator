public class Input {
  private long idFrom;
  private long idTo;
  private VehicleType vehicleType;
  private int month;
  private int day;
  private int hour;
  private int minute;
  private boolean holiday;
  private boolean vacation;
  private int temperature;
  private PrecipitationType pType;
  private int secondsDelay;

  public Input(long idFrom, long idTo, VehicleType vehicleType, int month, int day, int hour, int minute, boolean holiday, boolean vacation, int temperature, PrecipitationType pType, int secondsDelay) {
    this.idFrom = idFrom;
    this.idTo = idTo;
    this.vehicleType = vehicleType;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
    this.holiday = holiday;
    this.vacation = vacation;
    this.temperature = temperature;
    this.pType = pType;
    this.secondsDelay = secondsDelay;
  }

  public Input(long idFrom, long idTo, int secondsDelay) {
    this.idFrom = idFrom;
    this.idTo = idTo;
    this.secondsDelay = secondsDelay;
  }

  public long getIdFrom() {
    return idFrom;
  }

  public void setIdFrom(long idFrom) {
    this.idFrom = idFrom;
  }

  public long getIdTo() {
    return idTo;
  }

  public void setIdTo(long idTo) {
    this.idTo = idTo;
  }

  public VehicleType getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getMinute() {
    return minute;
  }

  public void setMinute(int minute) {
    this.minute = minute;
  }

  public boolean isHoliday() {
    return holiday;
  }

  public void setHoliday(boolean holiday) {
    this.holiday = holiday;
  }

  public boolean isVacation() {
    return vacation;
  }

  public void setVacation(boolean vacation) {
    this.vacation = vacation;
  }

  public int getTemperature() {
    return temperature;
  }

  public void setTemperature(int temperature) {
    this.temperature = temperature;
  }

  public PrecipitationType getpType() {
    return pType;
  }

  public void setpType(PrecipitationType pType) {
    this.pType = pType;
  }

  public int getSecondsDelay() {
    return secondsDelay;
  }

  public void setSecondsDelay(int secondsDelay) {
    this.secondsDelay = secondsDelay;
  }

  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(idFrom);
    stringBuilder.append(',');
    stringBuilder.append(idTo);
    stringBuilder.append(',');
    stringBuilder.append(vehicleType.getId());
    stringBuilder.append(',');
    stringBuilder.append(month);
    stringBuilder.append(',');
    stringBuilder.append(day);
    stringBuilder.append(',');
    stringBuilder.append(hour);
    stringBuilder.append(',');
    stringBuilder.append(minute);
    stringBuilder.append(',');
    stringBuilder.append(holiday);
    stringBuilder.append(',');
    stringBuilder.append(vacation);
    stringBuilder.append(',');
    stringBuilder.append(temperature);
    stringBuilder.append(',');
    stringBuilder.append(pType.getId());
    stringBuilder.append(',');
    stringBuilder.append(secondsDelay);
    stringBuilder.append('\n');

    return stringBuilder.toString();
  }
}

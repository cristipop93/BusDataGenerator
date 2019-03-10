import java.util.Random;

public class ScrambleUtils {

  private static double randomDoubleBetweenValues(double min, double max) {
    Random r = new Random();
    return min + (max - min) * r.nextDouble();
  }

  public static double getVehicleTypeDelay(VehicleType vehicleType) {
    switch (vehicleType) {
      case BUS:
        return randomDoubleBetweenValues(0.99, 1.01);
      case TROLLEYBUS:
        return randomDoubleBetweenValues(1.00, 1.08);
      case TRAM:
        return randomDoubleBetweenValues(0.95, 1.03);
      default:
        return 1;
    }
  }

  public static double getMonthDelay(int month) {
    switch (month) {
      case 1:
        return randomDoubleBetweenValues(0.99, 1.01);
      case 2:
        return randomDoubleBetweenValues(0.99, 1.01);
      case 3:
        return randomDoubleBetweenValues(0.99, 1.01);
      case 4:
        return randomDoubleBetweenValues(0.99, 1.01);
      case 5:
        return randomDoubleBetweenValues(0.99, 1.01);
      case 6:
        return randomDoubleBetweenValues(0.99, 1.01);
      case 7:
        return randomDoubleBetweenValues(0.99, 1.01);
      case 8:
        return randomDoubleBetweenValues(0.99, 1.01);
      case 9:
        return randomDoubleBetweenValues(0.99, 1.01);
      case 10:
        return randomDoubleBetweenValues(0.99, 1.01);
      case 11:
        return randomDoubleBetweenValues(0.99, 1.01);
      case 12:
        return randomDoubleBetweenValues(0.99, 1.01);
      default:
        return 1;
    }
  }

  public static double getDayDelay(int day) {
    switch (day) {
      case 1:
        return randomDoubleBetweenValues(0.98, 1.03);
      case 2:
        return randomDoubleBetweenValues(0.98, 1.04);
      case 3:
        return randomDoubleBetweenValues(0.99, 1.03);
      case 4:
        return randomDoubleBetweenValues(0.98, 1.03);
      case 5:
        return randomDoubleBetweenValues(0.99, 1.01);
      case 6:
        return randomDoubleBetweenValues(0.95, 1.01);
      case 7:
        return randomDoubleBetweenValues(0.94, 1.01);
      default:
        return 1;
    }
  }

  public static double getHourDelay(int hour, int day) {  // on sunday and saturday early hours will not delay the traffic
    switch (hour) {
      case 6:
        return randomDoubleBetweenValues(0.97, 1.02);
      case 7:
        if (day == 6 || day == 7) {
          return randomDoubleBetweenValues(0.97, 1.02);
        } else {
          return randomDoubleBetweenValues(0.99, 1.15);
        }
      case 8:
        if (day == 6 || day == 7) {
          return randomDoubleBetweenValues(0.97, 1.02);
        } else {
          return randomDoubleBetweenValues(0.98, 1.14);
        }
      case 9:
        return randomDoubleBetweenValues(0.98, 1.09);
      case 10:
        return randomDoubleBetweenValues(0.97, 1.03);
      case 11:
        return randomDoubleBetweenValues(0.96, 1.02);
      case 12:
        return randomDoubleBetweenValues(0.98, 1.06);
      case 13:
        return randomDoubleBetweenValues(0.96, 1.02);
      case 14:
        return randomDoubleBetweenValues(0.98, 1.04);
      case 15:
        return randomDoubleBetweenValues(0.98, 1.05);
      case 16:
        return randomDoubleBetweenValues(0.99, 1.07);
      case 17:
        return randomDoubleBetweenValues(1.00, 1.10);
      case 18:
        return randomDoubleBetweenValues(1.00, 1.10);
      case 19:
        return randomDoubleBetweenValues(0.98, 1.08);
      case 20:
        return randomDoubleBetweenValues(0.97, 1.05);
      case 21:
        return randomDoubleBetweenValues(0.95, 1.02);
      case 22:
        return randomDoubleBetweenValues(0.91, 1.01);
      default:
        return 1;
    }
  }

  public static double getMinuteDelay(int minute) {
    return 1;
  }

  public static double getHolidayDelay(boolean holiday) {
    return holiday ? randomDoubleBetweenValues(0.82, 0.95) : randomDoubleBetweenValues(0.99, 1.01);
  }

  public static double getVacationDelay(boolean vacation) {
    return vacation ? randomDoubleBetweenValues(0.9, 1) : 1;
  }

  public static double getTemperatureDelay(int temperature) {
    if (temperature > -25 && temperature < 10) { // cold
      return randomDoubleBetweenValues(1, 1.10);
    } else if (temperature >= 10 && temperature <= 25) { // normal
      return randomDoubleBetweenValues(0.99, 1);
    } else if (temperature > 25 && temperature < 40) { // hot
      return randomDoubleBetweenValues(1, 1.01);
    } else {
      return 1;
    }
  }

  public static double getPrecipitationDelay(PrecipitationType precipitationType) {
    switch (precipitationType) {
      case DRY:
        return randomDoubleBetweenValues(0.98, 1);
      case RAIN:
        return randomDoubleBetweenValues(0.99, 1.10);
      case SNOW:
        return randomDoubleBetweenValues(0.99, 1.10);
      default:
        return 1;
    }
  }

}

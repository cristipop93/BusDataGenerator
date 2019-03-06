import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

  public static long positiveLong(long number) {
    return number > 0 ? number : number * -1;
  }

  public static int positiveInt(int number) {
    return number > 0 ? number : number * -1;
  }

  public static void main(String[] args) {
    List<Input> inputs = new ArrayList<>();
    inputs.add(new Input(0, 1, 300));
//    inputs.add(new Input(1, 2, 160));
//    inputs.add(new Input(2, 3, 200));
//    inputs.add(new Input(3, 4, 400));
//    inputs.add(new Input(4, 3, 400));
//    inputs.add(new Input(3, 2, 220));
//    inputs.add(new Input(2, 1, 175));
//    inputs.add(new Input(1, 0, 370));

    Random random = new Random();
    Input generated;
    try (
            PrintWriter writer = new PrintWriter(new File("data.csv"))) {
      for (Input input : inputs) {
//        int iterationsForOneInput = positiveInt(random.nextInt()) % 1000 + 100;
        int iterationsForOneInput = 50000;
        for (int i = 0; i < iterationsForOneInput; i++) {
          VehicleType vehicleType = VehicleType.getById(positiveInt(random.nextInt()) % VehicleType.values().length);
          int month = positiveInt(random.nextInt()) % 12 + 1;
          int day = positiveInt(random.nextInt()) % 7 + 1;
          int hour = positiveInt(random.nextInt()) % 15 + 6;
          int minute = positiveInt(random.nextInt()) % 60;
          boolean holiday = false;
          boolean vacation = false;
          if (day != 6 && day != 7) { // on saturday and sunday it doesn't count if it is a holiday or vacation
            if (month == 12 || month == 1 || month == 4) {
              holiday = random.nextBoolean();
            }
            if (!holiday) { // if holiday than it is also a vacation, so no need to add vacation into account
              if (month == 1 || month == 12 || month == 2 || month == 4 || month == 6 || month == 9) {
                vacation = random.nextBoolean();
              } else if (month == 7 || month == 8) {
                vacation = true;
              }
            }
          }

          int temperature;
          boolean found = false;
          do {
            temperature = positiveInt(random.nextInt()) % 66 - 25;
            if (month == 1 || month == 2 || month == 3 || month == 11 || month == 12) {
              if (temperature < 10) {
                found = true;
              }
            } else if (month == 6 || month == 7 || month == 8) {
              if (temperature > 25) {
                found = true;
              }
            } else {
              if (temperature <= 25 && temperature >= 10) {
                found = true;
              }
            }
          } while (!found);

          PrecipitationType precipitationType;
          do {
            precipitationType = PrecipitationType.getById(positiveInt(random.nextInt()) % PrecipitationType.values().length);
          } while (precipitationType.getId() == PrecipitationType.RAIN.getId() && temperature <= 1);
          generated = new Input(input.getIdFrom(), input.getIdTo(), vehicleType, month, day, hour, minute, holiday, vacation, temperature, precipitationType, input.getSecondsDelay());
          int defaultDelay = input.getSecondsDelay();
          double randomDelay = defaultDelay
                  * ScrambleUtils.getVehicleTypeDelay(vehicleType)
                  * ScrambleUtils.getMonthDelay(month)
                  * ScrambleUtils.getDayDelay(day)
                  * ScrambleUtils.getHourDelay(hour, day)
                  * ScrambleUtils.getMinuteDelay(minute)
                  * ScrambleUtils.getHolidayDelay(holiday)
                  * ScrambleUtils.getVacationDelay(vacation)
                  * ScrambleUtils.getTemperatureDelay(temperature)
                  * ScrambleUtils.getPrecipitationDelay(precipitationType);
          generated.setSecondsDelay((int) randomDelay);
          writer.write(generated.toString());
        }
      }

      System.out.println("done!");

    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }
}

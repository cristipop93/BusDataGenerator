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

    inputs.add(new Input(6, 5, 120)); // expo - a vlaicu
    inputs.add(new Input(5, 8 , 200));// a vlaicu - arte plastice
    inputs.add(new Input(8, 11, 180)); // arte plastice - crinului
    inputs.add(new Input(11, 13, 180));// crinului - somesul I
    inputs.add(new Input(12, 10, 150));// somes - piata marasti
    inputs.add(new Input(10, 9, 180));// piata marasti - maresal prezan
    inputs.add(new Input(9, 4, 160));//maresal prezan - siretului
    inputs.add(new Input(4, 7, 120));//siretului - pod someseni
    inputs.add(new Input(19, 18, 180));// col pedagogic - iulius est
    inputs.add(new Input(18, 1, 150));// iulius est - campus est
    inputs.add(new Input(1, 8, 200));// campus est - arte palstice
    inputs.add(new Input(9, 3, 160));// maresal prezan - dorobantilor
    inputs.add(new Input(3, 2, 100));// dorobantilor - campus vest
    inputs.add(new Input(2, 17, 210));// campus vest - iulius vest
    inputs.add(new Input(17, 20, 150));// iulius vest - valeriu bologa
    inputs.add(new Input(8, 14, 220));// arte plastice - maresal averescu
    inputs.add(new Input(14, 15, 160));// maresal averescu - fabricii I
    inputs.add(new Input(16, 21, 260));// fabricii II - fabricii
    inputs.add(new Input(21, 9, 130));// fabricii - maresal prezan


    //4:  6 5 8 11 13
    // expo - a vlaicu - arte plastice - crinului - somesul I
    //4: 12 10 9 4 7
    // somes - piata marasti - maresal c-tin prezan - siretului - pod someseni
    //24: 19 18 1 8 11 13
    // colegiul pedagogic - iulius est - campus est - arte plastice - crinului - somesul I
    //24: 12 10 9 3 2 17 20
    // somes - marasti II - maresal prezan - dorobantilor - capus vest - iulius vest - valeriu bologa
    //48: 19 18 1 8 14 15
    // colegiul pedagogic - iulius est - campus est - arte plastice - maresal averescu - fabricii I
    //48: 16 21 9 3 2 17 20
    // fabricii II fabricii - maresal prezan - dorobantilor - capus vest - iulius vest - valeriu bologa



    Random random = new Random();
    Input generated;
    try (
            PrintWriter writer = new PrintWriter(new File("data.csv"))) {
      int iterationsForOneInput = 6000;
      for (int i = 0; i < iterationsForOneInput; i++) {
        for (Input input : inputs) {
          VehicleType vehicleType = VehicleType.getById(positiveInt(random.nextInt()) % VehicleType.values().length);
          int month = positiveInt(random.nextInt()) % 12 + 1;
          int day = positiveInt(random.nextInt()) % 7 + 1;
          int hour = positiveInt(random.nextInt()) % 17 + 6; // from 6 to 22
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

#include "cactus_io_DHT22.h"
#include "math.h"
#include<SPI.h>
#define DHT22_PIN 2 // what pin on the arduino is the DHT22 data line connected to



float x;
float y;

int ledRedLight = 12;
int ledGreenLight = 11;
int alarmBuzzer = 10;
int smokeAnalogPin = A5;



// Your threshold value
int sensorThresholdVal = 400; //400





// Initialize DHT sensor for normal 16mhz Arduino.
DHT22 dht(DHT22_PIN);

// co sensor
const int AOUTpin = 0; //the AOUT pin of the CO sensor goes into analog pin A0 of the arduino
const int DOUTpin = 8; //the DOUT pin of the CO sensor goes into digital pin D8 of the arduino
int limit;
int value;



void setup() {


  Serial.begin(9600);
  delay(10000);
  Serial.println("===Starting===");


    
  pinMode(ledRedLight, OUTPUT);
  pinMode(ledGreenLight, OUTPUT);
  pinMode(alarmBuzzer, OUTPUT);
  pinMode(smokeAnalogPin, INPUT);
  Serial.begin(9600);

  Serial.begin(9600);


  dht.begin();

  Serial.begin(115200);//sets the baud rate
  pinMode(DOUTpin, INPUT);//sets the pin as an input to the arduino
  pinMode(ledGreenLight, OUTPUT);//sets the pin as an output of the arduino
  pinMode(ledRedLight, OUTPUT);
}

void loop() {


    
  int analogSensor = analogRead(smokeA0);
  Serial.println("  ");
  Serial.println("MQ7 Gas Sensor  ");
  Serial.print("Pin A0: ");
  Serial.println(analogSensor);


  // Reading temperature or humidity takes about 250 milliseconds!
  // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
  dht.readHumidity();
  dht.readTemperature();

  // Check if any reads failed and exit early (to try again).
  if (isnan(dht.humidity) || isnan(dht.temperature_C)) {
    Serial.println("DHT sensor read failure!");
    return;
  }

  // Checks if it has reached the threshold value
  if (analogSensor >sensorThresholdVal)
  {
    digitalWrite(ledRedLight, HIGH);
    digitalWrite(ledGreenLight, LOW);
    tone(alarmBuzzer, 1000, 200);

  }
  else
  {
    digitalWrite(ledRedLight, LOW);
    digitalWrite(ledGreenLight, HIGH);
    noTone(alarmBuzzer);
  }

  Serial.println("  ");
  Serial.println("DHT22 Humidity - Temperature Sensor");
  Serial.println("RH\tTemp (C)\tTemp (F)\tHeat Index (C)\tHeat Index (F)");

  Serial.print(dht.humidity); Serial.print(" %\t\t");
  Serial.print(dht.temperature_C); Serial.print(" *C\t");
  Serial.print(dht.temperature_F); Serial.print(" *F\t");
  Serial.print(dht.computeHeatIndex_C()); Serial.print(" *C\t");
  Serial.print(dht.computeHeatIndex_F()); Serial.println(" *F");

  // Wait a few seconds between measurements. The DHT22 should not be read at a higher frequency of
  // about once every 2 seconds. So we add a 3 second delay to cover this.

  value = analogRead(AOUTpin); //reads the analaog value from the CO sensor's AOUT pin
  limit = digitalRead(DOUTpin); //reads the digital value from the CO sensor's DOUT pin
  Serial.print("  ");
  Serial.print("MQ2 CO Sensor");
  Serial.print("CO value: ");
  Serial.println(value);//prints the CO value
  //Serial.print("Limit: ");
  //Serial.print(limit);//prints the limit reached as either LOW or HIGH (above or underneath)


  if (limit == HIGH) {
    digitalWrite(ledGreenLight, HIGH);//if limit has been reached, Red LED turns on as status indicator
  }
  else {
    digitalWrite(ledRedLight, HIGH);//if threshold not reached, Green LED turns on as status indicator
  }

  // delay(100);
  delay(3000);
  delay(100);

  
}






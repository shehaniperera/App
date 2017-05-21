#include "cactus_io_DHT22.h"
#define DHT22_PIN 2 // what pin on the arduino is the DHT22 data line connected to

// Initialize DHT sensor for normal 16mhz Arduino.
DHT22 dht_22(DHT22_PIN);

void setup() {
  Serial.begin(9600);
  Serial.println("DHT22 Humidity - Temperature Sensor");
  Serial.println("RH\tTemp (C)\tTemp (F)\tHeat Index (C)\tHeat Index (F)");

  dht_22.begin();
}

void loop() {
  
  // Reading temperature or humidity takes about 250 milliseconds
  // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
  dht_22.readHumidity();
  dht_22.readTemperature();

  // Check if any reads failed and exit early (to try again).
  if (isnan(dht.humidity) || isnan(dht.temperature_C)) {
    Serial.println("DHT sensor read failure!");
    return;
  }

  Serial.print(dht_22.humidity); Serial.print(" %\t\t");
  Serial.print(dht_22.temperature_C); Serial.print(" *C\t");
  Serial.print(dht_22.temperature_F); Serial.print(" *F\t");
  Serial.print(dht_22.computeHeatIndex_C()); Serial.print(" *C\t");
  Serial.print(dht_22.computeHeatIndex_F()); Serial.println(" *F");

  // Wait a few seconds between measurements. The DHT22 should not be read at a higher frequency of
  // about once every 2 seconds. So we add a 3 second delay to cover this.
  delay(3000);
}

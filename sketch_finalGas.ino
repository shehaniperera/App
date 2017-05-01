
int ledGreenLight = 11; // led green light - connected to arduino pin11
int ledRedLight = 12; // led red light - connectde to arduino pin12
int smokeAnalogPin = A5; //
int alarmBuzzer = 10; // alarm is connected to arduino pin10

// the thresh hold value
int sensorThresholdVal = 400; //400

void setup() {
  pinMode(ledRedLight, OUTPUT);
  pinMode(ledGreenLight, OUTPUT);
  pinMode(alarmBuzzer, OUTPUT);
  pinMode(smokeAnalogPin, INPUT);
  Serial.begin(9600); // setes the serial port value to 9600
}

void loop() {
  int analogSensorData = analogRead(smokeAnalogPin); // read the analog input pin no A5

 
  Serial.println(analogSensor); // prints out the sensor read value

  // checks if the analog sesor data has reached the thresh hold value
  if (analogSensorData  > sensorThresholdVal)
  {
    digitalWrite(ledRedLight, HIGH); // led red light will blink
    digitalWrite(ledGreenLight, LOW);
    tone(alarmBuzzer, 1000, 200); // alrm will start to buz
    
  }
  else
  {
    digitalWrite(ledRedLight, LOW); 
    digitalWrite(ledGreenLight, HIGH); // led green light will blink
    noTone(alarmBuzzer); 
  }
  delay(100); // waits for 100 milliseconds to read the next input
}
